package com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.edit.EditStandardInfoActivity;

import java.io.Serializable;
import java.util.HashMap;

public class StandardInfoDetailActivity extends AppCompatActivity {
    //定义控件变量
    private TextView standard_no_tv;    //标准号
    private TextView standard_name_tv;  //标准名称
    private TextView standard_type_tv;  //标准类型
    private TextView standard_state_tv; //标准状态
    private TextView standard_classification_tv;    //标准类别
    private TextView standard_introduction_tv;  //标准简介
    private TextView edit_standard_info_tv; //编辑标准信息

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> standardInfoMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_standard_info_detail);
        intent = getIntent();
        standardInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("standardInfoMap");
        //初始化控件变量
        initWidget();
        //为控件设置数据值
        setDataForWidget();
        //为编辑按钮设置监听器
        setClickListenerForEditStandardTV();
    }

    /**
     * 初始化控件变量
     */
    private void initWidget() {
        standard_no_tv = (TextView) findViewById(R.id.standard_no_tv);    //标准号
        standard_name_tv = (TextView) findViewById(R.id.standard_name_tv);  //标准名称
        standard_type_tv = (TextView) findViewById(R.id.standard_type_tv);  //标准类型
        standard_state_tv = (TextView) findViewById(R.id.standard_state_tv); //标准状态
        standard_classification_tv = (TextView) findViewById(R.id.standard_classification_tv);    //标准类别
        standard_introduction_tv = (TextView) findViewById(R.id.standard_introduction_tv);  //标准简介
        edit_standard_info_tv = (TextView) findViewById(R.id.edit_standard_info_tv); //编辑标准信息
    }

    /**
     * 为控件设置数据值
     */
    private void setDataForWidget() {
        standard_no_tv.setText(standardInfoMap.get("standard_no").toString());    //标准号
        standard_name_tv.setText(standardInfoMap.get("standard_name").toString());  //标准名称
        standard_type_tv.setText(standardInfoMap.get("standard_type").toString());  //标准类型
        standard_state_tv.setText(standardInfoMap.get("standard_state").toString()); //标准状态
        standard_classification_tv.setText(standardInfoMap.get("standard_classification").toString());  //标准类别
        standard_introduction_tv.setText(standardInfoMap.get("standard_introduction").toString());  //仪器简介
    }

    /**
     * 为编辑按钮设置监听器
     */
    private void setClickListenerForEditStandardTV() {
        edit_standard_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StandardInfoDetailActivity.this, EditStandardInfoActivity.class);
                intent.putExtra("standardInfoMap",(Serializable) standardInfoMap);
                startActivity(intent);
                finish();
            }
        });
    }
}
