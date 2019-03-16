package com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.select;

import android.app.Activity;
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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.logInfo.edit.EditLogInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.detail.PriceInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.select.SelectCreatePricePersonActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectUsePersonActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_choose_tv;  //筛选按钮
    private ListView person_basic_info_listview; //下拉列表
    private EditText person_search_name_edt; //姓名
    private EditText person_search_username_edt;    //用户名
    private TextView person_canncle_search_tv;  //取消按钮
    private TextView person_reset_search_tv;    //重置按钮
    private TextView person_ok_search_tv;   //确定按钮

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> person_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> person_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> person_basic_info_list_result = new ArrayList<>();
    private String person_search_name_value = "";
    private String person_search_username_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_select_use_person);
        //绑定控件变量
        initWidget();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchProject();
        //开启子线程获取分析项目信息
        person_basic_info_list_original = getProjectInfo();
        person_basic_info_list_current = (ArrayList<HashMap<String, Object>>) person_basic_info_list_original.clone();
        //为创建人ListView设置适配器
        createBaseAdapterForPersonListView();
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
        person_basic_info_listview = (ListView) findViewById(R.id.person_basic_info_listview); //下拉列表
        person_search_name_edt = (EditText) findViewById(R.id.person_search_name_edt); //姓名
        person_search_username_edt = (EditText) findViewById(R.id.person_search_username_edt);    //用户名
        person_canncle_search_tv = (TextView) findViewById(R.id.person_canncle_search_tv);  //取消按钮
        person_reset_search_tv = (TextView) findViewById(R.id.person_reset_search_tv);    //重置按钮
        person_ok_search_tv = (TextView) findViewById(R.id.person_ok_search_tv);   //确定按钮
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
        map1.put("person_username","nigcqcao");
        map1.put("person_name","曹长群");
        map1.put("person_phone","151");
        map1.put("person_mail","cqcao@nigpas.ac.cn");
        map1.put("person_unit","南京地质古生物研究所");
        map1.put("person_group","曹长群课题组");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("person_username","nigczwang");
        map2.put("person_name","王春朝");
        map2.put("person_phone","2233");
        map2.put("person_mail","czwang@nigpas.ac.cn");
        map2.put("person_unit","南京地质古生物研究所");
        map2.put("person_group","南京地质古生物研究所公共技术服务中心");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("person_username","nigxmchen");
        map3.put("person_name","陈小明");
        map3.put("person_phone","270");
        map3.put("person_mail","xmchen@nigpas.ac.cn");
        map3.put("person_unit","南京地质古生物研究所");
        map3.put("person_group","返聘");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("person_username","niglmfeng");
        map4.put("person_name","冯立梅");
        map4.put("person_phone","83282265");
        map4.put("person_mail","fenglm@nigpas.ac.cn");
        map4.put("person_unit","南京地质古生物研究所");
        map4.put("person_group","南京地质古生物研究所公共技术服务中心");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("person_username","nigjliu");
        map5.put("person_name","刘静");
        map5.put("person_phone","83282268");
        map5.put("person_mail","liujing@nipgas.ac.cn");
        map5.put("person_unit","南京地质古生物研究所");
        map5.put("person_group","南京地质古生物研究所公共技术服务中心");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("person_username","nigyjli");
        map6.put("person_name","李一军");
        map6.put("person_phone","83282288");
        map6.put("person_mail","yijunli@nigpas.ac.cn");
        map6.put("person_unit","南京地质古生物研究所");
        map6.put("person_group","管理组");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("person_username","maoyongqiang");
        map7.put("person_name","茅永强");
        map7.put("person_phone","83282268");
        map7.put("person_mail","yqmao@nigpas.ac.cn");
        map7.put("person_unit","南京地质古生物研究所");
        map7.put("person_group","返聘");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("person_username","nigzjchen");
        map8.put("person_name","陈周庆");
        map8.put("person_phone","83282216");
        map8.put("person_mail","zqchen@nigpas.ac.cn");
        map8.put("person_unit","南京地质古生物研究所");
        map8.put("person_group","南京地质古生物研究所公共技术服务中心");
        list.add(map8);

        HashMap<String,Object> map9 = new HashMap<>();
        map9.put("person_username","nigyfang");
        map9.put("person_name","方艳");
        map9.put("person_phone","2276");
        map9.put("person_mail","yanfang@nigpas.ac.cn");
        map9.put("person_unit","南京地质古生物研究所");
        map9.put("person_group","南京地质古生物研究所公共技术服务中心");
        list.add(map9);

        HashMap<String,Object> map10 = new HashMap<>();
        map10.put("person_username","nigxtcheng");
        map10.put("person_name","程西亭");
        map10.put("person_phone","025-83282258");
        map10.put("person_mail","xtcheng@nigpas.ac.cn");
        map10.put("person_unit","南京地质古生物研究所");
        map10.put("person_group","返聘");
        list.add(map10);
        return list;
    }

    /**
     * 为创建人ListView设置适配器
     */
    private void createBaseAdapterForPersonListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (person_basic_info_list_current.size() > 0)
                    return person_basic_info_list_current.size();
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
                if (person_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_person_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.person_no_tv = (TextView) convertView.findViewById(R.id.person_no_tv);
                    viewHolder.person_username_tv = (TextView) convertView.findViewById(R.id.person_username_tv);
                    viewHolder.person_name_tv = (TextView) convertView.findViewById(R.id.person_name_tv);
                    viewHolder.person_phone_tv = (TextView) convertView.findViewById(R.id.person_phone_tv);
                    viewHolder.person_mail_tv = (TextView) convertView.findViewById(R.id.person_mail_tv);
                    viewHolder.person_unit_tv = (TextView) convertView.findViewById(R.id.person_unit_tv);
                    viewHolder.person_group_tv = (TextView) convertView.findViewById(R.id.person_group_tv);
                    //设置适配器控件的属性值
                    viewHolder.person_no_tv.setText((position+1)+"、");
                    viewHolder.person_username_tv.setText("用户名："+person_basic_info_list_current.get(position).get("person_username").toString());
                    viewHolder.person_name_tv.setText("姓名："+person_basic_info_list_current.get(position).get("person_name").toString());
                    viewHolder.person_phone_tv.setText("电话："+person_basic_info_list_current.get(position).get("person_phone").toString());
                    viewHolder.person_mail_tv.setText("邮箱："+person_basic_info_list_current.get(position).get("person_mail").toString());
                    viewHolder.person_unit_tv.setText("单位："+person_basic_info_list_current.get(position).get("person_unit").toString());
                    viewHolder.person_group_tv.setText("研究组："+person_basic_info_list_current.get(position).get("person_group").toString());
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
                protected TextView text1,person_no_tv,person_username_tv,
                        person_name_tv,person_phone_tv,
                        person_mail_tv,person_unit_tv,person_group_tv;
            }
        };
        //将创建人ListView适配器绑定适配器
        person_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        person_basic_info_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                intent = new Intent(SelectUsePersonActivity.this, EditLogInfoActivity.class);
                intent.putExtra("log_userName",person_basic_info_list_current.get(position).get("person_name").toString());
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
        person_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                person_search_name_edt.setText("");
                person_search_username_edt.setText("");
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        //为筛选侧栏中的重置按钮设置点击监听事件
        person_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                person_search_name_edt.setText("");
                person_search_username_edt.setText("");
            }
        });

        //为筛选侧栏中的确定按钮设置点击监听事件
        person_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person_search_name_value = person_search_name_edt.getText().toString();
                person_search_username_value = person_search_username_edt.getText().toString();
                Log.d("筛选界面中姓名：",person_search_name_value);
                Log.d("筛选界面中用户名：",person_search_username_value);
                person_basic_info_list_result = getSearchResultList(person_search_name_value,person_search_username_value);
                person_basic_info_list_current = (ArrayList<HashMap<String, Object>>) person_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                //回复默认显示信息
                person_search_name_edt.setText("");
                person_search_username_edt.setText("");
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选信息逻辑，返回筛选结果集合
     * @param person_search_name_value：姓名
     * @param person_search_username_value：用户名
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String person_search_name_value, String person_search_username_value) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("00");
        if (!"".equals(person_search_name_value)){
            tag.setCharAt(0,'1');
        }
        if (!"".equals(person_search_username_value)){
            tag.setCharAt(1,'1');
        }
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //00：表示两个筛选条件均无效
                isEffctive = false;
                break;
            case 1:     //01：表示仅有用户名筛选条件有效
                for (int i = 0; i < person_basic_info_list_original.size(); i++) {
                    if (person_basic_info_list_original.get(i).get("person_username").toString().contains(person_search_username_value)){
                        list.add(person_basic_info_list_original.get(i));
                    }
                }
                break;
            case 2:     //10：表示仅有姓名筛选条件有效
                for (int i = 0; i < person_basic_info_list_original.size(); i++) {
                    if (person_basic_info_list_original.get(i).get("person_name").toString().contains(person_search_name_value)){
                        list.add(person_basic_info_list_original.get(i));
                    }
                }
                break;
            case 3:     //11：表示两个筛选条件均有效
                for (int i = 0; i < person_basic_info_list_original.size(); i++) {
                    if (person_basic_info_list_original.get(i).get("person_username").toString().contains(person_search_username_value)
                            && person_basic_info_list_original.get(i).get("person_name").toString().contains(person_search_name_value)){
                        list.add(person_basic_info_list_original.get(i));
                    }
                }
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return person_basic_info_list_original;
        }
    }
}
