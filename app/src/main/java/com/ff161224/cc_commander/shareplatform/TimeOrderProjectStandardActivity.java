package com.ff161224.cc_commander.shareplatform;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeOrderProjectStandardActivity extends AppCompatActivity {
    //定义属性
    public static TimeOrderProjectStandardActivity instance = null;
    private TextView tittle_tv;
    private TextView time_order_want_to_order_instrument_tv3;
    private TextView time_order_project_standard_next_tv;
    private ListView time_order_project_standard_listview;
    BaseAdapter baseAdapter;
    ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_project_standard);

        instance = this;

        //绑定标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置标题
        tittle_tv.setText("时间预约——检测项目及标准");
        tittle_tv.setGravity(Gravity.CENTER);

        //绑定想要预约的仪器
        time_order_want_to_order_instrument_tv3 = (TextView)findViewById(R.id.time_order_want_to_order_instrument_tv3);

        //设置想要预约的仪器名称
        time_order_want_to_order_instrument_tv3.setText("液相色谱质谱联用仪LTQ-OrbitrapXL");

        //绑定检测项目及标准下拉列表
        time_order_project_standard_listview = (ListView)findViewById(R.id.time_order_project_standard_listview);

        //开启子线程，获取检测项目及标准下拉列表数据
        data_list = getTimeOrderProjectStandaardData();

        //为检测项目及标准下拉列表创建适配器
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return data_list.size();
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
                if (position == 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);

                    //为适配器布局文件绑定控件
                    TextView text1 = (TextView)convertView.findViewById(android.R.id.text1);

                    //设置适配器控件的属性值
                    text1.setText("点击增加检测项目及标准");
                    text1.setGravity(Gravity.CENTER);
                }else {
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, null);

                    //为适配器布局文件绑定控件
                    TextView text1 = (TextView)convertView.findViewById(android.R.id.text1);
                    TextView text2 = (TextView)convertView.findViewById(android.R.id.text2);

                    //设置适配器控件的属性值
                    text1.setText("检测项目："+data_list.get(position).get("time_order_project_standard_project_name").toString());
                    text2.setText("前处理标准："+data_list.get(position).get("time_order_project_standard_pre_standard_name").toString()+"\n检测标准："+data_list.get(position).get("time_order_project_standard_standard_name").toString());
                    text2.setTextSize(16);
                }

                return convertView;
            }
        };

        //为检测项目及标准下拉列表分配适配器
        time_order_project_standard_listview.setAdapter(baseAdapter);

        //为检测项目及标准下拉列表设置点击监听事件
        time_order_project_standard_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Toast.makeText(TimeOrderProjectStandardActivity.this, "点击增加新的检测项目及标准的详细信息", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TimeOrderProjectStandardActivity.this, "点击查看该检测项目及标准的详细信息并可以编辑", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //为检测项目及标准下拉列表设置长按监听事件
        time_order_project_standard_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position > 0){
                    new AlertDialog.Builder(TimeOrderProjectStandardActivity.this).setTitle("系统提示")//设置对话框标题
                            .setMessage("您确定要删除"+data_list.get(position).get("time_order_project_standard_project_name").toString()+"吗？")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    // TODO Auto-generated method stub
                                    Toast.makeText(TimeOrderProjectStandardActivity.this, "删除"+data_list.get(position).get("time_order_project_standard_project_name").toString()+"成功！", Toast.LENGTH_SHORT).show();
                                    data_list.remove(position);
                                    baseAdapter.notifyDataSetChanged();
                                }
                            }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//响应事件
                            // TODO Auto-generated method stub
                            Log.i("alertdialog"," 请保存数据！");
                        }
                    }).show();//在按键响应事件中显示此对话框
                }
                return true;
            }
        });

        //绑定下一步按钮
        time_order_project_standard_next_tv = (TextView)findViewById(R.id.time_order_project_standard_next_tv);

        //为下一步按钮设置点击监听器
        time_order_project_standard_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TimeOrderProjectStandardActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要进行下一步吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(TimeOrderProjectStandardActivity.this,TimeOrderChooseCoastNumActivity.class);
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

    //获取检测项目及标准下拉列表数据
    private ArrayList getTimeOrderProjectStandaardData(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        HashMap<String,Object> map = new HashMap<>();
        map.put("time_order_project_standard_project_name","");
        map.put("time_order_project_standard_standard_name","");
        list.add(map);

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("time_order_project_standard_project_name","本次实验检测项目");
        map1.put("time_order_project_standard_pre_standard_name","本次实验前处理标准");
        map1.put("time_order_project_standard_standard_name","本次实验检测标准");
        list.add(map1);

        return list;
    }
}
