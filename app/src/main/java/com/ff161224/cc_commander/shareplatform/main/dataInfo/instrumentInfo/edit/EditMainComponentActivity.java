package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.edit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;

public class EditMainComponentActivity extends AppCompatActivity {
    //定义属性
    private TextView tittle_edit_main_component_canncle_tv,tittle_edit_main_component_save_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_edit_main_component);

        //绑定取消按钮
        tittle_edit_main_component_canncle_tv = (TextView)findViewById(R.id.tittle_edit_main_component_canncle_tv);

        //为取消按钮设置点击监听器
        tittle_edit_main_component_canncle_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditMainComponentActivity.this).setTitle("系统提示")//设置对话框标题
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

        //绑定保存按钮
        tittle_edit_main_component_save_tv = (TextView)findViewById(R.id.tittle_edit_main_component_save_tv);

        //为保存按钮设置点击监听器
        tittle_edit_main_component_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditMainComponentActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要保存对主要零件信息的修改吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(EditMainComponentActivity.this, "保存修改！", Toast.LENGTH_SHORT).show();
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
}
