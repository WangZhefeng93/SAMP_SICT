package com.ff161224.cc_commander.shareplatform.main.dataInfo.projectInfo.create;

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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.projectInfo.detail.ProjectInfoActivity;

public class CreateProjectInfoAvtivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_project_tv;    //标题
    private EditText create_project_name_et;  //分析项目名称
    private Spinner create_sample_type_sp;    //样品分类下拉菜单
    private EditText create_project_description_et;   //描述
    private Spinner create_unit_name_sp;  //所属单位下拉菜单
    private TextView create_project_save_tv;  //完成创建按钮
    private TextView create_project_cancel_tv;    //取消返回按钮

    //定义其他变量
    private Intent intent = null;
    private String[] sample_classification_array = null;    //样品分类可选数据数组
    private String[] unit_name_array = null;    //所属单位可选数据数组
    private ArrayAdapter<String> sample_classification_adapter = null;  //样品分类下拉菜单适配器
    private ArrayAdapter<String> unit_name_adapter = null;  //所属单位下拉菜单适配器
    private int sample_classification_position = -1;    //样品分类选中下标
    private int unit_name_position = -1;    //所属单位选中下标
    private String sample_classification_value = "";    //样品分类选中值
    private String unit_name_value = "";        //所属单位选中值
    private String project_name_value = "";      //分析项目值
    private String project_description_value = "";      //描述值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_project_info_avtivity);
        //初始化控件变量
        initWidget();
        //为两个下拉菜单设置数据
        setDataForSpinner();
        //为两个下拉菜单设置选中监听器
        setSelectedListenerForSpinner();
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
            new android.app.AlertDialog.Builder(CreateProjectInfoAvtivity.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("您确定要取消新建并返回吗？\n点击确定后您新建的信息将无法保存！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(CreateProjectInfoAvtivity.this, ProjectInfoActivity.class);
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
        tittle_create_project_tv = (TextView) findViewById(R.id.tittle_create_project_tv);    //标题
        create_project_name_et = (EditText) findViewById(R.id.create_project_name_et);  //分析项目名称
        create_sample_type_sp = (Spinner) findViewById(R.id.create_sample_type_sp);    //样品分类下拉菜单
        create_project_description_et = (EditText) findViewById(R.id.create_project_description_et);   //描述
        create_unit_name_sp = (Spinner) findViewById(R.id.create_unit_name_sp);  //所属单位下拉菜单
        create_project_save_tv = (TextView) findViewById(R.id.create_project_save_tv);  //完成创建按钮
        create_project_cancel_tv = (TextView) findViewById(R.id.create_project_cancel_tv);    //取消返回按钮
    }

    /**
     * 为两个下拉菜单设置数据
     */
    private void setDataForSpinner() {
        //获取样品分类下拉菜单数据
        sample_classification_array = getSampleClassificationArray();
        //获取所属单位下拉菜单数据
        unit_name_array = getUnitNameArray();
        //创建样品分类下拉菜单适配器
        sample_classification_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sample_classification_array);
        //创建所属单位下拉菜单适配器
        unit_name_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unit_name_array);
        //为样品分类下拉菜单设置适配器
        create_sample_type_sp.setAdapter(sample_classification_adapter);
        //为所属单位下拉菜单设置适配器
        create_unit_name_sp.setAdapter(unit_name_adapter);
    }

    /**
     * 获取样品分类下拉菜单数据
     * @return
     */
    public String[] getSampleClassificationArray() {
        String[] array = {"薄片","玻璃管","大化石","粉末","固态或粉末","固体",
                "硅质或含硅质岩石","化石或现生植物动物标本","灰岩、碳酸盐","离心管保存",
                "硫化银、硫化钡及其他硫质物","凝胶","切片、连续切片","图像后期处理","微体化石和小型化石",
                "小型-微体化石","岩石薄片制备","液态"};
        return array;
    }

    /**
     * 获取所属单位下拉菜单数据
     * @return
     */
    public String[] getUnitNameArray() {
        String[] array = {"南京地质古生物研究所"};
        return array;
    }

    /**
     * 为两个下拉菜单设置选中监听器
     */
    private void setSelectedListenerForSpinner() {
        create_sample_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sample_classification_position = position;
                sample_classification_value = sample_classification_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        create_unit_name_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit_name_position = position;
                unit_name_value = unit_name_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 为取消返回按钮设置点击监听器
     */
    private void setOnClickListenerForCancelTV() {
        create_project_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateProjectInfoAvtivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要取消创建并返回吗？\n点击确定后您创建的信息将无法保存！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateProjectInfoAvtivity.this, ProjectInfoActivity.class);
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
        create_project_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project_name_value = create_project_name_et.getText().toString();
                project_description_value = create_project_description_et.getText().toString();
                Log.d("保存创建的分析项目",project_name_value+"-"+sample_classification_value+"-"+unit_name_value+"-"+project_description_value);
                Toast.makeText(CreateProjectInfoAvtivity.this, "创建分析项目成功！", Toast.LENGTH_SHORT).show();
                intent = new Intent(CreateProjectInfoAvtivity.this,ProjectInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
