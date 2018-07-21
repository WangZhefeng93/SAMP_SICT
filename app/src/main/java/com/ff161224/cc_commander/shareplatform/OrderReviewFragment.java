package com.ff161224.cc_commander.shareplatform;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;

public class OrderReviewFragment extends Fragment {
    //声明属性
    private SearchGridView order_review_gview;    //功能导航图标网格布局控件
    private SearchGridView hot_order_GridView;  //热门仪器预约网格布局控件
    private BaseAdapter baseAdapter;    //功能导航图标网格布局适配器
    private BaseAdapter baseAdapter_hot_order;    //热门仪器预约网格布局适配器
    private ScrollView order_review_fragment_scrollView;   //滑动控件
    private View rootView = null;   //Fragment布局
    private TextView hot_order_tip_tv;  //热门仪器预约提示
    private TextView hot_order_more_tv;  //热门仪器预约查看全部
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();    //功能导航图标数据
    private ArrayList<HashMap<String,Object>> hot_order_data_list = new ArrayList<>();    //热门仪器预约数据
    Intent intent;

    //构建功能导航图标数据
    private int[] icon = { R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher};
    private  String[] iconName = { "时间预约", "项目预约","预约审核", "委托单管理", "委托单结算", "费用结算"};

    //构建热门仪器预约数据
    private int[] image = { R.drawable.carousel_figure_1, R.drawable.carousel_figure_1,R.drawable.carousel_figure_1,
            R.drawable.carousel_figure_1, R.drawable.carousel_figure_1,R.drawable.carousel_figure_1};
    private  String[] imageName = { "热门仪器1", "热门仪器2","热门仪器3", "热门仪器4", "热门仪器5", "热门仪器6"};

    //接收传递参数
    public static OrderReviewFragment newInstance(){
        OrderReviewFragment fragment = new OrderReviewFragment();
        /*Bundle args=new Bundle();
        args.putInt("user_id",user_id);
        fragment.setArguments(args);*/
        return fragment;
    }

    //构造方法
    public OrderReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (rootView == null){
            //加载Fragment布局
            rootView = inflater.inflate(R.layout.fragment_order_review,container,false);

            //接收由MainActivity传递过来的user_id参数

            //绑定竖直滚动条控件
            order_review_fragment_scrollView = (ScrollView)rootView.findViewById(R.id.order_review_fragment_scrollView);

            //让scrollview跳转到顶部，即每次在顶部显示，必须放在runnable()方法中
            order_review_fragment_scrollView.post(new Runnable() {
                @Override
                public void run() {
                    order_review_fragment_scrollView.scrollTo(0, 0);
                }
            });

            //显示热门仪器预约提示框
            hot_order_tip_tv = (TextView)rootView.findViewById(R.id.hot_order_tip_tv);
            hot_order_tip_tv.setText(getString(R.string.hot_order_tip_tv));

            //显示热门仪器预约查看更多按钮
            hot_order_more_tv = (TextView)rootView.findViewById(R.id.hot_order_more_tv);
            hot_order_more_tv.setText(getString(R.string.hot_order_more_tv));

            //为热门仪器预约查看更多按钮设置点击监听器
            hot_order_more_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "点击查看更多热门预约仪器", Toast.LENGTH_SHORT).show();
                }
            });

            //获取功能图标网格布局控件
            order_review_gview = (SearchGridView) rootView.findViewById(R.id.order_review_gview);

            //开启子线程，获取功能图标网格布局数据
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
            order_review_gview.setAdapter(baseAdapter);

            //添加列表项点击的监听器
            order_review_gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(), "你点击了第"+(position+1)+"个图标\n功能是："+iconName[position], Toast.LENGTH_SHORT).show();
                    switch (position){
                        case 0:     //时间预约
                            intent = new Intent(getActivity(),TimeOrderListInstrumentActivity.class);
                            startActivity(intent);
                            break;
                        case 1:     //项目预约
                            break;
                        case 2:     //预约审核
                            break;
                        case 3:     //委托单管理
                            break;
                        case 4:     //委托单结算
                            break;
                        case 5:     //费用结算
                            break;
                    }
                }
            });
            //****************************结束处理子线程消息，将功能导航图标加入到网格布局中***********************

            //绑定热门仪器预约网格布局
            hot_order_GridView = (SearchGridView) rootView.findViewById(R.id.hot_order_GridView);

            //开启子线程，获取热门预约仪器信息
            hot_order_data_list = getHotOrderData();

            //****************************开始处理子线程消息，将热门预约仪器加入到网格布局中***********************
            //为热门预约仪器网格布局创建适配器
            baseAdapter_hot_order = new BaseAdapter() {
                @Override
                public int getCount() {
                    return hot_order_data_list.size();
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
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.hot_order, null);
                    ViewHolder viewHolder;
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
                    viewHolder.name = (TextView)convertView.findViewById(R.id.name);
                    viewHolder.name.setText(hot_order_data_list.get(position).get("text").toString());
                    viewHolder.imageView.setImageResource((Integer) hot_order_data_list.get(position).get("image"));
                    return convertView;
                }

                class ViewHolder {
                    protected TextView name;
                    protected ImageView imageView;
                }
            };

            //为功能导航图标网格布局绑定适配器
            hot_order_GridView.setAdapter(baseAdapter_hot_order);

            //添加列表项点击的监听器
            hot_order_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(), "你点击了第"+(position+1)+"个图标\n功能是："+imageName[position], Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(),InstrumentDetailInfoActivity.class);
                    startActivity(intent);
                }
            });
            //****************************结束处理子线程消息，将热门预约仪器加入到网格布局中***********************

            return rootView;
        }else {
            return rootView;
        }
    }

    //获取功能图标网格布局数据
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

    //获取热门预约仪器网格布局信息
    private ArrayList<HashMap<String,Object>> getHotOrderData(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<icon.length;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", image[i]);
            map.put("text", imageName[i]);
            list.add(map);
        }
        return list;
    }
}
