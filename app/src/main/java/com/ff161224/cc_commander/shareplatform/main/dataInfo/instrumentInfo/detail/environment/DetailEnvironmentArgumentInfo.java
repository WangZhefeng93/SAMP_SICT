package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.environment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;

public class DetailEnvironmentArgumentInfo extends AppCompatActivity {
    //定义属性
    private TextView tittle_environment_argument_edit_tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_detail_environment_argument_info);

        //绑定编辑按钮
        tittle_environment_argument_edit_tv = (TextView) findViewById(R.id.tittle_environment_argument_edit_tv);

        //为编辑按钮设置点击监听器
        tittle_environment_argument_edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailEnvironmentArgumentInfo.this, "编辑环境参数信息", Toast.LENGTH_SHORT).show();
                intent = new Intent(DetailEnvironmentArgumentInfo.this, EditEnvironmentArgumentActivity.class);
                startActivity(intent);
            }
        });
    }
}
