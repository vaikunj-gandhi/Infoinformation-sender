package com.example.aum.smartyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
public class FacultyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, FacultyAdapter.MessageAdapterListener {
    String GET_JSON_DATA_HTTP_URL = "http://192.168.56.1/Android/faculty.php";
    String JSON_NAME = "name";
    String JSON_EMAIL = "email";
    String JSON_MOBILE = "mobile";
    String JSON_BRANH = "branch";
    String JSON_IMGURL = "img_url";
    private List<FacultyItem> messages;


    private ActionModeCallback actionModeCallback;

    private ActionMode actionMode;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private FacultyAdapter mAdapter;
    String branch, s_branch,mobile,name,email,img;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     v=inflater.inflate(R.layout.faculty,container,false);
        TextView t=(TextView)v.findViewById(R.id.branch);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        t.setText( sharedPreferences.getString("branch","Not Available"));
        s_branch=t.getText().toString().trim();
        messages=new ArrayList<>();
        //Initializing Views
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        actionModeCallback = new ActionModeCallback();
        getData();
        return v;
    }
    private void getData() {
    final ProgressDialog loading = ProgressDialog.show(getContext(),"Loading Data", "Please wait...",false,false);



    StringRequest stringRequest=new StringRequest(Request.Method.POST,GET_JSON_DATA_HTTP_URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response){

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
            // Snackbar.make(, "Server Not Connected", Snackbar.LENGTH_LONG)
            //  .setAction("Action", null).show();
            // error.printStackTrace();
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params=new HashMap<String, String>();
            params.put("branch",s_branch);

            return params;
        }
    };
    MySingleton.getInstance(getContext()).addRequestQueue(stringRequest);

}
    private void parseData(String array) throws JSONException {
        JSONArray jsonArray = new JSONArray(array);
        for (int i = 0; i < jsonArray.length(); i++) {
            FacultyItem superHero = new FacultyItem();
            // JSONObject jsonObject = null;
            try {

                // JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonObject;

                jsonObject = jsonArray.getJSONObject(i);
                name = jsonObject.getString(JSON_NAME);
                email = jsonObject.getString(JSON_EMAIL);
                branch=jsonObject.getString(JSON_BRANH);
                mobile=jsonObject.getString(JSON_MOBILE);
                img=jsonObject.getString(JSON_IMGURL);
                superHero.setColor(getRandomMaterialColor("400"));
                if (s_branch.equals(branch)) {
                    superHero.setName(jsonObject.getString(JSON_NAME));
                    superHero.setEmail(jsonObject.getString(JSON_EMAIL));
                    superHero.setMobile(jsonObject.getString(JSON_MOBILE));
                    superHero.setBranch(jsonObject.getString(JSON_BRANH));
                    // superHero.setMsg(jsonObject.getString(JSON_MSG));
                    superHero.setPicture(jsonObject.getString(JSON_IMGURL));



                } else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            messages.add(superHero);
        }


        mAdapter = new FacultyAdapter(getContext(),messages,this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListenerFaculty() {
            @Override
            public void onItemClick(FacultyItem item) {
                Toast.makeText(getContext(),item.getEmail(),Toast.LENGTH_LONG).show();
                Intent i=new Intent(getContext(),Faculty_detailsHome.class);
                Bundle bundle=new Bundle();
                bundle.putString("name",item.getName());
                bundle.putString("email",item.getEmail());
                bundle.putString("mobile",item.getMobile());
                bundle.putString("branch",item.getBranch());
                bundle.putString("img",item.getPicture());

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
    public void onRefresh() {
        // swipe refresh is performed, fetch the messages again
        getData();
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
        FacultyItem message = messages.get(position);
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
            FacultyItem message = messages.get(position);
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


