package com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.detail;

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

import com.bigkoo.pickerview.TimePickerView;
import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.create.CreateSwitchingInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.switchInfo.edit.EditSwitchingInfoActivity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SwitchingInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_switching_tv;  //新建按钮
    private TextView tittle_choose_tv;  //筛选按钮
    private ListView switching_basic_info_listview;   //开关机记录信息下拉列表
    private EditText switching_search_startTime_edt;   //查询时间起
    private EditText switching_search_stopTime_edt;    //查询时间止
    private EditText switching_search_userName_edt;   //使用人
    private TextView switching_canncle_search_tv; //取消
    private TextView switching_reset_search_tv;   //重置
    private TextView switching_ok_search_tv;  //确定

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> switching_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> switching_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> switching_basic_info_list_result = new ArrayList<>();
    private CharSequence []items = {"编辑","查看","删除"};
    private String switching_search_startTime_value = "";   //查询时间起
    private String switching_search_stopTime_value = "";    //查询时间止
    private String switching_search_userName_value = "";   //使用人

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_switching_info);
        //绑定控件变量
        initWidget();
        //为新建按钮设置点击监听器
        setOnClickListenerForCreateSwitchingTV();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchSwitching();
        //开启子线程获取开关机记录信息
        switching_basic_info_list_original = getSwitchingInfo();
        switching_basic_info_list_current = (ArrayList<HashMap<String, Object>>) switching_basic_info_list_original.clone();
        //为开关机记录信息ListView设置适配器
        createBaseAdapterForSwitchingListView();
        //为ListView中的每一项设置监听器
        setClickListenerForListViewItem();
        //为筛选界面中的起止时间选择设置监听器
        setListenerForChooseDateTV();
        //为筛选界面中的三个按钮设置点击监听器
        setOnClickListenerForThreeButtons();
    }

    /**
     * 绑定控件变量
     */
    private void initWidget() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);  //为侧栏布局绑定
        tittle_create_switching_tv = (TextView) findViewById(R.id.tittle_create_switching_tv);  //新建按钮
        tittle_choose_tv = (TextView) findViewById(R.id.tittle_choose_tv);  //筛选按钮
        switching_basic_info_listview = (ListView) findViewById(R.id.switching_basic_info_listview);   //开关机记录信息下拉列表
        switching_search_startTime_edt = (EditText) findViewById(R.id.switching_search_startTime_edt);   //查询时间起
        switching_search_stopTime_edt = (EditText) findViewById(R.id.switching_search_stopTime_edt);    //查询时间止
        switching_search_userName_edt = (EditText) findViewById(R.id.switching_search_userName_edt);   //使用人
        switching_canncle_search_tv = (TextView) findViewById(R.id.switching_canncle_search_tv); //取消
        switching_reset_search_tv = (TextView) findViewById(R.id.switching_reset_search_tv);   //重置
        switching_ok_search_tv = (TextView) findViewById(R.id.switching_ok_search_tv);  //确定
    }

    /**
     * 为新建按钮设置点击监听器
     */
    private void setOnClickListenerForCreateSwitchingTV() {
        tittle_create_switching_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SwitchingInfoActivity.this, CreateSwitchingInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置点击筛选按钮弹出右侧栏事件监听器
     */
    private void setOnClickListenerForSearchSwitching() {
        tittle_choose_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 开启子线程获取开关机记录信息
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSwitchingInfo() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("switching_useDate","2019-03-04");
        map1.put("switching_startTime","10:00");
        map1.put("switching_stopTime","17:58");
        map1.put("switching_effectiveHours","7.97");
        map1.put("switching_workContent","");
        map1.put("switching_userName","");
        map1.put("switching_beforeState","");
        map1.put("switching_afterState","");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("switching_useDate","2019-03-01");
        map2.put("switching_startTime","00:00");
        map2.put("switching_stopTime","08:55");
        map2.put("switching_effectiveHours","8.92");
        map2.put("switching_workContent","");
        map2.put("switching_userName","");
        map2.put("switching_beforeState","");
        map2.put("switching_afterState","");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("switching_useDate","2019-02-28");
        map3.put("switching_startTime","13:07");
        map3.put("switching_stopTime","23:59");
        map3.put("switching_effectiveHours","10.87");
        map3.put("switching_workContent","");
        map3.put("switching_userName","");
        map3.put("switching_beforeState","");
        map3.put("switching_afterState","");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("switching_useDate","2019-02-27");
        map4.put("switching_startTime","09:35");
        map4.put("switching_stopTime","17:45");
        map4.put("switching_effectiveHours","8.16");
        map4.put("switching_workContent","");
        map4.put("switching_userName","");
        map4.put("switching_beforeState","");
        map4.put("switching_afterState","");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("switching_useDate","2019-02-27");
        map5.put("switching_startTime","00:00");
        map5.put("switching_stopTime","08:57");
        map5.put("switching_effectiveHours","8.96");
        map5.put("switching_workContent","");
        map5.put("switching_userName","");
        map5.put("switching_beforeState","");
        map5.put("switching_afterState","");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("switching_useDate","2019-02-26");
        map6.put("switching_startTime","09:42");
        map6.put("switching_stopTime","16:47");
        map6.put("switching_effectiveHours","7.08");
        map6.put("switching_workContent","");
        map6.put("switching_userName","");
        map6.put("switching_beforeState","");
        map6.put("switching_afterState","");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("switching_useDate","2019-02-26");
        map7.put("switching_startTime","00:00");
        map7.put("switching_stopTime","09:07");
        map7.put("switching_effectiveHours","9.13");
        map7.put("switching_workContent","");
        map7.put("switching_userName","");
        map7.put("switching_beforeState","");
        map7.put("switching_afterState","");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("switching_useDate","2019-02-26");
        map8.put("switching_startTime","17:01");
        map8.put("switching_stopTime","23:59");
        map8.put("switching_effectiveHours","9.13");
        map8.put("switching_workContent","");
        map8.put("switching_userName","");
        map8.put("switching_beforeState","");
        map8.put("switching_afterState","");
        list.add(map8);

        HashMap<String,Object> map9 = new HashMap<>();
        map9.put("switching_useDate","2019-02-25");
        map9.put("switching_startTime","09:31");
        map9.put("switching_stopTime","17:18");
        map9.put("switching_effectiveHours","7.78");
        map9.put("switching_workContent","");
        map9.put("switching_userName","");
        map9.put("switching_beforeState","");
        map9.put("switching_afterState","");
        list.add(map9);

        HashMap<String,Object> map10 = new HashMap<>();
        map10.put("switching_useDate","2019-02-25");
        map10.put("switching_startTime","00:00");
        map10.put("switching_stopTime","08:58");
        map10.put("switching_effectiveHours","8.98");
        map10.put("switching_workContent","");
        map10.put("switching_userName","");
        map10.put("switching_beforeState","");
        map10.put("switching_afterState","");
        list.add(map10);

        return list;
    }

    /**
     * 为开关机记录信息ListView设置适配器
     */
    private void createBaseAdapterForSwitchingListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (switching_basic_info_list_current.size() > 0)
                    return switching_basic_info_list_current.size();
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
                if (switching_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_standard_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.switching_id_tv = (TextView) convertView.findViewById(R.id.standard_id_tv);
                    viewHolder.switching_useDate_tv = (TextView) convertView.findViewById(R.id.standard_no_tv);
                    viewHolder.switching_startTime_tv = (TextView) convertView.findViewById(R.id.standard_name_tv);
                    viewHolder.switching_stopTime_tv = (TextView) convertView.findViewById(R.id.standard_type_tv);
                    viewHolder.switching_effectiveHours_tv = (TextView) convertView.findViewById(R.id.standard_state_tv);
                    //设置适配器控件的属性值
                    viewHolder.switching_id_tv.setText((position+1)+"、");
                    viewHolder.switching_useDate_tv.setText("使用日期："+switching_basic_info_list_current.get(position).get("switching_useDate").toString());
                    viewHolder.switching_startTime_tv.setText("开机时间："+switching_basic_info_list_current.get(position).get("switching_startTime").toString());
                    viewHolder.switching_stopTime_tv.setText("关机时间："+switching_basic_info_list_current.get(position).get("switching_stopTime").toString());
                    viewHolder.switching_effectiveHours_tv.setText("有效时间(h)："+switching_basic_info_list_current.get(position).get("switching_effectiveHours").toString());
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
                protected TextView text1,switching_id_tv,switching_useDate_tv,
                        switching_startTime_tv,switching_stopTime_tv,
                        switching_effectiveHours_tv;
            }
        };
        //将分析项目ListView适配器绑定适配器
        switching_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        switching_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(SwitchingInfoActivity.this)
                        .setTitle("操作日志信息")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     //编辑
                                        HashMap<String,Object> map = switching_basic_info_list_current.get(position);
                                        intent = new Intent(SwitchingInfoActivity.this, EditSwitchingInfoActivity.class);
                                        intent.putExtra("switchingInfoMap",(Serializable) map);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 1:     //查看
                                        HashMap<String,Object> map1 = switching_basic_info_list_current.get(position);
                                        intent = new Intent(SwitchingInfoActivity.this, SwitchingInfoDetailActivity.class);
                                        intent.putExtra("switchingInfoMap",(Serializable) map1);
                                        startActivity(intent);
                                        break;
                                    case 2:     //删除
                                        new android.app.AlertDialog.Builder(SwitchingInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要删除该开关机记录吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        switching_basic_info_list_current.remove(position);
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
     * 为筛选界面中的起止时间选择设置监听器
     */
    private void setListenerForChooseDateTV() {
        //起始时间
        switching_search_startTime_edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(SwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = new Date(date.getTime());
                            switching_search_startTime_value = sm.format(d);
                            switching_search_startTime_edt.setText(switching_search_startTime_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
        //结束时间
        switching_search_stopTime_edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerView pvTime = new TimePickerView.Builder(SwitchingInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = new Date(date.getTime());
                            switching_search_stopTime_value = sm.format(d);
                            switching_search_stopTime_edt.setText(switching_search_stopTime_value);
                        }
                    }).build();
                    pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                    pvTime.show();
                }
            }
        });
    }

    /**
     * 为筛选界面中的三个按钮设置点击监听器
     */
    private void setOnClickListenerForThreeButtons() {
        //为筛选侧栏中的取消按钮设置点击监听事件
        switching_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switching_search_startTime_edt.setText("");   //查询时间起
                switching_search_stopTime_edt.setText("");    //查询时间止
                switching_search_userName_edt.setText("");   //使用人
                switching_search_startTime_value = "";   //查询时间起
                switching_search_stopTime_value = "";    //查询时间止
                switching_search_userName_value = "";   //使用人
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        //为筛选侧栏中的重置按钮设置点击监听事件
        switching_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switching_search_startTime_edt.setText("");   //查询时间起
                switching_search_stopTime_edt.setText("");    //查询时间止
                switching_search_userName_edt.setText("");   //使用人
                switching_search_startTime_value = "";   //查询时间起
                switching_search_stopTime_value = "";    //查询时间止
                switching_search_userName_value = "";   //使用人
            }
        });
        //为筛选侧栏中的确定按钮设置点击监听事件
        switching_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switching_search_userName_value = switching_search_userName_edt.getText().toString();
                Log.d("筛选界面中的查询时间起：",switching_search_startTime_value);
                Log.d("筛选界面中的查询时间止：",switching_search_stopTime_value);
                Log.d("筛选界面中的使用人：",switching_search_userName_value);
                switching_basic_info_list_result = getSearchResultList(switching_search_startTime_value,switching_search_stopTime_value,switching_search_userName_value);
                switching_basic_info_list_current = (ArrayList<HashMap<String, Object>>) switching_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                switching_search_startTime_edt.setText("");   //查询时间起
                switching_search_stopTime_edt.setText("");    //查询时间止
                switching_search_userName_edt.setText("");   //使用人
                switching_search_startTime_value = "";   //查询时间起
                switching_search_stopTime_value = "";    //查询时间止
                switching_search_userName_value = "";   //使用人
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选逻辑
     * @param switching_search_startTime_value：查询时间起
     * @param switching_search_stopTime_value：查询时间止
     * @param switching_search_userName_value：使用人
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String switching_search_startTime_value, String switching_search_stopTime_value, String switching_search_userName_value) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("000");
        if (!"".equals(switching_search_startTime_value))
            tag.setCharAt(0,'1');
        if (!"".equals(switching_search_stopTime_value))
            tag.setCharAt(1,'1');
        if (!"".equals(switching_search_userName_value))
            tag.setCharAt(2,'1');
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //000：表示三个筛选条件均无效
                isEffctive = false;
                break;
            case 1:     //001：表示使用人条件有效
                for (int i = 0; i < switching_basic_info_list_original.size(); i++) {
                    if (switching_basic_info_list_original.get(i).get("switching_userName").toString().contains(switching_search_userName_value)){
                        list.add(switching_basic_info_list_original.get(i));
                    }
                }
                break;
            case 2:     //010：表示查询时间止条件有效
                for (int i = 0; i < switching_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        Date toDate = simpleFormat.parse(switching_search_stopTime_value);
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
                        if (days >= 0)
                            list.add(switching_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 3:     //011：表示查询时间止和使用人条件有效
                for (int i = 0; i < switching_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        Date toDate = simpleFormat.parse(switching_search_stopTime_value);
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
                        if (days >= 0 && switching_basic_info_list_original.get(i).get("switching_userName").toString().contains(switching_search_userName_value))
                            list.add(switching_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 4:     //100：表示查询时间起条件有效
                for (int i = 0; i < switching_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(switching_search_startTime_value);
                        Date toDate = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
                        if (days >= 0)
                            list.add(switching_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 5:     //101：表示查询时间起和使用人条件有效
                for (int i = 0; i < switching_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(switching_search_startTime_value);
                        Date toDate = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
                        if (days >= 0 && switching_basic_info_list_original.get(i).get("switching_userName").toString().contains(switching_search_userName_value))
                            list.add(switching_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 6:     //110：表示查询时间起和查询时间止条件有效
                for (int i = 0; i < switching_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        Date toDate = simpleFormat.parse(switching_search_stopTime_value);
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));

                        Date fromDate1 = simpleFormat.parse(switching_search_startTime_value);
                        Date toDate1 = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        long from11 = fromDate1.getTime();
                        long to11 = toDate1.getTime();
                        int days1 = (int) ((to11 - from11) / (1000 * 60 * 60 * 24));

                        if (days >= 0 && days1 >= 0)
                            list.add(switching_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 7:     //表示三个筛选条件有效
                for (int i = 0; i < switching_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        Date toDate = simpleFormat.parse(switching_search_stopTime_value);
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));

                        Date fromDate1 = simpleFormat.parse(switching_search_startTime_value);
                        Date toDate1 = simpleFormat.parse(switching_basic_info_list_original.get(i).get("switching_useDate").toString());
                        long from11 = fromDate1.getTime();
                        long to11 = toDate1.getTime();
                        int days1 = (int) ((to11 - from11) / (1000 * 60 * 60 * 24));

                        if (days >= 0 && days1 >= 0 && switching_basic_info_list_original.get(i).get("switching_userName").toString().contains(switching_search_userName_value))
                            list.add(switching_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return switching_basic_info_list_original;
        }
    }
}
