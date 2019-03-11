package com.ff161224.cc_commander.shareplatform.main.dataInfo.coastInfo.create;

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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.coastInfo.detail.CoastInfoActivity;

public class CreateCoastInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_coast_tv;  //新建耗材信息标题
    private TextView create_coast_id_tv;  //耗材ID
    private EditText create_coast_name_et;    //耗材名称
    private EditText create_coast_unit_et;    //耗材单位
    private EditText create_coast_num_et;     //耗材数量
    private EditText create_coast_description_et; //耗材描述
    private TextView create_coast_save_tv;    //保存修改按钮
    private TextView create_coast_cancel_tv;  //取消返回按钮

    //定义其他变量
    private Intent intent = null;
    private String create_coast_name_value = "";
    private String create_coast_unit_value = "";
    private String create_coast_num_value = "";
    private String create_coast_description_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_coast_info);
        //初始化控件变量
        initWidget();
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
            new android.app.AlertDialog.Builder(CreateCoastInfoActivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消新建并返回吗？\n点击确定后您新建的信息将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(CreateCoastInfoActivity.this, CoastInfoActivity.class);
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
        tittle_create_coast_tv = (TextView) findViewById(R.id.tittle_create_coast_tv);  //编辑耗材信息标题
        create_coast_name_et = (EditText) findViewById(R.id.create_coast_name_et);    //耗材名称
        create_coast_unit_et = (EditText) findViewById(R.id.create_coast_unit_et);    //耗材单位
        create_coast_num_et = (EditText) findViewById(R.id.create_coast_num_et);     //耗材数量
        create_coast_description_et = (EditText) findViewById(R.id.create_coast_description_et); //耗材描述
        create_coast_save_tv = (TextView) findViewById(R.id.create_coast_save_tv);    //保存修改按钮
        create_coast_cancel_tv = (TextView) findViewById(R.id.create_coast_cancel_tv);  //取消返回按钮
    }

    /**
     * 为取消返回按钮设置点击监听器
     */
    private void setOnClickListenerForCancelTV() {
        create_coast_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateCoastInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消创建并返回吗？\n点击确定后您创建的信息将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateCoastInfoActivity.this, CoastInfoActivity.class);
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
        create_coast_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_coast_name_value = create_coast_name_et.getText().toString();
                create_coast_unit_value = create_coast_unit_et.getText().toString();
                create_coast_num_value = create_coast_num_et.getText().toString();
                create_coast_description_value = create_coast_description_et.getText().toString();
                Log.d("新建仪器名称值：",create_coast_name_value);
                Log.d("新建仪器单位值：",create_coast_unit_value);
                Log.d("新建仪器数量值：",create_coast_num_value);
                Log.d("新建仪器描述值：",create_coast_description_value);
                Toast.makeText(CreateCoastInfoActivity.this, "修改耗材信息成功", Toast.LENGTH_SHORT).show();
                intent = new Intent(CreateCoastInfoActivity.this,CoastInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
