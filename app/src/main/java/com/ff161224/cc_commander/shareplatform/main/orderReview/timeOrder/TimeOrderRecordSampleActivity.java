package com.ff161224.cc_commander.shareplatform.main.orderReview.timeOrder;

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

import com.ff161224.cc_commander.shareplatform.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeOrderRecordSampleActivity extends AppCompatActivity {
    //定义属性
    public static TimeOrderRecordSampleActivity instance = null;
    private TextView tittle_tv;
    private TextView time_order_want_to_order_instrument_tv2;
    private TextView time_order_record_ssample_next_tv;
    private ListView time_order_record_sample_listview;
    BaseAdapter baseAdapter;
    ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_record_sample);

        instance = this;

        //绑定标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置标题
        tittle_tv.setText("时间预约——登记样品信息");
        tittle_tv.setGravity(Gravity.CENTER);

        //绑定想要预约的仪器
        time_order_want_to_order_instrument_tv2 = (TextView)findViewById(R.id.time_order_want_to_order_instrument_tv2);

        //设置想要预约的仪器名称
        time_order_want_to_order_instrument_tv2.setText("液相色谱质谱联用仪LTQ-OrbitrapXL");

        //绑定样品登记记录下拉列表
        time_order_record_sample_listview = (ListView)findViewById(R.id.time_order_record_sample_listview);

        //开启子线程，获取样品登记记录
        data_list = getTimeOrderRecordSampleData();

        //为样品登记记录下拉列表创建适配器
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
                    text1.setText("点击增加样品登记记录");
                    text1.setGravity(Gravity.CENTER);
                }else {
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, null);

                    //为适配器布局文件绑定控件
                    TextView text1 = (TextView)convertView.findViewById(android.R.id.text1);
                    TextView text2 = (TextView)convertView.findViewById(android.R.id.text2);

                    //设置适配器控件的属性值
                    text1.setText("样品登记名："+data_list.get(position).get("time_order_record_sample_name").toString());
                    text2.setText("样品数量："+data_list.get(position).get("time_order_record_sample_num").toString());
                }

                return convertView;
            }

        };

        //为样品登记记录下拉列表分配适配器
        time_order_record_sample_listview.setAdapter(baseAdapter);

        //为样品登记记录下拉列表设置点击监听器
        time_order_record_sample_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){     //点击增加样品登记
                    Toast.makeText(TimeOrderRecordSampleActivity.this, "点击增加样品登记", Toast.LENGTH_SHORT).show();
                }else {     //点击查看已有样品登记记录
                    Toast.makeText(TimeOrderRecordSampleActivity.this, "点击查看已有样品登记记录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(TimeOrderRecordSampleActivity.this,TimeOrderSampleDetailInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

        //为样品登记记录下拉列表设置长按监听器，删除已有的样品登记记录
        time_order_record_sample_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position > 0){
                    new AlertDialog.Builder(TimeOrderRecordSampleActivity.this).setTitle("系统提示")//设置对话框标题
                            .setMessage("您确定要删除"+data_list.get(position).get("time_order_record_sample_name").toString()+"吗？")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    // TODO Auto-generated method stub
                                    Toast.makeText(TimeOrderRecordSampleActivity.this, "删除"+data_list.get(position).get("time_order_record_sample_name").toString()+"成功！", Toast.LENGTH_SHORT).show();
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
        time_order_record_ssample_next_tv = (TextView)findViewById(R.id.time_order_record_ssample_next_tv);

        //为下一步按钮绑定点击监听器
        time_order_record_ssample_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TimeOrderRecordSampleActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要进行下一步吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(TimeOrderRecordSampleActivity.this,TimeOrderProjectStandardActivity.class);
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

    //获取样品登记记录
    private ArrayList getTimeOrderRecordSampleData(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        HashMap<String,Object> map = new HashMap<>();
        map.put("time_order_record_sample_name","");
        map.put("time_order_record_sample_num",0);
        list.add(map);

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("time_order_record_sample_name","样品登记记录1");
        map1.put("time_order_record_sample_num",5);
        list.add(map1);

        return list;
    }
}
