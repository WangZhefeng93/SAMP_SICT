package com.ff161224.cc_commander.shareplatform.main.dataInfo.sampleInfo.create;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.sampleInfo.detail.SampleInfoActivity;

import java.util.HashMap;

public class CreateSampleInfoActivity extends AppCompatActivity {
    //定义空间变量
    private EditText create_sample_name_et;   //样品分类名称
    private EditText create_sample_note_et;   //备注
    private TextView create_sample_save_tv;   //保存修改
    private TextView create_sample_cancel_tv; //取消返回

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> sampleInfoMap = new HashMap();
    private String create_sample_name_value = "";
    private String create_sample_note_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_sample_info);
        //初始化控件变量
        initWidget();
        //为取消返回按钮设置点击监听器
        setOnClickListenerForCancelTV();
        //为保存修改按钮设置点击监听器
        setOnCLickListenerForSaveTV();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            new android.app.AlertDialog.Builder(CreateSampleInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消创建样品分类并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(CreateSampleInfoActivity.this, SampleInfoActivity.class);
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
        create_sample_name_et = (EditText) findViewById(R.id.create_sample_name_et);   //样品分类名称
        create_sample_note_et = (EditText) findViewById(R.id.create_sample_note_et);   //备注
        create_sample_save_tv = (TextView) findViewById(R.id.create_sample_save_tv);   //保存修改
        create_sample_cancel_tv = (TextView) findViewById(R.id.create_sample_cancel_tv); //取消返回
    }

    /**
     * 为取消返回按钮设置点击监听器
     */
    private void setOnClickListenerForCancelTV() {
        create_sample_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateSampleInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消创建样品分类并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateSampleInfoActivity.this, SampleInfoActivity.class);
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
        create_sample_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_sample_name_value = create_sample_name_et.getText().toString();
                create_sample_note_value = create_sample_note_et.getText().toString();
                sampleInfoMap.put("sample_name",create_sample_name_value);
                sampleInfoMap.put("sample_note",create_sample_note_value);
                Log.d("修改后的样品分类名称：",create_sample_name_value);
                Log.d("修改后的样品分类备注：",create_sample_note_value);
                Toast.makeText(CreateSampleInfoActivity.this, "创建样品分类信息成功！", Toast.LENGTH_SHORT).show();
                intent = new Intent(CreateSampleInfoActivity.this,SampleInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
