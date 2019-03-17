package com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.create;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.detail.SwitchingInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CreateSwitchingInfoActivity extends AppCompatActivity {
    //定义控件变量
    private EditText create_switching_usekDate_et;    //使用日期
    private EditText create_switching_startTime_et;   //开机时间
    private EditText create_switching_stopTime_et;    //关机时间
    private EditText create_switching_workContent_et; //工作内容
    private EditText create_switching_userName_et;    //使用人
    private EditText create_switching_beforeState_et; //使用前状态
    private EditText create_switching_afterState_et;  //使用后状态
    private TextView create_save_switching_info_tv;   //完成并创建
    private TextView create_cancel_switching_info_tv; //取消返回

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> switchingInfoMap = new HashMap<>();
    private String create_switching_usekDate_value = "";    //使用日期
    private String create_switching_startTime_value = "";   //开机时间
    private String create_switching_stopTime_value = "";    //关机时间
    private String create_switching_workContent_value = ""; //工作内容
    private String create_switching_userName_value = "";    //使用人
    private String create_switching_beforeState_value = ""; //使用前状态
    private String create_switching_afterState_value = "";  //使用后状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_switching_info);
        //初始化控件变量
        initWidget();
        //为选择日期与时间输入框设置监听器
        setListenerForSelectDateAndTimeET();
        //为取消按钮设置点击监听器
        setClickListenerForCancelTV();
        //为保存创建按钮设置点击监听器
        setClickListenerForSavaTV();
    }

    /**
     * 为回退键设置点击监听器
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            new android.app.AlertDialog.Builder(CreateSwitchingInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消创建开关机记录信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(CreateSwitchingInfoActivity.this, SwitchingInfoActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {//响应事件
                    // TODO Auto-generated method stub
                    Log.i("alertdialog"," 请保存数据！");
                }
            }).show();//在按键响应事件中显示此对话框
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化控件变量
     */
    private void initWidget() {
        create_switching_usekDate_et = (EditText) findViewById(R.id.create_switching_usekDate_et);    //使用日期
        create_switching_startTime_et = (EditText) findViewById(R.id.create_switching_startTime_et);   //开机时间
        create_switching_stopTime_et = (EditText) findViewById(R.id.create_switching_stopTime_et);    //关机时间
        create_switching_workContent_et = (EditText) findViewById(R.id.create_switching_workContent_et); //工作内容
        create_switching_userName_et = (EditText) findViewById(R.id.create_switching_userName_et);    //使用人
        create_switching_beforeState_et = (EditText) findViewById(R.id.create_switching_beforeState_et); //使用前状态
        create_switching_afterState_et = (EditText) findViewById(R.id.create_switching_afterState_et);  //使用后状态
        create_save_switching_info_tv = (TextView) findViewById(R.id.create_save_switching_info_tv);   //完成并创建
        create_cancel_switching_info_tv = (TextView) findViewById(R.id.create_cancel_switching_info_tv); //取消返回
    }

    /**
     * 为选择日期与时间输入框设置监听器
     */
    private void setListenerForSelectDateAndTimeET() {
        //使用日期
        create_switching_usekDate_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(CreateSwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = new Date(date.getTime());
                            create_switching_usekDate_value = sm.format(d);
                            create_switching_usekDate_et.setText(create_switching_usekDate_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
        //开机时间
        create_switching_startTime_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(CreateSwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("HH:mm");
                            Date d = new Date(date.getTime());
                            create_switching_startTime_value = sm.format(d);
                            create_switching_startTime_et.setText(create_switching_startTime_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
        //关机时间
        create_switching_stopTime_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(CreateSwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("HH:mm");
                            Date d = new Date(date.getTime());
                            create_switching_stopTime_value = sm.format(d);
                            create_switching_stopTime_et.setText(create_switching_stopTime_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
    }

    /**
     * 为取消按钮设置点击监听器
     */
    private void setClickListenerForCancelTV() {
        create_cancel_switching_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateSwitchingInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消创建开关机记录信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateSwitchingInfoActivity.this, SwitchingInfoActivity.class);
                                startActivity(intent);
                                finish();
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

    /**
     * 为保存创建按钮设置点击监听器
     */
    private void setClickListenerForSavaTV() {
        create_save_switching_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_switching_usekDate_value = create_switching_usekDate_et.getText().toString();    //使用日期
                create_switching_startTime_value = create_switching_startTime_et.getText().toString();   //开机时间
                create_switching_stopTime_value = create_switching_stopTime_et.getText().toString();    //关机时间
                create_switching_workContent_value = create_switching_workContent_et.getText().toString(); //工作内容
                create_switching_userName_value = create_switching_userName_et.getText().toString();    //使用人
                create_switching_beforeState_value = create_switching_beforeState_et.getText().toString(); //使用前状态
                create_switching_afterState_value = create_switching_afterState_et.getText().toString();  //使用后状态

                switchingInfoMap.put("switching_useDate",create_switching_usekDate_value);
                switchingInfoMap.put("switching_startTime",create_switching_startTime_value);
                switchingInfoMap.put("switching_stopTime",create_switching_stopTime_value);
                switchingInfoMap.put("switching_effectiveHours","7.97");
                switchingInfoMap.put("switching_workContent",create_switching_workContent_value);
                switchingInfoMap.put("switching_userName",create_switching_userName_value);
                switchingInfoMap.put("switching_beforeState",create_switching_beforeState_value);
                switchingInfoMap.put("switching_afterState",create_switching_afterState_value);

                Log.d("创建使用日期的值：",create_switching_usekDate_value);
                Log.d("创建开机时间的值：",create_switching_startTime_value);
                Log.d("创建关机时间的值：",create_switching_stopTime_value);
                Log.d("创建工作内容的值：",create_switching_workContent_value);
                Log.d("创建使用人的值：",create_switching_userName_value);
                Log.d("创建使用前状态的值：",create_switching_beforeState_value);
                Log.d("创建使用后状态的值：",create_switching_afterState_value);
                Toast.makeText(CreateSwitchingInfoActivity.this, "创建开关机记录信息成功！", Toast.LENGTH_SHORT).show();
                intent = new Intent(CreateSwitchingInfoActivity.this,SwitchingInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
