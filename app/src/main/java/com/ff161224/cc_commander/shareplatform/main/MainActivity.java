package com.ff161224.cc_commander.shareplatform.main;

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
import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.DataInfoFragment;
import com.ff161224.cc_commander.shareplatform.main.orderReview.OrderReviewFragment;
import com.ff161224.cc_commander.shareplatform.main.personCenter.PersonalCenterFragment;
import com.ff161224.cc_commander.shareplatform.main.scanMachine.ScanMachineFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //声明属性
    private BottomNavigationBar mBottomNavigationBar;   //主界面底部导航栏
    private ArrayList<Fragment> fragments;      //Fragment布局集合
    private TextView tittle_tv;     //标题栏标题文本框控件
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //初始化数据信息Fragment图标提示
        String[] iconNameDataInfo = {this.getString(R.string.instrument_information),
                            this.getString(R.string.analysis_project),
                            this.getString(R.string.supplies_information),
                            this.getString(R.string.standard_information),
                            this.getString(R.string.price_information),
                            this.getString(R.string.sample_information),
                            this.getString(R.string.provide_announcement),
                            this.getString(R.string.notification_browsing),
                            this.getString(R.string.personel_information)};

        //初始化预约结算Fragment图标提示
        String[] iconNameOrderReview = {this.getString(R.string.time_order),
                this.getString(R.string.project_order),
                this.getString(R.string.order_check),
                this.getString(R.string.entrustment_management),
                this.getString(R.string.entrustment_settlement),
                this.getString(R.string.cost_settlement)};

        //初始化扫码上机Fragment图标提示
        String[] iconNameScanMachine = {this.getString(R.string.scan_up_machine_operation),
                        this.getString(R.string.scan_down_machine_operation),
                        this.getString(R.string.scan_connect_admin)};

        //加载布局文件
        setContentView(R.layout.activity_main);

        //设置标题栏标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);
        tittle_tv.setText(getString(R.string.tittle_name));

        //添加内容布局
        assignViews(iconNameDataInfo,iconNameOrderReview,iconNameScanMachine);
    }

    //添加内容布局
    private void assignViews(String[] iconNameDataInfo,String[] iconNameOrderReview,String[] iconNameScanMachine){
        /*
         * 添加底部导航栏标题
         */
        mBottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottom_bar);    //绑定底部导航栏控件
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(android.R.color.black);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_1)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_2)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_3)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher,getString(R.string.function_module_4)).setInActiveColorResource(android.R.color.white).setActiveColorResource(android.R.color.holo_red_light))
                .setFirstSelectedPosition(0)
                .initialise();

        //获取Fragment集合
        fragments = getFragments(iconNameDataInfo,iconNameOrderReview,iconNameScanMachine);

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

        //设置默认Fragment
        setDefaultFragment(iconNameDataInfo);
    }

    //获取Fragment集合
    private ArrayList<Fragment> getFragments(String[] iconNameDataInfo,String[] iconNameOrderReview,String[] iconNameScanMachine){     //创建Fragment集合
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(DataInfoFragment.newInstance(iconNameDataInfo));
        fragments.add(OrderReviewFragment.newInstance(iconNameOrderReview));
        fragments.add(ScanMachineFragment.newInstance(iconNameScanMachine));
        fragments.add(PersonalCenterFragment.newInstance());
        return fragments;
    }

    //设置默认的Fragment
    private void setDefaultFragment(String[] iconNameDataInfo){
        FragmentManager fragmentManager=getSupportFragmentManager();  //获取FragmentManager
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();//开启一个事务
        fragmentTransaction.add(R.id.root, DataInfoFragment.newInstance(iconNameDataInfo));
        fragmentTransaction.commit();
    }
}
