package com.ff161224.cc_commander.shareplatform;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xiaobaixiaoda on 2017/11/7.
 */

public class TabFragmentShouYeAdapter extends FragmentPagerAdapter {
    //定义变量
    private Context context;
    private List<Fragment> fragments;
    private List<String> strings;
    //构造函数
    public TabFragmentShouYeAdapter(List<Fragment> fragments, List<String> strings, FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        this.strings = strings;
        this.context = context;
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
