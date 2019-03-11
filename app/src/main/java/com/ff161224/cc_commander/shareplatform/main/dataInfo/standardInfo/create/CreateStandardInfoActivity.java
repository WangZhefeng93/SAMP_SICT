package com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.create;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.detail.StandardInfoActivity;

import java.io.Serializable;
import java.util.HashMap;

public class CreateStandardInfoActivity extends AppCompatActivity {
    //定义控件变量
    private EditText standard_no_et;    //标准号
    private EditText standard_name_et;  //标准名称
    private Spinner standard_type_sp;   //标准类型
    private Spinner standard_classification_sp; //标准类别
    private EditText standard_introduction_et;  //标准简介
    private TextView save_standard_info_tv; //完成创建
    private TextView cancel_standard_info_tv;   //取消返回

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> standardInfoMap = null;
    private String[] standard_type_array = null;
    private String[] standard_classification_array = null;
    private ArrayAdapter<String> standard_type_adapter = null;
    private ArrayAdapter<String> standard_classification_adapter = null;
    private String standard_no_value = "";
    private String standard_name_value = "";
    private String standard_type_value = "";
    private int standard_type_position = -1;
    private String  standard_classification_value = "";
    private int standard_classification_position = -1;
    private String standard_introduction_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_standard_info);
        //初始化控件变量
        initWidget();
        //为下拉菜单设置数据
        setDataForSpinner();
        //为两个下拉菜单设置监听器
        setListenerForSpinner();
        //为取消返回按钮设置点击监听器
        setClickListenerForCancelTV();
        //为完成创建按钮设置点击监听器
        setClickListenerForSaveTV();
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
            new android.app.AlertDialog.Builder(CreateStandardInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消新建该标准吗？\n点击确定后将返回，数据无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(CreateStandardInfoActivity.this, StandardInfoActivity.class);
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
        standard_no_et = (EditText) findViewById(R.id.standard_no_et);    //标准号
        standard_name_et = (EditText) findViewById(R.id.standard_name_et);  //标准名称
        standard_type_sp = (Spinner) findViewById(R.id.standard_type_sp);   //标准类型
        standard_classification_sp = (Spinner) findViewById(R.id.standard_classification_sp); //标准类别
        standard_introduction_et = (EditText) findViewById(R.id.standard_introduction_et);  //标准简介
        save_standard_info_tv = (TextView) findViewById(R.id.save_standard_info_tv); //保存修改
        cancel_standard_info_tv = (TextView) findViewById(R.id.cancel_standard_info_tv);   //取消返回
    }

    /**
     * 为下拉菜单设置数据
     */
    private void setDataForSpinner() {
        standard_type_array = getStandardTypeArray();
        standard_classification_array = getStandardClassificationArray();
        standard_type_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,standard_type_array);
        standard_classification_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,standard_classification_array);
        standard_type_sp.setAdapter(standard_type_adapter);
        standard_classification_sp.setAdapter(standard_classification_adapter);
    }

    /**
     * 获取标准类型数据数组
     * @return
     */
    public String[] getStandardTypeArray() {
        return new String[] {"--请选择--","前处理标准","分析项目标准"};
    }

    /**
     * 获取标准类别数据数组
     * @return
     */
    public String[] getStandardClassificationArray() {
        return new String[] {"--请选择--","所标","院标","国标"};
    }

    /**
     * 为两个下拉菜单设置监听器
     */
    private void setListenerForSpinner() {
        standard_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                standard_type_position = position;
                standard_type_value = standard_type_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        standard_classification_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                standard_classification_position = position;
                standard_classification_value = standard_classification_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 为取消返回按钮设置点击监听器
     */
    private void setClickListenerForCancelTV() {
        cancel_standard_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateStandardInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消新建该标准吗？\n点击确定后将返回，数据无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateStandardInfoActivity.this, StandardInfoActivity.class);
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
     * 为完成创建按钮设置点击监听器
     */
    private void setClickListenerForSaveTV() {
        save_standard_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standard_no_value = standard_no_et.getText().toString();
                standard_name_value = standard_name_et.getText().toString();
                standard_introduction_value = standard_introduction_et.getText().toString();
                Log.d("新建标准号：",standard_no_value);
                Log.d("新建标准名称：",standard_name_value);
                Log.d("新建标准类型：",standard_type_value);
                Log.d("新建标准类别：",standard_classification_value);
                Log.d("新建标准简介：",standard_introduction_value);
                intent = new Intent(CreateStandardInfoActivity.this,StandardInfoActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(CreateStandardInfoActivity.this, "创建标准信息成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
