package com.example.chuks.vibefmbenin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Monday tab1 = new Monday();
                return tab1;
            case 1:
                Tuesday tab2 = new Tuesday();
                return tab2;
            case 2:
                Wednesday tab3 = new Wednesday();
                return tab3;
            case 3:
                Thursday tab4 = new Thursday();
                return tab4;
            case 4:
                Friday tab5 = new Friday();
                return tab5;
            case 5:
                Saturday tab6 = new Saturday();
                return tab6;
            case 6:
                Sunday tab7 = new Sunday();
                return tab7;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}