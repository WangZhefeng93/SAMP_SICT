package com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.edit;

import android.app.Activity;
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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.detail.LogInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.select.SelectUsePersonActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.select.SelectCreatePricePersonActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class EditLogInfoActivity extends AppCompatActivity {
    //定义控件变量
    private EditText edit_log_orderFormNo_et;    //委托单号
    private EditText edit_log_checkDate_et;  //检测日期
    private EditText edit_log_startTime_et;  //检测开始时间
    private EditText edit_log_stopTime_et;   //检测结束时间
    private EditText edit_log_workContent_et;    //工作内容
    private EditText edit_log_userName_et;   //使用人
    private EditText edit_log_beforeState_et;    //使用前状态
    private EditText edit_log_afterState_et; //使用后状态
    private TextView edit_save_log_info_tv; //完成并修改
    private TextView edit_cancel_log_info_tv;   //取消并返回

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> logInfoMap = new HashMap<>();
    private String edit_log_checkDate_value = "";  //检测日期
    private String edit_log_startTime_value = "";  //检测开始时间
    private String edit_log_stopTime_value = "";   //检测结束时间
    private String edit_log_workContent_value = "";    //工作内容
    private String edit_log_userName_value = "";   //使用人
    private String edit_log_beforeState_value = "";    //使用前状态
    private String edit_log_afterState_value = ""; //使用后状态
    private final int REQUESTCODE_FOR_PERSON = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_edit_log_info);
        intent = getIntent();
        logInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("logInfoMap");
        //初始化控件变量
        initWidget();
        //为控件设置数据值
        setDataForWidget();
        //为选择日期与时间输入框设置监听器
        setListenerForSelectDateAndTimeET();
        //为选择使用人输入框设置监听器
        setListenerForSelectUserPersonET();
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
            new android.app.AlertDialog.Builder(EditLogInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消修改工作日志信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(EditLogInfoActivity.this, LogInfoActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUESTCODE_FOR_PERSON:
                if (resultCode == Activity.RESULT_OK){
                    edit_log_userName_value = data.getStringExtra("log_userName");
                    edit_log_userName_et.setText(edit_log_userName_value);
                }
                break;
        }
    }

    /**
     * 初始化控件变量
     */
    private void initWidget() {
        edit_log_orderFormNo_et = (EditText) findViewById(R.id.edit_log_orderFormNo_et);    //委托单号
        edit_log_checkDate_et = (EditText) findViewById(R.id.edit_log_checkDate_et);  //检测日期
        edit_log_startTime_et = (EditText) findViewById(R.id.edit_log_startTime_et);  //检测开始时间
        edit_log_stopTime_et = (EditText) findViewById(R.id.edit_log_stopTime_et);   //检测结束时间
        edit_log_workContent_et = (EditText) findViewById(R.id.edit_log_workContent_et);    //工作内容
        edit_log_userName_et = (EditText) findViewById(R.id.edit_log_userName_et);   //使用人
        edit_log_beforeState_et = (EditText) findViewById(R.id.edit_log_beforeState_et);    //使用前状态
        edit_log_afterState_et = (EditText) findViewById(R.id.edit_log_afterState_et); //使用后状态
        edit_save_log_info_tv = (TextView) findViewById(R.id.edit_save_log_info_tv); //完成并修改
        edit_cancel_log_info_tv = (TextView) findViewById(R.id.edit_cancel_log_info_tv);   //取消并返回
    }

    /**
     * 为控件设置数据值
     */
    private void setDataForWidget() {
        edit_log_orderFormNo_et.setText(logInfoMap.get("log_orderFormNo").toString());    //委托单号

        edit_log_checkDate_value = logInfoMap.get("log_checkDate").toString();
        edit_log_checkDate_et.setText(edit_log_checkDate_value);  //检测日期

        edit_log_startTime_value = logInfoMap.get("log_startTime").toString();
        edit_log_startTime_et.setText(edit_log_startTime_value);  //检测开始时间

        edit_log_stopTime_value = logInfoMap.get("log_stopTime").toString();
        edit_log_stopTime_et.setText(edit_log_stopTime_value);   //检测结束时间

        edit_log_workContent_value = logInfoMap.get("log_workContent").toString();
        edit_log_workContent_et.setText(edit_log_workContent_value);    //工作内容

        edit_log_userName_value = logInfoMap.get("log_userName").toString();
        edit_log_userName_et.setText(edit_log_userName_value);   //使用人

        edit_log_beforeState_value = "".equals(logInfoMap.get("log_beforeState").toString())?"":logInfoMap.get("log_beforeState").toString();
        edit_log_beforeState_et.setText(edit_log_beforeState_value);    //使用前状态

        edit_log_afterState_value = "".equals(logInfoMap.get("log_afterState").toString())?"":logInfoMap.get("log_afterState").toString();
        edit_log_afterState_et.setText(edit_log_afterState_value); //使用后状态
    }

    /**
     * 为选择日期与时间输入框设置监听器
     */
    private void setListenerForSelectDateAndTimeET() {
        //检测日期
        edit_log_checkDate_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    TimePickerView pvTime = new TimePickerView.Builder(EditLogInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = new Date(date.getTime());
                            edit_log_checkDate_value = sm.format(d);
                            edit_log_checkDate_et.setText(edit_log_checkDate_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
        //检测开始时间
        edit_log_startTime_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(EditLogInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("HH:mm");
                            Date d = new Date(date.getTime());
                            edit_log_startTime_value = sm.format(d);
                            edit_log_startTime_et.setText(edit_log_startTime_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
        //检测结束时间
        edit_log_stopTime_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(EditLogInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("HH:mm");
                            Date d = new Date(date.getTime());
                            edit_log_stopTime_value = sm.format(d);
                            edit_log_stopTime_et.setText(edit_log_stopTime_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
    }

    /**
     * 为选择使用人输入框设置监听器
     */
    private void setListenerForSelectUserPersonET() {
        edit_log_userName_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    intent = new Intent(EditLogInfoActivity.this, SelectUsePersonActivity.class);
                    //intent.putExtra("logInfoMap",logInfoMap);
                    startActivityForResult(intent,REQUESTCODE_FOR_PERSON);
                }
            }
        });
    }

    /**
     * 为取消按钮设置点击监听器
     */
    private void setClickListenerForCancelTV() {
        edit_cancel_log_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(EditLogInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消修改工作日志信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(EditLogInfoActivity.this, LogInfoActivity.class);
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
        edit_save_log_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_log_workContent_value = edit_log_workContent_et.getText().toString();
                edit_log_beforeState_value = edit_log_beforeState_et.getText().toString();    //使用前状态
                edit_log_afterState_value = edit_log_afterState_et.getText().toString(); //使用后状态
                Log.d("编辑日志信息检测日期：",edit_log_checkDate_value);
                Log.d("编辑日志信息检测开始时间：",edit_log_startTime_value);
                Log.d("编辑日志信息检测结束时间：",edit_log_stopTime_value);
                Log.d("编辑日志信息检测工作内容：",edit_log_workContent_value);
                Log.d("编辑日志信息使用人姓名：",edit_log_userName_value);
                Log.d("编辑日志信息使用前状态：",edit_log_beforeState_value);
                Log.d("编辑日志信息使用后状态：",edit_log_afterState_value);
                Toast.makeText(EditLogInfoActivity.this, "修改工作日志信息成功！", Toast.LENGTH_SHORT).show();
                intent = new Intent(EditLogInfoActivity.this,LogInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
