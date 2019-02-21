package com.ff161224.cc_commander.shareplatform.PersonInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;

public class PersonInfoActivity extends AppCompatActivity {
    //定义属性
    private TextView tittle_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_person_info);
        //绑定界面标题TextView控件

        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置界面标题
        tittle_tv.setText(this.getString(R.string.pia_personal_information));
    }
}
