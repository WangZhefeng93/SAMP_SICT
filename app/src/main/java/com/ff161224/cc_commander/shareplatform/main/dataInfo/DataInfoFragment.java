package com.ff161224.cc_commander.shareplatform.main.dataInfo;

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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.InstrumentInfoActivity;
import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.SearchGridView;

import java.util.ArrayList;
import java.util.HashMap;

public class DataInfoFragment extends Fragment {
    //声明属性
    private SearchGridView gridView;    //功能导航图标网格布局控件
    private ArrayList<HashMap<String,Object>> data_list = new ArrayList<>();    //功能导航图标数据
    private HashMap<String,Integer> url_maps = new HashMap<>(); //获取广告轮播条图片
    private BaseAdapter baseAdapter;    //网格布局适配器
    private ScrollView data_info_fragment_scrollView;   //滑动控件
    private View rootView = null;   //Fragment布局

    Intent intent;

    //构建数据
    private int[] icon = { R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher,
                            R.drawable.ic_launcher, R.drawable.ic_launcher,
                            R.drawable.ic_launcher, R.drawable.ic_launcher,
                            R.drawable.ic_launcher,R.drawable.ic_launcher};
    private  String[] iconName = new String[9];

    //为Fragment传递参数
    public static DataInfoFragment newInstance(String[] iconName){
        DataInfoFragment fragment = new DataInfoFragment();
        fragment.setIconName(iconName);
        /*Bundle args=new Bundle();
        args.putInt("user_id",user_id);
        fragment.setArguments(args);*/
        return fragment;
    }

    //Fragment构造方法
    public DataInfoFragment() {
    }

    public String[] getIconName() {
        return iconName;
    }

    public void setIconName(String[] iconName) {
        this.iconName = iconName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (rootView == null){
            //加载Fragment布局
            rootView = inflater.inflate(R.layout.fragment_data_info,container,false);

            //接收由MainActivity传递过来的user_id参数

            //绑定竖直滚动条控件
            data_info_fragment_scrollView = (ScrollView)rootView.findViewById(R.id.data_info_fragment_scrollView);

            //让scrollview跳转到顶部，即每次在顶部显示，必须放在runnable()方法中
            data_info_fragment_scrollView.post(new Runnable() {
                @Override
                public void run() {
                    data_info_fragment_scrollView.scrollTo(0, 0);
                }
            });

            //获取广告轮播条控件
            final SliderLayout mDemoSlider1 = (SliderLayout) rootView.findViewById(R.id.slider);

            //开启子线程，获取轮播图图片
            url_maps.put("共享平台宣传图片1",R.drawable.carousel_figure_1);
            url_maps.put("共享平台宣传图片2",R.drawable.carousel_figure_2);
            url_maps.put("共享平台宣传图片3",R.drawable.carousel_figure_3);
            url_maps.put("共享平台宣传图片4",R.drawable.carousel_figure_4);
            url_maps.put("共享平台宣传图片5",R.drawable.carousel_figure_5);

            //************************开始处理子线程消息，将图片加入到轮播条控件中*****************************
            for (final String name : url_maps.keySet()){
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView
                        .description(name)
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                Toast.makeText(getActivity(),"你点击了图片"+name,Toast.LENGTH_SHORT).show();
                            }
                        });//点击轮播图
                //点击图片时可用到
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);

                mDemoSlider1.addSlider(textSliderView);
            }

            // 设置默认指示器位置(默认指示器白色,位置在sliderlayout底部)
            mDemoSlider1.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

            // 设置自定义指示器(位置自定义)
            mDemoSlider1.setCustomIndicator((PagerIndicator) rootView.findViewById(R.id.custom_indicator));

            // 设置TextView自定义动画
            mDemoSlider1.setCustomAnimation(new DescriptionAnimation());

            //mDemoSlider2.setCustomAnimation(new ChildAnimationExample()); // 多种效果，进入该类修改，参考效果github/daimajia/AndroidViewAnimations

            // 设置持续时间
            mDemoSlider1.setDuration(5000);

            //mDemoSlider1.addOnPageChangeListener(this);//轮播图轮播监听

            //************************结束处理子线程消息，将图片加入到轮播条控件中*****************************

            //开启子线程，完成功能图标导航的显示
            data_list = getFunctionNavigationData();

            //获取网格布局控件
            gridView = (SearchGridView) rootView.findViewById(R.id.gview);

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
            gridView.setAdapter(baseAdapter);

            //添加列表项点击的监听器
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(), "你点击了第"+(position+1)+"个图标\n功能是："+iconName[position], Toast.LENGTH_SHORT).show();
                    switch (position){
                        case 0:     //点击的是仪器信息
                            intent = new Intent(getActivity(),InstrumentInfoActivity.class);
                            startActivity(intent);
                            break;
                    }
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
