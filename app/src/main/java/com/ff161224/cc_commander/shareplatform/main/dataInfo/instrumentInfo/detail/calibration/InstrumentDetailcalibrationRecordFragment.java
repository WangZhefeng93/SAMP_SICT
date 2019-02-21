package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.calibration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.random.DetailRandomFileInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class InstrumentDetailcalibrationRecordFragment extends Fragment {
    //定义属性
    private ListView random_file_listview;
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    BaseAdapter baseAdapter;
    private View rootView = null;
    Intent intent;

    //构造方法
    public InstrumentDetailcalibrationRecordFragment() {
        // Required empty public constructor
    }

    //接收传递参数
    public static InstrumentDetailcalibrationRecordFragment newInstance(){
        InstrumentDetailcalibrationRecordFragment fragment = new InstrumentDetailcalibrationRecordFragment();
        /*Bundle args=new Bundle();
        args.putInt("user_id",user_id);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null){
            //加载布局文件
            rootView = inflater.inflate(R.layout.fragment_instrument_detail_random_file, container, false);

            //绑定下拉列表控件
            random_file_listview = (ListView)rootView.findViewById(R.id.random_file_listview);

            //开启子线程，获取下拉列表数据
            data_list = getRandomFileData();

            //为下拉列表控件设置适配器
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
                        convertView = getActivity().getLayoutInflater().inflate(R.layout.item_instrument_detail_calibration_record, null);

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
                        convertView = getActivity().getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);

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

            //为随机文档ListView分配适配器
            random_file_listview.setAdapter(baseAdapter);


            if (data_list.size() > 0){
                //为随机文档ListView设置点击监听事件，点击查看随机文档全部详细信息
                random_file_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intent = new Intent(getActivity(),DetailCalibrationRecordInfo.class);
                        startActivity(intent);
                    }
                });

                //为随机文档ListView设置长按监听事件，删除随机文档
                random_file_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        new AlertDialog.Builder(getActivity()).setTitle("系统提示")//设置对话框标题
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

            return rootView;
        }else {
            return rootView;
        }
    }

    //获取下拉列表数据
    private ArrayList getRandomFileData(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("calibration_record_date","2016年5月16日");
        map.put("calibration_record_no","1592778");
        map.put("calibration_record_effective_date","2019年5月16日");
        list.add(map);
        return list;
    }

}
