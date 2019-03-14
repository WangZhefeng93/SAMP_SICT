package com.ff161224.cc_commander.shareplatform.main.dataInfo.sampleInfo.detail;

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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.sampleInfo.create.CreateSampleInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.sampleInfo.edit.EditSampleInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SampleInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_sample_tv;   //新建按钮
    private TextView tittle_choose_tv;  //筛选按钮
    private ListView sample_basic_info_listview;    //下拉列表
    private EditText sample_search_name_edt;    //样品分类名称
    private Spinner sample_search_center_sp;    //所属中心
    private Spinner sample_search_unit_sp;  //所属单位
    private TextView sample_canncle_search_tv;  //取消按钮
    private TextView sample_reset_search_tv;    //重置按钮
    private TextView sample_ok_search_tv;   //确定按钮

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> sample_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> sample_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> sample_basic_info_list_result = new ArrayList<>();
    private CharSequence []items = {"编辑","切换状态","删除"};
    private String[] centerArrays = new String[]{"--请选择--","河北","南京地球资源环境大型仪器区域中心","辽宁"};   //所属中心下拉列表选项值
    private String[][] unitArrays = new String[][]{
            {"--请选择--"},
            {"--请选择--","石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水"},
            {"--请选择--","南京地质古生物研究所", "南京地理与湖泊研究所", "南京土壤研究所"},
            {"--请选择--","沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛"}
    };   //所属单位下拉列表选项值
    private ArrayAdapter<String> centerAdapter = null;   //所属中心下拉列表适配器
    private ArrayAdapter<String> unitAdapter = null;   //所属单位下拉列表适配器
    private String sample_search_name_value = "";    //筛选界面样品分类名称的值
    private String sample_search_center_value = "";    //筛选界面所属中心的值
    private int sample_search_center_position = -1;    //筛选界面所属中心的下标
    private String sample_search_unit_value = "";   //筛选界面所属单位的值
    private int sample_search_unit_position = -1;   //筛选界面所属单位的下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_sample_info);
        //绑定控件变量
        initWidget();
        //开启子线程获取样品分类信息
        sample_basic_info_list_original = getProjectInfo();
        sample_basic_info_list_current = (ArrayList<HashMap<String, Object>>) sample_basic_info_list_original.clone();
        //为样品分类信息ListView设置适配器
        createBaseAdapterForCoastListView();
        //为ListView中的每一项设置监听器
        setClickListenerForListViewItem();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchCoast();
        //设置筛选界面中的二级联动下拉菜单
        setDataForSpinner();
        //为筛选界面中的三个按钮设置点击监听器
        setOnClickListenerForThreeButtons();
        //为新建按钮设置点击监听器
        setOnClickListenerForCreateSampleTV();
    }

    /**
     * 绑定控件变量
     */
    private void initWidget() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);  //为侧栏布局绑定
        tittle_create_sample_tv = (TextView) findViewById(R.id.tittle_create_sample_tv);   //新建按钮
        tittle_choose_tv = (TextView) findViewById(R.id.tittle_choose_tv);  //筛选按钮
        sample_basic_info_listview = (ListView) findViewById(R.id.sample_basic_info_listview);    //下拉列表
        sample_search_name_edt = (EditText) findViewById(R.id.sample_search_name_edt);    //样品分类名称
        sample_search_center_sp = (Spinner) findViewById(R.id.sample_search_center_sp);    //所属中心
        sample_search_unit_sp = (Spinner) findViewById(R.id.sample_search_unit_sp);  //所属单位
        sample_canncle_search_tv = (TextView) findViewById(R.id.sample_canncle_search_tv);  //取消按钮
        sample_reset_search_tv = (TextView) findViewById(R.id.sample_reset_search_tv);    //重置按钮
        sample_ok_search_tv = (TextView) findViewById(R.id.sample_ok_search_tv);   //确定按钮
    }

    /**
     * 开启子线程获取样品分类信息
     * @return
     */
    public ArrayList<HashMap<String,Object>> getProjectInfo() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("sample_name","岩石薄片制备");
        map1.put("sample_state","正常");
        map1.put("sample_state_flag","1");
        map1.put("sample_center","南京地球资源环境大型仪器区域中心");
        map1.put("sample_unit","南京地质古生物研究所");
        map1.put("sample_note","说了的可否沃尔");
        list.add(map1);

        HashMap<String,Object> map11 = new HashMap<>();
        map11.put("sample_name","灰岩、碳酸盐");
        map11.put("sample_state","正常");
        map11.put("sample_state_flag","1");
        map11.put("sample_center","南京地球资源环境大型仪器区域中心");
        map11.put("sample_unit","南京地质古生物研究所");
        map11.put("sample_note","说了的可否沃尔");
        list.add(map11);

        HashMap<String,Object> map111 = new HashMap<>();
        map111.put("sample_name","硫化银、硫化钡及其他含硫物质");
        map111.put("sample_state","正常");
        map111.put("sample_state_flag","1");
        map111.put("sample_center","南京地球资源环境大型仪器区域中心");
        map111.put("sample_unit","南京地质古生物研究所");
        map111.put("sample_note","说了的可否沃尔");
        list.add(map111);


        HashMap<String,Object> map11111 = new HashMap<>();
        map11111.put("sample_name","固体");
        map11111.put("sample_state","正常");
        map11111.put("sample_state_flag","1");
        map11111.put("sample_center","南京地球资源环境大型仪器区域中心");
        map11111.put("sample_unit","南京地质古生物研究所");
        map11111.put("sample_note","说了的可否沃尔");
        list.add(map11111);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("sample_name","硅质或含硅质岩石");
        map6.put("sample_state","正常");
        map6.put("sample_state_flag","1");
        map6.put("sample_center","南京地球资源环境大型仪器区域中心");
        map6.put("sample_unit","南京地质古生物研究所");
        map6.put("sample_note","说了的可否沃尔");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("sample_name","薄片");
        map7.put("sample_state","正常");
        map7.put("sample_state_flag","1");
        map7.put("sample_center","南京地球资源环境大型仪器区域中心");
        map7.put("sample_unit","南京地质古生物研究所");
        map7.put("sample_note","说了的可否沃尔");
        list.add(map7);

        return list;
    }

    /**
     * 为样品分类信息ListView设置适配器
     */
    private void createBaseAdapterForCoastListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (sample_basic_info_list_current.size() > 0)
                    return sample_basic_info_list_current.size();
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
                if (sample_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_coast_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.sample_no_tv = (TextView) convertView.findViewById(R.id.coast_no_tv);
                    viewHolder.sample_name_tv = (TextView) convertView.findViewById(R.id.coast_id_tv);
                    viewHolder.sample_state_tv = (TextView) convertView.findViewById(R.id.coast_name_tv);
                    viewHolder.sample_center_tv = (TextView) convertView.findViewById(R.id.coast_unit_tv);
                    viewHolder.sample_unit_tv = (TextView) convertView.findViewById(R.id.coast_price_tv);
                    viewHolder.sample_note_tv = (TextView) convertView.findViewById(R.id.coast_num_tv);
                    viewHolder.hiden_tv = (TextView) convertView.findViewById(R.id.coast_description_tv);
                    //设置适配器控件的属性值
                    viewHolder.sample_no_tv.setText((position+1)+"、");
                    viewHolder.sample_name_tv.setText("名称："+sample_basic_info_list_current.get(position).get("sample_name").toString());
                    viewHolder.sample_state_tv.setText("状态："+sample_basic_info_list_current.get(position).get("sample_state").toString());
                    viewHolder.sample_center_tv.setText("中心："+sample_basic_info_list_current.get(position).get("sample_center").toString());
                    viewHolder.sample_unit_tv.setText("单位："+sample_basic_info_list_current.get(position).get("sample_unit").toString());
                    viewHolder.sample_note_tv.setText("备注："+sample_basic_info_list_current.get(position).get("sample_note").toString());
                    viewHolder.hiden_tv.setVisibility(View.GONE);
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
                protected TextView text1,sample_no_tv,sample_name_tv,sample_state_tv,
                        sample_center_tv,sample_unit_tv,
                        sample_note_tv,hiden_tv;
            }
        };
        //将耗材信息ListView适配器绑定适配器
        sample_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        sample_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(SampleInfoActivity.this)
                        .setTitle("操作样品分类信息")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     //编辑
                                        intent = new Intent(SampleInfoActivity.this, EditSampleInfoActivity.class);
                                        HashMap<String,Object> map = sample_basic_info_list_current.get(position);
                                        intent.putExtra("sampleInfoMap",(Serializable) map);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 1:     //停用
                                        if ("正常".equals(sample_basic_info_list_current.get(position).get("sample_state").toString())){
                                            new android.app.AlertDialog.Builder(SampleInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                    .setMessage("您确定要停用样品分类：\n"+sample_basic_info_list_current.get(position).get("sample_name").toString()+"吗？")//设置显示的内容
                                                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                            // TODO Auto-generated method stub
                                                            sample_basic_info_list_current.get(position).put("sample_state","停用");
                                                            sample_basic_info_list_current.get(position).put("sample_state_flag","0");
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
                                            new android.app.AlertDialog.Builder(SampleInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                    .setMessage("您确定要启用样品分类：\n"+sample_basic_info_list_current.get(position).get("sample_name").toString()+"吗？")//设置显示的内容
                                                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                            // TODO Auto-generated method stub
                                                            sample_basic_info_list_current.get(position).put("sample_state","正常");
                                                            sample_basic_info_list_current.get(position).put("sample_state_flag","1");
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
                                    case 2:     //删除
                                        new android.app.AlertDialog.Builder(SampleInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要删除样品分类：\n"+sample_basic_info_list_current.get(position).get("sample_name").toString()+"吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        sample_basic_info_list_current.remove(position);
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
     * 设置筛选界面中的二级联动下拉菜单
     */
    private void setDataForSpinner() {
        //绑定适配器和值
        centerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,centerArrays);
        sample_search_center_sp.setAdapter(centerAdapter);
        sample_search_center_sp.setSelection(0,true);
        unitAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,unitArrays[0]);
        sample_search_unit_sp.setAdapter(unitAdapter);
        sample_search_unit_sp.setSelection(0,true);
        //设置所属中心监听事件
        sample_search_center_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitAdapter = new ArrayAdapter<String>(SampleInfoActivity.this,android.R.layout.simple_spinner_item,unitArrays[position]);
                sample_search_unit_sp.setAdapter(unitAdapter);
                sample_search_center_value = centerArrays[position];    //筛选界面所属中心的值
                sample_search_center_position = position;    //筛选界面所属中心的下标
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置所属单位监听事件
        sample_search_unit_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sample_search_unit_position = position;
                sample_search_unit_value = unitArrays[sample_search_center_position][position];
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
        sample_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                sample_search_name_edt.setText("");
                sample_search_center_sp.setSelection(0,true);
                sample_search_unit_sp.setSelection(0,true);
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        //为筛选侧栏中的重置按钮设置点击监听事件
        sample_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                sample_search_name_edt.setText("");
                sample_search_center_sp.setSelection(0,true);
                sample_search_unit_sp.setSelection(0,true);
            }
        });

        //为筛选侧栏中的确定按钮设置点击监听事件
        sample_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sample_search_name_value = sample_search_name_edt.getText().toString();
                Log.d("筛选界面中样品分类名称：",sample_search_name_value);
                Log.d("筛选界面中所属中心：",sample_search_center_value);
                Log.d("筛选界面中所属中心下标：",sample_search_center_position+"");
                Log.d("筛选界面中所属单位：",sample_search_unit_value);
                Log.d("筛选界面中所属单位下标：",sample_search_unit_position+"");

                sample_basic_info_list_result = getSearchResultList(sample_search_name_value,sample_search_center_position,sample_search_unit_position);
                sample_basic_info_list_current = (ArrayList<HashMap<String, Object>>) sample_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                //回复默认显示信息
                sample_search_name_edt.setText("");
                sample_search_center_sp.setSelection(0,true);
                sample_search_unit_sp.setSelection(0,true);
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选信息逻辑，返回筛选结果集合
     * @param sample_search_name_value：样品分类名称
     * @param sample_search_center_position：所属中心下标
     * @param sample_search_unit_position：所属单位下标
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String sample_search_name_value, int sample_search_center_position, int sample_search_unit_position) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("000");
        if (!"".equals(sample_search_name_value))
            tag.setCharAt(0,'1');
        if (sample_search_center_position > 0)
            tag.setCharAt(1,'1');
        if (sample_search_unit_position > 0)
            tag.setCharAt(2,'1');
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //000：表示三个条件均无效
                isEffctive = false;
                break;
            case 1:     //001：
                break;
            case 2:     //010：表示仅有所属中心有效
                for (int i = 0; i < sample_basic_info_list_original.size(); i++) {
                    if (centerArrays[sample_search_center_position].equals(sample_basic_info_list_original.get(i).get("sample_center").toString())){
                        list.add(sample_basic_info_list_original.get(i));
                    }
                }
                break;
            case 3:     //011：表示所属中心和所属单位均有效
                for (int i = 0; i < sample_basic_info_list_original.size(); i++) {
                    if (centerArrays[sample_search_center_position].equals(sample_basic_info_list_original.get(i).get("sample_center").toString()) &&
                            unitArrays[sample_search_center_position][sample_search_unit_position].equals(sample_basic_info_list_original.get(i).get("sample_unit").toString())){
                        list.add(sample_basic_info_list_original.get(i));
                    }
                }
                break;
            case 4:     //100：表示仅有样品分类名称有效
                for (int i = 0; i < sample_basic_info_list_original.size(); i++) {
                    if (sample_basic_info_list_original.get(i).get("sample_name").toString().contains(sample_search_name_value)){
                        list.add(sample_basic_info_list_original.get(i));
                    }
                }
                break;
            case 5:     //101：
                break;
            case 6:     //110：表示样品分类名称和所属中心有效
                for (int i = 0; i < sample_basic_info_list_original.size(); i++) {
                    if (sample_basic_info_list_original.get(i).get("sample_name").toString().contains(sample_search_name_value) &&
                            centerArrays[sample_search_center_position].equals(sample_basic_info_list_original.get(i).get("sample_center").toString())){
                        list.add(sample_basic_info_list_original.get(i));
                    }
                }
                break;
            case 7:     //111：表示三个筛选条件均有效
                for (int i = 0; i < sample_basic_info_list_original.size(); i++) {
                    if (sample_basic_info_list_original.get(i).get("sample_name").toString().contains(sample_search_name_value) &&
                            centerArrays[sample_search_center_position].equals(sample_basic_info_list_original.get(i).get("sample_center").toString()) &&
                            unitArrays[sample_search_center_position][sample_search_unit_position].equals(sample_basic_info_list_original.get(i).get("sample_unit").toString())){
                        list.add(sample_basic_info_list_original.get(i));
                    }
                }
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return sample_basic_info_list_original;
        }
    }

    /**
     * 为新建按钮设置点击监听器
     */
    private void setOnClickListenerForCreateSampleTV() {
        tittle_create_sample_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SampleInfoActivity.this, CreateSampleInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
