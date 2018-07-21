package com.ff161224.cc_commander.shareplatform;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonalCenterFragment extends Fragment {
    //声明属性
    private LinearLayout person_info_linearlayout;  //个人信息线性布局
    private LinearLayout order_record_linearlayout; //预约记录线性布局
    private LinearLayout have_ordered_linearlayout; //已预约仪器线性布局
    private LinearLayout my_collect_linearlayout;   //我的收藏线性布局
    private LinearLayout advice_linearlayout;   //意见反馈线性布局
    private LinearLayout logout_linearlayout;   //退出登录线性布局

    private TextView user_name_tv;  //用户名Textview
    private TextView user_student_no_tv;    //学号名Textview
    private TextView person_info_tv;    //个人信息Textview
    private TextView order_record_tv;   //预约记录Textview
    private TextView have_ordered_tv;   //已预约仪器Textview
    private TextView my_collect_tv;     //我的收藏Textview
    private TextView advice_tv;     //意见反馈Textview
    private TextView logout_tv;     //退出登录Textview

    private View rootView = null;   //Fragment布局

    Intent intent;

    //接收传递参数
    public static PersonalCenterFragment newInstance(){
        PersonalCenterFragment fragment = new PersonalCenterFragment();
        /*Bundle args=new Bundle();
        args.putInt("user_id",user_id);
        fragment.setArguments(args);*/
        return fragment;
    }

    //构造方法
    public PersonalCenterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (rootView == null){
            //加载Fragment布局
            rootView = inflater.inflate(R.layout.fragment_personal_center,container,false);

            //初始化控件
            initWidget(rootView);

            //设置TextView的值
            setText();

            //设置个人信息线性布局点击监听事件
            person_info_linearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  intent = new Intent(getActivity(),PersonInfoActivity.class);
                    startActivity(intent);
                }
            });

            //设置预约记录线性布局点击监听事件

            //设置已预约仪器线性布局点击监听事件

            //设置我的收藏线性布局点击监听事件

            //设置意见反馈线性布局点击监听事件

            //设置退出登录线性布局点击监听事件

            return rootView;
        }else {
            return rootView;
        }
    }

    //初始化控件
    private void initWidget(View view){
        person_info_linearlayout = (LinearLayout)view.findViewById(R.id.person_info_linearlayout);
        order_record_linearlayout = (LinearLayout)view.findViewById(R.id.order_record_linearlayout);
        have_ordered_linearlayout = (LinearLayout)view.findViewById(R.id.have_ordered_linearlayout);
        my_collect_linearlayout = (LinearLayout)view.findViewById(R.id.my_collect_linearlayout);
        advice_linearlayout = (LinearLayout)view.findViewById(R.id.advice_linearlayout);
        logout_linearlayout = (LinearLayout)view.findViewById(R.id.logout_linearlayout);

        user_name_tv = (TextView) view.findViewById(R.id.user_name_tv);
        user_student_no_tv = (TextView)view.findViewById(R.id.user_student_no_tv);
        person_info_tv = (TextView)view.findViewById(R.id.person_info_tv);
        order_record_tv = (TextView)view.findViewById(R.id.order_record_tv);
        have_ordered_tv = (TextView)view.findViewById(R.id.have_ordered_tv);
        my_collect_tv = (TextView)view.findViewById(R.id.my_collect_tv);
        advice_tv = (TextView)view.findViewById(R.id.advice_tv);
        logout_tv = (TextView)view.findViewById(R.id.logout_tv);
    }

    //设置TextView的值
    private void setText(){
        user_name_tv.setText("用户名：王喆峰");
        user_student_no_tv.setText("学号：201628013329010");
        person_info_tv.setText("个人信息");
        order_record_tv.setText("预约记录");
        have_ordered_tv.setText("已预约仪器");
        my_collect_tv.setText("我的收藏");
        advice_tv.setText("意见反馈");
        logout_tv.setText("退出登录");
    }

}
