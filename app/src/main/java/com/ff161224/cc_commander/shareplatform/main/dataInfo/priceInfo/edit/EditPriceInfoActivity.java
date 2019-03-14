package com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.edit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.detail.PriceInfoDetailActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.select.SelectAnalysisProjectStandardActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.select.SelectInstrumentActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.select.SelectPreDealStandardActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.select.SelectProjectActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.edit.EditStandardInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EditPriceInfoActivity extends AppCompatActivity {
    //定义控件变量
    private EditText edit_price_instrumentName_et;  //仪器名称
    private EditText edit_price_projectName_et; //分析项目
    private Spinner edit_price_sampleClassification_sp; //样品分类
    private EditText edit_price_preDealStandardName_et; //前处理标准
    private Spinner edit_price_preDealModel_sp; //前处理计费模式
    private EditText edit_price_analysisProjectStandardName_et; //分析项目标准
    private Spinner edit_price_analysisProjectModel_sp; //分析项目计费模式
    private EditText edit_price_preDealPrice_inUnit_et; //前处理收费（所内）
    private EditText edit_price_preDealPrice_inCenter_outUnit_et;   //前处理收费（中心内所外）
    private EditText edit_price_preDealPrice_inAcademy_outCenter_et;    //前处理收费（院内中心外）
    private EditText edit_price_preDealPrice_outAcademy_et; //前处理收费（院外）
    private EditText edit_price_preDealPrice_withoutTax_et; //前处理收费（不含税）
    private EditText edit_price_analysisProjectPrice_inUnit_et; //分析收费（所内）
    private EditText edit_price_analysisProjectPrice_inCenter_outUnit_et;   //分析收费（中心内所外）
    private EditText edit_price_analysisProjectPrice_inAcademy_outCenter_et;    //分析收费（院内中心外）
    private EditText edit_price_analysisProjectPrice_outAcademy_et; //分析收费（院外）
    private EditText edit_price_analysisProjectPrice_withoutTax_et; //分析收费（不含税）
    private Spinner edit_price_belongUnit_sp;   //所属单位
    private RadioGroup edit_price_isUsable_rg;  //是否启用
    private TextView edit_price_info_save_tv;   //完成并保存
    private TextView edit_price_info_cancel_tv; //取消并返回

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> priceDetailInfoMap = new HashMap<>();
    private String[] edit_price_sampleClassification_array = null;
    private ArrayAdapter<String> edit_price_sampleClassification_adapter = null;
    private String edit_price_sampleClassification_value = "";
    private int edit_price_sampleClassification_position = -1;
    private String[] edit_price_belongUnit_array = null;
    private ArrayAdapter<String> edit_price_belongUnit_adapter = null;
    private String edit_price_belongUnit_value = "";
    private int edit_price_belongUnit_position = -1;
    private String  edit_price_instrumentName_value = "";  //仪器名称
    private String edit_price_projectName_value = ""; //分析项目
    private String edit_price_preDealStandardName_value = ""; //前处理标准
    private String edit_price_preDealModel_value = ""; //前处理计费模式
    private int edit_price_preDealModel_position = -1; //前处理计费模式
    private String edit_price_analysisProjectStandardName_value = ""; //分析项目标准
    private String edit_price_analysisProjectModel_value = ""; //分析项目计费模式
    private int edit_price_analysisProjectModel_position = -1;
    private String edit_price_preDealPrice_inUnit_value = ""; //前处理收费（所内）
    private String edit_price_preDealPrice_inCenter_outUnit_value = "";   //前处理收费（中心内所外）
    private String edit_price_preDealPrice_inAcademy_outCenter_value = "";    //前处理收费（院内中心外）
    private String edit_price_preDealPrice_outAcademy_value = ""; //前处理收费（院外）
    private String edit_price_preDealPrice_withoutTax_value = ""; //前处理收费（不含税）
    private String edit_price_analysisProjectPrice_inUnit_value = ""; //分析收费（所内）
    private String edit_price_analysisProjectPrice_inCenter_outUnit_value = "";   //分析收费（中心内所外）
    private String edit_price_analysisProjectPrice_inAcademy_outCenter_value = "";    //分析收费（院内中心外）
    private String edit_price_analysisProjectPrice_outAcademy_value = ""; //分析收费（院外）
    private String edit_price_analysisProjectPrice_withoutTax_value = ""; //分析收费（不含税）
    private String edit_price_isUsable_value = "";  //是否启用
    private final int REQUESTCODE_FOR_INSTRUMENT = 1;
    private final int REQUESTCODE_FOR_PROJECT = 2;
    private final int REQUESTCODE_FOR_PREDEALSTANDARD = 3;
    private final int REQUESTCODE_FOR_ANALYSISPROJECTSTANDARD = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_edit_price_info);
        //接收传递过来的价格具体数据Map
        intent = getIntent();
        priceDetailInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("priceInfoDetail");
        //初始化控件变量
        initWidget();
        //为下拉菜单设置数据
        setDataForSpinner();
        //为控件设置数据
        setDataForWidget();
        //为下拉菜单设置监听器
        setListenerForSpinner();
        //为单选按钮设置监听器
        setListenerForRadioGroup();
        //为不可编辑输入框设置监听器
        setListenerForEditText();
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
            new android.app.AlertDialog.Builder(EditPriceInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消修改价格信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(EditPriceInfoActivity.this, PriceInfoDetailActivity.class);
                            intent.putExtra("priceInfoDetail",(Serializable) priceDetailInfoMap);
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
     * 根据回调码设置回调数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUESTCODE_FOR_INSTRUMENT:
                if (resultCode == Activity.RESULT_OK){
                    edit_price_instrumentName_value = data.getStringExtra("instrument_name");
                    edit_price_instrumentName_et.setText(data.getStringExtra("instrument_name"));
                } else {
                    Log.i("onactivityresultdemo", "i blabalabal");
                }
                break;
            case REQUESTCODE_FOR_PROJECT:
                if (resultCode == Activity.RESULT_OK){
                    edit_price_projectName_value = data.getStringExtra("project_name");
                    edit_price_projectName_et.setText(edit_price_projectName_value);
                } else {
                    Log.i("onactivityresultdemo", "i blabalabal");
                }
                break;
            case REQUESTCODE_FOR_PREDEALSTANDARD:
                if (resultCode == Activity.RESULT_OK){
                    edit_price_preDealStandardName_value = data.getStringExtra("pre_deal_standard_name");
                    edit_price_preDealStandardName_et.setText(edit_price_preDealStandardName_value);
                } else {
                    Log.i("onactivityresultdemo", "i blabalabal");
                }
                break;
            case REQUESTCODE_FOR_ANALYSISPROJECTSTANDARD:
                if (resultCode == Activity.RESULT_OK){
                    edit_price_analysisProjectStandardName_value = data.getStringExtra("analysis_project_standard_name");
                    edit_price_analysisProjectStandardName_et.setText(edit_price_analysisProjectStandardName_value);
                } else {
                    Log.i("onactivityresultdemo", "i blabalabal");
                }
                break;
        }
    }

    /**
     * 初始化控件变量
     */
    private void initWidget() {
        edit_price_instrumentName_et = (EditText) findViewById(R.id.edit_price_instrumentName_et);  //仪器名称
        edit_price_projectName_et = (EditText) findViewById(R.id.edit_price_projectName_et); //分析项目
        edit_price_sampleClassification_sp = (Spinner) findViewById(R.id.edit_price_sampleClassification_sp); //样品分类
        edit_price_preDealStandardName_et = (EditText) findViewById(R.id.edit_price_preDealStandardName_et); //前处理标准
        edit_price_preDealModel_sp = (Spinner) findViewById(R.id.edit_price_preDealModel_sp); //前处理计费模式
        edit_price_analysisProjectStandardName_et = (EditText) findViewById(R.id.edit_price_analysisProjectStandardName_et); //分析项目标准
        edit_price_analysisProjectModel_sp = (Spinner) findViewById(R.id.edit_price_analysisProjectModel_sp); //分析项目计费模式
        edit_price_preDealPrice_inUnit_et = (EditText) findViewById(R.id.edit_price_preDealPrice_inUnit_et); //前处理收费（所内）
        edit_price_preDealPrice_inCenter_outUnit_et = (EditText) findViewById(R.id.edit_price_preDealPrice_inCenter_outUnit_et);   //前处理收费（中心内所外）
        edit_price_preDealPrice_inAcademy_outCenter_et = (EditText) findViewById(R.id.edit_price_preDealPrice_inAcademy_outCenter_et);    //前处理收费（院内中心外）
        edit_price_preDealPrice_outAcademy_et = (EditText) findViewById(R.id.edit_price_preDealPrice_outAcademy_et); //前处理收费（院外）
        edit_price_preDealPrice_withoutTax_et = (EditText) findViewById(R.id.edit_price_preDealPrice_withoutTax_et); //前处理收费（不含税）
        edit_price_analysisProjectPrice_inUnit_et = (EditText) findViewById(R.id.edit_price_analysisProjectPrice_inUnit_et); //分析收费（所内）
        edit_price_analysisProjectPrice_inCenter_outUnit_et = (EditText) findViewById(R.id.edit_price_analysisProjectPrice_inCenter_outUnit_et);   //分析收费（中心内所外）
        edit_price_analysisProjectPrice_inAcademy_outCenter_et = (EditText) findViewById(R.id.edit_price_analysisProjectPrice_inAcademy_outCenter_et);    //分析收费（院内中心外）
        edit_price_analysisProjectPrice_outAcademy_et = (EditText) findViewById(R.id.edit_price_analysisProjectPrice_outAcademy_et); //分析收费（院外）
        edit_price_analysisProjectPrice_withoutTax_et = (EditText) findViewById(R.id.edit_price_analysisProjectPrice_withoutTax_et); //分析收费（不含税）
        edit_price_belongUnit_sp = (Spinner) findViewById(R.id.edit_price_belongUnit_sp);   //所属单位
        edit_price_isUsable_rg = (RadioGroup) findViewById(R.id.edit_price_isUsable_rg);  //是否启用
        edit_price_info_save_tv = (TextView) findViewById(R.id.edit_price_info_save_tv);   //完成并保存
        edit_price_info_cancel_tv = (TextView) findViewById(R.id.edit_price_info_cancel_tv); //取消并返回
    }

    /**
     * 为下拉菜单设置数据
     */
    private void setDataForSpinner() {
        //样品分类下拉菜单
        edit_price_sampleClassification_array = getSampleClassificationData();
        edit_price_sampleClassification_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,edit_price_sampleClassification_array);
        edit_price_sampleClassification_sp.setAdapter(edit_price_sampleClassification_adapter);
        //所属单位下拉菜单
        edit_price_belongUnit_array = getBelongUnitData();
        edit_price_belongUnit_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,edit_price_belongUnit_array);
        edit_price_belongUnit_sp.setAdapter(edit_price_belongUnit_adapter);
    }

    /**
     * 获取样品分类数组数据
     * @return
     */
    public String[] getSampleClassificationData() {
        String[] array = {"薄片","玻璃管","大化石","粉末","固态或粉末","固体",
                "硅质或含硅质岩石","化石或现生植物动物标本","灰岩、碳酸盐","离心管保存",
                "硫化银、硫化钡及其他硫质物","凝胶","切片、连续切片","图像后期处理","微体化石和小型化石",
                "小型-微体化石","岩石薄片制备","液态"};
        return array;
    }

    /**
     * 获取所属单位数组数据
     * @return
     */
    public String[] getBelongUnitData() {
        return new String[] {"南京地质古生物研究所"};
    }

    /**
     * 为控件设置数据
     */
    private void setDataForWidget() {
        edit_price_instrumentName_et.setText(priceDetailInfoMap.get("price_instrumentName").toString());  //仪器名称
        edit_price_projectName_et.setText(priceDetailInfoMap.get("price_projectName").toString()); //分析项目

        List list = Arrays.asList(edit_price_sampleClassification_array);
        int position = list.indexOf(priceDetailInfoMap.get("price_sampleClassification").toString());
        edit_price_sampleClassification_sp.setSelection(position); //样品分类

        edit_price_preDealStandardName_et.setText(priceDetailInfoMap.get("price_preDealStandardName").toString()); //前处理标准

        if ("时间计费".equals(priceDetailInfoMap.get("price_preDealModel").toString())){
            edit_price_preDealModel_sp.setSelection(1); //前处理计费模式
        }else if ("样品数计费".equals(priceDetailInfoMap.get("price_preDealModel").toString())){
            edit_price_preDealModel_sp.setSelection(2); //前处理计费模式
        }else{
            edit_price_preDealModel_sp.setSelection(0); //前处理计费模式
        }

        edit_price_analysisProjectStandardName_et.setText(priceDetailInfoMap.get("price_analysisProjectStandardName").toString()); //分析项目标准

        if ("时间计费".equals(priceDetailInfoMap.get("price_preDealModel").toString())){
            edit_price_analysisProjectModel_sp.setSelection(1); //前处理计费模式
        }else if ("样品数计费".equals(priceDetailInfoMap.get("price_preDealModel").toString())){
            edit_price_analysisProjectModel_sp.setSelection(2); //前处理计费模式
        }else{
            edit_price_analysisProjectModel_sp.setSelection(0); //前处理计费模式
        }

        edit_price_preDealPrice_inUnit_et.setText(priceDetailInfoMap.get("price_preDealPrice_inUnit").toString()); //前处理收费（所内）
        edit_price_preDealPrice_inCenter_outUnit_et.setText(priceDetailInfoMap.get("price_preDealPrice_inCenter_outUnit").toString());   //前处理收费（中心内所外）
        edit_price_preDealPrice_inAcademy_outCenter_et.setText(priceDetailInfoMap.get("price_preDealPrice_inAcademy_outCenter").toString());    //前处理收费（院内中心外）
        edit_price_preDealPrice_outAcademy_et.setText(priceDetailInfoMap.get("price_preDealPrice_outAcademy").toString()); //前处理收费（院外）
        edit_price_preDealPrice_withoutTax_et.setText(priceDetailInfoMap.get("price_preDealPrice_withoutTax").toString()); //前处理收费（不含税）
        edit_price_analysisProjectPrice_inUnit_et.setText(priceDetailInfoMap.get("price_analysisProjectPrice_inUnit").toString()); //分析收费（所内）
        edit_price_analysisProjectPrice_inCenter_outUnit_et.setText(priceDetailInfoMap.get("price_analysisProjectPrice_inCenter_outUnit").toString());   //分析收费（中心内所外）
        edit_price_analysisProjectPrice_inAcademy_outCenter_et.setText(priceDetailInfoMap.get("price_analysisProjectPrice_inAcademy_outCenter").toString());    //分析收费（院内中心外）
        edit_price_analysisProjectPrice_outAcademy_et.setText(priceDetailInfoMap.get("price_analysisProjectPrice_outAcademy").toString()); //分析收费（院外）
        edit_price_analysisProjectPrice_withoutTax_et.setText(priceDetailInfoMap.get("price_analysisProjectPrice_withoutTax").toString()); //分析收费（不含税）

        list = Arrays.asList(edit_price_belongUnit_array);
        position = list.indexOf(priceDetailInfoMap.get("price_belongUnit").toString());
        edit_price_belongUnit_sp.setSelection(position);   //所属单位

        if ("是".equals(priceDetailInfoMap.get("price_isUsable").toString())){
            edit_price_isUsable_rg.check(R.id.edit_price_isUsable_rb_yes);  //是否启用
        }else if ("否".equals(priceDetailInfoMap.get("price_isUsable").toString())){
            edit_price_isUsable_rg.check(R.id.edit_price_isUsable_rb__no);  //是否启用
        }else{
            edit_price_isUsable_rg.check(R.id.edit_price_isUsable_rb_yes);  //是否启用
        }
    }

    /**
     * 为下拉菜单设置监听器
     */
    private void setListenerForSpinner() {
        //样品分类
        edit_price_sampleClassification_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_price_sampleClassification_position = position;
                edit_price_sampleClassification_value = edit_price_sampleClassification_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //前处理计费模式
        edit_price_preDealModel_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_price_preDealModel_position = position;
                switch (position){
                    case 0:
                        edit_price_preDealModel_value = "--请选择--";
                        break;
                    case 1:
                        edit_price_preDealModel_value = "时间计费";
                        break;
                    case 2:
                        edit_price_preDealModel_value = "样品数计费";
                        break;
                    default:
                        edit_price_preDealModel_value = "--请选择--";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //分析项目计费模式
        edit_price_analysisProjectModel_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_price_analysisProjectModel_position = position;
                switch (position){
                    case 0:
                        edit_price_analysisProjectModel_value = "--请选择--";
                        break;
                    case 1:
                        edit_price_analysisProjectModel_value = "时间计费";
                        break;
                    case 2:
                        edit_price_analysisProjectModel_value = "样品数计费";
                        break;
                    default:
                        edit_price_analysisProjectModel_value = "--请选择--";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //所属单位
        edit_price_belongUnit_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_price_belongUnit_position = position;
                edit_price_belongUnit_value = edit_price_belongUnit_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 为单选按钮设置监听器
     */
    private void setListenerForRadioGroup() {
        edit_price_isUsable_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                edit_price_isUsable_value = radioButton.getText().toString();
            }
        });
    }

    /**
     * 为不可编辑输入框设置监听器
     */
    private void setListenerForEditText() {
        //仪器名称
        edit_price_instrumentName_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    intent = new Intent(EditPriceInfoActivity.this, SelectInstrumentActivity.class);
                    startActivityForResult(intent,REQUESTCODE_FOR_INSTRUMENT);
                }
            }
        });

        //分析项目
        edit_price_projectName_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    intent = new Intent(EditPriceInfoActivity.this, SelectProjectActivity.class);
                    startActivityForResult(intent,REQUESTCODE_FOR_PROJECT);
                }
            }
        });

        //前处理标准
        edit_price_preDealStandardName_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    intent = new Intent(EditPriceInfoActivity.this, SelectPreDealStandardActivity.class);
                    startActivityForResult(intent,REQUESTCODE_FOR_PREDEALSTANDARD);
                }
            }
        });

        //分析项目标准
        edit_price_analysisProjectStandardName_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    intent = new Intent(EditPriceInfoActivity.this, SelectAnalysisProjectStandardActivity.class);
                    startActivityForResult(intent,REQUESTCODE_FOR_ANALYSISPROJECTSTANDARD);
                }
            }
        });
    }

    /**
     * 为取消按钮设置点击监听器
     */
    private void setClickListenerForCancelTV() {
        edit_price_info_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(EditPriceInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消修改价格信息并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(EditPriceInfoActivity.this, PriceInfoDetailActivity.class);
                                intent.putExtra("priceInfoDetail",(Serializable) priceDetailInfoMap);
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
        edit_price_info_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromEditText();
                HashMap<String,Object> priceDetailInfoMapEdited = new HashMap<>();
                priceDetailInfoMapEdited.put("price_instrumentName",edit_price_instrumentName_value);
                priceDetailInfoMapEdited.put("price_projectName",edit_price_projectName_value);
                priceDetailInfoMapEdited.put("price_sampleClassification",edit_price_sampleClassification_value);
                priceDetailInfoMapEdited.put("price_preDealStandardName",edit_price_preDealStandardName_value);
                priceDetailInfoMapEdited.put("price_preDealModel",edit_price_preDealModel_value);
                priceDetailInfoMapEdited.put("price_analysisProjectStandardName",edit_price_analysisProjectStandardName_value);
                priceDetailInfoMapEdited.put("price_analysisProjectModel",edit_price_analysisProjectModel_value);
                priceDetailInfoMapEdited.put("price_preDealPrice_inUnit",edit_price_preDealPrice_inUnit_value);
                priceDetailInfoMapEdited.put("price_preDealPrice_inCenter_outUnit",edit_price_preDealPrice_inCenter_outUnit_value);
                priceDetailInfoMapEdited.put("price_preDealPrice_inAcademy_outCenter",edit_price_preDealPrice_inAcademy_outCenter_value);
                priceDetailInfoMapEdited.put("price_preDealPrice_outAcademy",edit_price_preDealPrice_outAcademy_value);
                priceDetailInfoMapEdited.put("price_preDealPrice_withoutTax",edit_price_preDealPrice_withoutTax_value);
                priceDetailInfoMapEdited.put("price_analysisProjectPrice_inUnit",edit_price_analysisProjectPrice_inUnit_value);
                priceDetailInfoMapEdited.put("price_analysisProjectPrice_inCenter_outUnit",edit_price_analysisProjectPrice_inCenter_outUnit_value);
                priceDetailInfoMapEdited.put("price_analysisProjectPrice_inAcademy_outCenter",edit_price_analysisProjectPrice_inAcademy_outCenter_value);
                priceDetailInfoMapEdited.put("price_analysisProjectPrice_outAcademy",edit_price_analysisProjectPrice_outAcademy_value);
                priceDetailInfoMapEdited.put("price_analysisProjectPrice_withoutTax",edit_price_analysisProjectPrice_withoutTax_value);
                priceDetailInfoMapEdited.put("price_belongUnit",edit_price_belongUnit_value);
                priceDetailInfoMapEdited.put("price_isUsable",edit_price_isUsable_value);
                if ("是".equals(edit_price_isUsable_value)){
                    priceDetailInfoMapEdited.put("price_isUsable_flag","1");
                }else {
                    priceDetailInfoMapEdited.put("price_isUsable_flag","0");
                }
                priceDetailInfoMapEdited.put("price_createPerson","张羽");
                Toast.makeText(EditPriceInfoActivity.this, "修改价格信息成功！", Toast.LENGTH_SHORT).show();
                intent = new Intent(EditPriceInfoActivity.this,PriceInfoDetailActivity.class);
                intent.putExtra("priceInfoDetail",(Serializable) priceDetailInfoMapEdited);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 获取输入框的数据
     */
    public void getDataFromEditText() {
        edit_price_instrumentName_value = edit_price_instrumentName_et.getText().toString();
        edit_price_projectName_value = edit_price_projectName_et.getText().toString();
        edit_price_preDealStandardName_value = edit_price_preDealStandardName_et.getText().toString();
        edit_price_analysisProjectStandardName_value = edit_price_analysisProjectStandardName_et.getText().toString();
        edit_price_preDealPrice_inUnit_value = edit_price_preDealPrice_inUnit_et.getText().toString();
        edit_price_preDealPrice_inUnit_value = edit_price_preDealPrice_inUnit_et.getText().toString(); //前处理收费（所内）
        edit_price_preDealPrice_inCenter_outUnit_value = edit_price_preDealPrice_inCenter_outUnit_et.getText().toString();   //前处理收费（中心内所外）
        edit_price_preDealPrice_inAcademy_outCenter_value = edit_price_preDealPrice_inAcademy_outCenter_et.getText().toString();    //前处理收费（院内中心外）
        edit_price_preDealPrice_outAcademy_value = edit_price_preDealPrice_outAcademy_et.getText().toString(); //前处理收费（院外）
        edit_price_preDealPrice_withoutTax_value = edit_price_preDealPrice_withoutTax_et.getText().toString(); //前处理收费（不含税）
        edit_price_analysisProjectPrice_inUnit_value = edit_price_analysisProjectPrice_inUnit_et.getText().toString(); //分析收费（所内）
        edit_price_analysisProjectPrice_inCenter_outUnit_value = edit_price_analysisProjectPrice_inCenter_outUnit_et.getText().toString();   //分析收费（中心内所外）
        edit_price_analysisProjectPrice_inAcademy_outCenter_value = edit_price_analysisProjectPrice_inAcademy_outCenter_et.getText().toString();    //分析收费（院内中心外）
        edit_price_analysisProjectPrice_outAcademy_value = edit_price_analysisProjectPrice_outAcademy_et.getText().toString(); //分析收费（院外）
        edit_price_analysisProjectPrice_withoutTax_value = edit_price_analysisProjectPrice_withoutTax_et.getText().toString(); //分析收费（不含税）
    }
}
