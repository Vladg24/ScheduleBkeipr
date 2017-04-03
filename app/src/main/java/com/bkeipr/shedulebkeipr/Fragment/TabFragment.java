package com.bkeipr.shedulebkeipr.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bkeipr.shedulebkeipr.Fragment.FridayFragment;
import com.bkeipr.shedulebkeipr.Fragment.MondayFragment;
import com.bkeipr.shedulebkeipr.Fragment.ThuesdayFragment;
import com.bkeipr.shedulebkeipr.Fragment.TuesdayFragment;
import com.bkeipr.shedulebkeipr.Fragment.WednesdayFragment;
import com.bkeipr.shedulebkeipr.R;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 5 ;
    private String group_name;

    public TabFragment(String group) {
        group_name=group;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x =  inflater.inflate(R.layout.tab_layuot, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new MondayFragment(group_name);
                case 1 : return new TuesdayFragment(group_name);
                case 2 : return new WednesdayFragment(group_name);
                case 3 : return new ThuesdayFragment(group_name);
                case 4 : return new FridayFragment(group_name);
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Понеділок";
                case 1 :
                    return "Вівторок";
                case 2 :
                    return "Середа";
                case 3 :
                    return "Четвер";
                case 4 :
                    return "П'ятниця";
            }
            return null;
        }
    }

}
