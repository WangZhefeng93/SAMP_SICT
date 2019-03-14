package com.ff161224.cc_commander.shareplatform.main.dataInfo.coastInfo.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.coastInfo.create.CreateCoastInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.coastInfo.edit.EditCoastInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.projectInfo.edit.EditProjectInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CoastInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_coast_tv;  //新建耗材信息按钮
    private TextView tittle_name_tv;    //耗材信息标题
    private TextView tittle_choose_tv;  //耗材信息筛选按钮
    private ListView coast_basic_info_listview;   //耗材信息ListView
    private EditText coast_search_id_edt;   //筛选界面耗材ID
    private EditText coast_search_name_edt; //筛选界面耗材名称
    private TextView coast_canncle_search_tv;   //筛选界面取消按钮
    private TextView coast_reset_search_tv;     //筛选界面重置按钮
    private TextView coast_ok_search_tv;        //筛选界面重确定按钮

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> coast_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> coast_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> coast_basic_info_list_result = new ArrayList<>();
    private CharSequence []items = {"编辑","删除"};
    private String coast_search_id_value = "";    //筛选界面耗材ID输入框的值
    private String coast_search_name_value = "";    //筛选界面耗材名称输入框的值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_coast_info);
        //绑定控件变量
        initWidget();
        //开启子线程获取耗材信息
        coast_basic_info_list_original = getProjectInfo();
        coast_basic_info_list_current = (ArrayList<HashMap<String, Object>>) coast_basic_info_list_original.clone();
        //为耗材信息ListView设置适配器
        createBaseAdapterForCoastListView();
        //为ListView中的每一项设置监听器
        setClickListenerForListViewItem();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchCoast();
        //为筛选界面中的三个按钮设置点击监听器
        setOnClickListenerForThreeButtons();
        //为新建按钮设置点击监听器
        setOnClickListenerForCreateCoastTV();
    }

    /**
     * 绑定控件变量
     */
    private void initWidget() {
        tittle_create_coast_tv = (TextView) findViewById(R.id.tittle_create_coast_tv);  //新建耗材信息按钮
        tittle_name_tv = (TextView) findViewById(R.id.tittle_name_tv);    //耗材信息标题
        tittle_choose_tv = (TextView) findViewById(R.id.tittle_choose_tv);  //耗材信息筛选按钮
        coast_basic_info_listview = (ListView) findViewById(R.id.coast_basic_info_listview);   //耗材信息ListView
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);  //为侧栏布局绑定
        coast_search_id_edt = (EditText) findViewById(R.id.coast_search_id_edt);   //筛选界面仪器ID
        coast_search_name_edt = (EditText) findViewById(R.id.coast_search_name_edt); //筛选界面仪器名称
        coast_canncle_search_tv = (TextView) findViewById(R.id.coast_canncle_search_tv);   //筛选界面取消按钮
        coast_reset_search_tv = (TextView) findViewById(R.id.coast_reset_search_tv);     //筛选界面重置按钮
        coast_ok_search_tv = (TextView) findViewById(R.id.coast_ok_search_tv);        //筛选界面重确定按钮
    }

    /**
     * 开启子线程获取耗材信息
     * @return
     */
    public ArrayList<HashMap<String,Object>> getProjectInfo() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("coast_ID","耗材ID1");
        map1.put("coast_name","耗材名称1");
        map1.put("measurement_unit","个");
        map1.put("coast_price",9.00);
        map1.put("coast_num","100");
        map1.put("coast_description","加适量的经费落实到了咖啡机");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("coast_ID","耗材ID2");
        map2.put("coast_name","耗材名称2");
        map2.put("measurement_unit","个");
        map2.put("coast_price",10.00);
        map2.put("coast_num","200");
        map2.put("coast_description","");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("coast_ID","耗材ID3");
        map3.put("coast_name","耗材名称3");
        map3.put("measurement_unit","个");
        map3.put("coast_price",8.00);
        map3.put("coast_num","300");
        map3.put("coast_description","加适量的经费落实到了咖啡机");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("coast_ID","耗材ID4");
        map4.put("coast_name","耗材名称4");
        map4.put("measurement_unit","个");
        map4.put("coast_price",7.00);
        map4.put("coast_num","400");
        map4.put("coast_description","");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("coast_ID","耗材ID5");
        map5.put("coast_name","耗材名称5");
        map5.put("measurement_unit","个");
        map5.put("coast_price",6.00);
        map5.put("coast_num","500");
        map5.put("coast_description","加适量的经费落实到了咖啡机");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("coast_ID","耗材ID6");
        map6.put("coast_name","耗材名称6");
        map6.put("measurement_unit","个");
        map6.put("coast_price",5.00);
        map6.put("coast_num","600");
        map6.put("coast_description","加适量的经费落实到了咖啡机");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("coast_ID","耗材ID7");
        map7.put("coast_name","耗材名称7");
        map7.put("measurement_unit","个");
        map7.put("coast_price",3.00);
        map7.put("coast_num","700");
        map7.put("coast_description","加适量的经费落实到了咖啡机");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("coast_ID","耗材ID8");
        map8.put("coast_name","耗材名称8");
        map8.put("measurement_unit","个");
        map8.put("coast_price",2.00);
        map8.put("coast_num","800");
        map8.put("coast_description","加适量的经费落实到了咖啡机");
        list.add(map8);

        return list;
    }

    /**
     * 为耗材信息ListView设置适配器
     */
    private void createBaseAdapterForCoastListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (coast_basic_info_list_current.size() > 0)
                    return coast_basic_info_list_current.size();
                else
                    return 1;
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
                if (coast_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_coast_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.coast_no_tv = (TextView) convertView.findViewById(R.id.coast_no_tv);
                    viewHolder.coast_id_tv = (TextView) convertView.findViewById(R.id.coast_id_tv);
                    viewHolder.coast_name_tv = (TextView) convertView.findViewById(R.id.coast_name_tv);
                    viewHolder.coast_unit_tv = (TextView) convertView.findViewById(R.id.coast_unit_tv);
                    viewHolder.coast_price_tv = (TextView) convertView.findViewById(R.id.coast_price_tv);
                    viewHolder.coast_num_tv = (TextView) convertView.findViewById(R.id.coast_num_tv);
                    viewHolder.coast_description_tv = (TextView) convertView.findViewById(R.id.coast_description_tv);
                    //设置适配器控件的属性值
                    viewHolder.coast_no_tv.setText((position+1)+"、");
                    viewHolder.coast_id_tv.setText("耗材ID："+coast_basic_info_list_current.get(position).get("coast_ID").toString());
                    viewHolder.coast_name_tv.setText("耗材名称："+coast_basic_info_list_current.get(position).get("coast_name").toString());
                    viewHolder.coast_unit_tv.setText("耗材单位："+coast_basic_info_list_current.get(position).get("measurement_unit").toString());
                    viewHolder.coast_price_tv.setText("耗材单价(￥)："+coast_basic_info_list_current.get(position).get("coast_price").toString());
                    viewHolder.coast_num_tv.setText("耗材数量："+coast_basic_info_list_current.get(position).get("coast_num").toString());
                    viewHolder.coast_description_tv.setText("描述："+coast_basic_info_list_current.get(position).get("coast_description").toString());
                }else {
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.text1 = (TextView)convertView.findViewById(android.R.id.text1);

                    //设置适配器控件的属性值
                    viewHolder.text1.setText("暂无任何筛选结果！");
                    viewHolder.text1.setGravity(Gravity.CENTER);
                }
                return convertView;
            }
            class ViewHolder {
                protected TextView text1,coast_no_tv,coast_id_tv,coast_name_tv,
                        coast_unit_tv,coast_price_tv,
                        coast_num_tv,coast_description_tv;
            }
        };
        //将耗材信息ListView适配器绑定适配器
        coast_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        coast_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(CoastInfoActivity.this)
                        .setTitle("操作分析项目")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     //编辑
                                        intent = new Intent(CoastInfoActivity.this, EditCoastInfoActivity.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 1:     //删除
                                        new android.app.AlertDialog.Builder(CoastInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要删除耗材信息：\n"+coast_basic_info_list_current.get(position).get("coast_name").toString()+"吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        coast_basic_info_list_current.remove(position);
                                                        baseAdapter.notifyDataSetChanged();
                                                    }
                                                }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {//响应事件
                                                // TODO Auto-generated method stub
                                                Log.i("alertdialog"," 请保存数据！");
                                            }
                                        }).show();//在按键响应事件中显示此对话框
                                        break;
                                }
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });
    }

    /**
     * 设置点击筛选按钮弹出右侧栏事件监听器
     */
    private void setOnClickListenerForSearchCoast() {
        tittle_choose_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 为筛选界面中的三个按钮设置点击监听器
     */
    private void setOnClickListenerForThreeButtons() {
        //为筛选侧栏中的取消按钮设置点击监听事件
        coast_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                coast_search_id_edt.setText("");
                coast_search_name_edt.setText("");
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        //为筛选侧栏中的重置按钮设置点击监听事件
        coast_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                coast_search_id_edt.setText("");
                coast_search_name_edt.setText("");
            }
        });

        //为筛选侧栏中的确定按钮设置点击监听事件
        coast_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取筛选界面仪器ID输入框的值
                coast_search_id_value = coast_search_id_edt.getText().toString();
                //获取筛选界面仪器名称输入框的值
                coast_search_name_value = coast_search_name_edt.getText().toString();
                Log.d("筛选界面中耗材ID：",coast_search_id_value);
                Log.d("筛选界面中耗材名称：",coast_search_name_value);

                coast_basic_info_list_result = getSearchResultList(coast_search_id_value,coast_search_name_value);
                coast_basic_info_list_current = (ArrayList<HashMap<String, Object>>) coast_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                //回复默认显示信息
                coast_search_id_edt.setText("");
                coast_search_name_edt.setText("");
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选信息逻辑，返回筛选结果集合
     * @param coast_search_id_value：耗材ID
     * @param coast_search_name_value：耗材名称
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String coast_search_id_value, String coast_search_name_value) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("00");
        if (!"".equals(coast_search_id_value))
            tag.setCharAt(0,'1');
        if (!"".equals(coast_search_name_value))
            tag.setCharAt(1,'1');
        //Log.d("tag的值为：",Integer.valueOf(tag.toString(),2)+"");
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //00：表示2个筛选条件均无效，此时返回全部结果
                isEffctive = false;
                break;
            case 1:     //01：表示仅有耗材名称筛选条件有效
                for (int i = 0; i < coast_basic_info_list_original.size(); i++) {
                    if (coast_basic_info_list_original.get(i).get("coast_name").toString().contains(coast_search_name_value))
                        list.add(coast_basic_info_list_original.get(i));
                }
                break;
            case 2:     //10：表示仅有耗材ID筛选条件有效
                for (int i = 0; i < coast_basic_info_list_original.size(); i++) {
                    if (coast_basic_info_list_original.get(i).get("coast_ID").toString().contains(coast_search_id_value))
                        list.add(coast_basic_info_list_original.get(i));
                }
                break;
            case 3:     //011：表示2个筛选条件均有效
                for (int i = 0; i < coast_basic_info_list_original.size(); i++) {
                    if (coast_basic_info_list_original.get(i).get("coast_name").toString().contains(coast_search_name_value) &&
                            coast_basic_info_list_original.get(i).get("coast_ID").toString().contains(coast_search_id_value))
                        list.add(coast_basic_info_list_original.get(i));
                }
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return coast_basic_info_list_original;
        }
    }

    /**
     * 为新建按钮设置点击监听器
     */
    private void setOnClickListenerForCreateCoastTV() {
        tittle_create_coast_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CoastInfoActivity.this, CreateCoastInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
