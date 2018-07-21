package com.ff161224.cc_commander.shareplatform;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class TimeOrderShowMoneyActivity extends AppCompatActivity {
    //定义属性
    public static TimeOrderShowMoneyActivity instance = null;
    private TextView tittle_tv;
    private TextView time_order_want_to_order_instrument_tv5;
    private TextView time_order_show_money_next_tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_show_money);

        instance = this;

        //绑定标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置标题
        tittle_tv.setText("时间预约——费用明细");
        tittle_tv.setGravity(Gravity.CENTER);

        //绑定想要预约的仪器
        time_order_want_to_order_instrument_tv5 = (TextView)findViewById(R.id.time_order_want_to_order_instrument_tv5);

        //设置想要预约的仪器名称
        time_order_want_to_order_instrument_tv5.setText("液相色谱质谱联用仪LTQ-OrbitrapXL");

        //绑定下一步按钮
        time_order_show_money_next_tv = (TextView)findViewById(R.id.time_order_show_money_next_tv);

        //为下一步按钮设置点击监听事件
        time_order_show_money_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TimeOrderShowMoneyActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要进行下一步吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(TimeOrderShowMoneyActivity.this,TimeOrderSetCommentActivity.class);
                                startActivity(intent);
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
