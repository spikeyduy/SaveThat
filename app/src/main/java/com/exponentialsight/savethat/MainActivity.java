package com.exponentialsight.savethat;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

    private String[] mSideMenu; // drawer menu items
    private DrawerLayout mDrawerLayout; // drawer layout
    private ListView mDrawerList; // list inside of drawer
    private ActionBarDrawerToggle mDrawerToggle; // toggle for hamburg menu icon

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setting up drawer icon
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);


        // TODO start up with either the signup layout or main depending on if user is logged in or not
        // Set up the main swipe fragment
        MainActivityFragment fragment = new MainActivityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_replace, fragment,"1").commit();

        // set up drawer
        mSideMenu = getResources().getStringArray(R.array.side_settings);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            // called when the drawer has been completely closed
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_item, mSideMenu));
        // set up the list's listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // sync the toggle state after onRestoreInstanceState has occurred
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    // go back through backstackFrags
    @Override
    public void onBackPressed(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            Log.i("MainFrag","Popping backstack");
            fragmentManager.popBackStack();
            int i = fragmentManager.getBackStackEntryCount()-1;
            String tag = fragmentManager.getBackStackEntryAt(i).getName();
            setTitle(mSideMenu[Integer.parseInt(tag)]);
        } else {
            Log.i("MainFrag","Nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
    // drawer listener
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        FragmentManager fragmentManager = getSupportFragmentManager();

        // TODO make the back action close the navigation instead of quiting the app
        // TODO may have to make each fragment be children to the mainSwipeFrag so that the back button can go there instead of just exiting
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Log.d("DRAWER", "onItemClick POSITION: " + position);
            switch (position) {
                case 0:
                    // profile
                    LoginActivity loginActivity = new LoginActivity();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    // home
                    MainActivityFragment fragmentMain = new MainActivityFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentMain,"1").addToBackStack("1").commit();
                    setTitle(R.string.app_name);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                case 2:
                    // saved coupons
                    SavedCouponsFragment fragmentSaved = new SavedCouponsFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentSaved,"2").addToBackStack("2").commit();
                    Log.i("SavedFrag","SavedFrag tag: " + fragmentSaved.getTag());
                    setTitle(mSideMenu[2]);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                case 3:
                    // settings
                    SettingsFragment fragmentSettings = new SettingsFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentSettings,"3").addToBackStack("3").commit();
                    setTitle(mSideMenu[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                case 4:
                    // send feedback
                    FeedbackFragment fragmentFeedback = new FeedbackFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentFeedback,"4").addToBackStack("4").commit();
                    setTitle(mSideMenu[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                default:
                    fragmentMain = new MainActivityFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentMain,"1").addToBackStack("1").commit();
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
