package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.environment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.InstrumentInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.component.CreateNewInstrumentActivity5;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.environment.DetailEnvironmentArgumentInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateNewInstrumentActivity8 extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_new_instrument_cancel_tv88;  //取消新建仪器按钮
    private TextView create_new_instrument_argument_tv;   //点击添加环境参数
    private ListView create_new_instrument_argument_listview; //环境参数下拉列表
    private TextView create_new_instrument_pre_tv7; //上一步按钮
    private TextView create_new_instrument_commit_tv;    //提交按钮

    //定义其他变量
    private Intent intent;
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_new_instrument8);
        //为控件变量绑定布局文件中的控件
        initWidget();
        //开启子线程，获取下拉列表数据
        data_list = getEnvironmentArgumentData();
        //为下拉列表控件设置适配器
        createMyBaseAdapter();
        //为主要配件ListView分配适配器
        create_new_instrument_argument_listview.setAdapter(baseAdapter);
        //为添加主要配件信息按钮设置点击监听器
        setOnClickListenerForAddCalibrationRecord();
        //为已添加好的主要配件项目设置点击监听器
        setOnclickListenerForCalibrationRecordItem();
        //为提交按钮设置点击监听器
        setOnclickListenerForCommit();
        //为上一步按钮设置点击监听器
        setOnclickListenerForPre7();
        //为取消新建仪器按钮设置点击监听器
        setOnClickListenerForCancel88();
    }

    /**
     * 为取消新建仪器按钮设置点击监听器
     */
    private void setOnClickListenerForCancel88() {
        tittle_create_new_instrument_cancel_tv88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.support.v7.app.AlertDialog.Builder(CreateNewInstrumentActivity8.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("点击取消，您的修改将无法保存！直接退出新建仪器流程！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateNewInstrumentActivity8.this,InstrumentInfoActivity.class);
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
     * 为上一步按钮设置点击监听器
     */
    private void setOnclickListenerForPre7() {
        create_new_instrument_pre_tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity8.this,CreateNewInstrumentActivity5.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为提交按钮设置点击监听器
     */
    private void setOnclickListenerForCommit() {
        create_new_instrument_commit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateNewInstrumentActivity8.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要创建该仪器吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(CreateNewInstrumentActivity8.this, "提交创建的仪器信息", Toast.LENGTH_SHORT).show();
                                intent = new Intent(CreateNewInstrumentActivity8.this,InstrumentInfoActivity.class);
                                startActivity(intent);
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
     * 为已添加好的主要配件项目设置点击监听器
     */
    private void setOnclickListenerForCalibrationRecordItem() {
        if (data_list.size() > 0){
            //为主要零件ListView设置点击监听事件，点击查看主要零件全部详细信息
            create_new_instrument_argument_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent = new Intent(CreateNewInstrumentActivity8.this, DetailEnvironmentArgumentInfo.class);
                    startActivity(intent);
                }
            });

            //为随机文档ListView设置长按监听事件，删除随机文档
            create_new_instrument_argument_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(CreateNewInstrumentActivity8.this).setTitle("系统提示")//设置对话框标题
                            .setMessage("您确定要删除"+data_list.get(position).get("environment_argument_name").toString()+"吗？")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    // TODO Auto-generated method stub
                                    data_list.remove(position);
                                    baseAdapter.notifyDataSetChanged();
                                    //getActivity().finish();
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
            });

        }
    }

    /**
     * 为添加主要配件信息按钮设置点击监听器
     */
    private void setOnClickListenerForAddCalibrationRecord() {
        create_new_instrument_argument_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity8.this,AddEnvironmentArgumentActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 为下拉列表控件设置适配器
     */
    private void createMyBaseAdapter() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (data_list.size() > 0){
                    return data_list.size();
                }else {
                    return 1;
                }
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (data_list.size() > 0){      //说明有数据
                    //加载ListView中的每一项布局文件
                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_instrument_detail_environment_argument, null);

                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.environment_argument_id_tv = (TextView)convertView.findViewById(R.id.environment_argument_id_tv);
                    viewHolder.environment_argument_name_tv = (TextView)convertView.findViewById(R.id.environment_argument_name_tv);
                    viewHolder.environment_argument_unit_tv = (TextView)convertView.findViewById(R.id.environment_argument_unit_tv);
                    //viewHolder.project_standard_start_tv = (TextView)convertView.findViewById(R.id.project_standard_start_tv);

                    //设置适配器控件的属性值
                    viewHolder.environment_argument_id_tv.setText((position+1)+"、");
                    viewHolder.environment_argument_name_tv.setText("环境参数名称："+data_list.get(position).get("environment_argument_name").toString());
                    viewHolder.environment_argument_unit_tv.setText("环境参数单位："+data_list.get(position).get("environment_argument_unit").toString());
                    //viewHolder.project_standard_start_tv.setText("是否启用标准："+data_list.get(position).get("project_standard_start").toString());

                }else {     //说明没有数据
                    //加载ListView中的每一项布局文件
                    convertView = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_1, null);

                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.text1 = (TextView)convertView.findViewById(android.R.id.text1);

                    //设置适配器控件的属性值
                    viewHolder.text1.setText("暂无任何记录");
                    viewHolder.text1.setGravity(Gravity.CENTER);
                }

                return convertView;
            }

            class ViewHolder {
                protected TextView text1,
                        environment_argument_id_tv,
                        environment_argument_name_tv,
                        environment_argument_unit_tv;
            }
        };
    }

    /**
     * 开启子线程，获取下拉列表数据
     * @return
     */
    private ArrayList getEnvironmentArgumentData() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("environment_argument_name","工作温度");
        map.put("environment_argument_unit","摄氏度");
        //map.put("project_standard_start","是");
        list.add(map);
        return list;
    }

    /**
     * 为控件变量绑定布局文件中的控件
     */
    private void initWidget() {
        tittle_create_new_instrument_cancel_tv88 = (TextView) findViewById(R.id.tittle_create_new_instrument_cancel_tv88);  //取消新建仪器按钮
        create_new_instrument_argument_tv = (TextView) findViewById(R.id.create_new_instrument_argument_tv);   //点击添加环境参数
        create_new_instrument_argument_listview = (ListView) findViewById(R.id.create_new_instrument_argument_listview); //环境参数下拉列表
        create_new_instrument_pre_tv7 = (TextView) findViewById(R.id.create_new_instrument_pre_tv7);    //上一步按钮
        create_new_instrument_commit_tv = (TextView) findViewById(R.id.create_new_instrument_commit_tv);    //下一步按钮
    }
}
