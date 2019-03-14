package com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.edit.EditPriceInfoActivity;

import java.io.Serializable;
import java.util.HashMap;

public class PriceInfoDetailActivity extends AppCompatActivity {
    //定义控件变量
    private TextView price_instrumentName_tv;   //仪器名称
    private TextView price_projectName_tv;  //分析项目名称
    private TextView price_sampleClassification_tv; //样品分类
    private TextView price_preDealStandardName_tv;  //前处理标准
    private TextView price_preDealModel_tv; //前处理计费模式
    private TextView price_analysisProjectStandardName_tv;  //分析项目标准
    private TextView price_analysisProjectModel_tv; //分析项目计费模式
    private TextView price_preDealPrice_inUnit_tv;  //前处理收费（所内）
    private TextView price_preDealPrice_inCenter_outUnit_tv;    //前处理收费（中心内所外）
    private TextView price_preDealPrice_inAcademy_outCenter_tv; //前处理收费（院内中心外）
    private TextView price_preDealPrice_outAcademy_tv;  //前处理收费（院外）
    private TextView price_preDealPrice_withoutTax_tv;  //前处理收费（不含税）
    private TextView price_analysisProjectPrice_inUnit_tv;  //分析收费（所内）
    private TextView price_analysisProjectPrice_inCenter_outUnit_tv;    //分析收费（中心内所外）
    private TextView price_analysisProjectPrice_inAcademy_outCenter_tv; //分析收费（院内中心外）
    private TextView price_analysisProjectPrice_outAcademy_tv;  //分析收费（院外）
    private TextView price_analysisProjectPrice_withoutTax_tv;  //分析收费（不含税）
    private TextView price_belongUnit_tv;   //所属单位
    private TextView price_isUsable_tv; //是否启用
    private TextView price_createPerson_tv; //创建人
    private TextView edit_price_info_tv;    //编辑按钮

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> priceDetailInfoMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_price_info_detail);
        //接收传递过来的价格具体数据Map
        intent = getIntent();
        priceDetailInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("priceInfoDetail");
        //初始化控件变量
        initWidget();
        //为控件设置数据值
        setDataForWidget();
        //为编辑按钮设置监听器
        setClickListenerForEditPriceTV();
    }

    /**
     * 初始化控件变量
     */
    private void initWidget() {
        price_instrumentName_tv = (TextView) findViewById(R.id.price_instrumentName_tv);   //仪器名称
        price_projectName_tv = (TextView) findViewById(R.id.price_projectName_tv);  //分析项目名称
        price_sampleClassification_tv = (TextView) findViewById(R.id.price_sampleClassification_tv); //样品分类
        price_preDealStandardName_tv = (TextView) findViewById(R.id.price_preDealStandardName_tv);  //前处理标准
        price_preDealModel_tv = (TextView) findViewById(R.id.price_preDealModel_tv); //前处理计费模式
        price_analysisProjectStandardName_tv = (TextView) findViewById(R.id.price_analysisProjectStandardName_tv);  //分析项目标准
        price_analysisProjectModel_tv = (TextView) findViewById(R.id.price_analysisProjectModel_tv); //分析项目计费模式
        price_preDealPrice_inUnit_tv = (TextView) findViewById(R.id.price_preDealPrice_inUnit_tv);  //前处理收费（所内）
        price_preDealPrice_inCenter_outUnit_tv = (TextView) findViewById(R.id.price_preDealPrice_inCenter_outUnit_tv);    //前处理收费（中心内所外）
        price_preDealPrice_inAcademy_outCenter_tv = (TextView) findViewById(R.id.price_preDealPrice_inAcademy_outCenter_tv); //前处理收费（院内中心外）
        price_preDealPrice_outAcademy_tv = (TextView) findViewById(R.id.price_preDealPrice_outAcademy_tv);  //前处理收费（院外）
        price_preDealPrice_withoutTax_tv = (TextView) findViewById(R.id.price_preDealPrice_withoutTax_tv);  //前处理收费（不含税）
        price_analysisProjectPrice_inUnit_tv = (TextView) findViewById(R.id.price_analysisProjectPrice_inUnit_tv);  //分析收费（所内）
        price_analysisProjectPrice_inCenter_outUnit_tv = (TextView) findViewById(R.id.price_analysisProjectPrice_inCenter_outUnit_tv);    //分析收费（中心内所外）
        price_analysisProjectPrice_inAcademy_outCenter_tv = (TextView) findViewById(R.id.price_analysisProjectPrice_inAcademy_outCenter_tv); //分析收费（院内中心外）
        price_analysisProjectPrice_outAcademy_tv = (TextView) findViewById(R.id.price_analysisProjectPrice_outAcademy_tv);  //分析收费（院外）
        price_analysisProjectPrice_withoutTax_tv = (TextView) findViewById(R.id.price_analysisProjectPrice_withoutTax_tv);  //分析收费（不含税）
        price_belongUnit_tv = (TextView) findViewById(R.id.price_belongUnit_tv);   //所属单位
        price_isUsable_tv = (TextView) findViewById(R.id.price_isUsable_tv); //是否启用
        price_createPerson_tv = (TextView) findViewById(R.id.price_createPerson_tv); //创建人
        edit_price_info_tv = (TextView) findViewById(R.id.edit_price_info_tv);    //编辑按钮
    }

    /**
     * 为控件设置数据值
     */
    private void setDataForWidget() {
        price_instrumentName_tv.setText(priceDetailInfoMap.get("price_instrumentName").toString());   //仪器名称
        price_projectName_tv.setText(priceDetailInfoMap.get("price_projectName").toString());  //分析项目名称
        price_sampleClassification_tv.setText(priceDetailInfoMap.get("price_sampleClassification").toString()); //样品分类
        price_preDealStandardName_tv.setText(priceDetailInfoMap.get("price_preDealStandardName").toString());  //前处理标准
        price_preDealModel_tv.setText(priceDetailInfoMap.get("price_preDealModel").toString()); //前处理计费模式
        price_analysisProjectStandardName_tv.setText(priceDetailInfoMap.get("price_analysisProjectStandardName").toString());  //分析项目标准
        price_analysisProjectModel_tv.setText(priceDetailInfoMap.get("price_analysisProjectModel").toString()); //分析项目计费模式
        price_preDealPrice_inUnit_tv.setText(priceDetailInfoMap.get("price_preDealPrice_inUnit").toString());  //前处理收费（所内）
        price_preDealPrice_inCenter_outUnit_tv.setText(priceDetailInfoMap.get("price_preDealPrice_inCenter_outUnit").toString());    //前处理收费（中心内所外）
        price_preDealPrice_inAcademy_outCenter_tv.setText(priceDetailInfoMap.get("price_preDealPrice_inAcademy_outCenter").toString()); //前处理收费（院内中心外）
        price_preDealPrice_outAcademy_tv.setText(priceDetailInfoMap.get("price_preDealPrice_outAcademy").toString());  //前处理收费（院外）
        price_preDealPrice_withoutTax_tv.setText(priceDetailInfoMap.get("price_preDealPrice_withoutTax").toString());  //前处理收费（不含税）
        price_analysisProjectPrice_inUnit_tv.setText(priceDetailInfoMap.get("price_analysisProjectPrice_inUnit").toString());  //分析收费（所内）
        price_analysisProjectPrice_inCenter_outUnit_tv.setText(priceDetailInfoMap.get("price_analysisProjectPrice_inCenter_outUnit").toString());    //分析收费（中心内所外）
        price_analysisProjectPrice_inAcademy_outCenter_tv.setText(priceDetailInfoMap.get("price_analysisProjectPrice_inAcademy_outCenter").toString()); //分析收费（院内中心外）
        price_analysisProjectPrice_outAcademy_tv.setText(priceDetailInfoMap.get("price_analysisProjectPrice_outAcademy").toString());  //分析收费（院外）
        price_analysisProjectPrice_withoutTax_tv.setText(priceDetailInfoMap.get("price_analysisProjectPrice_withoutTax").toString());  //分析收费（不含税）
        price_belongUnit_tv.setText(priceDetailInfoMap.get("price_belongUnit").toString());   //所属单位
        price_isUsable_tv.setText(priceDetailInfoMap.get("price_isUsable").toString()); //是否启用
        price_createPerson_tv.setText(priceDetailInfoMap.get("price_createPerson").toString()); //创建人
    }

    /**
     * 为编辑按钮设置监听器
     */
    private void setClickListenerForEditPriceTV() {
        edit_price_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(PriceInfoDetailActivity.this,EditPriceInfoActivity.class);
                intent.putExtra("priceInfoDetail",(Serializable) priceDetailInfoMap);
                startActivity(intent);
                finish();
            }
        });
    }
}
