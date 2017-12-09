package com.example.chuks.vibefmbenin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

        //Declaring DrawerLayout ActionBarDrawerToggle and NavigationView & Toolbar
        private DrawerLayout mDrawerLayout;
        private ActionBarDrawerToggle mToggle;
        private NavigationView mNavigationView;
        private Toolbar mToolBar;
        String podID;

        //BottomNavigationView OnNavigationItemSelectedListener
        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navbottom_schedule:
                        ScheduleFragment scheduleBottom = new ScheduleFragment();
                        FragmentManager scheduleManager = getSupportFragmentManager();
                        scheduleManager.beginTransaction().replace(R.id.frament_layout,scheduleBottom).commit();
                        setTitle(item.getTitle());
                        return true;

                    case R.id.navbottom_radio:
                        RadioFragment radioBottom = new RadioFragment();
                        FragmentManager radioManager = getSupportFragmentManager();
                        radioManager.beginTransaction().replace(R.id.frament_layout,radioBottom).commit();
                        setTitle(item.getTitle());
                        return true;

                    case R.id.navbottom_podcast:
                        PodcastFragment podcastBottom = new PodcastFragment();
                        FragmentManager podcastManager = getSupportFragmentManager();
                        podcastManager.beginTransaction().replace(R.id.frament_layout,podcastBottom).commit();
                        setTitle(item.getTitle());
                        return true;
                }
                return false;
            }

        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding toolbar
        mToolBar = (Toolbar) findViewById(R.id.navigation_toolbar);
        setSupportActionBar(mToolBar);

        //Initializing DrawerLayout, ActionBarDrawerToggle, NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        //finding our navigation view
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationBottom);

        //adding our drawer listener to listen to the toggle
        //syncstate enables the toggle return to the hamburger icon when tapped again
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setupDrawerContent(mNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        RadioFragment radioBottom = new RadioFragment();
        FragmentManager radioManager = getSupportFragmentManager();
        radioManager.beginTransaction().replace(R.id.frament_layout,radioBottom).commit();



        //Moving Data to Subscribe Fragment
        Bundle bundle = getIntent().getExtras();


        if(bundle!= null){
            if(bundle.getString("podID") != null){
                //Moving to Subscribed fragment LOWKEY:)
                podID = getIntent().getStringExtra("podID");




                SubscribedFragment subscribedFragmentObject = new SubscribedFragment();
                FragmentManager subscribeManager = getSupportFragmentManager();
                subscribeManager.beginTransaction().replace(R.id.frament_layout,subscribedFragmentObject).commit();
                setTitle("Subscribed");

                Bundle bundleSend = new Bundle();
                bundleSend.putString("podID",podID);
                subscribedFragmentObject.setArguments(bundleSend);



            }}


    }

    //Setup Drawer Content Method
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    //Select Drawer Item Method
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_radio:
                fragmentClass = RadioFragment.class;
                System.out.print("working case 1 ");
                break;
            case R.id.nav_schedule:
                fragmentClass = ScheduleFragment.class;
                System.out.print("working case 2 ");
                break;
            case R.id.nav_twitter:
                fragmentClass = TwitterFragment.class;
                break;
            case R.id.nav_about_us:
                fragmentClass = AboutUsFragment.class;
                break;
            case R.id.nav_share:
                fragmentClass = ShareFragment.class;
                break;
            case R.id.nav_exit:
                fragmentClass = ExitFragment.class;
                break;
            default:
                fragmentClass = RadioFragment.class;
        }

        //Assigning a new Instance of a Fragment Class to fragment
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frament_layout, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
