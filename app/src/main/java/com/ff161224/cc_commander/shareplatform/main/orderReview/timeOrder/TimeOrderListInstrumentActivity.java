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

import com.ff161224.cc_commander.shareplatform.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeOrderListInstrumentActivity extends AppCompatActivity {
    //定义属性
    private TextView tittle_tv;
    private ListView time_order_choose_instrument_listview;
    BaseAdapter baseAdapter;
    ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题栏
        getSupportActionBar().hide();

        //加载布局文件
        setContentView(R.layout.activity_time_order_list_instrument);

        //绑定标题
        tittle_tv = (TextView)findViewById(R.id.tittle_tv);

        //设置标题
        tittle_tv.setText("时间预约——选择预约仪器");

        //绑定仪器预约信息下拉列表
        time_order_choose_instrument_listview = (ListView)findViewById(R.id.time_order_choose_instrument_listview);

        //开启子线程，获取仪器信息列表数据
        data_list = getTimeOrderInstrumentInfo();

        //为仪器预约信息下拉列表创建适配器
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
                    convertView = getLayoutInflater().inflate(R.layout.item_time_order_instrument_info, null);

                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.time_order_instrument_no_tv = (TextView)convertView.findViewById(R.id.time_order_instrument_no_tv);
                    viewHolder.time_order_instrument_name_tv = (TextView)convertView.findViewById(R.id.time_order_instrument_name_tv);
                    viewHolder.time_order_max_plan_num_tv = (TextView)convertView.findViewById(R.id.time_order_max_plan_num_tv);
                    viewHolder.time_order_max_sample_num_tv = (TextView)convertView.findViewById(R.id.time_order_max_sample_num_tv);
                    viewHolder.time_order_support_auto_order_tv = (TextView)convertView.findViewById(R.id.time_order_support_auto_order_tv);

                    //设置适配器控件的属性值
                    viewHolder.time_order_instrument_no_tv.setText((position+1)+"、");
                    viewHolder.time_order_instrument_name_tv.setText("仪器名称："+data_list.get(position).get("instrument_name").toString());
                    viewHolder.time_order_max_plan_num_tv.setText("最大样品估计量："+data_list.get(position).get("max_plan_num").toString());
                    viewHolder.time_order_max_sample_num_tv.setText("最大样品数："+data_list.get(position).get("max_sample_num").toString());
                    if (Integer.valueOf(data_list.get(position).get("support_auto_order").toString()) == 1){
                        viewHolder.time_order_support_auto_order_tv.setText("是否支持自动预约：支持");
                    }else {
                        viewHolder.time_order_support_auto_order_tv.setText("是否支持自动预约：不支持");
                    }
                }else {
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);

                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.text1 = (TextView)convertView.findViewById(android.R.id.text1);

                    //设置适配器控件的属性值
                    viewHolder.text1.setText("暂无任何可以时间预约的仪器");
                    viewHolder.text1.setGravity(Gravity.CENTER);
                }
                return convertView;
            }

            class ViewHolder {
                protected TextView text1,time_order_instrument_no_tv,time_order_instrument_name_tv,time_order_max_plan_num_tv,time_order_max_sample_num_tv,time_order_support_auto_order_tv;
            }
        };

        //将仪器预约信息下拉列表与适配器绑定
        time_order_choose_instrument_listview.setAdapter(baseAdapter);

        if (data_list.size() > 0){
            //为仪器预约信息下拉列表设置项目点击监听事件
            time_order_choose_instrument_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new AlertDialog.Builder(TimeOrderListInstrumentActivity.this).setTitle("系统提示")//设置对话框标题
                            .setMessage("您确定要预约"+data_list.get(position).get("instrument_name").toString()+"吗？")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    // TODO Auto-generated method stub
                                    intent = new Intent(TimeOrderListInstrumentActivity.this,TimeOrderChooseTimeActivity.class);
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
    }

    //获取仪器信息列表数据
    private ArrayList getTimeOrderInstrumentInfo(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("instrument_name","透射电子显微镜");
        map1.put("max_plan_num",50);
        map1.put("max_sample_num",3333);
        map1.put("support_auto_order",1);
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("instrument_name","液相色谱质谱联用仪LTQ-OrbitrapXL");
        map2.put("max_plan_num",50);
        map2.put("max_sample_num",3333);
        map2.put("support_auto_order",0);
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("instrument_name","超高效液相色谱仪UPLC-PDA-1");
        map3.put("max_plan_num",50);
        map3.put("max_sample_num",3333);
        map3.put("support_auto_order",0);
        list.add(map3);


        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("instrument_name","扫描电子显微镜");
        map4.put("max_plan_num",50);
        map4.put("max_sample_num",3333);
        map4.put("support_auto_order",0);
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("instrument_name","气质联用仪ThermoISQ");
        map5.put("max_plan_num",50);
        map5.put("max_sample_num",3333);
        map5.put("support_auto_order",1);
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("instrument_name","液相色谱质谱联用仪5500Q");
        map6.put("max_plan_num",50);
        map6.put("max_sample_num",3333);
        map6.put("support_auto_order",0);
        list.add(map3);

        return list;
    }
}
