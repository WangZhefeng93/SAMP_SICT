package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;

public class EditInstrumentBasicInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_edit_instrument_tittle_tv;  //标题
    private TextView edit_instrument_ID_tv;     //仪器ID
    private EditText edit_instrument_name_et;   //仪器名称
    private EditText edit_instrument_typeID_et;     //仪器型号
    private EditText edit_instrument_assetID_et;    //资产编号
    private Spinner edit_instrument__type1_spinner; //仪器大类名称
    private Spinner edit_instrument_type2_spinner;  //仪器中类名称
    private Spinner edit_instrument_type3_spinner;  //仪器小类名称
    private EditText edit_instrument_engName_et;    //仪器英文名称
    private EditText edit_instrument_makeFactory_et;    //制造商
    private EditText edit_instrument_saleFactory_et;    //经销商
    private EditText edit_instrument_country_et;    //国别
    private TextView edit_instrument_buyDate_tv;    //购置日期
    private EditText edit_instrument_buyCoast_et;   //购置金额
    private EditText edit_instrument_factoryID_et;  //出厂编号
    private EditText edit_instrument_dealPerson_et;     //经办领用人
    private EditText edit_instrument_moneySource_et;    //购置经费来源
    private Spinner edit_instrument_areaCenterName_spinner; //所属区域中心
    private Spinner edit_instrument_unitName_spinner;   //所属单位
    private Spinner edit_instrument_groupName_spinner;  //所属研究组
    private Spinner edit_instrument_operatePerson_spinner;  //操作人员
    private EditText edit_instrument_location_et;   //放置地点
    private EditText edit_instrument_function_et;   //功能描述
    private EditText edit_instrument_note_et;   //备注
    private TextView edit_instrument_save_tv;   //保存按钮
    private TextView edit_instrument_cancel_tv; //取消按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_edit_instrument_basic_info);
    }
}
