package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.calibration;

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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.component.CreateNewInstrumentActivity5;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.random.CreateNewInstrumentActivity3;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.calibration.DetailCalibrationRecordInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateNewInstrumentActivity4 extends AppCompatActivity {
    //定义控件变量
    private TextView tittle_create_new_instrument_cancel_tv44;  //取消新建仪器
    private TextView create_new_instrument_calibration_tv;   //点击添加校准记录
    private ListView create_new_instrument_calibration_listview; //校准记录下拉列表
    private TextView create_new_instrument_pre_tv3; //上一步按钮
    private TextView create_new_instrument_next_tv4;    //下一步按钮

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
        setContentView(R.layout.activity_create_new_instrument4);
        //为控件变量绑定布局文件中的控件
        initWidget();
        //开启子线程，获取下拉列表数据
        data_list = getCalibrationRecordData();
        //为下拉列表控件设置适配器
        createMyBaseAdapter();
        //为校准记录ListView分配适配器
        create_new_instrument_calibration_listview.setAdapter(baseAdapter);
        //为添加校准记录信息按钮设置点击监听器
        setOnClickListenerForAddCalibrationRecord();
        //为已添加好的校准记录项目设置点击监听器
        setOnclickListenerForCalibrationRecordItem();
        //为下一步按钮设置点击监听器
        setOnclickListenerForNext4();
        //为上一步按钮设置点击监听器
        setOnClickListenerForPre3();
        //为取消按钮设置点击监听器
        setOnClickListenerForCancel44();
    }

    /**
     * 为取消按钮设置点击监听器
     */
    private void setOnClickListenerForCancel44() {
        tittle_create_new_instrument_cancel_tv44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.support.v7.app.AlertDialog.Builder(CreateNewInstrumentActivity4.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("点击取消，您的修改将无法保存！直接退出新建仪器流程！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateNewInstrumentActivity4.this,InstrumentInfoActivity.class);
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
    private void setOnClickListenerForPre3() {
        create_new_instrument_pre_tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity4.this,CreateNewInstrumentActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为下一步按钮设置点击监听器
     */
    private void setOnclickListenerForNext4() {
        create_new_instrument_next_tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity4.this,CreateNewInstrumentActivity5.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为已添加好的随机文档项目设置点击监听器
     */
    private void setOnclickListenerForCalibrationRecordItem() {
        if (data_list.size() > 0){
            //为随机文档ListView设置点击监听事件，点击查看随机文档全部详细信息
            create_new_instrument_calibration_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent = new Intent(CreateNewInstrumentActivity4.this,DetailCalibrationRecordInfo.class);
                    startActivity(intent);
                }
            });

            //为随机文档ListView设置长按监听事件，删除随机文档
            create_new_instrument_calibration_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(CreateNewInstrumentActivity4.this).setTitle("系统提示")//设置对话框标题
                            .setMessage("您确定要删除校准编号为"+data_list.get(position).get("calibration_record_no").toString()+"的校准记录吗？")//设置显示的内容
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
     * 为添加校准记录信息按钮设置点击监听器
     */
    private void setOnClickListenerForAddCalibrationRecord() {
        create_new_instrument_calibration_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity4.this,AddNewCalibrationRecordActivity.class);
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
                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_instrument_detail_calibration_record, null);

                    //创建适配器控件管理对象
                    ViewHolder viewHolder = new ViewHolder();

                    //为适配器布局文件绑定控件
                    viewHolder.calibration_record_id_tv = (TextView)convertView.findViewById(R.id.calibration_record_id_tv);
                    viewHolder.calibration_record_date_tv = (TextView)convertView.findViewById(R.id.calibration_record_date_tv);
                    viewHolder.calibration_record_no_tv = (TextView)convertView.findViewById(R.id.calibration_record_no_tv);
                    viewHolder.calibration_record_effective_date_tv = (TextView)convertView.findViewById(R.id.calibration_record_effective_date_tv);

                    //设置适配器控件的属性值
                    viewHolder.calibration_record_id_tv.setText((position+1)+"、");
                    viewHolder.calibration_record_date_tv.setText("校准日期："+data_list.get(position).get("calibration_record_date").toString());
                    viewHolder.calibration_record_no_tv.setText("证书编号：No."+data_list.get(position).get("calibration_record_no").toString());
                    viewHolder.calibration_record_effective_date_tv.setText("校准有效日期："+data_list.get(position).get("calibration_record_effective_date").toString());

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
                protected TextView text1,calibration_record_id_tv,calibration_record_date_tv,calibration_record_no_tv,calibration_record_effective_date_tv;
            }
        };
    }

    /**
     * 开启子线程，获取下拉列表数据
     * @return
     */
    private ArrayList getCalibrationRecordData() {
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("calibration_record_date","2016年5月16日");
        map.put("calibration_record_no","1592778");
        map.put("calibration_record_effective_date","2019年5月16日");
        list.add(map);
        return list;
    }

    /**
     * 为控件变量绑定布局文件中的控件
     */
    private void initWidget() {
        tittle_create_new_instrument_cancel_tv44 = (TextView) findViewById(R.id.tittle_create_new_instrument_cancel_tv44);  //取消新建仪器按钮
        create_new_instrument_calibration_tv = (TextView) findViewById(R.id.create_new_instrument_calibration_tv);   //点击添加校准记录
        create_new_instrument_calibration_listview = (ListView) findViewById(R.id.create_new_instrument_calibration_listview); //校准记录下拉列表
        create_new_instrument_pre_tv3 = (TextView) findViewById(R.id.create_new_instrument_pre_tv3);    //上一步按钮
        create_new_instrument_next_tv4 = (TextView) findViewById(R.id.create_new_instrument_next_tv4);    //下一步按钮
    }
}
