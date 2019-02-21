package com.ff161224.cc_commander.shareplatform.main.orderReview.timeOrder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.ff161224.cc_commander.shareplatform.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeOrderChooseTimeActivity extends AppCompatActivity {
    //定义属性
    public static TimeOrderChooseTimeActivity instance = null;
    private TextView tittle_tv;
    private TextView time_order_choose_time_next_tv;
    private TextView time_order_choose_start_date_tv,time_order_choose_end_date_tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_choose_time);

        instance = this;

        //绑定标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置标题
        tittle_tv.setText("时间预约——选择预约时间");

        //绑定选择开始时间
        time_order_choose_start_date_tv = (TextView)findViewById(R.id.time_order_choose_start_date_tv);

        //为开始时间设置点击监听器
        time_order_choose_start_date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(TimeOrderChooseTimeActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d = new Date(date.getTime());
                        String time = sm.format(d);
                        //Toast.makeText(getApplicationContext(),time, Toast.LENGTH_LONG).show();
                        time_order_choose_start_date_tv.setText(time);
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });

        //绑定选择结束时间
        time_order_choose_end_date_tv = (TextView)findViewById(R.id.time_order_choose_end_date_tv);

        //为结束时间设置点击监听器
        time_order_choose_end_date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(TimeOrderChooseTimeActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d = new Date(date.getTime());
                        String time = sm.format(d);
                        //Toast.makeText(getApplicationContext(),time, Toast.LENGTH_LONG).show();
                        time_order_choose_end_date_tv.setText(time);
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });

        //绑定下一步按钮
        time_order_choose_time_next_tv = (TextView)findViewById(R.id.time_order_choose_time_next_tv);

        //为下一步按钮设置点击监听器
        time_order_choose_time_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TimeOrderChooseTimeActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要进行下一步吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(TimeOrderChooseTimeActivity.this,TimeOrderRecordSampleActivity.class);
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
