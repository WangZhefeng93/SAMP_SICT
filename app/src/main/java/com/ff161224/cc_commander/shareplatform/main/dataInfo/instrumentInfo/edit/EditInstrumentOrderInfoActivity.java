package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;

public class EditInstrumentOrderInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_edit_instrument_order_info_tittle_tv;   //编辑仪器预约信息标题
    private Spinner edit_instrument_orderType_spinner;  //预约类型
    private Spinner edit_instrument_orderFormat_spinner;    //预约形式
    private Spinner edit_instrument_procedure_spinner;  //仪器流程
    private Spinner edit_instrument_state_spinner;  //仪器状态
    private Spinner edit_instrument_charge_spinner; //计费方式
    private RadioGroup edit_instrument_simpleTemplate_radioGroup;   //是否是简约模板
    private RadioGroup edit_instrument_useable_radioGroup;  //是否可用
    private RadioGroup edit_instrument_autoOrder_radioGroup;    //是否可自动预约
    private RadioGroup edit_instrument_machineDeal_radioGroup;  //是否机加工
    private RadioGroup edit_instrument_sortInstrument_radioGroup;   //是否测序仪
    private RadioGroup edit_instrument_moreInMoreOut_radioGroup;    //是否多进多出
    private EditText edit_instrument_usingCharge_et;    //使用单价
    private EditText edit_instrument_dealDataCharge_et; //处理数据单价
    private EditText edit_instrument_advancedDays_et;   //提前预约天数
    private EditText edit_instrument_maxOrderDays_et;   //最长预约天数
    private TextView edit_instrument_orderStartDate_tv; //预约开始时间
    private EditText edit_instrument_maxWishValue_et;   //最大预估量
    private EditText edit_instrument_oneTimeMaxValue_et;    //同一时间最大样品数
    private TextView edit_instrument_save_tv;   //保存修改按钮
    private TextView edit_instrument_cancel_tv; //取消编辑按钮

    //定义其他变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_edit_instrument_order_info);
    }
}
