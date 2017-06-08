package org.berendeev.roma.simplerealm.presentation.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.berendeev.roma.simplerealm.presentation.ui.fragment.InputFragment;
import org.berendeev.roma.simplerealm.presentation.ui.fragment.ListFragment;

import java.util.List;

public class FragmentsPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles;

    public FragmentsPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override public Fragment getItem(int position) {

        switch (position){
            case 0:{
                return InputFragment.getInstance();
            }
            case 1:{
                return ListFragment.getInstance();
            }
            default:
                throw new IllegalArgumentException("illegal viewPager position");
        }
    }

    @Override public int getCount() {
        return titles.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}