package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;

public class InstrumentDetailOrderInfoFragment extends Fragment {

    //定义属性
    private View rootView = null;
    private TextView edit_instrument_detail_order_info_tv,edit_instrument_detail_order_info_tv2;

    //构造方法
    public InstrumentDetailOrderInfoFragment() {
        // Required empty public constructor
    }

    //接收传递参数
    public static InstrumentDetailOrderInfoFragment newInstance(){
        InstrumentDetailOrderInfoFragment fragment = new InstrumentDetailOrderInfoFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (rootView == null){
            //加载布局文件
            rootView = inflater.inflate(R.layout.fragment_instrument_detail_order_info, container, false);

            //绑定编辑按钮
            edit_instrument_detail_order_info_tv = (TextView)rootView.findViewById(R.id.edit_instrument_detail_order_info_tv);
            edit_instrument_detail_order_info_tv2 = (TextView)rootView.findViewById(R.id.edit_instrument_detail_order_info_tv2);

            //为编辑按钮设置点击监听器
            edit_instrument_detail_order_info_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "编辑预约信息", Toast.LENGTH_SHORT).show();
                }
            });
            edit_instrument_detail_order_info_tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "编辑特殊信息设置", Toast.LENGTH_SHORT).show();
                }
            });

            return rootView;
        }else {
            return rootView;
        }
    }

}
