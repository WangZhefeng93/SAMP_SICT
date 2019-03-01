package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.calibration;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.ff161224.cc_commander.shareplatform.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNewCalibrationRecordActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_new_instrument_cancel_tv4;   //取消按钮
    private TextView tittle_create_new_instrument_save_tv4; //保存按钮
    private TextView create_new_instrument_calibrationDate_tv;  //校准日期
    private EditText create_new_instrument_calibrationNo_et;    //证书编号
    private TextView create_new_instrument_effectiveDate_tv;    //校准有效日期
    private RadioGroup create_new_instrument_judgeResult_radioGroup;    //判定结果
    private EditText create_new_instrument_checkUnit_et;    //校验单位
    private EditText create_new_instrument_calibrationNote_et;  //备注

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_add_new_calibration_record);
        //为控件变量绑定布局文件中的控件
        initWidget();
        //为取消按钮设置点击监听器
        setOnClickListenerForCancle4();
        //为保存按钮设置点击监听器
        setOnClickListenerForSave4();
        //为校准日期按钮设置点击监听器
        setOnclickListenerForCalibrationDate();
        //为校准有效日期按钮设置点击监听器
        setOnclickListenerForEffectiveDate();
    }

    /**
     * 为校准有效日期按钮设置点击监听器
     */
    private void setOnclickListenerForEffectiveDate() {
        create_new_instrument_effectiveDate_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(AddNewCalibrationRecordActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = new Date(date.getTime());
                        String time = sm.format(d);
                        //Toast.makeText(getApplicationContext(),time, Toast.LENGTH_LONG).show();
                        create_new_instrument_effectiveDate_tv.setText(time);
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
    }

    /**
     * 为校准日期按钮设置点击监听器
     */
    private void setOnclickListenerForCalibrationDate() {
        create_new_instrument_calibrationDate_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(AddNewCalibrationRecordActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = new Date(date.getTime());
                        String time = sm.format(d);
                        //Toast.makeText(getApplicationContext(),time, Toast.LENGTH_LONG).show();
                        create_new_instrument_calibrationDate_tv.setText(time);
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
    }

    /**
     * 为保存按钮设置点击监听器
     */
    private void setOnClickListenerForSave4() {
        tittle_create_new_instrument_save_tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(AddNewCalibrationRecordActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要保存对仪器基本信息的修改吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(AddNewCalibrationRecordActivity.this, "保存修改！", Toast.LENGTH_SHORT).show();
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
     * 为取消按钮设置点击监听器
     */
    private void setOnClickListenerForCancle4() {
        tittle_create_new_instrument_cancel_tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddNewCalibrationRecordActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("点击取消，您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
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
     * 为控件变量绑定布局文件中的控件
     */
    private void initWidget() {
        tittle_create_new_instrument_cancel_tv4 = (TextView) findViewById(R.id.tittle_create_new_instrument_cancel_tv4);   //取消按钮
        tittle_create_new_instrument_save_tv4 = (TextView) findViewById(R.id.tittle_create_new_instrument_save_tv4); //保存按钮
        create_new_instrument_calibrationDate_tv = (TextView) findViewById(R.id.create_new_instrument_calibrationDate_tv);  //校准日期
        create_new_instrument_calibrationNo_et = (EditText) findViewById(R.id.create_new_instrument_calibrationNo_et);    //证书编号
        create_new_instrument_effectiveDate_tv = (TextView) findViewById(R.id.create_new_instrument_effectiveDate_tv);    //校准有效日期
        create_new_instrument_judgeResult_radioGroup = (RadioGroup) findViewById(R.id.create_new_instrument_judgeResult_radioGroup);    //判定结果
        create_new_instrument_checkUnit_et = (EditText) findViewById(R.id.create_new_instrument_checkUnit_et);    //校验单位
        create_new_instrument_calibrationNote_et = (EditText) findViewById(R.id.create_new_instrument_calibrationNote_et);  //备注
    }
}
