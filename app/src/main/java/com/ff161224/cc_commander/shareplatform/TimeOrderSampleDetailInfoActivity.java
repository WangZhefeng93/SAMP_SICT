package com.ff161224.cc_commander.shareplatform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TimeOrderSampleDetailInfoActivity extends AppCompatActivity {
    //定义属性
    private TextView tittle_time_order_sample_detail_info_name_tv,tittle_time_order_sample_detail_info_edit_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_sample_detail_info);

        //绑定标题
        tittle_time_order_sample_detail_info_name_tv = (TextView)findViewById(R.id.tittle_time_order_sample_detail_info_name_tv);

        //设置标题
        tittle_time_order_sample_detail_info_name_tv.setText("样品详细信息");
        tittle_time_order_sample_detail_info_name_tv.setGravity(Gravity.CENTER);

        //绑定编辑按钮
        tittle_time_order_sample_detail_info_edit_tv = (TextView)findViewById(R.id.tittle_time_order_sample_detail_info_edit_tv);

        //为编辑按钮设置点击监听器
        tittle_time_order_sample_detail_info_edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimeOrderSampleDetailInfoActivity.this, "编辑样品详细信息", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
