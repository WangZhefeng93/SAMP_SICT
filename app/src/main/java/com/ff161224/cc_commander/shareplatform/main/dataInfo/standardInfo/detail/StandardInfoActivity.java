package com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.detail;

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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.create.CreateStandardInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.standardInfo.edit.EditStandardInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class StandardInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_standard_tv; //新建按钮
    private TextView tittle_name_tv;    //标题信息
    private TextView tittle_choose_tv;  //筛选按钮
    private ListView standard_basic_info_listview;  //标准下拉列表
    private EditText standard_search_no_edt;    //筛选界面中的标准号
    private EditText standard_search_name_edt;  //筛选界面中的标准名称
    private Spinner standard_search_type_sp;    //筛选界面中的标准类型
    private TextView standard_canncle_search_tv;    //筛选界面中的取消按钮
    private TextView standard_reset_search_tv;  //筛选界面中的重置按钮
    private TextView standard_ok_search_tv; //筛选界面中的确定按钮

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> standard_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> standard_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> standard_basic_info_list_result = new ArrayList<>();
    private CharSequence []items = {"切换状态","删除"};
    private String[] standard_type_array = null;
    private ArrayAdapter<String> standard_type_adapter = null;
    private int standard_search_type_position = -1;
    private String standard_search_type_value = "";
    private String standard_search_no_value = "";
    private String standard_search_name_value = "";
    private boolean usable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_standard_info);
        //绑定控件变量
        initWidget();
        //为新建按钮设置点击监听器
        setOnClickListenerForCreateProjectTV();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchProject();
        //开启子线程获取分析项目信息
        standard_basic_info_list_original = getProjectInfo();
        standard_basic_info_list_current = (ArrayList<HashMap<String, Object>>) standard_basic_info_list_original.clone();
        //为标准信息ListView设置适配器
        createBaseAdapterForStandardtListView();
        //为ListView中的每一项设置监听器
        setClickListenerForListViewItem();
        //为筛选界面中的下拉菜单设置数据
        setDataForSpinner();
        //为筛选界面中的下拉列表设置点击监听器
        setOnClickListenerForSpinner();
        //为筛选界面中的三个按钮设置点击监听器
        setOnClickListenerForThreeButtons();
    }

    /**
     * 绑定控件变量
     */
    private void initWidget() {
        tittle_create_standard_tv = (TextView) findViewById(R.id.tittle_create_standard_tv); //新建按钮
        tittle_name_tv = (TextView) findViewById(R.id.tittle_name_tv);    //标题信息
        tittle_choose_tv = (TextView) findViewById(R.id.tittle_choose_tv);  //筛选按钮
        standard_basic_info_listview = (ListView) findViewById(R.id.standard_basic_info_listview);  //标准下拉列表
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);  //为侧栏布局绑定
        standard_search_no_edt = (EditText) findViewById(R.id.standard_search_no_edt);    //筛选界面中的标准号
        standard_search_name_edt = (EditText) findViewById(R.id.standard_search_name_edt);  //筛选界面中的标准名称
        standard_search_type_sp = (Spinner) findViewById(R.id.standard_search_type_sp);    //筛选界面中的标准类型
        standard_canncle_search_tv = (TextView) findViewById(R.id.standard_canncle_search_tv);    //筛选界面中的取消按钮
        standard_reset_search_tv = (TextView) findViewById(R.id.standard_reset_search_tv);  //筛选界面中的重置按钮
        standard_ok_search_tv = (TextView) findViewById(R.id.standard_ok_search_tv); //筛选界面中的确定按钮
    }

    /**
     * 为新建按钮设置点击监听器
     */
    private void setOnClickListenerForCreateProjectTV() {
        tittle_create_standard_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StandardInfoActivity.this, CreateStandardInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 设置点击筛选按钮弹出右侧栏事件监听器
     */
    private void setOnClickListenerForSearchProject() {
        tittle_choose_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 开启子线程获取分析项目信息
     * @return
     */
    public ArrayList<HashMap<String,Object>> getProjectInfo() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("standard_no","手持式元素快速分析仪");
        map1.put("standard_name","手持式元素快速分析仪");
        map1.put("standard_type","前处理标准");
        map1.put("standard_state","现行");
        map1.put("standard_classification","所标");
        map1.put("standard_introduction","开发江山帝景弗兰克斯建档立卡福建省了的框架房卡洛斯的减肥了开始的减肥离开");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("standard_no","岩石或化石样品抛光");
        map2.put("standard_name","岩石或化石样品抛光");
        map2.put("standard_type","前处理标准");
        map2.put("standard_state","现行");
        map2.put("standard_classification","所标");
        map2.put("standard_introduction","开发江山帝景弗兰克斯建档立卡福建省了的框架房卡洛斯的减肥了开始的减肥离开");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("standard_no","化石或岩石切片");
        map3.put("standard_name","化石或岩石切片");
        map3.put("standard_type","前处理标准");
        map3.put("standard_state","现行");
        map3.put("standard_classification","所标");
        map3.put("standard_introduction","开发江山帝景弗兰克斯建档立卡福建省了的框架房卡洛斯的减肥了开始的减肥离开");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("standard_no","岩石薄片制备标准");
        map4.put("standard_name","岩石薄片制备标准");
        map4.put("standard_type","分析项目标准");
        map4.put("standard_state","现行");
        map4.put("standard_classification","院标");
        map4.put("standard_introduction","的会计师的浪费");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("standard_no","扫描电镜前处理");
        map5.put("standard_name","扫描电镜前处理");
        map5.put("standard_type","前处理标准");
        map5.put("standard_state","现行");
        map5.put("standard_classification","所标");
        map5.put("standard_introduction","开发江山帝景弗兰克斯建档立卡福建省了的框架房卡洛斯的减肥了开始的减肥离开");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("standard_no","扫描电镜收费标准1");
        map6.put("standard_name","试验平台收费标准");
        map6.put("standard_type","分析项目标准");
        map6.put("standard_state","现行");
        map6.put("standard_classification","所标");
        map6.put("standard_introduction","开发江山帝景弗兰克斯建档立卡福建省了的框架房卡洛斯的减肥了开始的减肥离开");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("standard_no","20120302-萃取仪");
        map7.put("standard_name","快速萃取仪标准管理");
        map7.put("standard_type","分析项目标准");
        map7.put("standard_state","现行");
        map7.put("standard_classification","所标");
        map7.put("standard_introduction","开发江山帝景弗兰克斯建档立卡福建省了的框架房卡洛斯的减肥了开始的减肥离开");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("standard_no","JSM20130915");
        map8.put("standard_name","JSM电镜收费标准");
        map8.put("standard_type","前处理标准");
        map8.put("standard_state","现行");
        map8.put("standard_classification","院标");
        map8.put("standard_introduction","是的服务而枯萎了");
        list.add(map7);

        return list;
    }

    /**
     * 为标准信息ListView设置适配器
     */
    private void createBaseAdapterForStandardtListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (standard_basic_info_list_current.size() > 0)
                    return standard_basic_info_list_current.size();
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
                if (standard_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_standard_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.standard_id_tv = (TextView) convertView.findViewById(R.id.standard_id_tv);
                    viewHolder.standard_no_tv = (TextView) convertView.findViewById(R.id.standard_no_tv);
                    viewHolder.standard_name_tv = (TextView) convertView.findViewById(R.id.standard_name_tv);
                    viewHolder.standard_type_tv = (TextView) convertView.findViewById(R.id.standard_type_tv);
                    viewHolder.standard_state_tv = (TextView) convertView.findViewById(R.id.standard_state_tv);
                    //设置适配器控件的属性值
                    viewHolder.standard_id_tv.setText((position+1)+"、");
                    viewHolder.standard_no_tv.setText("标准号："+standard_basic_info_list_current.get(position).get("standard_no").toString());
                    viewHolder.standard_name_tv.setText("标准名称："+standard_basic_info_list_current.get(position).get("standard_name").toString());
                    viewHolder.standard_type_tv.setText("标准类型："+standard_basic_info_list_current.get(position).get("standard_type").toString());
                    viewHolder.standard_state_tv.setText("标准状态："+standard_basic_info_list_current.get(position).get("standard_state").toString());
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
                protected TextView text1,standard_id_tv,standard_no_tv,
                        standard_name_tv,standard_type_tv,
                        standard_state_tv;
            }
        };
        //将分析项目ListView适配器绑定适配器
        standard_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        standard_basic_info_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> map = standard_basic_info_list_current.get(position);
                intent = new Intent(StandardInfoActivity.this,StandardInfoDetailActivity.class);
                intent.putExtra("standardInfoMap",(Serializable)map);
                startActivity(intent);
            }
        });
        standard_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(StandardInfoActivity.this)
                        .setTitle("操作标准信息")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     //停用
                                        if (usable){
                                            new android.app.AlertDialog.Builder(StandardInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                    .setMessage("您确定要停用标准：\n"+standard_basic_info_list_current.get(position).get("standard_name").toString()+"吗？")//设置显示的内容
                                                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                            // TODO Auto-generated method stub
                                                            standard_basic_info_list_current.get(position).put("standard_state","停用");
                                                            usable = false;
                                                            baseAdapter.notifyDataSetChanged();
                                                        }
                                                    }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {//响应事件
                                                    // TODO Auto-generated method stub
                                                    Log.i("alertdialog"," 请保存数据！");
                                                }
                                            }).show();//在按键响应事件中显示此对话框
                                        }else {
                                            new android.app.AlertDialog.Builder(StandardInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                    .setMessage("您确定要启用标准：\n"+standard_basic_info_list_current.get(position).get("standard_name").toString()+"吗？")//设置显示的内容
                                                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                            // TODO Auto-generated method stub
                                                            standard_basic_info_list_current.get(position).put("standard_state","现行");
                                                            usable = true;
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
                                        break;
                                    case 1:     //删除
                                        new android.app.AlertDialog.Builder(StandardInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要删除标准：\n"+standard_basic_info_list_current.get(position).get("standard_name").toString()+"吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        standard_basic_info_list_current.remove(position);
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
     * 为筛选界面中的下拉菜单设置数据
     */
    private void setDataForSpinner() {
        //获取标准类型数组
        standard_type_array = getStandardTypeArray();
        //创建标准类型适配器
        standard_type_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,standard_type_array);
        //为标准类型下拉菜单设置适配器
        standard_search_type_sp.setAdapter(standard_type_adapter);
    }

    /**
     * 获取标准类型数组
     * @return
     */
    public String[] getStandardTypeArray() {
        return new String[]{"--请选择--","前处理标准","分析项目标准"};
    }

    /**
     * 为筛选界面中的下拉列表设置点击监听器
     */
    private void setOnClickListenerForSpinner() {
        standard_search_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                standard_search_type_position = position;
                standard_search_type_value = standard_type_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 为筛选界面中的三个按钮设置点击监听器
     */
    private void setOnClickListenerForThreeButtons() {
        //为筛选侧栏中的取消按钮设置点击监听事件
        standard_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                standard_search_no_edt.setText("");
                standard_search_name_edt.setText("");
                standard_search_type_sp.setSelection(0);
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        //为筛选侧栏中的重置按钮设置点击监听事件
        standard_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                standard_search_no_edt.setText("");
                standard_search_name_edt.setText("");
                standard_search_type_sp.setSelection(0);
            }
        });
        //为筛选侧栏中的确定按钮设置点击监听事件
        standard_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取筛选界面分析项目名称输入框的值
                standard_search_no_value = standard_search_no_edt.getText().toString();
                standard_search_name_value = standard_search_name_edt.getText().toString();
                Log.d("筛选界面中标准号：",standard_search_no_value);
                Log.d("筛选界面中标准名称：",standard_search_name_value);
                Log.d("筛选界面中标准类型：",standard_search_type_value);
                Log.d("筛选界面中标准类型下标：",standard_search_type_position+"");
                standard_basic_info_list_result = getSearchResultList(standard_search_no_value,standard_search_name_value,standard_search_type_position);
                standard_basic_info_list_current = (ArrayList<HashMap<String, Object>>) standard_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                //回复默认显示信息
                standard_search_no_edt.setText("");
                standard_search_name_edt.setText("");
                standard_search_type_sp.setSelection(0);
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选信息逻辑，返回筛选结果集合
     * @param standard_search_no_value：标准号
     * @param standard_search_name_value：标准名称
     * @param standard_search_type_position：标准类型下标
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String standard_search_no_value, String standard_search_name_value, int standard_search_type_position) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("000");
        if (!"".equals(standard_search_no_value))
            tag.setCharAt(0,'1');
        if (!"".equals(standard_search_name_value))
            tag.setCharAt(1,'1');
        if (standard_search_type_position > 0)
            tag.setCharAt(2,'1');
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //000：表示3个筛选条件均无效，此时返回全部结果
                isEffctive = false;
                break;
            case 1:     //001：表示仅有标准类型筛选条件有效
                for (int i = 0; i < standard_basic_info_list_original.size(); i++) {
                    if (standard_type_array[standard_search_type_position].equals(standard_basic_info_list_original.get(i).get("standard_type"))) {
                        list.add(standard_basic_info_list_original.get(i));
                    }
                }
                break;
            case 2:     //010：表示仅有标准名称筛选条件有效
                for (int i = 0; i < standard_basic_info_list_original.size(); i++) {
                    if (standard_basic_info_list_original.get(i).get("standard_name").toString().contains(standard_search_name_value)) {
                        list.add(standard_basic_info_list_original.get(i));
                    }
                }
                break;
            case 3:     //011：表示标准类型和标准名称筛选条件有效
                for (int i = 0; i < standard_basic_info_list_original.size(); i++) {
                    if (standard_type_array[standard_search_type_position].equals(standard_basic_info_list_original.get(i).get("standard_type"))
                            && standard_basic_info_list_original.get(i).get("standard_name").toString().contains(standard_search_name_value)) {
                        list.add(standard_basic_info_list_original.get(i));
                    }
                }
                break;
            case 4:     //100：表示仅有标准号筛选条件有效
                for (int i = 0; i < standard_basic_info_list_original.size(); i++) {
                    if (standard_basic_info_list_original.get(i).get("standard_no").toString().contains(standard_search_no_value))
                        list.add(standard_basic_info_list_original.get(i));
                }
                break;
            case 5:     //101：表示标准号和标准类型筛选条件有效
                for (int i = 0; i < standard_basic_info_list_original.size(); i++) {
                    if (standard_basic_info_list_original.get(i).get("standard_no").toString().contains(standard_search_no_value) &&
                            standard_type_array[standard_search_type_position].equals(standard_basic_info_list_original.get(i).get("standard_type")))
                        list.add(standard_basic_info_list_original.get(i));
                }
                break;
            case 6:     //110：表示标准号和标准名称筛选条件有效
                for (int i = 0; i < standard_basic_info_list_original.size(); i++) {
                    if (standard_basic_info_list_original.get(i).get("standard_no").toString().contains(standard_search_no_value) &&
                            standard_basic_info_list_original.get(i).get("standard_name").toString().contains(standard_search_name_value))
                        list.add(standard_basic_info_list_original.get(i));
                }
                break;
            case 7:     //111：表示三个筛选条件均有效
                for (int i = 0; i < standard_basic_info_list_original.size(); i++) {
                    if (standard_basic_info_list_original.get(i).get("standard_no").toString().contains(standard_search_no_value) &&
                            standard_basic_info_list_original.get(i).get("standard_name").toString().contains(standard_search_name_value) &&
                            standard_type_array[standard_search_type_position].equals(standard_basic_info_list_original.get(i).get("standard_type")))
                        list.add(standard_basic_info_list_original.get(i));
                }
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return standard_basic_info_list_original;
        }
    }
}
