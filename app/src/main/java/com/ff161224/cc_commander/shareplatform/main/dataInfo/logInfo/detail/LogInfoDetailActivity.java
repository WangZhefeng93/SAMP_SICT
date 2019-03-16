package com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.edit.EditLogInfoActivity;

import java.util.HashMap;

public class LogInfoDetailActivity extends AppCompatActivity {
    //定义控件变量
    private TextView log_orderFormNo_tv;    //委托单号
    private TextView log_checkDate_tv;  //检测日期
    private TextView log_startTime_tv;  //检测开始时间
    private TextView log_stopTime_tv;   //检测结束时间
    private TextView log_checkHours_tv; //检测时间
    private TextView log_workContent_tv;    //工作内容
    private TextView log_userName_tv;   //使用人
    private TextView log_state_tv;  //日志状态
    private TextView log_beforeState_tv;    //使用前状态
    private TextView log_afterState_tv; //使用后状态
    private TextView edit_log_info_tv;  //编辑日志信息

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> logInfoMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_log_info_detail);
        intent = getIntent();
        logInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("logInfoMap");
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
        log_orderFormNo_tv = (TextView) findViewById(R.id.log_orderFormNo_tv);    //委托单号
        log_checkDate_tv = (TextView) findViewById(R.id.log_checkDate_tv);  //检测日期
        log_startTime_tv = (TextView) findViewById(R.id.log_startTime_tv);  //检测开始时间
        log_stopTime_tv = (TextView) findViewById(R.id.log_stopTime_tv);   //检测结束时间
        log_checkHours_tv = (TextView) findViewById(R.id.log_checkHours_tv); //检测时间
        log_workContent_tv = (TextView) findViewById(R.id.log_workContent_tv);    //工作内容
        log_userName_tv = (TextView) findViewById(R.id.log_userName_tv);   //使用人
        log_state_tv = (TextView) findViewById(R.id.log_state_tv);  //日志状态
        log_beforeState_tv = (TextView) findViewById(R.id.log_beforeState_tv);    //使用前状态
        log_afterState_tv = (TextView) findViewById(R.id.log_afterState_tv); //使用后状态
        edit_log_info_tv = (TextView) findViewById(R.id.edit_log_info_tv);  //编辑日志信息
    }

    /**
     * 为控件设置数据值
     */
    private void setDataForWidget() {
        log_orderFormNo_tv.setText(logInfoMap.get("log_orderFormNo").toString());
        log_checkDate_tv.setText(logInfoMap.get("log_checkDate").toString());
        log_startTime_tv.setText(logInfoMap.get("log_startTime").toString());
        log_stopTime_tv.setText(logInfoMap.get("log_stopTime").toString());
        log_checkHours_tv.setText(logInfoMap.get("log_checkHours").toString());
        log_workContent_tv.setText(logInfoMap.get("log_workContent").toString());
        log_userName_tv.setText(logInfoMap.get("log_userName").toString());
        log_state_tv.setText(logInfoMap.get("log_state").toString());
        log_beforeState_tv.setText("".equals(logInfoMap.get("log_beforeState").toString())?"":logInfoMap.get("log_beforeState").toString());
        log_afterState_tv.setText("".equals(logInfoMap.get("log_afterState").toString())?"":logInfoMap.get("log_afterState").toString());
    }

    /**
     * 为编辑按钮设置监听器
     */
    private void setClickListenerForEditLogTV() {
        edit_log_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LogInfoDetailActivity.this, EditLogInfoActivity.class);
                intent.putExtra("logInfoMap",logInfoMap);
                startActivity(intent);
                finish();
            }
        });
    }
}
