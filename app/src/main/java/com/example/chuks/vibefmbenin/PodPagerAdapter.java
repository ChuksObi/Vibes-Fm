package com.example.chuks.vibefmbenin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by chuks on 9/17/2017.
 */

public class PodPagerAdapter extends FragmentStatePagerAdapter {
   int mNumOfTabs;
    
    public PodPagerAdapter (FragmentManager fm, int mNumOfTabs){
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DiscoverFragment tab1 = new DiscoverFragment();
                return tab1;
            case 1:
                SubscribedFragment tab2 = new SubscribedFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
