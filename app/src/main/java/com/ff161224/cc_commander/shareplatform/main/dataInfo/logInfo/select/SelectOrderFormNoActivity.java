package com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.select;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.create.CreateLogInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectOrderFormNoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_choose_tv;  //筛选按钮
    private ListView orderFormNo_basic_info_listview;   //委托单信息下拉列表
    private EditText orderForm_search_no_edt;   //委托单编号
    private EditText orderForm_search_person_edt;   //委托人
    private EditText orderForm_search_project_edt;  //分析项目
    private TextView orderForm_canncle_search_tv;  //取消按钮
    private TextView orderForm_reset_search_tv;    //重置按钮
    private TextView orderForm_ok_search_tv;   //确定按钮

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> orderForm_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> orderForm_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> orderForm_basic_info_list_result = new ArrayList<>();
    private String orderForm_search_no_value = "";
    private String orderForm_search_person_value = "";
    private String orderForm_search_project_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_select_order_form_no);
        //绑定控件变量
        initWidget();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchProject();
        //开启子线程获取委托单信息
        orderForm_basic_info_list_original = getProjectInfo();
        orderForm_basic_info_list_current = (ArrayList<HashMap<String, Object>>) orderForm_basic_info_list_original.clone();
        //为创建人ListView设置适配器
        createBaseAdapterForOrderFormListView();
        //为ListView中的每一项设置监听器
        setClickListenerForListViewItem();
        //为筛选界面中的三个按钮设置点击监听器
        setOnClickListenerForThreeButtons();
    }

    /**
     * 绑定控件变量
     */
    private void initWidget() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);  //为侧栏布局绑定
        tittle_choose_tv = (TextView) findViewById(R.id.tittle_choose_tv);  //筛选按钮
        orderFormNo_basic_info_listview = (ListView) findViewById(R.id.orderFormNo_basic_info_listview);   //委托单信息下拉列表
        orderForm_search_no_edt = (EditText) findViewById(R.id.orderForm_search_no_edt);   //委托单编号
        orderForm_search_person_edt = (EditText) findViewById(R.id.orderForm_search_person_edt);   //委托人
        orderForm_search_project_edt = (EditText) findViewById(R.id.orderForm_search_project_edt);  //分析项目
        orderForm_canncle_search_tv = (TextView) findViewById(R.id.orderForm_canncle_search_tv);  //取消按钮
        orderForm_reset_search_tv = (TextView) findViewById(R.id.orderForm_reset_search_tv);    //重置按钮
        orderForm_ok_search_tv = (TextView) findViewById(R.id.orderForm_ok_search_tv);   //确定按钮
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
     * 开启子线程获取委托单信息
     * @return
     */
    private ArrayList<HashMap<String,Object>> getProjectInfo() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("orderForm_no","2019-03-11-YY0001");
        map1.put("orderForm_person","张华侨");
        map1.put("orderForm_project","化石三维无损成像");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("orderForm_no","2019-03-08-YY0001");
        map2.put("orderForm_person","黄璞");
        map2.put("orderForm_project","化石三维无损成像");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("orderForm_no","2019-03-05-YY0002");
        map3.put("orderForm_person","梁艳");
        map3.put("orderForm_project","化石三维无损成像");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("orderForm_no","2019-02-01-YY0005");
        map4.put("orderForm_person","赵方臣");
        map4.put("orderForm_project","化石三维无损成像");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("orderForm_no","2019-01-30-YY0002");
        map5.put("orderForm_person","王鑫");
        map5.put("orderForm_project","化石三维无损成像");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("orderForm_no","2019-01-29-YY0001");
        map6.put("orderForm_person","王博");
        map6.put("orderForm_project","化石三维无损成像");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("orderForm_no","2019-01-25-YY0001");
        map7.put("orderForm_person","殷宗军");
        map7.put("orderForm_project","化石三维无损成像");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("orderForm_no","2019-01-12-YY0001");
        map8.put("orderForm_person","张海春/姜慧");
        map8.put("orderForm_project","化石三维无损成像");
        list.add(map8);

        HashMap<String,Object> map9 = new HashMap<>();
        map9.put("orderForm_no","2018-12-21-YY0001");
        map9.put("orderForm_person","王博/张青青");
        map9.put("orderForm_project","化石三维无损成像");
        list.add(map9);

        HashMap<String,Object> map10 = new HashMap<>();
        map10.put("orderForm_no","2018-12-13-YY0001");
        map10.put("orderForm_person","张元动/武学进");
        map10.put("orderForm_project","化石三维无损成像");
        list.add(map10);
        return list;
    }

    /**
     * 为创建人ListView设置适配器
     */
    private void createBaseAdapterForOrderFormListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (orderForm_basic_info_list_current.size() > 0)
                    return orderForm_basic_info_list_current.size();
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
                if (orderForm_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_person_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.orderForm_id_tv = (TextView) convertView.findViewById(R.id.person_no_tv);
                    viewHolder.orderForm_no_tv = (TextView) convertView.findViewById(R.id.person_username_tv);
                    viewHolder.orderForm_person_tv = (TextView) convertView.findViewById(R.id.person_name_tv);
                    viewHolder.orderForm_project_tv = (TextView) convertView.findViewById(R.id.person_phone_tv);
                    viewHolder.person_mail_tv = (TextView) convertView.findViewById(R.id.person_mail_tv);
                    viewHolder.person_unit_tv = (TextView) convertView.findViewById(R.id.person_unit_tv);
                    viewHolder.person_group_tv = (TextView) convertView.findViewById(R.id.person_group_tv);
                    //设置适配器控件的属性值
                    viewHolder.orderForm_id_tv.setText((position+1)+"、");
                    viewHolder.orderForm_no_tv.setText("委托单编号："+orderForm_basic_info_list_current.get(position).get("orderForm_no").toString());
                    viewHolder.orderForm_person_tv.setText("委托人："+orderForm_basic_info_list_current.get(position).get("orderForm_person").toString());
                    viewHolder.orderForm_project_tv.setText("分析项目："+orderForm_basic_info_list_current.get(position).get("orderForm_project").toString());
                    viewHolder.person_mail_tv.setVisibility(View.GONE);
                    viewHolder.person_unit_tv.setVisibility(View.GONE);
                    viewHolder.person_group_tv.setVisibility(View.GONE);
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
                protected TextView text1,orderForm_id_tv,orderForm_no_tv,
                        orderForm_person_tv,orderForm_project_tv,
                        person_mail_tv,person_unit_tv,person_group_tv;
            }
        };
        //将创建人ListView适配器绑定适配器
        orderFormNo_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        orderFormNo_basic_info_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(SelectOrderFormNoActivity.this, CreateLogInfoActivity.class);
                intent.putExtra("log_orderFormNo",orderForm_basic_info_list_current.get(position).get("orderForm_no").toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }

    /**
     * 为筛选界面中的三个按钮设置点击监听器
     */
    private void setOnClickListenerForThreeButtons() {
        //为筛选侧栏中的取消按钮设置点击监听事件
        orderForm_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderForm_search_no_edt.setText("");   //委托单编号
                orderForm_search_person_edt.setText("");   //委托人
                orderForm_search_project_edt.setText("");  //分析项目
                orderForm_search_no_value = "";
                orderForm_search_person_value = "";
                orderForm_search_project_value = "";
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        //为筛选侧栏中的重置按钮设置点击监听事件
        orderForm_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderForm_search_no_edt.setText("");   //委托单编号
                orderForm_search_person_edt.setText("");   //委托人
                orderForm_search_project_edt.setText("");  //分析项目
                orderForm_search_no_value = "";
                orderForm_search_person_value = "";
                orderForm_search_project_value = "";
            }
        });
        //为筛选侧栏中的确定按钮设置点击监听事件
        orderForm_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderForm_search_no_value = orderForm_search_no_edt.getText().toString();
                orderForm_search_person_value = orderForm_search_person_edt.getText().toString();
                orderForm_search_project_value = orderForm_search_project_edt.getText().toString();
                Log.d("筛选界面中的委托单号：",orderForm_search_no_value);
                Log.d("筛选界面中的委托人：",orderForm_search_person_value);
                Log.d("筛选界面中的分析项目：",orderForm_search_project_value);
                orderForm_basic_info_list_result = getSearchResultList(orderForm_search_no_value,orderForm_search_person_value,orderForm_search_project_value);
                orderForm_basic_info_list_current = (ArrayList<HashMap<String, Object>>) orderForm_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                orderForm_search_no_edt.setText("");   //委托单编号
                orderForm_search_person_edt.setText("");   //委托人
                orderForm_search_project_edt.setText("");  //分析项目
                orderForm_search_no_value = "";
                orderForm_search_person_value = "";
                orderForm_search_project_value = "";
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选逻辑
     * @param orderForm_search_no_value：委托单号值
     * @param orderForm_search_person_value：委托人值
     * @param orderForm_search_project_value：分析项目值
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String orderForm_search_no_value, String orderForm_search_person_value, String orderForm_search_project_value) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("000");
        if (!"".equals(orderForm_search_no_value))
            tag.setCharAt(0,'1');
        if (!"".equals(orderForm_search_person_value))
            tag.setCharAt(1,'1');
        if (!"".equals(orderForm_search_project_value))
            tag.setCharAt(2,'1');
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //000：表示三个筛选条件均无效
                isEffctive = false;
                break;
            case 1:     //001：表示分析项目筛选条件有效
                for (int i = 0; i < orderForm_basic_info_list_original.size(); i++) {
                    if (orderForm_basic_info_list_original.get(i).get("orderForm_project").toString().contains(orderForm_search_project_value)){
                        list.add(orderForm_basic_info_list_original.get(i));
                    }
                }
                break;
            case 2:     //010：表示委托人筛选条件有效
                for (int i = 0; i < orderForm_basic_info_list_original.size(); i++) {
                    if (orderForm_basic_info_list_original.get(i).get("orderForm_person").toString().contains(orderForm_search_person_value)){
                        list.add(orderForm_basic_info_list_original.get(i));
                    }
                }
                break;
            case 3:     //011：表示委托人与分析项目筛选条件有效
                for (int i = 0; i < orderForm_basic_info_list_original.size(); i++) {
                    if (orderForm_basic_info_list_original.get(i).get("orderForm_person").toString().contains(orderForm_search_person_value) &&
                            orderForm_basic_info_list_original.get(i).get("orderForm_project").toString().contains(orderForm_search_project_value)){
                        list.add(orderForm_basic_info_list_original.get(i));
                    }
                }
                break;
            case 4:     //100：表示委托单编号筛选条件有效
                for (int i = 0; i < orderForm_basic_info_list_original.size(); i++) {
                    if (orderForm_basic_info_list_original.get(i).get("orderForm_no").toString().contains(orderForm_search_no_value)){
                        list.add(orderForm_basic_info_list_original.get(i));
                    }
                }
                break;
            case 5:     //101：表示委托单编号与分析项目筛选条件有效
                for (int i = 0; i < orderForm_basic_info_list_original.size(); i++) {
                    if (orderForm_basic_info_list_original.get(i).get("orderForm_no").toString().contains(orderForm_search_no_value) &&
                            orderForm_basic_info_list_original.get(i).get("orderForm_project").toString().contains(orderForm_search_project_value)){
                        list.add(orderForm_basic_info_list_original.get(i));
                    }
                }
                break;
            case 6:     //110：表示委托单编号与委托人筛选条件有效
                for (int i = 0; i < orderForm_basic_info_list_original.size(); i++) {
                    if (orderForm_basic_info_list_original.get(i).get("orderForm_no").toString().contains(orderForm_search_no_value) &&
                            orderForm_basic_info_list_original.get(i).get("orderForm_person").toString().contains(orderForm_search_person_value)){
                        list.add(orderForm_basic_info_list_original.get(i));
                    }
                }
                break;
            case 7:     //111：表示三个筛选条件均有效
                for (int i = 0; i < orderForm_basic_info_list_original.size(); i++) {
                    if (orderForm_basic_info_list_original.get(i).get("orderForm_no").toString().contains(orderForm_search_no_value) &&
                            orderForm_basic_info_list_original.get(i).get("orderForm_person").toString().contains(orderForm_search_person_value) &&
                            orderForm_basic_info_list_original.get(i).get("orderForm_project").toString().contains(orderForm_search_project_value)){
                        list.add(orderForm_basic_info_list_original.get(i));
                    }
                }
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return orderForm_basic_info_list_original;
        }
    }
}
