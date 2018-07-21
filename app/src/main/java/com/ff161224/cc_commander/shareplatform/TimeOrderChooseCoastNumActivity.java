package com.ff161224.cc_commander.shareplatform;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeOrderChooseCoastNumActivity extends AppCompatActivity {
    //定义属性
    public static TimeOrderChooseCoastNumActivity instance = null;
    private TextView tittle_tv;
    private TextView time_order_want_to_order_instrument_tv4;
    private TextView time_order_choose_coast_num_next_tv;
    private ListView time_order_choose_coast_num_listview;
    BaseAdapter baseAdapter;
    ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_choose_coast_num);

        instance = this;

        //绑定标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置标题
        tittle_tv.setText("时间预约——订购耗材");
        tittle_tv.setGravity(Gravity.CENTER);

        //绑定想要预约的仪器
        time_order_want_to_order_instrument_tv4 = (TextView)findViewById(R.id.time_order_want_to_order_instrument_tv4);

        //设置想要预约的仪器名称
        time_order_want_to_order_instrument_tv4.setText("液相色谱质谱联用仪LTQ-OrbitrapXL");

        //绑定订购耗材下拉列表
        time_order_choose_coast_num_listview = (ListView)findViewById(R.id.time_order_choose_coast_num_listview);

        //开启子线程，获取订购耗材下拉列表数据
        data_list = getTimeOrderChooseCoastNumData();

        //为订购耗材下拉列表创建适配器
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
                if (data_list.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_time_order_choose_coast_num, null);

                    //创建ViewHolder对象
                    ViewHolder viewHolder = new ViewHolder();

                    //绑定控件
                    viewHolder.time_order_choose_coast_num_no_tv = (TextView)convertView.findViewById(R.id.time_order_choose_coast_num_no_tv);
                    viewHolder.time_order_choose_coast_num_coast_name_tv = (TextView)convertView.findViewById(R.id.time_order_choose_coast_num_coast_name_tv);
                    viewHolder.time_order_choose_coast_num_coast_weight_tv = (TextView)convertView.findViewById(R.id.time_order_choose_coast_num_coast_weight_tv);
                    viewHolder.time_order_choose_coast_num_coast_total_num_tv = (TextView)convertView.findViewById(R.id.time_order_choose_coast_num_coast_total_num_tv);
                    viewHolder.time_order_choose_coast_num_coast_per_price_tv = (TextView)convertView.findViewById(R.id.time_order_choose_coast_num_coast_per_price_tv);
                    viewHolder.time_order_choose_coast_num_coast_minus_tv = (TextView)convertView.findViewById(R.id.time_order_choose_coast_num_coast_minus_tv);
                    viewHolder.time_order_choose_coast_num_coast_plus_tv = (TextView)convertView.findViewById(R.id.time_order_choose_coast_num_coast_plus_tv);
                    viewHolder.time_order_choose_coast_num_want_num_edt = (EditText)convertView.findViewById(R.id.time_order_choose_coast_num_want_num_edt);

                    //设置控件的值
                    viewHolder.time_order_choose_coast_num_no_tv.setText((position+1)+"、");
                    viewHolder.time_order_choose_coast_num_coast_name_tv.setText("耗材名称："+data_list.get(position).get("caost_name").toString());
                    viewHolder.time_order_choose_coast_num_coast_weight_tv.setText("耗材单位："+data_list.get(position).get("coast_weight").toString());
                    viewHolder.time_order_choose_coast_num_coast_total_num_tv.setText("耗材数量："+data_list.get(position).get("coast_total_num").toString());
                    viewHolder.time_order_choose_coast_num_coast_per_price_tv.setText("耗材单价："+data_list.get(position).get("coast_pre_price").toString());

                }else {
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);

                    //为适配器布局文件绑定控件
                    TextView text1 = (TextView)convertView.findViewById(android.R.id.text1);

                    //设置适配器控件的属性值
                    text1.setText("暂无任何耗材");
                    text1.setGravity(Gravity.CENTER);
                }
                return convertView;
            }

            class ViewHolder {
                protected TextView time_order_choose_coast_num_no_tv;
                protected TextView time_order_choose_coast_num_coast_name_tv,time_order_choose_coast_num_coast_weight_tv,time_order_choose_coast_num_coast_total_num_tv,time_order_choose_coast_num_coast_per_price_tv;
                protected TextView time_order_choose_coast_num_coast_minus_tv,time_order_choose_coast_num_coast_plus_tv;
                protected EditText time_order_choose_coast_num_want_num_edt;

            }
        };

        //为订购耗材下拉列表绑定适配器
        time_order_choose_coast_num_listview.setAdapter(baseAdapter);

        //为订购耗材下拉列表设置点击监听事件

        //为订购耗材下拉列表设置长按监听事件

        //绑定下一步按钮
        time_order_choose_coast_num_next_tv = (TextView)findViewById(R.id.time_order_choose_coast_num_next_tv);

        //为下一步按钮设置点击监听器
        time_order_choose_coast_num_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TimeOrderChooseCoastNumActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要进行下一步吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(TimeOrderChooseCoastNumActivity.this,TimeOrderShowMoneyActivity.class);
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

    //获取订购耗材下拉列表数据
    private ArrayList getTimeOrderChooseCoastNumData(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        /*HashMap<String,Object> map = new HashMap<>();
        map.put("caost_name","");
        map.put("coast_weight","");
        map.put("coast_total_num",0);
        map.put("coast_pre_price",0.00);
        list.add(map);*/

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("caost_name","玻璃刀");
        map1.put("coast_weight","个");
        map1.put("coast_total_num",10);
        map1.put("coast_pre_price",20.00);
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("caost_name","SSA sensor");
        map2.put("coast_weight","根");
        map2.put("coast_total_num",1000);
        map2.put("coast_pre_price",5.00);
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("caost_name","玻璃管");
        map3.put("coast_weight","个");
        map3.put("coast_total_num",1000);
        map3.put("coast_pre_price",5.00);
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("caost_name","滤器/管子/耗材");
        map4.put("coast_weight","个");
        map4.put("coast_total_num",1000);
        map4.put("coast_pre_price",5.00);
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("caost_name","his抗体");
        map5.put("coast_weight","次");
        map5.put("coast_total_num",1000);
        map5.put("coast_pre_price",5.00);
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("caost_name","人源抗体");
        map6.put("coast_weight","次");
        map6.put("coast_total_num",1000);
        map6.put("coast_pre_price",5.00);
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("caost_name","小鼠抗体");
        map7.put("coast_weight","次");
        map7.put("coast_total_num",1000);
        map7.put("coast_pre_price",5.00);
        list.add(map7);

        return list;
    }
}
