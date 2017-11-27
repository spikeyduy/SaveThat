package com.exponentialsight.savethat;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] mSideMenu;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO start up with either the signup layout or main depending on if user is logged in or not
        // Set up the main swipe fragment
        MainActivityFragment fragment = new MainActivityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_replace, fragment).commit();

        // set up drawer
        mSideMenu = getResources().getStringArray(R.array.side_settings);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);

        // set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, mSideMenu));
        // set up the list's listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    // drawer listener
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        FragmentManager fragmentManager = getSupportFragmentManager();

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Log.d("DRAWER", "onItemClick POSITION: " + position);
            switch (position) {
                case 0:
                    // profile
                    break;
                case 1:
                    // home
                    MainActivityFragment fragmentMain = new MainActivityFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentMain).commit();
                    setTitle(R.string.app_name);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                case 2:
                    // saved coupons
                    SavedCouponsFragment fragmentSaved = new SavedCouponsFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentSaved).commit();
                    setTitle(mSideMenu[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                case 3:
                    // settings
                    SettingsFragment fragmentSettings = new SettingsFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentSettings).commit();
                    setTitle(mSideMenu[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                case 4:
                    // send feedback
                    FeedbackFragment fragmentFeedback = new FeedbackFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentFeedback).commit();
                    setTitle(mSideMenu[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                default:
                    fragmentMain = new MainActivityFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentMain).commit();
                    setTitle(R.string.app_name);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
            }
        }

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
