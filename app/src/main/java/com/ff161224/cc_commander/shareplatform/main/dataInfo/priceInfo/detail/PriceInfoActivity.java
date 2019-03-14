package com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.detail;

import android.app.Activity;
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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.create.CreatePriceInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.priceInfo.select.SelectCreatePricePersonActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PriceInfoActivity extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_price_tv;    //新建按钮
    private TextView tittle_choose_price_tv;    //筛选按钮
    private ListView price_basic_info_listview; //标准价格信息ListView
    private EditText price_search_instrumentName_edt;   //仪器名称
    private EditText price_search_projectName_edt;  //分析项目名称
    private EditText price_search_standardName_edt; //标准名称
    private Spinner price_search_sampleClassification_sp;   //样品分类
    private Spinner price_search_isUsable_sp;    //是否启用
    private Spinner price_search_belongUnit_sp; //所属单位
    private TextView price_canncle_search_tv;   //取消按钮
    private TextView price_reset_search_tv; //重置按钮
    private TextView price_ok_search_tv;    //确定按钮

    //定义其他变量
    private DrawerLayout drawerLayout = null;
    private Intent intent = null;
    private BaseAdapter baseAdapter = null;
    private ArrayList<HashMap<String,Object>> price_basic_info_list_original = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> price_basic_info_list_current = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> price_basic_info_list_result = new ArrayList<>();
    private CharSequence []items = {"修改创建人","删除"};
    private String[] price_search_sampleClassification_array = null;
    private String[] price_search_belongUnit_array = null;
    private ArrayAdapter<String> price_search_sampleClassification_adapter = null;
    private ArrayAdapter<String> price_search_belongUnit_adapter = null;
    private String price_search_instrumentName_value = "";
    private String price_search_projectName_value = "";
    private String price_search_standardName_value = "";
    private String price_search_sampleClassification_value = "";
    private int price_search_sampleClassification_position = -1;
    private String price_search_isUsable_value = "";
    private int price_search_isUsable_position = -1;
    private String price_search_belongUnit_value = "";
    private int price_search_belongUnit_position = -1;
    private final int REQUESTCODE_FOR_PERSON = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_price_info);
        //绑定控件变量
        initWidget();
        //为新建按钮设置点击监听器
        setOnClickListenerForCreateProjectTV();
        //设置点击筛选按钮弹出右侧栏事件监听器
        setOnClickListenerForSearchProject();
        //开启子线程获取分析项目信息
        price_basic_info_list_original = getPriceInfo();
        price_basic_info_list_current = (ArrayList<HashMap<String, Object>>) price_basic_info_list_original.clone();
        //为标准价格信息ListView设置适配器
        createBaseAdapterForPriceListView();
        //为ListView中的每一项设置监听器
        setClickListenerForListViewItem();
        //为筛选界面中的下拉菜单设置数据
        setDataForSpinner();
        //为筛选界面中的下拉列表设置点击监听器
        setOnClickListenerForSpinner();
        //为筛选界面中的三个按钮设置点击监听器
        setOnClickListenerForThreeButtons();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUESTCODE_FOR_PERSON:
                if (resultCode == Activity.RESULT_OK){
                    int index = data.getIntExtra("position",-1);
                    String name = data.getStringExtra("price_createPerson");
                    price_basic_info_list_current.get(index).put("price_createPerson",name);
                    baseAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    /**
     * 绑定控件变量
     */
    private void initWidget() {
        tittle_create_price_tv = (TextView) findViewById(R.id.tittle_create_price_tv);    //新建按钮
        tittle_choose_price_tv = (TextView) findViewById(R.id.tittle_choose_price_tv);    //筛选按钮
        price_basic_info_listview = (ListView) findViewById(R.id.price_basic_info_listview); //标准价格信息ListView
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);  //为侧栏布局绑定
        price_search_instrumentName_edt = (EditText) findViewById(R.id.price_search_instrumentName_edt);   //仪器名称
        price_search_projectName_edt = (EditText) findViewById(R.id.price_search_projectName_edt);  //分析项目名称
        price_search_standardName_edt = (EditText) findViewById(R.id.price_search_standardName_edt); //标准名称
        price_search_sampleClassification_sp = (Spinner) findViewById(R.id.price_search_sampleClassification_sp);   //样品分类
        price_search_isUsable_sp = (Spinner) findViewById(R.id.price_search_isUsable_sp);    //是否启用
        price_search_belongUnit_sp = (Spinner) findViewById(R.id.price_search_belongUnit_sp); //所属单位
        price_canncle_search_tv = (TextView) findViewById(R.id.price_canncle_search_tv);   //取消按钮
        price_reset_search_tv = (TextView) findViewById(R.id.price_reset_search_tv); //重置按钮
        price_ok_search_tv = (TextView) findViewById(R.id.price_ok_search_tv);    //确定按钮
    }

    /**
     * 为新建按钮设置点击监听器
     */
    private void setOnClickListenerForCreateProjectTV() {
        tittle_create_price_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(PriceInfoActivity.this, CreatePriceInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 设置点击筛选按钮弹出右侧栏事件监听器
     */
    private void setOnClickListenerForSearchProject() {
        tittle_choose_price_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 获取标准价格信息
     * @return
     */
    public ArrayList<HashMap<String,Object>> getPriceInfo() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("price_instrumentName","岩石薄片制备系统");
        map1.put("price_projectName","筳类");
        map1.put("price_sampleClassification","化石或现生植物动物标本");
        map1.put("price_preDealStandardName","手持式元素快速分析仪");
        map1.put("price_preDealModel","样品数计费");
        map1.put("price_analysisProjectStandardName","用户指定");
        map1.put("price_analysisProjectModel","样品数计费");
        map1.put("price_preDealPrice_inUnit","0.00");
        map1.put("price_preDealPrice_inCenter_outUnit","0.00");
        map1.put("price_preDealPrice_inAcademy_outCenter","0.00");
        map1.put("price_preDealPrice_outAcademy","0.00");
        map1.put("price_preDealPrice_withoutTax","0.00");
        map1.put("price_analysisProjectPrice_inUnit","30.00");
        map1.put("price_analysisProjectPrice_inCenter_outUnit","60.00");
        map1.put("price_analysisProjectPrice_inAcademy_outCenter","60.00");
        map1.put("price_analysisProjectPrice_outAcademy","60.00");
        map1.put("price_analysisProjectPrice_withoutTax","60.00");
        map1.put("price_belongUnit","南京地质古生物研究所");
        map1.put("price_isUsable","是");
        map1.put("price_isUsable_flag","1");
        map1.put("price_createPerson","张羽");
        list.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("price_instrumentName","光学显微成像平台");
        map2.put("price_projectName","体式显微镜");
        map2.put("price_sampleClassification","化石或现生植物动物标本");
        map2.put("price_preDealStandardName","用户指定");
        map2.put("price_preDealModel","样品数计费");
        map2.put("price_analysisProjectStandardName","用户指定");
        map2.put("price_analysisProjectModel","样品数计费");
        map2.put("price_preDealPrice_inUnit","0.00");
        map2.put("price_preDealPrice_inCenter_outUnit","0.00");
        map2.put("price_preDealPrice_inAcademy_outCenter","0.00");
        map2.put("price_preDealPrice_outAcademy","0.00");
        map2.put("price_preDealPrice_withoutTax","0.00");
        map2.put("price_analysisProjectPrice_inUnit","20.00");
        map2.put("price_analysisProjectPrice_inCenter_outUnit","40.00");
        map2.put("price_analysisProjectPrice_inAcademy_outCenter","40.00");
        map2.put("price_analysisProjectPrice_outAcademy","40.00");
        map2.put("price_analysisProjectPrice_withoutTax","40.00");
        map2.put("price_belongUnit","南京地质古生物研究所");
        map2.put("price_isUsable","是");
        map2.put("price_isUsable_flag","1");
        map2.put("price_createPerson","张羽");
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("price_instrumentName","DPM-250A单盘磨片机");
        map3.put("price_projectName","岩石或化石样品抛光");
        map3.put("price_sampleClassification","固体");
        map3.put("price_preDealStandardName","岩石或化石样品抛光");
        map3.put("price_preDealModel","样品数计费");
        map3.put("price_analysisProjectStandardName","岩石薄片制备标准");
        map3.put("price_analysisProjectModel","样品数计费");
        map3.put("price_preDealPrice_inUnit","5.00");
        map3.put("price_preDealPrice_inCenter_outUnit","0.00");
        map3.put("price_preDealPrice_inAcademy_outCenter","10.00");
        map3.put("price_preDealPrice_outAcademy","10.00");
        map3.put("price_preDealPrice_withoutTax","10.00");
        map3.put("price_analysisProjectPrice_inUnit","30.00");
        map3.put("price_analysisProjectPrice_inCenter_outUnit","40.00");
        map3.put("price_analysisProjectPrice_inAcademy_outCenter","40.00");
        map3.put("price_analysisProjectPrice_outAcademy","40.00");
        map3.put("price_analysisProjectPrice_withoutTax","40.00");
        map3.put("price_belongUnit","南京地质古生物研究所");
        map3.put("price_isUsable","是");
        map3.put("price_isUsable_flag","1");
        map3.put("price_createPerson","张羽");
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("price_instrumentName","扫描电子显微镜 EVO 18");
        map4.put("price_projectName","扫描电镜形态观察");
        map4.put("price_sampleClassification","固态或粉末");
        map4.put("price_preDealStandardName","扫描电镜前处理");
        map4.put("price_preDealModel","时间计费");
        map4.put("price_analysisProjectStandardName","试验平台收费标准");
        map4.put("price_analysisProjectModel","时间计费");
        map4.put("price_preDealPrice_inUnit","50.00");
        map4.put("price_preDealPrice_inCenter_outUnit","100.00");
        map4.put("price_preDealPrice_inAcademy_outCenter","100.00");
        map4.put("price_preDealPrice_outAcademy","100.00");
        map4.put("price_preDealPrice_withoutTax","100.00");
        map4.put("price_analysisProjectPrice_inUnit","200.00");
        map4.put("price_analysisProjectPrice_inCenter_outUnit","200.00");
        map4.put("price_analysisProjectPrice_inAcademy_outCenter","200.00");
        map4.put("price_analysisProjectPrice_outAcademy","200.00");
        map4.put("price_analysisProjectPrice_withoutTax","200.00");
        map4.put("price_belongUnit","南京地质古生物研究所");
        map4.put("price_isUsable","是");
        map4.put("price_isUsable_flag","1");
        map4.put("price_createPerson","张羽");
        list.add(map4);

        return list;
    }

    /**
     * 为标准价格信息ListView设置适配器
     */
    private void createBaseAdapterForPriceListView() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (price_basic_info_list_current.size() > 0)
                    return price_basic_info_list_current.size();
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
                if (price_basic_info_list_current.size() > 0){
                    //加载ListView中的每一项布局文件
                    convertView = getLayoutInflater().inflate(R.layout.item_price_basic_info, null);
                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();
                    //为适配器布局文件绑定控件
                    viewHolder.price_no_tv = (TextView) convertView.findViewById(R.id.price_no_tv);
                    viewHolder.price_instrumentName_tv = (TextView) convertView.findViewById(R.id.price_instrumentName_tv);
                    viewHolder.price_projectName_tv = (TextView) convertView.findViewById(R.id.price_projectName_tv);
                    viewHolder.price_sampleClassification_tv = (TextView) convertView.findViewById(R.id.price_sampleClassification_tv);
                    viewHolder.price_preDealStandardName_tv = (TextView) convertView.findViewById(R.id.price_preDealStandardName_tv);
                    viewHolder.price_preDealModel_tv = (TextView) convertView.findViewById(R.id.price_preDealModel_tv);
                    viewHolder.price_analysisProjectStandardName_tv = (TextView) convertView.findViewById(R.id.price_analysisProjectStandardName_tv);
                    viewHolder.price_analysisProjectModel_tv = (TextView) convertView.findViewById(R.id.price_analysisProjectModel_tv);
                    //设置适配器控件的属性值
                    viewHolder.price_no_tv.setText((position+1)+"、");
                    viewHolder.price_instrumentName_tv.setText("仪器名称："+price_basic_info_list_current.get(position).get("price_instrumentName").toString());
                    viewHolder.price_projectName_tv.setText("分析项目名称："+price_basic_info_list_current.get(position).get("price_projectName").toString());
                    viewHolder.price_sampleClassification_tv.setText("样品分类："+price_basic_info_list_current.get(position).get("price_sampleClassification").toString());
                    viewHolder.price_preDealStandardName_tv.setText("前处理标准："+price_basic_info_list_current.get(position).get("price_preDealStandardName").toString());
                    viewHolder.price_preDealModel_tv.setText("前处理计费模式："+price_basic_info_list_current.get(position).get("price_preDealModel").toString());
                    viewHolder.price_analysisProjectStandardName_tv.setText("分析项目标准："+price_basic_info_list_current.get(position).get("price_analysisProjectStandardName").toString());
                    viewHolder.price_analysisProjectModel_tv.setText("分析项目计费模式："+price_basic_info_list_current.get(position).get("price_analysisProjectModel").toString());
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
                protected TextView text1,price_no_tv,price_instrumentName_tv,
                        price_projectName_tv,price_sampleClassification_tv,
                        price_preDealStandardName_tv,price_preDealModel_tv,
                        price_analysisProjectStandardName_tv,price_analysisProjectModel_tv;
            }
        };
        //将分析项目ListView适配器绑定适配器
        price_basic_info_listview.setAdapter(baseAdapter);
    }

    /**
     * 为ListView中的每一项设置监听器
     */
    private void setClickListenerForListViewItem() {
        //点击每一项
        price_basic_info_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(PriceInfoActivity.this,PriceInfoDetailActivity.class);
                HashMap<String,Object> map = price_basic_info_list_current.get(position);
                intent.putExtra("priceInfoDetail",(Serializable) map);
                startActivity(intent);
            }
        });

        //长按某一项
        price_basic_info_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(PriceInfoActivity.this)
                        .setTitle("操作分析项目")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:     //修改创建人
                                        intent = new Intent(PriceInfoActivity.this,SelectCreatePricePersonActivity.class);
                                        intent.putExtra("position",position);
                                        startActivityForResult(intent,REQUESTCODE_FOR_PERSON);
                                        break;
                                    case 1:     //删除
                                        new android.app.AlertDialog.Builder(PriceInfoActivity.this).setTitle("系统提示")//设置对话框标题
                                                .setMessage("您确定要删除该标准价格吗？")//设置显示的内容
                                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                        // TODO Auto-generated method stub
                                                        price_basic_info_list_current.remove(position);
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
        price_search_sampleClassification_array = getSampleClassificationArray();
        price_search_sampleClassification_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,price_search_sampleClassification_array);
        price_search_sampleClassification_sp.setAdapter(price_search_sampleClassification_adapter);
        price_search_belongUnit_array = getUnitNameArray();
        price_search_belongUnit_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,price_search_belongUnit_array);
        price_search_belongUnit_sp.setAdapter(price_search_belongUnit_adapter);
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
     * 为筛选界面中的下拉列表设置点击监听器
     */
    private void setOnClickListenerForSpinner() {
        //样品分类
        price_search_sampleClassification_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                price_search_sampleClassification_position = position;
                price_search_sampleClassification_value = price_search_sampleClassification_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //是否启用
        price_search_isUsable_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                price_search_isUsable_position = position;
                price_search_isUsable_value = position==1?"否":"是";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //所属单位
        price_search_belongUnit_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                price_search_belongUnit_position = position;
                price_search_belongUnit_value = price_search_belongUnit_array[position];
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
        //取消按钮
        price_canncle_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                price_search_instrumentName_edt.setText("");   //仪器名称
                price_search_projectName_edt.setText("");  //分析项目名称
                price_search_standardName_edt.setText(""); //标准名称
                price_search_sampleClassification_sp.setSelection(0);   //样品分类
                price_search_isUsable_sp.setSelection(0);    //是否启用
                price_search_belongUnit_sp.setSelection(0); //所属单位
                //点击取消按钮，关闭右侧菜单
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        //重置按钮
        price_reset_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回复默认显示信息
                price_search_instrumentName_edt.setText("");   //仪器名称
                price_search_projectName_edt.setText("");  //分析项目名称
                price_search_standardName_edt.setText(""); //标准名称
                price_search_sampleClassification_sp.setSelection(0);   //样品分类
                price_search_isUsable_sp.setSelection(0);    //是否启用
                price_search_belongUnit_sp.setSelection(0); //所属单位
            }
        });
        //确定按钮
        price_ok_search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_search_instrumentName_value = price_search_instrumentName_edt.getText().toString();   //仪器名称
                price_search_projectName_value = price_search_projectName_edt.getText().toString();  //分析项目名称
                price_search_standardName_value = price_search_standardName_edt.getText().toString(); //标准名称
                Log.d("筛选界面中仪器名称：",price_search_instrumentName_value);
                Log.d("筛选界面中分析项目名称：",price_search_projectName_value);
                Log.d("筛选界面中标准名称：",price_search_standardName_value);
                Log.d("筛选界面中样品分类：",price_search_sampleClassification_value);
                Log.d("筛选界面中是否启用：",price_search_isUsable_value);
                Log.d("筛选界面中所属单位：",price_search_belongUnit_value);
                price_basic_info_list_result = getSearchResultList(price_search_instrumentName_value,price_search_projectName_value,price_search_standardName_value,
                        price_search_sampleClassification_position,price_search_isUsable_position,price_search_belongUnit_position);
                price_basic_info_list_current = (ArrayList<HashMap<String, Object>>) price_basic_info_list_result.clone();
                baseAdapter.notifyDataSetChanged();
                //回复默认显示信息
                price_search_instrumentName_edt.setText("");   //仪器名称
                price_search_projectName_edt.setText("");  //分析项目名称
                price_search_standardName_edt.setText(""); //标准名称
                price_search_sampleClassification_sp.setSelection(0);   //样品分类
                price_search_isUsable_sp.setSelection(0);    //是否启用
                price_search_belongUnit_sp.setSelection(0); //所属单位
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    /**
     * 筛选信息逻辑，返回筛选结果集合
     * @param price_search_instrumentName_value：仪器名称
     * @param price_search_projectName_value：分析项目名称
     * @param price_search_standardName_value：标准名称
     * @param price_search_sampleClassification_position：样品分类下标
     * @param price_search_isUsable_position：是否启用下标
     * @param price_search_belongUnit_position：所属单位下标
     * @return
     */
    private ArrayList<HashMap<String,Object>> getSearchResultList(String price_search_instrumentName_value, String price_search_projectName_value, String price_search_standardName_value, int price_search_sampleClassification_position, int price_search_isUsable_position, int price_search_belongUnit_position) {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        boolean isEffctive = true;
        StringBuilder tag = new StringBuilder("000000");
        if (!"".equals(price_search_instrumentName_value))
            tag.setCharAt(0,'1');
        if (!"".equals(price_search_projectName_value))
            tag.setCharAt(1,'1');
        if (!"".equals(price_search_standardName_value))
            tag.setCharAt(2,'1');
        if (price_search_sampleClassification_position > 0)
            tag.setCharAt(3,'1');
        if (price_search_isUsable_position > 0)
            tag.setCharAt(4,'1');
        if (price_search_belongUnit_position > 0)
            tag.setCharAt(5,'1');
        switch (Integer.valueOf(tag.toString(),2)){
            case 0:
                isEffctive = false;
                break;
            case 1:     //00 0001：仅有所属单位筛选条件有效
                Toast.makeText(this, "仅有所属单位筛选条件有效", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < price_basic_info_list_original.size(); i++) {
                    if (price_search_belongUnit_array[price_search_belongUnit_position].equals(price_basic_info_list_original.get(i).get("price_belongUnit").toString()))
                        list.add(price_basic_info_list_original.get(i));
                }
                break;
            case 2:     //00 0010：仅有是否可用筛选条件有效
                Toast.makeText(this, "仅有是否可用筛选条件有效", Toast.LENGTH_SHORT).show();
                String str = price_search_isUsable_position==1?"否":"是";
                for (int i = 0; i < price_basic_info_list_original.size(); i++) {
                    if (str.equals(price_basic_info_list_original.get(i).get("price_isUsable").toString()))
                        list.add(price_basic_info_list_original.get(i));
                }
                break;
            case 3:
                isEffctive = false;
                break;
            case 4:     //00 0100：仅有样品分类筛选条件有效
                Toast.makeText(this, "仅有样品分类筛选条件有效", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < price_basic_info_list_original.size(); i++) {
                    if (price_search_sampleClassification_array[price_search_sampleClassification_position].equals(price_basic_info_list_original.get(i).get("price_sampleClassification").toString()))
                        list.add(price_basic_info_list_original.get(i));
                }
                break;
            case 5:
                isEffctive = false;
                break;
            case 6:
                isEffctive = false;
                break;
            case 7:
                isEffctive = false;
                break;
            case 8:     //00 1000：仅有标准名称筛选条件有效
                Toast.makeText(this, "仅有标准名称筛选条件有效", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < price_basic_info_list_original.size(); i++) {
                    if (price_basic_info_list_original.get(i).get("price_preDealStandardName").toString().contains(price_search_standardName_value) ||
                            price_basic_info_list_original.get(i).get("price_analysisProjectStandardName").toString().contains(price_search_standardName_value))
                        list.add(price_basic_info_list_original.get(i));
                }
                break;
            case 9:
                isEffctive = false;
                break;
            case 10:
                isEffctive = false;
                break;
            case 11:
                isEffctive = false;
                break;
            case 12:
                isEffctive = false;
                break;
            case 13:
                isEffctive = false;
                break;
            case 14:
                isEffctive = false;
                break;
            case 15:
                isEffctive = false;
                break;
            case 16:    //01 0000：仅有分析项目筛选条件有效
                Toast.makeText(this, "仅有分析项目筛选条件有效", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < price_basic_info_list_original.size(); i++) {
                    if (price_basic_info_list_original.get(i).get("price_projectName").toString().contains(price_search_projectName_value))
                        list.add(price_basic_info_list_original.get(i));
                }
                break;
            case 17:
                isEffctive = false;
                break;
            case 18:
                isEffctive = false;
                break;
            case 19:
                isEffctive = false;
                break;
            case 20:
                isEffctive = false;
                break;
            case 21:
                isEffctive = false;
                break;
            case 22:
                isEffctive = false;
                break;
            case 23:
                isEffctive = false;
                break;
            case 24:
                isEffctive = false;
                break;
            case 25:
                isEffctive = false;
                break;
            case 26:
                isEffctive = false;
                break;
            case 27:
                isEffctive = false;
                break;
            case 28:
                isEffctive = false;
                break;
            case 29:
                isEffctive = false;
                break;
            case 30:
                isEffctive = false;
                break;
            case 31:
                isEffctive = false;
                break;
            case 32:    //10 0000：仅有仪器名称筛选条件有效
                Toast.makeText(this, "仅有仪器名称筛选条件有效", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < price_basic_info_list_original.size(); i++) {
                    if (price_basic_info_list_original.get(i).get("price_instrumentName").toString().contains(price_search_instrumentName_value))
                        list.add(price_basic_info_list_original.get(i));
                }
                break;
            case 33:
                isEffctive = false;
                break;
            case 34:
                isEffctive = false;
                break;
            case 35:
                isEffctive = false;
                break;
            case 36:
                isEffctive = false;
                break;
            case 37:
                isEffctive = false;
                break;
            case 38:
                isEffctive = false;
                break;
            case 39:
                isEffctive = false;
                break;
            case 40:
                isEffctive = false;
                break;
            case 41:
                isEffctive = false;
                break;
            case 42:
                isEffctive = false;
                break;
            case 43:
                isEffctive = false;
                break;
            case 44:
                isEffctive = false;
                break;
            case 45:
                isEffctive = false;
                break;
            case 46:
                isEffctive = false;
                break;
            case 47:
                isEffctive = false;
                break;
            case 48:
                isEffctive = false;
                break;
            case 49:
                isEffctive = false;
                break;
            case 50:
                isEffctive = false;
                break;
            case 51:
                isEffctive = false;
                break;
            case 52:
                isEffctive = false;
                break;
            case 53:
                isEffctive = false;
                break;
            case 54:
                isEffctive = false;
                break;
            case 55:
                isEffctive = false;
                break;
            case 56:
                isEffctive = false;
                break;
            case 57:
                isEffctive = false;
                break;
            case 58:
                isEffctive = false;
                break;
            case 59:
                isEffctive = false;
                break;
            case 60:
                isEffctive = false;
                break;
            case 61:
                isEffctive = false;
                break;
            case 62:
                isEffctive = false;
                break;
            case 63:
                isEffctive = false;
                break;
        }
        if (isEffctive){    //筛选条件有效
            return list;
        }else {     //筛选条件无效
            return price_basic_info_list_original;
        }
    }
}
