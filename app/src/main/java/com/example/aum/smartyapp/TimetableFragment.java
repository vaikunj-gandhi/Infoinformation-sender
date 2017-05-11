package com.example.aum.smartyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.aum.smartyapp.helper.DividerItemDecoration;
import com.example.aum.smartyapp.model.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aum on 8/13/2016.
 */
public class TimetableFragment extends Fragment  implements  RecyclerAdapter_TB.MessageAdapterListener{

    private List<Message> messages;

    String GET_JSON_DATA_HTTP_URL = "http://192.168.56.1/Android/tb.php";
    String JSON_NAME = "stream";
    String JSON_ADDRESS = "sem";
    String JSON_MSG = "msg";
    String JSON_DE = "name";
    String JSON_FNAME = "f_name";
    String JSON_URl = "url";
    String JSON_TIME = "created";
    private List<DataItem> listSuperHeroes;
    View v;


    private ActionModeCallback actionModeCallback;

    private ActionMode actionMode;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_TB mAdapter;
    TextView t1,t2,t3;
    String sem1,stream,s_sem, s_branch,msg,name,fname,url;;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.timetable,container,false);

        messages = new ArrayList<>();

        recyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        actionModeCallback = new ActionModeCallback();

        // /Initializing our superheroes list

        t1=(TextView)v.findViewById(R.id.sem);
        t2=(TextView)v.findViewById(R.id.branch);
        t3=(TextView)v.findViewById(R.id.time);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        t2.setText( sharedPreferences.getString("semester","Not Available"));
        t1.setText( sharedPreferences.getString("branch","Not Available"));
        s_sem = t2.getText().toString().trim();
        s_branch=t1.getText().toString().trim();
        //Calling method to get data
        getData();

        return v;

    }



    private void getData() {
        final ProgressDialog loading = ProgressDialog.show(getContext(),"Loading Data", "Please wait...",false,false);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,GET_JSON_DATA_HTTP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();

                try {
                    parseData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(v, "Server Not Connected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("stream",s_branch);
                params.put("sem",s_sem);
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addRequestQueue(stringRequest);

    }
    private void parseData(String array) throws JSONException {
        JSONArray jsonArray = new JSONArray(array);
        for (int i = 0; i < jsonArray.length(); i++) {
            Message superHero = new Message();
            // JSONObject json =null;
            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //JSONObject jsonObject=jsonArray.getJSONObject();
                stream = jsonObject.getString(JSON_NAME);
                sem1 = jsonObject.getString(JSON_ADDRESS);
                msg=jsonObject.getString(JSON_MSG);
                name=jsonObject.getString(JSON_DE);
                 fname=jsonObject.getString(JSON_FNAME);
                url=jsonObject.getString(JSON_URl);
                superHero.setColor(getRandomMaterialColor("400"));
                if (s_branch.equals(stream) && s_sem.equals(sem1)) {
                    superHero.setFrom(jsonObject.getString(JSON_NAME));
                    superHero.setSubject(jsonObject.getString(JSON_ADDRESS));
                    superHero.setMessage(jsonObject.getString(JSON_MSG));
                    superHero.setFile(jsonObject.getString(JSON_DE));
                   // superHero.setMessage(jsonObject.getString(JSON_MSG));
                    superHero.setFname(jsonObject.getString(JSON_FNAME));
                    superHero.setUrl(jsonObject.getString(JSON_URl));
                    superHero.setTimestamp(jsonObject.getString(JSON_TIME));


                } else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            messages.add(superHero);
        }


        mAdapter = new RecyclerAdapter_TB(getContext(),messages,this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Message item) {
                Toast.makeText(getContext(),item.getSubject(),Toast.LENGTH_LONG).show();
                Intent i=new Intent(getContext(),Home_main.class);
                Bundle bundle=new Bundle();
                bundle.putString("Stream",item.getFrom());
                bundle.putString("sem",item.getSubject());
                bundle.putString("msg",item.getMessage());
                bundle.putString("name",item.getFile());
                bundle.putString("fname",item.getFname());
                bundle.putString("url",item.getUrl());
                bundle.putString("time",item.getTimestamp());
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }
    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array",getActivity().getPackageName());

        if (arrayId != 0) {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(getContext(), "Search...", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onIconClicked(int position) {
        if (actionMode == null) {
            actionMode = ((AppCompatActivity)getActivity()).startSupportActionMode(actionModeCallback);
        }

        toggleSelection(position);
    }

    @Override
    public void onIconImportantClicked(int position) {
        // Star icon is clicked,
        // mark the message as important
        Message message = messages.get(position);
        message.setImportant(!message.isImportant());
        messages.set(position, message);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMessageRowClicked(int position) {
        // verify whether action mode is enabled or not
        // if enabled, change the row state to activated
        if (mAdapter.getSelectedItemCount() > 0) {
            enableActionMode(position);
        } else {
            // read the message which removes bold from the row
            Message message = messages.get(position);
            message.setRead(true);
            messages.set(position, message);
            mAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void onRowLongClicked(int position) {
        // long press is performed, enable action mode
        enableActionMode(position);
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode= ((AppCompatActivity)getActivity()).startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }


    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable swipe refresh if action mode is enabled
            // swipeRefreshLayout.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected messages
                    deleteMessages();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapter.clearSelections();
            //swipeRefreshLayout.setEnabled(true);
            actionMode = null;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.resetAnimationIndex();
                    // mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    // deleting the messages from recycler view
    private void deleteMessages() {
        mAdapter.resetAnimationIndex();
        List<Integer> selectedItemPositions =
                mAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            mAdapter.removeData(selectedItemPositions.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }
}





