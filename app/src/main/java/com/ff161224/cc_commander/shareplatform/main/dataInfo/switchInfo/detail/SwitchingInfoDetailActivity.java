package com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.edit.EditSwitchingInfoActivity;

import java.util.HashMap;

public class SwitchingInfoDetailActivity extends AppCompatActivity {
    //定义控件变量
    private TextView switching_useDate_tv;  //使用日期
    private TextView switching_startTime_tv;  //开机时间
    private TextView switching_stopTime_tv;   //结束时间
    private TextView switching_effectiveHours_tv; //有效时间
    private TextView switching_workContent_tv;    //工作内容
    private TextView switching_userName_tv;   //使用人
    private TextView switching_beforeState_tv;    //使用前状态
    private TextView switching_afterState_tv; //使用后状态
    private TextView edit_switching_info_tv;    //编辑开关机记录

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> switchingInfoMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_switching_info_detail);
        intent = getIntent();
        switchingInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("switchingInfoMap");
        //初始化控件变量
        initWidget();
        //为控件设置数据值
        setDataForWidget();
        //为编辑按钮设置监听器
        setClickListenerForEditLogTV();
    }

    /**
     * 初始化控件变量
     */
    private void initWidget() {
        switching_useDate_tv = (TextView) findViewById(R.id.switching_useDate_tv);  //使用日期
        switching_startTime_tv = (TextView) findViewById(R.id.switching_startTime_tv);  //开机时间
        switching_stopTime_tv = (TextView) findViewById(R.id.switching_stopTime_tv);   //结束时间
        switching_effectiveHours_tv = (TextView) findViewById(R.id.switching_effectiveHours_tv); //有效时间
        switching_workContent_tv = (TextView) findViewById(R.id.switching_workContent_tv);    //工作内容
        switching_userName_tv = (TextView) findViewById(R.id.switching_userName_tv);   //使用人
        switching_beforeState_tv = (TextView) findViewById(R.id.switching_beforeState_tv);    //使用前状态
        switching_afterState_tv = (TextView) findViewById(R.id.switching_afterState_tv); //使用后状态
        edit_switching_info_tv = (TextView) findViewById(R.id.edit_switching_info_tv);    //编辑开关机记录
    }

    /**
     * 为控件设置数据值
     */
    private void setDataForWidget() {
        switching_useDate_tv.setText(switchingInfoMap.get("switching_useDate").toString());  //使用日期
        switching_startTime_tv.setText(switchingInfoMap.get("switching_startTime").toString());  //开机时间
        switching_stopTime_tv.setText(switchingInfoMap.get("switching_stopTime").toString());   //结束时间
        switching_effectiveHours_tv.setText(switchingInfoMap.get("switching_effectiveHours").toString()); //有效时间
        switching_workContent_tv.setText(switchingInfoMap.get("switching_workContent").toString());    //工作内容
        switching_userName_tv.setText(switchingInfoMap.get("switching_userName").toString());   //使用人
        switching_beforeState_tv.setText(switchingInfoMap.get("switching_beforeState").toString());    //使用前状态
        switching_afterState_tv.setText(switchingInfoMap.get("switching_afterState").toString()); //使用后状态
    }

    /**
     * 为编辑按钮设置监听器
     */
    private void setClickListenerForEditLogTV() {
        edit_switching_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SwitchingInfoDetailActivity.this, EditSwitchingInfoActivity.class);
                intent.putExtra("switchingInfoMap",switchingInfoMap);
                startActivity(intent);
                finish();
            }
        });
    }
}
