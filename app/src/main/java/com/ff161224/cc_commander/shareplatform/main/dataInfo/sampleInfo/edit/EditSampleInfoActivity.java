package com.ff161224.cc_commander.shareplatform.main.dataInfo.sampleInfo.edit;

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

import java.util.ArrayList;
import java.util.HashMap;

public class EditSampleInfoActivity extends AppCompatActivity {
    //定义空间变量
    private EditText edit_sample_name_et;   //样品分类名称
    private EditText edit_sample_note_et;   //备注
    private TextView edit_sample_save_tv;   //保存修改
    private TextView edit_sample_cancel_tv; //取消返回

    //定义其他变量
    private Intent intent = null;
    private HashMap<String,Object> sampleInfoMap = new HashMap();
    private String edit_sample_name_value = "";
    private String edit_sample_note_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_edit_sample_info);
        //初始化控件变量
        initWidget();
        //接收样品分类数据
        intent = getIntent();
        sampleInfoMap = (HashMap<String, Object>) intent.getSerializableExtra("sampleInfoMap");
        //为控件设置数据
        setDataForWidget();
        //为取消返回按钮设置点击监听器
        setOnClickListenerForCancelTV();
        //为保存修改按钮设置点击监听器
        setOnCLickListenerForSaveTV();
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
            new android.app.AlertDialog.Builder(EditSampleInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消修改并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(EditSampleInfoActivity.this, SampleInfoActivity.class);
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
        edit_sample_name_et = (EditText) findViewById(R.id.edit_sample_name_et);   //样品分类名称
        edit_sample_note_et = (EditText) findViewById(R.id.edit_sample_note_et);   //备注
        edit_sample_save_tv = (TextView) findViewById(R.id.edit_sample_save_tv);   //保存修改
        edit_sample_cancel_tv = (TextView) findViewById(R.id.edit_sample_cancel_tv); //取消返回
    }

    /**
     * 为控件设置数据
     */
    private void setDataForWidget() {
        edit_sample_name_et.setText(sampleInfoMap.get("sample_name").toString());   //样品分类名称
        edit_sample_note_et.setText(sampleInfoMap.get("sample_note").toString());   //备注
    }

    /**
     * 为取消返回按钮设置点击监听器
     */
    private void setOnClickListenerForCancelTV() {
        edit_sample_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(EditSampleInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消修改并返回吗？\n点击确定后您的修改将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(EditSampleInfoActivity.this, SampleInfoActivity.class);
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
        edit_sample_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_sample_name_value = edit_sample_name_et.getText().toString();
                edit_sample_note_value = edit_sample_note_et.getText().toString();
                sampleInfoMap.put("sample_name",edit_sample_name_value);
                sampleInfoMap.put("sample_note",edit_sample_note_value);
                Log.d("修改后的样品分类名称：",edit_sample_name_value);
                Log.d("修改后的样品分类备注：",edit_sample_note_value);
                Toast.makeText(EditSampleInfoActivity.this, "修改样品分类信息成功！", Toast.LENGTH_SHORT).show();
                intent = new Intent(EditSampleInfoActivity.this,SampleInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
