package com.exponentialsight.savethat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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

        MainActivityFragment fragment = new MainActivityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_replace, fragment,"1").addToBackStack("1").commit();

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
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        startUI(account);
    }

    public void startUI(@Nullable GoogleSignInAccount gsa) {
        if (gsa == null) {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
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
        if (fragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            //            Log.i("MainFrag","Popping backstack");
            fragmentManager.popBackStack();
            // backstacklistener so that the UI can be updated if it gets popped
            fragmentManager.addOnBackStackChangedListener(
                    new FragmentManager.OnBackStackChangedListener() {
                        @Override
                        public void onBackStackChanged() {
//                            Log.i("BACK","BACKSTACK HAS CHANGED");
                            String currTitle = getActiveFragmentTag();
                            Log.i("TITLE","String: " + currTitle);
                            if (currTitle != null) {
                                changeTitle(currTitle);
                            } else {
                                System.exit(0);
                            }
                        }
                    }
            );
        }
    }

    // helper function to return the current fragment
    public String getActiveFragmentTag() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() == 0) {
            return null;
        } else {
            return fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1).getName();
        }
    }

    // helper function to change the title
    public void changeTitle(String tag) {
        int pos = Integer.parseInt(tag);
        setTitle(mSideMenu[pos]);
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
                    ProfileFragment fragmentProfile = new ProfileFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace,fragmentProfile,"0").addToBackStack("0").commit();
                    setTitle(mSideMenu[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);
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
                default:
                    fragmentMain = new MainActivityFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_replace, fragmentMain,"1").addToBackStack("1").commit();
                    setTitle(R.string.app_name);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
            }
        }
    }
}
