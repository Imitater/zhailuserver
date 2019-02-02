package com.mouqukeji.zhailuserver.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private final List page;

    public MainPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> page) {
        super(supportFragmentManager);
        this.page=page;
    }

    @Override
    public Fragment getItem(int i) {
        return (Fragment) page.get(i);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
