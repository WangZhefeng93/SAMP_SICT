package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.random;

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
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.random.AddNewRandomFileActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class InstrumentDetailRandomFileFragment extends Fragment {
    //定义属性
    private ListView random_file_listview;
    private TextView add_file_tv;
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();
    BaseAdapter baseAdapter;
    private View rootView = null;
    Intent intent;

    //构造方法
    public InstrumentDetailRandomFileFragment() {
        // Required empty public constructor
    }

    //接收传递参数
    public static InstrumentDetailRandomFileFragment newInstance(){
        InstrumentDetailRandomFileFragment fragment = new InstrumentDetailRandomFileFragment();
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

            //绑定控件
            initWidget();

            //开启子线程，获取下拉列表数据
            data_list = getRandomFileData();

            //为下拉列表控件设置适配器
            createBaseAdapterForRandomListView();

            //为下拉列表中的每一项设置点击监听器
            setClickListenerForRandomListViewItem();

            //为添加随机文档按钮设置点击监听器
            setClickListenerForAddRandomFileTV();

            return rootView;
        }else {
            return rootView;
        }
    }

    /**
     * 绑定控件
     */
    private void initWidget() {
        //绑定下拉列表控件
        random_file_listview = (ListView)rootView.findViewById(R.id.random_file_listview);
        //绑定添加随机文档按钮
        add_file_tv = (TextView) rootView.findViewById(R.id.add_file_tv);
        add_file_tv.setText("添加随机文档信息");
    }

    /**
     * 获取下拉列表数据
     */
    private ArrayList getRandomFileData(){
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
     * 为下拉列表控件设置适配器
     */
    private void createBaseAdapterForRandomListView() {
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
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.item_instrument_detail_random_file, null);

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
                protected TextView text1,random_file_no_tv,random_file_name_tv,random_file_num_tv,random_file_page_num_tv;
            }
        };
        //为随机文档ListView分配适配器
        random_file_listview.setAdapter(baseAdapter);
    }

    /**
     * 为下拉列表中的每一项设置点击监听器
     */
    private void setClickListenerForRandomListViewItem() {
        if (data_list.size() > 0){
            //为随机文档ListView设置点击监听事件，点击查看随机文档全部详细信息
            random_file_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent = new Intent(getActivity(),DetailRandomFileInfo.class);
                    startActivity(intent);
                }
            });

            //为随机文档ListView设置长按监听事件，删除随机文档
            random_file_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(getActivity()).setTitle("系统提示")//设置对话框标题
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
     * 为添加随机文档按钮设置点击监听器
     */
    private void setClickListenerForAddRandomFileTV() {
        add_file_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), AddNewRandomFileActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}
