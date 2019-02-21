package com.ff161224.cc_commander.shareplatform.main.scanMachine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.SearchGridView;

import java.util.ArrayList;
import java.util.HashMap;

public class ScanMachineFragment extends Fragment {
    //声明属性
    private SearchGridView scan_machine_gview;    //功能导航图标网格布局控件
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();    //功能导航图标数据
    private BaseAdapter baseAdapter;    //网格布局适配器
    private ScrollView scan_machine_scrollView;   //滑动控件
    private View rootView = null;   //Fragment布局

    //构建数据
    private int[] icon = { R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher};
    private  String[] iconName = new String[3];

    //接收传递参数
    public static ScanMachineFragment newInstance(String[] iconNameScanMachine){
        ScanMachineFragment fragment = new ScanMachineFragment();
        fragment.iconName = iconNameScanMachine;
        /*Bundle args=new Bundle();
        args.putInt("user_id",user_id);
        fragment.setArguments(args);*/
        return fragment;
    }

    //构造方法
    public ScanMachineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (rootView == null){
            //加载Fragment布局
            rootView = inflater.inflate(R.layout.fragment_scan_machine,container,false);

            //绑定竖直滚动条控件
            scan_machine_scrollView = (ScrollView)rootView.findViewById(R.id.scan_machine_scrollView);

            //让scrollview跳转到顶部，即每次在顶部显示，必须放在runnable()方法中
            scan_machine_scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scan_machine_scrollView.scrollTo(0, 0);
                }
            });

            //获取网格布局控件
            scan_machine_gview = (SearchGridView) rootView.findViewById(R.id.scan_machine_gview);

            //开启子线程，完成功能图标导航的显示
            data_list = getFunctionNavigationData();

            //****************************开始处理子线程消息，将功能导航图标加入到网格布局中***********************
            //为功能导航图标网格布局创建适配器
            baseAdapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return data_list.size();
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
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.item, null);
                    ViewHolder viewHolder;
                    viewHolder = new ViewHolder();
                    viewHolder.item_image = (ImageView)convertView.findViewById(R.id.image);
                    viewHolder.item_text = (TextView)convertView.findViewById(R.id.text);
                    viewHolder.item_text.setText(data_list.get(position).get("text").toString());
                    viewHolder.item_image.setImageResource((Integer) data_list.get(position).get("image"));
                    return convertView;
                }

                class ViewHolder {
                    protected TextView item_text;
                    protected ImageView item_image;
                }
            };

            //为功能导航图标网格布局绑定适配器
            scan_machine_gview.setAdapter(baseAdapter);

            //添加列表项点击的监听器
            scan_machine_gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(), "你点击了第"+(position+1)+"个图标\n功能是："+iconName[position], Toast.LENGTH_SHORT).show();
                }
            });
            //****************************结束处理子线程消息，将功能导航图标加入到网格布局中***********************

            return rootView;
        }else {
            return rootView;
        }
    }

    //获取功能图标导航的显示
    private ArrayList<HashMap<String,Object>> getFunctionNavigationData(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<icon.length;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            list.add(map);
        }
        return list;
    }

}
