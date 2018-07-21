package com.ff161224.cc_commander.shareplatform;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InstrumentDetailEnvironmentArgumentFragment extends Fragment {
    //定义属性
    private View rootView = null;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_instrument_detail_basic_info, container, false);
            return rootView;
        }else {
            return rootView;
        }
    }

}
