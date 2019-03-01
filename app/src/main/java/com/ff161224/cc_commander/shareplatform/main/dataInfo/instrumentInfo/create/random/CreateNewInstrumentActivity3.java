package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.InstrumentInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.calibration.CreateNewInstrumentActivity4;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.order.CreateNewInstrumentActivity2;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.random.DetailRandomFileInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateNewInstrumentActivity3 extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_new_instrument_cancel_tv33;  //取消新建仪器按钮
    private TextView create_new_instrument_randomFile_tv;   //点击添加随机文档
    private ListView create_new_instrument_randomFile_listview; //随机文档下拉列表
    private TextView create_new_instrument_pre_tv2; //上一步按钮
    private TextView create_new_instrument_next_tv3;    //下一步按钮

    //定义其他变量
    private Intent intent;
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_new_instrument3);
        //为控件变量绑定布局文件中的控件
        initWidget();
        //开启子线程，获取下拉列表数据
        data_list = getRandomFileData();
        //为下拉列表控件设置适配器
        createMyBaseAdapter();
        //为随机文档ListView分配适配器
        create_new_instrument_randomFile_listview.setAdapter(baseAdapter);
        //为添加随机文档信息按钮设置点击监听器
        setOnClickListenerForAddRandomFile();
        //为已添加好的随机文档项目设置点击监听器
        setOnclickListenerForRandomFileItem();
        //为下一步按钮设置点击监听器
        setOnclickListenerForNext3();
        //为上一步按钮设置点击监听器
        setOnclickListenerForPre2();
        //为取消新建按钮设置点击监听器
        setOnclickListenerForCancel33();
    }

    /**
     * 为取消新建按钮设置点击监听器
     */
    private void setOnclickListenerForCancel33() {
        tittle_create_new_instrument_cancel_tv33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.support.v7.app.AlertDialog.Builder(CreateNewInstrumentActivity3.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("点击取消，您的修改将无法保存！直接退出新建仪器流程！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateNewInstrumentActivity3.this,InstrumentInfoActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                        Log.i("alertdialog"," 请保存数据！");
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });
    }

    /**
     * 为上一步按钮设置点击监听器
     */
    private void setOnclickListenerForPre2() {
        create_new_instrument_pre_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity3.this,CreateNewInstrumentActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为下一步按钮设置点击监听器
     */
    private void setOnclickListenerForNext3() {
        create_new_instrument_next_tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity3.this,CreateNewInstrumentActivity4.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为已添加好的随机文档项目设置点击监听器
     */
    private void setOnclickListenerForRandomFileItem() {
        if (data_list.size() > 0){
            //为随机文档ListView设置点击监听事件，点击查看随机文档全部详细信息
            create_new_instrument_randomFile_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent = new Intent(CreateNewInstrumentActivity3.this,DetailRandomFileInfo.class);
                    startActivity(intent);
                }
            });

            //为随机文档ListView设置长按监听事件，删除随机文档
            create_new_instrument_randomFile_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(CreateNewInstrumentActivity3.this).setTitle("系统提示")//设置对话框标题
                            .setMessage("您确定要删除"+data_list.get(position).get("random_file_name").toString()+"吗？")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    // TODO Auto-generated method stub
                                    data_list.remove(position);
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

        }
    }

    /**
     * 为添加随机文档信息按钮设置点击监听器
     */
    private void setOnClickListenerForAddRandomFile() {
        create_new_instrument_randomFile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity3.this,AddNewRandomFileActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 为下拉列表控件设置适配器
     */
    private void createMyBaseAdapter() {
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                if (data_list.size() > 0){
                    return data_list.size();
                }else {
                    return 1;
                }
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
                if (data_list.size() > 0){      //说明有数据
                    //加载ListView中的每一项布局文件
                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_instrument_detail_random_file, null);

                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.random_file_no_tv = (TextView)convertView.findViewById(R.id.random_file_no_tv);
                    viewHolder.random_file_name_tv = (TextView)convertView.findViewById(R.id.random_file_name_tv);
                    viewHolder.random_file_num_tv = (TextView)convertView.findViewById(R.id.random_file_num_tv);
                    viewHolder.random_file_page_num_tv = (TextView)convertView.findViewById(R.id.random_file_page_num_tv);

                    //设置适配器控件的属性值
                    viewHolder.random_file_no_tv.setText((position+1)+"、");
                    viewHolder.random_file_name_tv.setText("文档名称："+data_list.get(position).get("random_file_name").toString());
                    viewHolder.random_file_num_tv.setText("文档份数："+data_list.get(position).get("random_file_num").toString());
                    viewHolder.random_file_page_num_tv.setText("文档页码："+data_list.get(position).get("random_file_page_num").toString());

                }else {     //说明没有数据
                    //加载ListView中的每一项布局文件
                    convertView = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_1, null);

                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.text1 = (TextView)convertView.findViewById(android.R.id.text1);

                    //设置适配器控件的属性值
                    viewHolder.text1.setText("暂无任何记录");
                    viewHolder.text1.setGravity(Gravity.CENTER);
                }

                return convertView;
            }

            class ViewHolder {
                protected TextView text1,random_file_no_tv,random_file_name_tv,random_file_num_tv,random_file_page_num_tv;
            }
        };
    }

    /**
     * 开启子线程，获取下拉列表数据
     * @return
     */
    private ArrayList getRandomFileData() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("random_file_name","透射电子显微镜说明书");
        map.put("random_file_num",1);
        map.put("random_file_page_num",25);
        map.put("random_file_collect_date","2007-12-31");
        map.put("random_file_sign_person","闻杰");
        map.put("random_file_comment","无备注信息");
        list.add(map);
        return list;
    }

    /**
     * 为控件变量绑定布局文件中的控件
     */
    private void initWidget() {
        tittle_create_new_instrument_cancel_tv33 = (TextView) findViewById(R.id.tittle_create_new_instrument_cancel_tv33);  //取消新建按钮
        create_new_instrument_randomFile_tv = (TextView) findViewById(R.id.create_new_instrument_randomFile_tv);    //点击添加随机文档
        create_new_instrument_randomFile_listview = (ListView) findViewById(R.id.create_new_instrument_randomFile_listview); //随机文档下拉列表
        create_new_instrument_pre_tv2 = (TextView) findViewById(R.id.create_new_instrument_pre_tv2);    //上一步按钮
        create_new_instrument_next_tv3 = (TextView) findViewById(R.id.create_new_instrument_next_tv3);  //下一步按钮
    }
}
