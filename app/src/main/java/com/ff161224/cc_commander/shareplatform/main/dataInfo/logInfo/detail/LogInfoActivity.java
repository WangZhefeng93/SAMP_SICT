package com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.detail;

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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.create.CreateLogInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.edit.EditLogInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.sampleInfo.edit.EditSampleInfoActivity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class LogInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_log_tv;  //新建按钮
    private TextView tittle_choose_tv;  //筛选按钮
    private ListView log_basic_info_listview;   //日志信息下拉列表
    private EditText log_search_orderFormNo_edt;    //委托单编号
    private EditText log_search_userName_edt;   //使用人
    private TextView log_search_startTime_tv;   //查询时间起
    private TextView log_search_stopTime_tv;    //查询时间止
    private TextView log_canncle_search_tv; //取消
    private TextView log_reset_search_tv;   //重置
    private TextView log_ok_search_tv;  //确定

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> log_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> log_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> log_basic_info_list_result = new ArrayList<>();
    private CharSequence []items = {"编辑","查看","删除"};
    private String log_search_orderFormNo_value = "";    //委托单编号
    private String log_search_userName_value = "";   //使用人
    private String log_search_startTime_value = "";   //查询时间起
    private String log_search_stopTime_value = "";    //查询时间止

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_log_info);
        //绑定控件变量
        initWidget();
        //为新建按钮设置点击监听器
        setOnClickListenerForCreateProjectTV();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchProject();
        //开启子线程获取分析项目信息
        log_basic_info_list_original = getLogInfo();
        log_basic_info_list_current = (ArrayList<HashMap<String, Object>>) log_basic_info_list_original.clone();
        //为日志信息ListView设置适配器
        createBaseAdapterForLogtListView();
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
        tittle_create_log_tv = (TextView) findViewById(R.id.tittle_create_log_tv);  //新建按钮
        tittle_choose_tv = (TextView) findViewById(R.id.tittle_choose_tv);  //筛选按钮
        log_basic_info_listview = (ListView) findViewById(R.id.log_basic_info_listview);   //日志信息下拉列表
        log_search_orderFormNo_edt = (EditText) findViewById(R.id.log_search_orderFormNo_edt);    //委托单编号
        log_search_userName_edt = (EditText) findViewById(R.id.log_search_userName_edt);   //使用人
        log_search_startTime_tv = (TextView) findViewById(R.id.log_search_startTime_tv);   //查询时间起
        log_search_stopTime_tv = (TextView) findViewById(R.id.log_search_stopTime_tv);    //查询时间止
        log_canncle_search_tv = (TextView) findViewById(R.id.log_canncle_search_tv); //取消
        log_reset_search_tv = (TextView) findViewById(R.id.log_reset_search_tv);   //重置
        log_ok_search_tv = (TextView) findViewById(R.id.log_ok_search_tv);  //确定
    }

    /**
     * 为新建按钮设置点击监听器
     */
    private void setOnClickListenerForCreateProjectTV() {
        tittle_create_log_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LogInfoActivity.this, CreateLogInfoActivity.class);
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
     * 获取日志信息
     * @return
     */
    private ArrayList<HashMap<String,Object>> getLogInfo() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("log_orderFormNo","2019-01-09-YY0001");
        map1.put("log_checkDate","2019-02-28");
        map1.put("log_startTime","00:00");
        map1.put("log_stopTime","17:03");
        map1.put("log_checkHours","17.05");
        map1.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map1.put("log_userName","李光");
        map1.put("log_state","刷卡生成");
        map1.put("log_beforeState","");
        map1.put("log_afterState","");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("log_orderFormNo","2019-01-09-YY0001");
        map2.put("log_checkDate","2019-02-27");
        map2.put("log_startTime","00:00");
        map2.put("log_stopTime","23:59");
        map2.put("log_checkHours","24");
        map2.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map2.put("log_userName","李光");
        map2.put("log_state","刷卡生成");
        map2.put("log_beforeState","");
        map2.put("log_afterState","");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("log_orderFormNo","2019-01-09-YY0001");
        map3.put("log_checkDate","2019-02-26");
        map3.put("log_startTime","00:00");
        map3.put("log_stopTime","23:59");
        map3.put("log_checkHours","24");
        map3.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map3.put("log_userName","李光");
        map3.put("log_state","刷卡生成");
        map3.put("log_beforeState","");
        map3.put("log_afterState","");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("log_orderFormNo","2019-01-09-YY0001");
        map4.put("log_checkDate","2019-02-25");
        map4.put("log_startTime","00:00");
        map4.put("log_stopTime","23:59");
        map4.put("log_checkHours","24");
        map4.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map4.put("log_userName","李光");
        map4.put("log_state","刷卡生成");
        map4.put("log_beforeState","");
        map4.put("log_afterState","");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("log_orderFormNo","2019-01-09-YY0001");
        map5.put("log_checkDate","2019-02-24");
        map5.put("log_startTime","00:00");
        map5.put("log_stopTime","23:59");
        map5.put("log_checkHours","24");
        map5.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map5.put("log_userName","李光");
        map5.put("log_state","刷卡生成");
        map5.put("log_beforeState","");
        map5.put("log_afterState","");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("log_orderFormNo","2019-01-09-YY0001");
        map6.put("log_checkDate","2019-02-23");
        map6.put("log_startTime","00:00");
        map6.put("log_stopTime","23:59");
        map6.put("log_checkHours","24");
        map6.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map6.put("log_userName","李光");
        map6.put("log_state","刷卡生成");
        map6.put("log_beforeState","");
        map6.put("log_afterState","");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("log_orderFormNo","2019-01-09-YY0001");
        map7.put("log_checkDate","2019-02-22");
        map7.put("log_startTime","00:00");
        map7.put("log_stopTime","23:59");
        map7.put("log_checkHours","24");
        map7.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map7.put("log_userName","李光");
        map7.put("log_state","刷卡生成");
        map7.put("log_beforeState","");
        map7.put("log_afterState","");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("log_orderFormNo","2019-01-09-YY0001");
        map8.put("log_checkDate","2019-02-21");
        map8.put("log_startTime","00:00");
        map8.put("log_stopTime","23:59");
        map8.put("log_checkHours","24");
        map8.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map8.put("log_userName","李光");
        map8.put("log_state","刷卡生成");
        map8.put("log_beforeState","");
        map8.put("log_afterState","");
        list.add(map8);

        HashMap<String,Object> map9 = new HashMap<>();
        map9.put("log_orderFormNo","2019-01-09-YY0001");
        map9.put("log_checkDate","2019-02-20");
        map9.put("log_startTime","09:12");
        map9.put("log_stopTime","23:59");
        map9.put("log_checkHours","14.79");
        map9.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map9.put("log_userName","李光");
        map9.put("log_state","刷卡生成");
        map9.put("log_beforeState","");
        map9.put("log_afterState","");
        list.add(map9);

        HashMap<String,Object> map10 = new HashMap<>();
        map10.put("log_orderFormNo","2019-01-09-YY0001");
        map10.put("log_checkDate","2019-02-19");
        map10.put("log_startTime","10:26");
        map10.put("log_stopTime","17:07");
        map10.put("log_checkHours","6.68");
        map10.put("log_workContent","检测分析(2019-01-09-YY0001)样品");
        map10.put("log_userName","李光");
        map10.put("log_state","刷卡生成");
        map10.put("log_beforeState","");
        map10.put("log_afterState","");
        list.add(map10);

        return list;
    }

    /**
     * 为日志信息ListView设置适配器
     */
    private void createBaseAdapterForLogtListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (log_basic_info_list_current.size() > 0)
                    return log_basic_info_list_current.size();
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
                if (log_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_standard_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.log_id_tv = (TextView) convertView.findViewById(R.id.standard_id_tv);
                    viewHolder.log_orderFormNo_tv = (TextView) convertView.findViewById(R.id.standard_no_tv);
                    viewHolder.log_checkDate_tv = (TextView) convertView.findViewById(R.id.standard_name_tv);
                    viewHolder.log_workContent_tv = (TextView) convertView.findViewById(R.id.standard_type_tv);
                    viewHolder.log_userName_tv = (TextView) convertView.findViewById(R.id.standard_state_tv);
                    //设置适配器控件的属性值
                    viewHolder.log_id_tv.setText((position+1)+"、");
                    viewHolder.log_orderFormNo_tv.setText("委托单编号："+log_basic_info_list_current.get(position).get("log_orderFormNo").toString());
                    viewHolder.log_checkDate_tv.setText("检测日期："+log_basic_info_list_current.get(position).get("log_checkDate").toString());
                    viewHolder.log_workContent_tv.setText("检测内容："+log_basic_info_list_current.get(position).get("log_workContent").toString());
                    viewHolder.log_userName_tv.setText("使用人："+log_basic_info_list_current.get(position).get("log_userName").toString());
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
                protected TextView text1,log_id_tv,log_orderFormNo_tv,
                        log_checkDate_tv,log_workContent_tv,
                        log_userName_tv;
            }
        };
        //将分析项目ListView适配器绑定适配器
        log_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        log_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(LogInfoActivity.this)
                        .setTitle("操作日志信息")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     //编辑
                                        HashMap<String,Object> map = log_basic_info_list_current.get(position);
                                        intent = new Intent(LogInfoActivity.this, EditLogInfoActivity.class);
                                        intent.putExtra("logInfoMap",(Serializable) map);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 1:     //查看
                                        HashMap<String,Object> map1 = log_basic_info_list_current.get(position);
                                        intent = new Intent(LogInfoActivity.this, LogInfoDetailActivity.class);
                                        intent.putExtra("logInfoMap",(Serializable) map1);
                                        startActivity(intent);
                                        break;
                                    case 2:     //删除
                                        new android.app.AlertDialog.Builder(LogInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要删除该日志信息吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        log_basic_info_list_current.remove(position);
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
        log_search_startTime_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(LogInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = new Date(date.getTime());
                        log_search_startTime_value = sm.format(d);
                        log_search_startTime_tv.setText(log_search_startTime_value);
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
        //结束时间
        log_search_stopTime_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(LogInfoActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = new Date(date.getTime());
                        log_search_stopTime_value = sm.format(d);
                        log_search_stopTime_tv.setText(log_search_stopTime_value);
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
    }

    /**
     * 为筛选界面中的三个按钮设置点击监听器
     */
    private void setOnClickListenerForThreeButtons() {
        //为筛选侧栏中的取消按钮设置点击监听事件
        log_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                log_search_orderFormNo_edt.setText("");    //委托单编号
                log_search_userName_edt.setText("");   //使用人
                log_search_startTime_tv.setText("点击选择起始查询时间");   //查询时间起
                log_search_stopTime_tv.setText("点击选择截止查询时间");    //查询时间止
                log_search_startTime_value = "";   //查询时间起
                log_search_stopTime_value = "";    //查询时间止
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        //为筛选侧栏中的重置按钮设置点击监听事件
        log_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                log_search_orderFormNo_edt.setText("");    //委托单编号
                log_search_userName_edt.setText("");   //使用人
                log_search_startTime_tv.setText("点击选择起始查询时间");   //查询时间起
                log_search_stopTime_tv.setText("点击选择截止查询时间");    //查询时间止
                log_search_startTime_value = "";   //查询时间起
                log_search_stopTime_value = "";    //查询时间止
            }
        });
        //为筛选侧栏中的确定按钮设置点击监听事件
        log_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_search_orderFormNo_value = log_search_orderFormNo_edt.getText().toString();    //委托单编号
                log_search_userName_value = log_search_userName_edt.getText().toString();   //使用人
                Log.d("筛选界面的委托单编号值：","".equals(log_search_orderFormNo_value)?"空值":log_search_orderFormNo_value);
                Log.d("筛选界面的使用人值：","".equals(log_search_userName_value)?"空值":log_search_userName_value);
                Log.d("筛选界面的查询时间起值：","".equals(log_search_startTime_value)?"空值":log_search_startTime_value);
                Log.d("筛选界面的查询时间止值：","".equals(log_search_stopTime_value)?"空值":log_search_stopTime_value);
                log_basic_info_list_result = getSearchResultList(log_search_orderFormNo_value,log_search_userName_value,log_search_startTime_value,log_search_stopTime_value);
                log_basic_info_list_current = (ArrayList<HashMap<String, Object>>) log_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                //回复默认显示信息
                log_search_orderFormNo_edt.setText("");    //委托单编号
                log_search_userName_edt.setText("");   //使用人
                log_search_startTime_tv.setText("点击选择起始查询时间");   //查询时间起
                log_search_stopTime_tv.setText("点击选择截止查询时间");    //查询时间止
                log_search_startTime_value = "";   //查询时间起
                log_search_stopTime_value = "";    //查询时间止
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选条件逻辑
     * @param log_search_orderFormNo_value：委托单号
     * @param log_search_userName_value：使用人
     * @param log_search_startTime_value：查询时间起
     * @param log_search_stopTime_value：查询时间止
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String log_search_orderFormNo_value, String log_search_userName_value, String log_search_startTime_value, String log_search_stopTime_value) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("0000");
        if (!"".equals(log_search_orderFormNo_value))
            tag.setCharAt(0,'1');
        if (!"".equals(log_search_userName_value))
            tag.setCharAt(1,'1');
        if (!"".equals(log_search_startTime_value))
            tag.setCharAt(2,'1');
        if (!"".equals(log_search_stopTime_value))
            tag.setCharAt(3,'1');
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //0000：四个筛选条件均无效
                isEffctive = false;
                break;
            case 1:     //0001：仅有查询时间止条件有效
                for (int i = 0; i < log_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(log_basic_info_list_original.get(i).get("log_checkDate").toString());
                        Date toDate = simpleFormat.parse(log_search_stopTime_value);
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
                        if (days >= 0)
                            list.add(log_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:     //0010：仅有查询时间起条件有效
                for (int i = 0; i < log_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate = simpleFormat.parse(log_search_startTime_value);
                        Date toDate = simpleFormat.parse(log_basic_info_list_original.get(i).get("log_checkDate").toString());
                        long from1 = fromDate.getTime();
                        long to1 = toDate.getTime();
                        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
                        if (days >= 0)
                            list.add(log_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 3:     //0011：查询时间起与查询时间止条件有效
                for (int i = 0; i < log_basic_info_list_original.size(); i++) {
                    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                    /*天数差*/
                    try {
                        Date fromDate1 = simpleFormat.parse(log_basic_info_list_original.get(i).get("log_checkDate").toString());
                        Date toDate1 = simpleFormat.parse(log_search_stopTime_value);
                        long from1 = fromDate1.getTime();
                        long to1 = toDate1.getTime();
                        int days1 = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));

                        Date fromDate2 = simpleFormat.parse(log_search_startTime_value);
                        Date toDate2 = simpleFormat.parse(log_basic_info_list_original.get(i).get("log_checkDate").toString());
                        long from2 = fromDate2.getTime();
                        long to2 = toDate2.getTime();
                        int days2 = (int) ((to2 - from2) / (1000 * 60 * 60 * 24));
                        if (days1 >= 0 && days2 >= 0)
                            list.add(log_basic_info_list_original.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return log_basic_info_list_original;
        }
    }
}
