package com.ff161224.cc_commander.shareplatform;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TimeOrderSetCommentActivity extends AppCompatActivity {
    //定义属性
    private TextView tittle_tv;
    private TextView time_order_set_comment_next_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_set_comment);

        //绑定标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置标题
        tittle_tv.setText("时间预约——备注信息");
        tittle_tv.setGravity(Gravity.CENTER);

        //绑定下一步按钮
        time_order_set_comment_next_tv = (TextView)findViewById(R.id.time_order_set_comment_next_tv);

        //为下一步按钮设置点击监听器
        time_order_set_comment_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TimeOrderSetCommentActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要进行提交预约申请吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(TimeOrderSetCommentActivity.this, "预约申请提交成功！", Toast.LENGTH_SHORT).show();
                                TimeOrderSetCommentActivity.this.finish();
                                TimeOrderShowMoneyActivity.instance.finish();
                                TimeOrderChooseCoastNumActivity.instance.finish();
                                TimeOrderProjectStandardActivity.instance.finish();
                                TimeOrderRecordSampleActivity.instance.finish();
                                TimeOrderChooseTimeActivity.instance.finish();
                            }
                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                        Log.i("alertdialog"," 请保存数据！");
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });
    }
}
