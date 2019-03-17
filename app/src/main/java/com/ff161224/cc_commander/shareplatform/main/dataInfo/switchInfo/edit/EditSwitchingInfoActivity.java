package com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.edit;

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

public class EditSwitchingInfoActivity extends AppCompatActivity {
    //定义控件变量
    private EditText edit_switching_usekDate_et;    //使用日期
    private EditText edit_switching_startTime_et;   //开机时间
    private EditText edit_switching_stopTime_et;    //关机时间
    private EditText edit_switching_workContent_et; //工作内容
    private EditText edit_switching_userName_et;    //使用人
    private EditText edit_switching_beforeState_et; //使用前状态
    private EditText edit_switching_afterState_et;  //使用后状态
    private TextView edit_save_switching_info_tv;   //完成并修改
    private TextView edit_cancel_switching_info_tv; //取消返回

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> switchingInfoMap = new HashMap<>();
    private String edit_switching_usekDate_value = "";    //使用日期
    private String edit_switching_startTime_value = "";   //开机时间
    private String edit_switching_stopTime_value = "";    //关机时间
    private String edit_switching_workContent_value = ""; //工作内容
    private String edit_switching_userName_value = "";    //使用人
    private String edit_switching_beforeState_value = ""; //使用前状态
    private String edit_switching_afterState_value = "";  //使用后状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_edit_switching_info);
        //接收开关机记录Map数据
        intent = getIntent();
        switchingInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("switchingInfoMap");
        //初始化控件变量
        initWidget();
        //为控件设置数据值
        setDataForWidget();
        //为选择日期与时间输入框设置监听器
        setListenerForSelectDateAndTimeET();
        //为取消按钮设置点击监听器
        setClickListenerForCancelTV();
        //为保存修改按钮设置点击监听器
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
            new android.app.AlertDialog.Builder(EditSwitchingInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消修改开关机记录信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(EditSwitchingInfoActivity.this, SwitchingInfoActivity.class);
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
        edit_switching_usekDate_et = (EditText) findViewById(R.id.edit_switching_usekDate_et);    //使用日期
        edit_switching_startTime_et = (EditText) findViewById(R.id.edit_switching_startTime_et);   //开机时间
        edit_switching_stopTime_et = (EditText) findViewById(R.id.edit_switching_stopTime_et);    //关机时间
        edit_switching_workContent_et = (EditText) findViewById(R.id.edit_switching_workContent_et); //工作内容
        edit_switching_userName_et = (EditText) findViewById(R.id.edit_switching_userName_et);    //使用人
        edit_switching_beforeState_et = (EditText) findViewById(R.id.edit_switching_beforeState_et); //使用前状态
        edit_switching_afterState_et = (EditText) findViewById(R.id.edit_switching_afterState_et);  //使用后状态
        edit_save_switching_info_tv = (TextView) findViewById(R.id.edit_save_switching_info_tv);   //完成并修改
        edit_cancel_switching_info_tv = (TextView) findViewById(R.id.edit_cancel_switching_info_tv); //取消返回
    }

    /**
     * 为控件设置数据值
     */
    private void setDataForWidget() {
        edit_switching_usekDate_value = switchingInfoMap.get("switching_useDate").toString();    //使用日期
        edit_switching_startTime_value = switchingInfoMap.get("switching_startTime").toString();   //开机时间
        edit_switching_stopTime_value = switchingInfoMap.get("switching_stopTime").toString();    //关机时间
        edit_switching_workContent_value = switchingInfoMap.get("switching_workContent").toString(); //工作内容
        edit_switching_userName_value = switchingInfoMap.get("switching_userName").toString();    //使用人
        edit_switching_beforeState_value = switchingInfoMap.get("switching_beforeState").toString(); //使用前状态
        edit_switching_afterState_value = switchingInfoMap.get("switching_afterState").toString();  //使用后状态

        edit_switching_usekDate_et.setText(edit_switching_usekDate_value);    //使用日期
        edit_switching_startTime_et.setText(edit_switching_startTime_value);   //开机时间
        edit_switching_stopTime_et.setText(edit_switching_stopTime_value);    //关机时间
        edit_switching_workContent_et.setText(edit_switching_workContent_value); //工作内容
        edit_switching_userName_et.setText(edit_switching_userName_value);    //使用人
        edit_switching_beforeState_et.setText(edit_switching_beforeState_value); //使用前状态
        edit_switching_afterState_et.setText(edit_switching_afterState_value);  //使用后状态
    }

    /**
     * 为选择日期与时间输入框设置监听器
     */
    private void setListenerForSelectDateAndTimeET() {
        //使用日期
        edit_switching_usekDate_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(EditSwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = new Date(date.getTime());
                            edit_switching_usekDate_value = sm.format(d);
                            edit_switching_usekDate_et.setText(edit_switching_usekDate_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
        //开机时间
        edit_switching_startTime_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(EditSwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("HH:mm");
                            Date d = new Date(date.getTime());
                            edit_switching_startTime_value = sm.format(d);
                            edit_switching_startTime_et.setText(edit_switching_startTime_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
        //关机时间
        edit_switching_stopTime_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(EditSwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("HH:mm");
                            Date d = new Date(date.getTime());
                            edit_switching_stopTime_value = sm.format(d);
                            edit_switching_stopTime_et.setText(edit_switching_stopTime_value);
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
        edit_cancel_switching_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(EditSwitchingInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消修改开关机记录信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(EditSwitchingInfoActivity.this, SwitchingInfoActivity.class);
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
     * 为保存修改按钮设置点击监听器
     */
    private void setClickListenerForSavaTV() {
        edit_save_switching_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_switching_usekDate_value = edit_switching_usekDate_et.getText().toString();    //使用日期
                edit_switching_startTime_value = edit_switching_startTime_et.getText().toString();   //开机时间
                edit_switching_stopTime_value = edit_switching_stopTime_et.getText().toString();    //关机时间
                edit_switching_workContent_value = edit_switching_workContent_et.getText().toString(); //工作内容
                edit_switching_userName_value = edit_switching_userName_et.getText().toString();    //使用人
                edit_switching_beforeState_value = edit_switching_beforeState_et.getText().toString(); //使用前状态
                edit_switching_afterState_value = edit_switching_afterState_et.getText().toString();  //使用后状态

                switchingInfoMap.put("switching_useDate",edit_switching_usekDate_value);
                switchingInfoMap.put("switching_startTime",edit_switching_startTime_value);
                switchingInfoMap.put("switching_stopTime",edit_switching_stopTime_value);
                switchingInfoMap.put("switching_effectiveHours","7.97");
                switchingInfoMap.put("switching_workContent",edit_switching_workContent_value);
                switchingInfoMap.put("switching_userName",edit_switching_userName_value);
                switchingInfoMap.put("switching_beforeState",edit_switching_beforeState_value);
                switchingInfoMap.put("switching_afterState",edit_switching_afterState_value);

                Log.d("修改使用日期的值：",edit_switching_usekDate_value);
                Log.d("修改开机时间的值：",edit_switching_startTime_value);
                Log.d("修改关机时间的值：",edit_switching_stopTime_value);
                Log.d("修改工作内容的值：",edit_switching_workContent_value);
                Log.d("修改使用人的值：",edit_switching_userName_value);
                Log.d("修改使用前状态的值：",edit_switching_beforeState_value);
                Log.d("修改使用后状态的值：",edit_switching_afterState_value);
                Toast.makeText(EditSwitchingInfoActivity.this, "修改开关机记录信息成功！", Toast.LENGTH_SHORT).show();
                intent = new Intent(EditSwitchingInfoActivity.this,SwitchingInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
