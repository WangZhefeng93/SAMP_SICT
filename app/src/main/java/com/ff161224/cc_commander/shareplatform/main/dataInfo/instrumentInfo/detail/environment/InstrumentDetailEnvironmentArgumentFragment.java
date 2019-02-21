package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.environment;

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

import java.util.ArrayList;
import java.util.HashMap;

public class InstrumentDetailEnvironmentArgumentFragment extends Fragment {
    //定义属性
    private ListView random_file_listview;
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    BaseAdapter baseAdapter;
    private View rootView = null;
    Intent intent;

    //构造方法
    public InstrumentDetailEnvironmentArgumentFragment() {
        // Required empty public constructor
    }

    //接收传递参数
    public static InstrumentDetailEnvironmentArgumentFragment newInstance(){
        InstrumentDetailEnvironmentArgumentFragment fragment = new InstrumentDetailEnvironmentArgumentFragment();
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
                        convertView = getActivity().getLayoutInflater().inflate(R.layout.item_instrument_detail_environment_argument, null);

                        //创建适配器控件管理对象
                        ViewHolder viewHolder = new ViewHolder();

                        //为适配器布局文件绑定控件
                        viewHolder.environment_argument_id_tv = (TextView)convertView.findViewById(R.id.environment_argument_id_tv);
                        viewHolder.environment_argument_name_tv = (TextView)convertView.findViewById(R.id.environment_argument_name_tv);
                        viewHolder.environment_argument_unit_tv = (TextView)convertView.findViewById(R.id.environment_argument_unit_tv);
                        //viewHolder.project_standard_start_tv = (TextView)convertView.findViewById(R.id.project_standard_start_tv);

                        //设置适配器控件的属性值
                        viewHolder.environment_argument_id_tv.setText((position+1)+"、");
                        viewHolder.environment_argument_name_tv.setText("环境参数名称："+data_list.get(position).get("environment_argument_name").toString());
                        viewHolder.environment_argument_unit_tv.setText("环境参数单位："+data_list.get(position).get("environment_argument_unit").toString());
                        //viewHolder.project_standard_start_tv.setText("是否启用标准："+data_list.get(position).get("project_standard_start").toString());

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
                    protected TextView text1,
                            environment_argument_id_tv,
                            environment_argument_name_tv,
                            environment_argument_unit_tv;
                }
            };

            //为主要零件ListView分配适配器
            random_file_listview.setAdapter(baseAdapter);


            if (data_list.size() > 0){
                //为主要零件ListView设置点击监听事件，点击查看主要零件全部详细信息
                random_file_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intent = new Intent(getActivity(), DetailEnvironmentArgumentInfo.class);
                        startActivity(intent);
                    }
                });

                //为随机文档ListView设置长按监听事件，删除随机文档
                random_file_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        new AlertDialog.Builder(getActivity()).setTitle("系统提示")//设置对话框标题
                                .setMessage("您确定要删除"+data_list.get(position).get("environment_argument_name").toString()+"吗？")//设置显示的内容
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
        map.put("environment_argument_name","工作温度");
        map.put("environment_argument_unit","摄氏度");
        //map.put("project_standard_start","是");
        list.add(map);
        return list;
    }
}