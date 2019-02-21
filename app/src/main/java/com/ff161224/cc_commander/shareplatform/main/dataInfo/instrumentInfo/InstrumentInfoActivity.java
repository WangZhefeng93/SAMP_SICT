package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.InstrumentDetailInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class InstrumentInfoActivity extends AppCompatActivity {

    //定义属性
    private TextView tittle_name_tv,tittle_choose_tv,tittle_create_instrument_tv;
    private TextView instrument_canncle_search_tv,instrument_reset_search_tv,instrument_ok_search_tv;
    private ListView instrument_basic_info_listview;
    private EditText instrument_search_name_edt;
    private DrawerLayout drawerLayout;
    private Spinner instrument_search_order_model_sp,instrument_search_way_model_sp;
    BaseAdapter baseAdapter;
    private ArrayList<HashMap<String,Object>> instrument_basic_info_list = new ArrayList<>();

    String instrument_search_name_input;    //用户输入的仪器名称筛选内容
    String instrument_search_order_model_input;     //用户选择的预约模式筛选内容
    int instrument_search_order_model_input_positon;    //用户选择的预约模式筛选内容的数组下标
    String instrument_search_order_way_input;     //用户选择的预约方式筛选内容
    int instrument_search_order_way_input_positon;    //用户选择的预约方式筛选内容的数组下标

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局文件
        setContentView(R.layout.activity_instrument_info);

        //去掉标题栏
        getSupportActionBar().hide();

        //绑定标题TextView控件
        tittle_name_tv = (TextView)findViewById(R.id.tittle_name_tv);

        //设置标题
        tittle_name_tv.setText("仪器信息");

        //绑定新建TextView控件
        tittle_create_instrument_tv = (TextView)findViewById(R.id.tittle_create_instrument_tv);

        //为新建TextView设置点击监听器
        tittle_create_instrument_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InstrumentInfoActivity.this, "开始新建仪器信息", Toast.LENGTH_SHORT).show();
            }
        });

        //绑定筛选TextView控件
        tittle_choose_tv = (TextView)findViewById(R.id.tittle_choose_tv);

        //为侧栏布局绑定
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        //设置筛选按钮
        tittle_choose_tv.setText("筛选");

        //设置点击筛选按钮弹出右侧栏事件监听器
        tittle_choose_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        //绑定仪器信息列表控件
        instrument_basic_info_listview = (ListView)findViewById(R.id.instrument_basic_info_listview);

        //开启子线程获取仪器信息
        instrument_basic_info_list = getInstrumentBasicInfo();

        //创建仪器信息ListView适配器
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return instrument_basic_info_list.size();
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
            public View getView(final int position, View convertView, ViewGroup parent) {
                //加载ListView中的每一项布局文件
                convertView = getLayoutInflater().inflate(R.layout.item_instrument_basic_info, null);

                //创建适配器控件管理对象
                ViewHolder viewHolder = new ViewHolder();

                //为适配器布局文件绑定控件
                viewHolder.instrument_no_tv = (TextView) convertView.findViewById(R.id.instrument_no_tv);
                viewHolder.instrument_name_tv = (TextView) convertView.findViewById(R.id.instrument_name_tv);
                viewHolder.instrument_statue_tv = (TextView) convertView.findViewById(R.id.instrument_statue_tv);
                viewHolder.instrument_order_model_tv = (TextView) convertView.findViewById(R.id.instrument_order_model_tv);
                viewHolder.instrument_order_way_tv = (TextView) convertView.findViewById(R.id.instrument_order_way_tv);
                //viewHolder.instrument_modify_tv = (TextView) convertView.findViewById(R.id.instrument_modify_tv);
                //instrument_check_tv = (TextView) convertView.findViewById(R.id.instrument_check_tv);
                //viewHolder.instrument_delete_tv = (TextView) convertView.findViewById(R.id.instrument_delete_tv);

                //设置适配器控件的属性值
                viewHolder.instrument_no_tv.setText((position+1)+"、");
                viewHolder.instrument_name_tv.setText("名称："+instrument_basic_info_list.get(position).get("instrument_name").toString());
                viewHolder.instrument_statue_tv.setText("状态："+instrument_basic_info_list.get(position).get("instrument_statue").toString());
                viewHolder.instrument_order_model_tv.setText("预约模式："+instrument_basic_info_list.get(position).get("instrument_order_model").toString());
                viewHolder.instrument_order_way_tv.setText("预约方式："+instrument_basic_info_list.get(position).get("instrument_order_way").toString());

                //为ListView每一项中的编辑按钮设置点击监听器
                /*viewHolder.instrument_modify_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(InstrumentInfoActivity.this, "点击的是第"+(position+1)+"项中的编辑按钮", Toast.LENGTH_SHORT).show();
                    }
                });*/

                //为ListView每一项中的查看按钮设置点击监听器
                /*viewHolder.instrument_check_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(InstrumentInfoActivity.this, "点击的是第"+(position+1)+"项中的查看按钮", Toast.LENGTH_SHORT).show();
                        intent = new Intent(InstrumentInfoActivity.this,InstrumentDetailInfoActivity.class);
                        startActivity(intent);
                    }
                });*/

                //为ListView每一项中的删除按钮设置点击监听器
                /*viewHolder.instrument_delete_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(InstrumentInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                .setMessage("您确定要删除"+instrument_basic_info_list.get(position).get("instrument_name").toString()+"吗？")//设置显示的内容
                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                        // TODO Auto-generated method stub
                                        Toast.makeText(InstrumentInfoActivity.this, "点击的是第"+(position+1)+"项中的删除按钮,删除"+instrument_basic_info_list.get(position).get("instrument_name").toString()+"成功", Toast.LENGTH_SHORT).show();
                                        instrument_basic_info_list.remove(position);
                                        notifyDataSetChanged();
                                        //getActivity().finish();
                                    }
                                }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件
                                // TODO Auto-generated method stub
                                Log.i("alertdialog"," 请保存数据！");
                            }
                        }).show();//在按键响应事件中显示此对话框
                    }
                });*/

                return convertView;
            }

            class ViewHolder {
                protected TextView instrument_no_tv,instrument_name_tv,instrument_statue_tv,instrument_order_model_tv,instrument_order_way_tv,instrument_modify_tv,instrument_check_tv,instrument_delete_tv;
            }
        };

        //将仪器信息ListView适配器绑定适配器
        instrument_basic_info_listview.setAdapter(baseAdapter);

        //为ListView点击项设置点击监听事件,查看仪器详细信息
        instrument_basic_info_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(InstrumentInfoActivity.this,InstrumentDetailInfoActivity.class);
                startActivity(intent);
            }
        });

        //为ListView点击项设置长按监听事件，删除仪器
        instrument_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(InstrumentInfoActivity.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要删除"+instrument_basic_info_list.get(position).get("instrument_name").toString()+"吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(InstrumentInfoActivity.this, "点击的是第"+(position+1)+"项中的删除按钮,删除"+instrument_basic_info_list.get(position).get("instrument_name").toString()+"成功", Toast.LENGTH_SHORT).show();
                                instrument_basic_info_list.remove(position);
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

        //绑定仪器名称EditText控件
        instrument_search_name_edt = (EditText)findViewById(R.id.instrument_search_name_edt);

        //绑定预约模式Spinner控件
        instrument_search_order_model_sp = (Spinner)findViewById(R.id.instrument_search_order_model_sp);

        //设置预约模式Spinner数组
        final String[] order_model_arr = {"不选","时间预约","项目预约"};

        //创建预约模式ArrayAdapter对象
        ArrayAdapter<String> order_model_arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,order_model_arr);

        //为预约模式Spinner设置Adapter
        instrument_search_order_model_sp.setAdapter(order_model_arrayAdapter);

        //为预约模式Spinner设置点击监听事件
        instrument_search_order_model_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                instrument_search_order_model_input = order_model_arr[position];
                instrument_search_order_model_input_positon = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //绑定预约方式Spinner控件
        instrument_search_way_model_sp = (Spinner)findViewById(R.id.instrument_search_way_model_sp);

        //设置预约方式Spinner数组
        final String[] order_way_arr = {"不选","可自动预约","不可自动预约"};

        //创建预约方式ArrayAdapter对象
        ArrayAdapter<String> order_way_arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,order_way_arr);

        //为预约方式Spinner设置Adapter
        instrument_search_way_model_sp.setAdapter(order_way_arrayAdapter);

        //为预约方式Spinner设置点击监听事件
        instrument_search_way_model_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                instrument_search_order_way_input = order_way_arr[position];
                instrument_search_order_way_input_positon = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //绑定筛选侧栏中的取消按钮控件
        instrument_canncle_search_tv = (TextView)findViewById(R.id.instrument_canncle_search_tv);

        //为筛选侧栏中的取消按钮设置点击监听事件
        instrument_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                instrument_search_name_edt.setText("");
                instrument_search_order_model_sp.setSelection(0);
                instrument_search_way_model_sp.setSelection(0);
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        //绑定筛选侧栏中的重置按钮控件
        instrument_reset_search_tv = (TextView)findViewById(R.id.instrument_reset_search_tv);

        //为筛选侧栏中的重置按钮设置点击监听事件
        instrument_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                instrument_search_name_edt.setText("");
                instrument_search_order_model_sp.setSelection(0);
                instrument_search_way_model_sp.setSelection(0);
            }
        });

        //绑定筛选侧栏中的确定按钮控件
        instrument_ok_search_tv = (TextView)findViewById(R.id.instrument_ok_search_tv);

        //为筛选侧栏中的确定按钮设置点击监听事件
        instrument_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instrument_search_name_input = instrument_search_name_edt.getText().toString();
                //显示输入的仪器名称
                Log.d("仪器名称：",instrument_search_name_input);
                //显示选择的预约模式
                Log.d("预约模式信息：",instrument_search_order_model_input);
                Log.d("预约模式下标：",instrument_search_order_model_input_positon+"");
                //显示选择的预约方式
                Log.d("预约方式信息：",instrument_search_order_way_input);
                Log.d("预约方式下标：",instrument_search_order_way_input_positon+"");
                //回复默认显示信息
                instrument_search_name_edt.setText("");
                instrument_search_order_model_sp.setSelection(0);
                instrument_search_way_model_sp.setSelection(0);
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

    }

    //开启子线程获取仪器信息
    private ArrayList getInstrumentBasicInfo(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("instrument_name","透射电子显微镜");
        map1.put("instrument_statue","空闲");
        map1.put("instrument_order_model","时间预约");
        map1.put("instrument_order_way","可以自动预约");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("instrument_name","液相色谱质谱联用仪LTQ-OrbitrapXL");
        map2.put("instrument_statue","使用中");
        map2.put("instrument_order_model","时间预约");
        map2.put("instrument_order_way","不可以自动预约");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("instrument_name","超高效液相色谱仪UPLC-PDA-1");
        map3.put("instrument_statue","离线");
        map3.put("instrument_order_model","时间预约");
        map3.put("instrument_order_way","不可以自动预约");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("instrument_name","扫描电子显微镜");
        map4.put("instrument_statue","未使用");
        map4.put("instrument_order_model","时间预约");
        map4.put("instrument_order_way","不可以自动预约");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("instrument_name","超高效液相色谱仪UPLC-PDA-2");
        map5.put("instrument_statue","空闲");
        map5.put("instrument_order_model","时间预约");
        map5.put("instrument_order_way","不可以自动预约");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("instrument_name","气质联用仪ThermoISQ");
        map6.put("instrument_statue","离线");
        map6.put("instrument_order_model","时间预约");
        map6.put("instrument_order_way","不可以自动预约");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("instrument_name","液相色谱质谱联用仪5500Q");
        map7.put("instrument_statue","离线");
        map7.put("instrument_order_model","时间预约");
        map7.put("instrument_order_way","不可以自动预约");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("instrument_name","气质联用仪安捷伦GC-MS");
        map8.put("instrument_statue","离线");
        map8.put("instrument_order_model","时间预约");
        map8.put("instrument_order_way","可以自动预约");
        list.add(map8);

        HashMap<String,Object> map9 = new HashMap<>();
        map9.put("instrument_name","电感耦合等离子质谱仪ICP-MS");
        map9.put("instrument_statue","离线");
        map9.put("instrument_order_model","时间预约");
        map9.put("instrument_order_way","可以自动预约");
        list.add(map9);

        HashMap<String,Object> map10 = new HashMap<>();
        map10.put("instrument_name","液相色谱质谱联用仪4500Q");
        map10.put("instrument_statue","离线");
        map10.put("instrument_order_model","时间预约");
        map10.put("instrument_order_way","不可以自动预约");
        list.add(map10);

        return list;
    }

}
