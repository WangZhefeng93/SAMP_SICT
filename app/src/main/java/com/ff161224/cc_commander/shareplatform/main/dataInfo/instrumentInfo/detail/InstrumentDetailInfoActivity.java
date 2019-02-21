package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.TabFragmentShouYeAdapter;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.basic.InstrumentDetailBasicInfoFragment;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.calibration.InstrumentDetailcalibrationRecordFragment;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.coast.InstrumentDetailCoastInfoFragment;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.component.InstrumentDetailMainComponetFragment;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.environment.InstrumentDetailEnvironmentArgumentFragment;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.order.InstrumentDetailOrderInfoFragment;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.project.InstrumentDetailProjectStandardFragment;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.random.InstrumentDetailRandomFileFragment;

import java.util.ArrayList;
import java.util.List;

public class InstrumentDetailInfoActivity extends AppCompatActivity {
    //定义属性
    private TextView tittle_name_tv;
    private TabLayout tabLayout_shouye;
    private ViewPager viewPager_shouye;
    private List<String> strings = new ArrayList<String>();;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_instrument_detail_info);

        //绑定标题栏
        tittle_name_tv = (TextView)findViewById(R.id.tittle_name_tv);

        //设置标题内容
        tittle_name_tv.setText("仪器详细信息");

        //构建导航条数据
        initdate();

        //初始化控件
        initView();
    }

    //构建导航条数据
    private void initdate(){
        InstrumentDetailBasicInfoFragment instrumentDetailBasicInfoFragment = new InstrumentDetailBasicInfoFragment();
        fragments.add(InstrumentDetailBasicInfoFragment.newInstance());
        strings.add("基本信息");
        InstrumentDetailOrderInfoFragment instrumentDetailOrderInfoFragment = new InstrumentDetailOrderInfoFragment();
        fragments.add(InstrumentDetailOrderInfoFragment.newInstance());
        strings.add("预约信息");
        InstrumentDetailRandomFileFragment instrumentDetailRandomFileFragment = new InstrumentDetailRandomFileFragment();
        fragments.add(instrumentDetailRandomFileFragment.newInstance());
        strings.add("随机文档");
        InstrumentDetailcalibrationRecordFragment instrumentDetailcalibrationRecordFragment = new InstrumentDetailcalibrationRecordFragment();
        fragments.add(instrumentDetailcalibrationRecordFragment.newInstance());
        strings.add("校准记录");
        InstrumentDetailMainComponetFragment instrumentDetailMainComponetFragment = new InstrumentDetailMainComponetFragment();
        fragments.add(instrumentDetailMainComponetFragment.newInstance());
        strings.add("主要零件");
        InstrumentDetailProjectStandardFragment instrumentDetailProjectStandardFragment = new InstrumentDetailProjectStandardFragment();
        fragments.add(instrumentDetailProjectStandardFragment.newInstance());
        strings.add("项目标准");
        InstrumentDetailCoastInfoFragment instrumentDetailCoastInfoFragment = new InstrumentDetailCoastInfoFragment();
        fragments.add(instrumentDetailCoastInfoFragment.newInstance());
        strings.add("耗材信息");
        InstrumentDetailEnvironmentArgumentFragment detailEnvironmentArgumentFragment = new InstrumentDetailEnvironmentArgumentFragment();
        fragments.add(detailEnvironmentArgumentFragment.newInstance());
        strings.add("环境参数");
    }

    //初始化控件
    private void initView(){
        tabLayout_shouye = (TabLayout)findViewById(R.id.tablayout_shouye);
        viewPager_shouye = (ViewPager)findViewById(R.id.viewpager_ShouYe);
        viewPager_shouye.setAdapter(new TabFragmentShouYeAdapter(fragments,strings,
                getSupportFragmentManager(),this));
        tabLayout_shouye.setupWithViewPager(viewPager_shouye);
        tabLayout_shouye.setTabTextColors(Color.GRAY,Color.RED);
    }
}
