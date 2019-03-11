package com.ff161224.cc_commander.shareplatform.main.dataInfo.coastInfo.edit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.coastInfo.detail.CoastInfoActivity;

public class EditCoastInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_edit_coast_tv;  //编辑耗材信息标题
    private TextView edit_coast_id_tv;  //耗材ID
    private EditText edit_coast_name_et;    //耗材名称
    private EditText edit_coast_unit_et;    //耗材单位
    private EditText edit_coast_num_et;     //耗材数量
    private EditText edit_coast_description_et; //耗材描述
    private TextView edit_coast_save_tv;    //保存修改按钮
    private TextView edit_coast_cancel_tv;  //取消返回按钮

    //定义其他变量
    private Intent intent = null;
    private String edit_coast_name_value = "";
    private String edit_coast_unit_value = "";
    private String edit_coast_num_value = "";
    private String edit_coast_description_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_edit_coast_info2);
        //初始化控件变量
        initWidget();
        //为取消返回按钮设置点击监听器
        setOnClickListenerForCancelTV();
        //为保存修改按钮设置点击监听器
        setOnCLickListenerForSaveTV();
    }

    /**
     * 初始化控件变量
     */
    private void initWidget() {
        tittle_edit_coast_tv = (TextView) findViewById(R.id.tittle_edit_coast_tv);  //编辑耗材信息标题
        edit_coast_id_tv = (TextView) findViewById(R.id.edit_coast_id_tv);  //耗材ID
        edit_coast_name_et = (EditText) findViewById(R.id.edit_coast_name_et);    //耗材名称
        edit_coast_unit_et = (EditText) findViewById(R.id.edit_coast_unit_et);    //耗材单位
        edit_coast_num_et = (EditText) findViewById(R.id.edit_coast_num_et);     //耗材数量
        edit_coast_description_et = (EditText) findViewById(R.id.edit_coast_description_et); //耗材描述
        edit_coast_save_tv = (TextView) findViewById(R.id.edit_coast_save_tv);    //保存修改按钮
        edit_coast_cancel_tv = (TextView) findViewById(R.id.edit_coast_cancel_tv);  //取消返回按钮
    }

    /**
     * 为取消返回按钮设置点击监听器
     */
    private void setOnClickListenerForCancelTV() {
        edit_coast_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(EditCoastInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消修改并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(EditCoastInfoActivity.this, CoastInfoActivity.class);
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
    private void setOnCLickListenerForSaveTV() {
        edit_coast_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_coast_name_value = edit_coast_name_et.getText().toString();
                edit_coast_unit_value = edit_coast_unit_et.getText().toString();
                edit_coast_num_value = edit_coast_num_et.getText().toString();
                edit_coast_description_value = edit_coast_description_et.getText().toString();
                Log.d("编辑仪器名称值：",edit_coast_name_value);
                Log.d("编辑仪器单位值：",edit_coast_unit_value);
                Log.d("编辑仪器数量值：",edit_coast_num_value);
                Log.d("编辑仪器描述值：",edit_coast_description_value);
                Toast.makeText(EditCoastInfoActivity.this, "修改耗材信息成功", Toast.LENGTH_SHORT).show();
                intent = new Intent(EditCoastInfoActivity.this,CoastInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
