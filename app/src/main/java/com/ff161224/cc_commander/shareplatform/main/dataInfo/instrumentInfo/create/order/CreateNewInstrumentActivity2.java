package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.order;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.InstrumentInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.random.CreateNewInstrumentActivity3;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.basic.CreateNewInstrumentActivity1;

public class CreateNewInstrumentActivity2 extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_new_instrument_cancel_tv2;   //取消按钮
    private TextView tittle_create_new_instrument_save_tv2; //保存按钮
    private Spinner create_new_instrument_orderType_spinner;    //预约类型
    private Spinner create_new_instrument_orderFormat_spinner;  //预约形式
    private Spinner create_new_instrument_procedure_spinner;    //仪器流程
    private Spinner create_new_instrument_state_spinner;    //仪器状态
    private Spinner create_new_instrument_charge_spinner;   //计费方式
    private RadioGroup create_new_instrument_simpleTemplate_radioGroup; //是否是简约模板
    private RadioGroup create_new_instrument_useable_radioGroup;    //是否可用
    private RadioGroup create_new_instrument_autoOrder_radioGroup;  //是否可自动预约
    private RadioGroup create_new_instrument_machineDeal_radioGroup;    //是否机加工
    private RadioGroup create_new_instrument_sortInstrument_radioGroup; //是否测序仪
    private RadioGroup create_new_instrument_moreInMoreOut_radioGroup;  //是否是多进多出
    private EditText create_new_instrument_usingCharge_et;  //使用单价
    private EditText create_new_instrument_dealDataCharge_et;   //数据处理单价
    private EditText create_new_instrument_advancedDays_et; //提前预约天数
    private EditText create_new_instrument_maxOrderDays_et; //最长预约天数
    private TextView create_new_instrument_orderStartDate_tv;   //选择预约开始时间
    private EditText create_new_instrument_maxWishValue_et; //最大预估量
    private EditText create_new_instrument_oneTimeMaxValue_et;  //同一时间最大样品数
    private TextView create_new_instrument_pre_tv1; //上一步按钮
    private TextView create_new_instrument_next_tv2;    //下一步按钮

    //定义其他变量
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_new_instrument2);
        //为控件变量绑定布局文件中的控件
        initWidget();
        //为取消按钮设置点击监听器
        setOnClickListenerForCancle2();
        //为保存按钮设置点击监听器
        setOnClickListenerForSave2();
        //为下一步按钮设置点击监听器
        setOnClickListenerForNext2();
        //为上一步按钮设置点击监听器
        setOnClickListenerForPre1();
    }

    /**
     * 为上一步按钮设置点击监听器
     */
    private void setOnClickListenerForPre1() {
        create_new_instrument_pre_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity2.this,CreateNewInstrumentActivity1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为下一步按钮设置点击监听器
     */
    private void setOnClickListenerForNext2() {
        create_new_instrument_next_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity2.this,CreateNewInstrumentActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为保存按钮设置点击监听器
     */
    private void setOnClickListenerForSave2() {
        tittle_create_new_instrument_save_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateNewInstrumentActivity2.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要保存对仪器基本信息的修改吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(CreateNewInstrumentActivity2.this, "保存修改！", Toast.LENGTH_SHORT).show();
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
    private void setOnClickListenerForCancle2() {
        tittle_create_new_instrument_cancel_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CreateNewInstrumentActivity2.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("点击取消，您的修改将无法保存！直接退出新建仪器流程！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateNewInstrumentActivity2.this,InstrumentInfoActivity.class);
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
     * 为控件变量绑定布局文件中的控件
     */
    private void initWidget() {
        tittle_create_new_instrument_cancel_tv2 = (TextView) findViewById(R.id.tittle_create_new_instrument_cancel_tv2);   //取消按钮
        tittle_create_new_instrument_save_tv2 = (TextView) findViewById(R.id.tittle_create_new_instrument_save_tv2); //保存按钮
        create_new_instrument_orderType_spinner = (Spinner) findViewById(R.id.create_new_instrument_orderType_spinner);    //预约类型
        create_new_instrument_orderFormat_spinner = (Spinner) findViewById(R.id.create_new_instrument_orderFormat_spinner);  //预约形式
        create_new_instrument_procedure_spinner = (Spinner) findViewById(R.id.create_new_instrument_procedure_spinner);    //仪器流程
        create_new_instrument_state_spinner = (Spinner) findViewById(R.id.create_new_instrument_state_spinner);    //仪器状态
        create_new_instrument_charge_spinner = (Spinner) findViewById(R.id.create_new_instrument_charge_spinner);   //计费方式
        create_new_instrument_simpleTemplate_radioGroup = (RadioGroup) findViewById(R.id.create_new_instrument_simpleTemplate_radioGroup); //是否是简约模板
        create_new_instrument_useable_radioGroup = (RadioGroup) findViewById(R.id.create_new_instrument_useable_radioGroup);    //是否可用
        create_new_instrument_autoOrder_radioGroup = (RadioGroup) findViewById(R.id.create_new_instrument_autoOrder_radioGroup);  //是否可自动预约
        create_new_instrument_machineDeal_radioGroup = (RadioGroup) findViewById(R.id.create_new_instrument_machineDeal_radioGroup);    //是否机加工
        create_new_instrument_sortInstrument_radioGroup = (RadioGroup) findViewById(R.id.create_new_instrument_sortInstrument_radioGroup);  ////是否测序仪
        create_new_instrument_moreInMoreOut_radioGroup = (RadioGroup) findViewById(R.id.create_new_instrument_moreInMoreOut_radioGroup);    //是否多进多出
        create_new_instrument_usingCharge_et = (EditText) findViewById(R.id.create_new_instrument_usingCharge_et);  //使用单价
        create_new_instrument_dealDataCharge_et = (EditText) findViewById(R.id.create_new_instrument_dealDataCharge_et);   //数据处理单价
        create_new_instrument_advancedDays_et = (EditText) findViewById(R.id.create_new_instrument_advancedDays_et); //提前预约天数
        create_new_instrument_maxOrderDays_et = (EditText) findViewById(R.id.create_new_instrument_maxOrderDays_et); //最长预约天数
        create_new_instrument_orderStartDate_tv = (TextView) findViewById(R.id.create_new_instrument_orderStartDate_tv);   //选择预约开始时间
        create_new_instrument_maxWishValue_et = (EditText) findViewById(R.id.create_new_instrument_maxWishValue_et); //最大预估量
        create_new_instrument_oneTimeMaxValue_et = (EditText) findViewById(R.id.create_new_instrument_oneTimeMaxValue_et);  //同一时间最大样品数
        create_new_instrument_pre_tv1 = (TextView) findViewById(R.id.create_new_instrument_pre_tv1);    //上一步按钮
        create_new_instrument_next_tv2 = (TextView) findViewById(R.id.create_new_instrument_next_tv2);    //下一步按钮
    }
}
