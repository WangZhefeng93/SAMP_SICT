package com.ff161224.cc_commander.shareplatform;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //声明属性
    private BottomNavigationBar mBottomNavigationBar;
    private ArrayList<Fragment> fragments;
    private TextView tittle_tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_main);

        //设置标题栏标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);
        tittle_tv.setText(getString(R.string.tittle_name));

        //添加内容布局
        assignViews();
    }

    //添加内容布局
    private void assignViews(){
        //添加底部导航栏标题
        mBottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottom_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(android.R.color.black);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_1)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_2)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_3)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_4)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .setFirstSelectedPosition(0)
                .initialise();

        //设置默认Fragment
        setDefaultFragment();

        //获取Fragment集合
        fragments = getFragments();

        //设置导航点击监听
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {   //未选中 -> 选中
                if(fragments!=null){
                    if(position<fragments.size()){
                        FragmentManager fm=getSupportFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        Fragment fragment=fragments.get(position);
                        Log.d("四大功能模块切换，当前所处的功能模块位置编号为：",position+"");
                        //if(fragment.isAdded()){
                        ft.replace(R.id.root,fragment);
                        //}else {
                        //ft.add(R.id.root,fragment);
                        //}
                        ft.commitAllowingStateLoss();
                    }
                }
            }

            @Override
            public void onTabUnselected(int position) {     //选中 -> 未选中
                if(fragments!=null){
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    Fragment fragment=fragments.get(position);
                    ft.remove(fragment);
                    ft.commitAllowingStateLoss();
                }
            }

            @Override
            public void onTabReselected(int position) {     //选中 -> 选中

            }
        }); //设置监听
    }

    //获取Fragment集合
    private ArrayList<Fragment> getFragments(){     //创建Fragment集合
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(DataInfoFragment.newInstance());
        fragments.add(OrderReviewFragment.newInstance());
        fragments.add(ScanMachineFragment.newInstance());
        fragments.add(PersonalCenterFragment.newInstance());
        return fragments;
    }

    private void setDefaultFragment(){  //设置默认的Fragment
        FragmentManager fragmentManager=getSupportFragmentManager();  //获取FragmentManager
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();//开启一个事务
        fragmentTransaction.add(R.id.root, DataInfoFragment.newInstance());
        fragmentTransaction.commit();


    }
}
