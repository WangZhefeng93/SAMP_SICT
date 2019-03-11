package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.component;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.edit.EditMainComponentActivity;

public class DetailMainComponentInfo extends AppCompatActivity {
    //定义属性
    private TextView tittle_main_component_edit_tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_detail_main_component_info);

        //绑定编辑按钮
        tittle_main_component_edit_tv = (TextView)findViewById(R.id.tittle_main_component_edit_tv);

        //为编辑按钮设置点击监听器
        tittle_main_component_edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailMainComponentInfo.this, "编辑主要零件信息", Toast.LENGTH_SHORT).show();
                intent = new Intent(DetailMainComponentInfo.this,EditMainComponentActivity.class);
                startActivity(intent);
            }
        });
    }
}
