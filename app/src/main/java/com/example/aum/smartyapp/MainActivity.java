package com.example.aum.smartyapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aum.smartyapp.fragment.ScrollFragment;
import com.example.aum.smartyapp.fragment.ScrollFragment2;
import com.example.aum.smartyapp.fragment.ScrollFragment3;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
public class MainActivity extends AppCompatActivity {


Toolbar toolbar;
    ImageView imheader;
    private MaterialViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView login=(TextView)findViewById(R.id.login_title);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   LoginActivity Fragment=new LoginActivity();
                android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content,Fragment);
                fragmentTransaction.commit();
            }
        });
        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        imheader=(ImageView)findViewById(R.id.logo_white);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 3) {
                    case 0:
                     return ScrollFragment.newInstance();
                     case 1:
                     return ScrollFragment2.newInstance();
                     case 2:
                     return ScrollFragment3.newInstance();

                     default:
                     return ScrollFragment.newInstance();
                }

            }

            @Override
            public int getCount() {
                return 3;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Home";
                    case 1:
                        return "About Us";
                    case 2:
                        return "Conatct us";

                }
                return "";
            }
        });
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:imheader.setImageDrawable(getResources().getDrawable(R.drawable.home));
                        return HeaderDesign.fromColorAndDrawable(getResources().getColor(R.color.main),getResources().getDrawable(R.drawable.book_name));
                    case 1:
                        imheader.setImageDrawable(getResources().getDrawable(R.drawable.book_name));
                        return HeaderDesign.fromColorAndDrawable(getResources().getColor(R.color.main),getResources().getDrawable(R.drawable.book_name));
                    case 2:imheader.setImageDrawable(getResources().getDrawable(R.drawable.facult_icon));
                        return HeaderDesign.fromColorAndDrawable(getResources().getColor(R.color.main),getResources().getDrawable(R.drawable.book_name));

                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getViewPager();



        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

}
