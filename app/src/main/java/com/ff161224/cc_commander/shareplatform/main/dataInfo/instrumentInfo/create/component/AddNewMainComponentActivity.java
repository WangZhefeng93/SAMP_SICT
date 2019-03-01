package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.component;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;

public class AddNewMainComponentActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_new_instrument_cancel_tv5;   //取消
    private TextView tittle_create_new_instrument_save_tv5; //保存
    private EditText create_new_instrument_componentName_et;    //配件名称
    private EditText create_new_instrument_componentType_et;    //配件型号
    private EditText create_new_instrument_componentFactory_et; //配件厂商
    private EditText create_new_instrument_componentMoney_et;   //人民币价值(元)
    private EditText create_new_instrument_componentARP_et; //资产标签号(ARP)
    private EditText create_new_instrument_componentNote_et;    //备注

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_add_new_main_component);
        //为控件变量绑定布局文件中的控件
        initWidget();
        //为取消按钮设置点击监听器
        setOnClickListenerForCancle5();
        //为保存按钮设置点击监听器
        setOnClickListenerForSave5();
    }

    /**
     * 为保存按钮设置点击监听器
     */
    private void setOnClickListenerForSave5() {
        tittle_create_new_instrument_save_tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(AddNewMainComponentActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要保存对仪器基本信息的修改吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(AddNewMainComponentActivity.this, "保存修改！", Toast.LENGTH_SHORT).show();
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
    private void setOnClickListenerForCancle5() {
        tittle_create_new_instrument_cancel_tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddNewMainComponentActivity.this).setTitle("系统提示")//设置对话框标题
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
        tittle_create_new_instrument_cancel_tv5 = (TextView) findViewById(R.id.tittle_create_new_instrument_cancel_tv5);   //取消
        tittle_create_new_instrument_save_tv5 = (TextView) findViewById(R.id.tittle_create_new_instrument_save_tv5); //保存
        create_new_instrument_componentName_et = (EditText) findViewById(R.id.create_new_instrument_componentName_et);    //配件名称
        create_new_instrument_componentType_et = (EditText) findViewById(R.id.create_new_instrument_componentType_et);    //配件型号
        create_new_instrument_componentFactory_et = (EditText) findViewById(R.id.create_new_instrument_componentFactory_et); //配件厂商
        create_new_instrument_componentMoney_et = (EditText) findViewById(R.id.create_new_instrument_componentMoney_et);   //人民币价值(元)
        create_new_instrument_componentARP_et = (EditText) findViewById(R.id.create_new_instrument_componentARP_et); //资产标签号(ARP)
        create_new_instrument_componentNote_et = (EditText) findViewById(R.id.create_new_instrument_componentNote_et);    //备注
    }
}
