package com.ff161224.cc_commander.shareplatform.main.dataInfo.projectInfo.detail;

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
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.InstrumentInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.projectInfo.create.CreateProjectInfoAvtivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.projectInfo.edit.EditProjectInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_project_tv;  //新建分析项目按钮
    private TextView tittle_name_tv;    //分析项目标题
    private TextView tittle_choose_tv;  //分析项目筛选按钮
    private ListView project_basic_info_listview;   //分析项目ListView
    private EditText project_search_name_edt;   //筛选界面中的分析项目名称输入框
    private Spinner project_search_sample_classification_sp;    //筛选界面中的样品分类
    private Spinner project_search_unit_name_sp;    //筛选界面中的所属单位
    private TextView project_canncle_search_tv; //筛选界面中的取消按钮
    private TextView project_reset_search_tv;   //筛选界面中的重置按钮
    private TextView project_ok_search_tv;  //筛选界面中的确定按钮

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> project_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> project_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> project_basic_info_list_result = new ArrayList<>();
    private CharSequence []items = {"编辑","停用","删除"};
    private String[] sample_classification_array = null;
    private String[] unit_name_array = null;
    private ArrayAdapter<String> sample_classification_adapter = null;
    private ArrayAdapter<String> unit_name_adapter = null;
    private int sample_classification_position = -1;
    private int unit_name_position = -1;
    private String sample_classification_value = "";
    private String unit_name_value = "";
    private String project_search_name_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_project_info);
        //绑定控件变量
        initWidget();
        //为新建按钮设置点击监听器
        setOnClickListenerForCreateProjectTV();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchProject();
        //开启子线程获取分析项目信息
        project_basic_info_list_original = getProjectInfo();
        project_basic_info_list_current = (ArrayList<HashMap<String, Object>>) project_basic_info_list_original.clone();
        //为分析项目ListView设置适配器
        createBaseAdapterForProjectListView();
        //为ListView中的每一项设置监听器
        setClickListenerForListViewItem();
        //为筛选界面中的两个下拉菜单设置数据
        setDataForSpinner();
        //为筛选界面中的两个下拉列表设置点击监听器
        setOnClickListenerForSpinner();
        //为筛选界面中的三个按钮设置点击监听器
        setOnClickListenerForThreeButtons();
    }

    /**
     * 绑定控件变量
     */
    private void initWidget() {
        tittle_create_project_tv = (TextView) findViewById(R.id.tittle_create_project_tv);  //新建分析项目按钮
        tittle_name_tv = (TextView) findViewById(R.id.tittle_name_tv);    //分析项目标题
        tittle_choose_tv = (TextView) findViewById(R.id.tittle_choose_tv);  //分析项目筛选按钮
        project_basic_info_listview = (ListView) findViewById(R.id.project_basic_info_listview);   //分析项目ListView
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);  //为侧栏布局绑定
        project_search_name_edt = (EditText) findViewById(R.id.project_search_name_edt);   //筛选界面中的分析项目名称输入框
        project_search_sample_classification_sp = (Spinner) findViewById(R.id.project_search_sample_classification_sp);    //筛选界面中的样品分类
        project_search_unit_name_sp = (Spinner) findViewById(R.id.project_search_unit_name_sp);    //筛选界面中的所属单位
        project_canncle_search_tv = (TextView) findViewById(R.id.project_canncle_search_tv); //筛选界面中的取消按钮
        project_reset_search_tv = (TextView) findViewById(R.id.project_reset_search_tv);   //筛选界面中的重置按钮
        project_ok_search_tv = (TextView) findViewById(R.id.project_ok_search_tv);  //筛选界面中的确定按钮
    }

    /**
     * 为新建按钮设置点击监听器
     */
    private void setOnClickListenerForCreateProjectTV() {
        tittle_create_project_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ProjectInfoActivity.this,CreateProjectInfoAvtivity.class);
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
        map1.put("project_name","大标本");
        map1.put("sample_classification","大化石");
        map1.put("project_description","制作大标本岩石");
        map1.put("unit_name","南京地质古生物研究所");
        map1.put("project_statue","正常");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("project_name","硅质类");
        map2.put("sample_classification","硅质或含硅质岩石");
        map2.put("project_description","");
        map2.put("unit_name","南京地质古生物研究所");
        map2.put("project_statue","正常");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("project_name","普通岩石");
        map3.put("sample_classification","灰岩、碳酸盐");
        map3.put("project_description","");
        map3.put("unit_name","南京地质古生物研究所");
        map3.put("project_statue","正常");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("project_name","木化岩");
        map4.put("sample_classification","化石或现生植物动物标本");
        map4.put("project_description","");
        map4.put("unit_name","南京地质古生物研究所");
        map4.put("project_statue","正常");
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("project_name","有孔虫、成孔虫等");
        map5.put("sample_classification","化石或现生植物动物标本");
        map5.put("project_description","除筳类、珊瑚类的生物");
        map5.put("unit_name","南京地质古生物研究所");
        map5.put("project_statue","正常");
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("project_name","珊瑚");
        map6.put("sample_classification","化石或现生植物动物标本");
        map6.put("project_description","单体或复体珊瑚");
        map6.put("unit_name","南京地质古生物研究所");
        map6.put("project_statue","正常");
        list.add(map6);

        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("project_name","筳类");
        map7.put("sample_classification","化石或现生植物动物标本");
        map7.put("project_description","筳科类生物");
        map7.put("unit_name","南京地质古生物研究所");
        map7.put("project_statue","正常");
        list.add(map7);

        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("project_name","体式显微镜");
        map8.put("sample_classification","化石或现生植物动物标本");
        map8.put("project_description","观测实体");
        map8.put("unit_name","南京地质古生物研究所");
        map8.put("project_statue","正常");
        list.add(map8);

        HashMap<String,Object> map9 = new HashMap<>();
        map9.put("project_name","生物显微镜");
        map9.put("sample_classification","化石或现生植物动物标本");
        map9.put("project_description","荧光镜片、偏光镜片等");
        map9.put("unit_name","南京地质古生物研究所");
        map9.put("project_statue","正常");
        list.add(map9);

        HashMap<String,Object> map10 = new HashMap<>();
        map10.put("project_name","手持式元素快速分析仪");
        map10.put("sample_classification","固体");
        map10.put("project_description","");
        map10.put("unit_name","南京地质古生物研究所");
        map10.put("project_statue","正常");
        list.add(map10);
        return list;
    }

    /**
     * 为分析项目ListView设置适配器
     */
    private void createBaseAdapterForProjectListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (project_basic_info_list_current.size() > 0)
                    return project_basic_info_list_current.size();
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
                if (project_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_project_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.project_no_tv = (TextView) convertView.findViewById(R.id.project_no_tv);
                    viewHolder.project_name_tv = (TextView) convertView.findViewById(R.id.project_name_tv);
                    viewHolder.sample_classification_tv = (TextView) convertView.findViewById(R.id.sample_classification_tv);
                    viewHolder.project_description_tv = (TextView) convertView.findViewById(R.id.project_description_tv);
                    viewHolder.project_unit_name_tv = (TextView) convertView.findViewById(R.id.project_unit_name_tv);
                    viewHolder.project_statue_tv = (TextView) convertView.findViewById(R.id.project_statue_tv);
                    //设置适配器控件的属性值
                    viewHolder.project_no_tv.setText((position+1)+"、");
                    viewHolder.project_name_tv.setText("分析项目名称："+project_basic_info_list_current.get(position).get("project_name").toString());
                    viewHolder.sample_classification_tv.setText("样品分类："+project_basic_info_list_current.get(position).get("sample_classification").toString());
                    viewHolder.project_description_tv.setText("描述："+project_basic_info_list_current.get(position).get("project_description").toString());
                    viewHolder.project_unit_name_tv.setText("所属单位："+project_basic_info_list_current.get(position).get("unit_name").toString());
                    viewHolder.project_statue_tv.setText("状态："+project_basic_info_list_current.get(position).get("project_statue").toString());
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
                protected TextView text1,project_no_tv,project_name_tv,
                        sample_classification_tv,project_description_tv,
                        project_unit_name_tv,project_statue_tv;
            }
        };
        //将分析项目ListView适配器绑定适配器
        project_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置长按监听器
     */
    private void setClickListenerForListViewItem() {
        //当点击ListView中的某一项时进行选择，编辑或停用
        project_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ProjectInfoActivity.this)
                        .setTitle("操作分析项目")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     //编辑
                                        //Toast.makeText(ProjectInfoActivity.this, "跳转到编辑分析项目页面", Toast.LENGTH_SHORT).show();
                                        intent = new Intent(ProjectInfoActivity.this, EditProjectInfoActivity.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 1:     //停用
                                        new android.app.AlertDialog.Builder(ProjectInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要停用分析项目：\n"+project_basic_info_list_current.get(position).get("project_name").toString()+"吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        project_basic_info_list_current.get(position).put("project_statue","停用");
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
                                    case 2:
                                        new android.app.AlertDialog.Builder(ProjectInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要删除分析项目：\n"+project_basic_info_list_current.get(position).get("project_name").toString()+"吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        project_basic_info_list_current.remove(position);
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
     * 获取筛选界面中的样品分类下拉菜单数据
     * @return
     */
    public String[] getSampleClassificationArray() {
        String[] array = {"--不选--","薄片","玻璃管","大化石","粉末","固态或粉末","固体",
                "硅质或含硅质岩石","化石或现生植物动物标本","灰岩、碳酸盐","离心管保存",
        "硫化银、硫化钡及其他硫质物","凝胶","切片、连续切片","图像后期处理","微体化石和小型化石",
        "小型-微体化石","岩石薄片制备","液态"};
        return array;
    }

    /**
     * 获取筛选界面中的所属单位下拉菜单数据
     * @return
     */
    public String[] getUnitNameArray() {
        String[] array = {"--不选--","南京地质古生物研究所"};
        return array;
    }

    /**
     * 为筛选界面中的两个下拉菜单设置数据
     */
    private void setDataForSpinner() {
        //获取筛选界面中的样品分类下拉菜单数据
        sample_classification_array = getSampleClassificationArray();
        //获取筛选界面中的所属单位下拉菜单数据
        unit_name_array = getUnitNameArray();
        //创建筛选界面中的样品分类下拉菜单适配器
        sample_classification_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sample_classification_array);
        //创建筛选界面中的所属单位下拉菜单适配器
        unit_name_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unit_name_array);
        //为筛选界面中的样品分类下拉菜单设置适配器
        project_search_sample_classification_sp.setAdapter(sample_classification_adapter);
        //为筛选界面中的所属单位下拉菜单设置适配器
        project_search_unit_name_sp.setAdapter(unit_name_adapter);
    }

    /**
     * 为筛选界面中的两个下拉列表设置点击监听器
     */
    private void setOnClickListenerForSpinner() {
        //为样品分类下拉菜单设置监听器
        project_search_sample_classification_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sample_classification_position = position;
                sample_classification_value = sample_classification_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //为所属单位下拉菜单设置监听器
        project_search_unit_name_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit_name_position = position;
                unit_name_value = unit_name_array[position];
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
        project_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                project_search_name_edt.setText("");
                project_search_sample_classification_sp.setSelection(0);
                project_search_unit_name_sp.setSelection(0);
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        //为筛选侧栏中的重置按钮设置点击监听事件
        project_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                project_search_name_edt.setText("");
                project_search_sample_classification_sp.setSelection(0);
                project_search_unit_name_sp.setSelection(0);
            }
        });

        //为筛选侧栏中的确定按钮设置点击监听事件
        project_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取筛选界面分析项目名称输入框的值
                project_search_name_value = project_search_name_edt.getText().toString();
                Log.d("筛选界面中分析项目名称：",project_search_name_value);
                Log.d("筛选界面中样品分类信息：",sample_classification_value);
                Log.d("筛选界面中样品分类下标：",sample_classification_position+"");
                Log.d("筛选界面中所属单位信息：",unit_name_value);
                Log.d("筛选界面中所属单位下标：",unit_name_position+"");

                project_basic_info_list_result = getSearchResultList(project_search_name_value,sample_classification_position,unit_name_position);
                project_basic_info_list_current = (ArrayList<HashMap<String, Object>>) project_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                //回复默认显示信息
                project_search_name_edt.setText("");
                project_search_sample_classification_sp.setSelection(0);
                project_search_unit_name_sp.setSelection(0);
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选仪器信息逻辑，返回筛选结果集合
     * @param project_search_name_value：搜索框内容
     * @param sample_classification_position：样品分类数据下标
     * @param unit_name_position：所属单位数据下标
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String project_search_name_value, int sample_classification_position, int unit_name_position) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("000");
        if (!"".equals(project_search_name_value))
            tag.setCharAt(0,'1');
        if (sample_classification_position > 0)
            tag.setCharAt(1,'1');
        if (unit_name_position > 0)
            tag.setCharAt(2,'1');
        //Log.d("tag的值为：",Integer.valueOf(tag.toString(),2)+"");
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:     //000：表示3个筛选条件均无效，此时返回全部结果
                isEffctive = false;
                break;
            case 1:     //001：表示仅有所属单位筛选条件有效
                for (int i = 0; i < project_basic_info_list_original.size(); i++) {
                    if (unit_name_array[unit_name_position].equals(project_basic_info_list_original.get(i).get("unit_name"))) {
                        list.add(project_basic_info_list_original.get(i));
                    }
                }
                break;
            case 2:     //010：表示仅有样品分类筛选条件有效
                for (int i = 0; i < project_basic_info_list_original.size(); i++) {
                    if (sample_classification_array[sample_classification_position].equals(project_basic_info_list_original.get(i).get("sample_classification"))) {
                        list.add(project_basic_info_list_original.get(i));
                    }
                }
                break;
            case 3:     //011：表示样品分类和所属单位筛选条件有效
                for (int i = 0; i < project_basic_info_list_original.size(); i++) {
                    if (sample_classification_array[sample_classification_position].equals(project_basic_info_list_original.get(i).get("sample_classification"))
                            && unit_name_array[unit_name_position].equals(project_basic_info_list_original.get(i).get("unit_name"))) {
                        list.add(project_basic_info_list_original.get(i));
                    }
                }
                break;
            case 4:     //100：表示仅有分析项目名称筛选条件有效
                for (int i = 0; i < project_basic_info_list_original.size(); i++) {
                    if (project_basic_info_list_original.get(i).get("project_name").toString().contains(project_search_name_value))
                        list.add(project_basic_info_list_original.get(i));
                }
                break;
            case 5:     //101：表示分析项目名称和所属单位筛选条件有效
                for (int i = 0; i < project_basic_info_list_original.size(); i++) {
                    if (project_basic_info_list_original.get(i).get("project_name").toString().contains(project_search_name_value) &&
                            unit_name_array[unit_name_position].equals(project_basic_info_list_original.get(i).get("unit_name")))
                        list.add(project_basic_info_list_original.get(i));
                }
                break;
            case 6:     //110：表示分析项目名称和样品分类筛选条件有效
                for (int i = 0; i < project_basic_info_list_original.size(); i++) {
                    if (project_basic_info_list_original.get(i).get("project_name").toString().contains(project_search_name_value) &&
                            sample_classification_array[sample_classification_position].equals(project_basic_info_list_original.get(i).get("sample_classification")))
                        list.add(project_basic_info_list_original.get(i));
                }
                break;
            case 7:     //111：表示三个筛选条件均有效
                for (int i = 0; i < project_basic_info_list_original.size(); i++) {
                    if (project_basic_info_list_original.get(i).get("project_name").toString().contains(project_search_name_value) &&
                            sample_classification_array[sample_classification_position].equals(project_basic_info_list_original.get(i).get("sample_classification")) &&
                            unit_name_array[unit_name_position].equals(project_basic_info_list_original.get(i).get("unit_name")))
                        list.add(project_basic_info_list_original.get(i));
                }
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return project_basic_info_list_original;
        }
    }
}
