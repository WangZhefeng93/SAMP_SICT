package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.edit.EditInstrumentOrderInfoActivity;

public class InstrumentDetailOrderInfoFragment extends Fragment {
    //定义属性
    private TextView edit_instrument_detail_order_info_tv;  //编辑仪器预约信息按钮
    private TextView instrument_order_info_orderType_tv;    //预约类型
    private TextView instrument_order_info_orderForm_tv;    //预约形式
    private TextView instrument_order_info_simpleTemplate_tv;   //是否是简约模板
    private TextView instrument_order_info_procedure_tv;    //仪器流程
    private TextView instrument_order_info_state_tv;    //仪器状态
    private TextView instrument_order_info_useable_tv;  //是否可用
    private TextView instrument_order_info_advancedDays_tv; //提前预约天数
    private TextView instrument_order_info_orderStartDate_tv;   //预约开始时间
    private TextView instrument_order_info_maxOrderDays_tv;     //最长预约天数
    private TextView instrument_order_info_billingMethod_tv;    //计费方式
    private TextView instrument_order_info_usingCharge_tv;  //使用单价
    private TextView instrument_order_info_dealDataCharge_tv;   //处理数据单价
    private TextView instrument_order_info_machineDeal_tv;  //是否机加工
    private TextView instrument_order_info_sortInstrument_tv;   //是否测序仪
    private TextView instrument_order_info_maxWishValue_tv; //最大预估量
    private TextView instrument_order_info_moreInMoreOut_tv;    //是否多进多出
    private TextView instrument_order_info_oneTimeMaxValue_tv;  //统一时间最大样品数
    private TextView instrument_order_info_autoOrder_tv;    //是否可自动预约

    private View rootView = null;
    private Intent intent;

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

            //绑定布局文件控件
            initWidget();

            //为编辑按钮设置点击监听器
            setClickListenerForEditOrderInfo();

            return rootView;
        }else {
            return rootView;
        }
    }

    /**
     * 为编辑按钮设置点击监听器
     */
    private void setClickListenerForEditOrderInfo() {
        edit_instrument_detail_order_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), EditInstrumentOrderInfoActivity.class);
                getActivity().startActivity(intent);
                //Toast.makeText(getActivity(), "编辑预约信息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 绑定布局文件控件
     */
    private void initWidget() {
        edit_instrument_detail_order_info_tv = (TextView)rootView.findViewById(R.id.edit_instrument_detail_order_info_tv);
        instrument_order_info_orderType_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_orderType_tv);    //预约类型
        instrument_order_info_orderForm_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_orderForm_tv);    //预约形式
        instrument_order_info_simpleTemplate_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_simpleTemplate_tv);   //是否是简约模板
        instrument_order_info_procedure_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_procedure_tv);    //仪器流程
        instrument_order_info_state_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_state_tv);    //仪器状态
        instrument_order_info_useable_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_useable_tv);  //是否可用
        instrument_order_info_advancedDays_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_advancedDays_tv); //提前预约天数
        instrument_order_info_orderStartDate_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_orderStartDate_tv);   //预约开始时间
        instrument_order_info_maxOrderDays_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_maxOrderDays_tv);     //最长预约天数
        instrument_order_info_billingMethod_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_billingMethod_tv);    //计费方式
        instrument_order_info_usingCharge_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_usingCharge_tv);  //使用单价
        instrument_order_info_dealDataCharge_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_dealDataCharge_tv);   //处理数据单价
        instrument_order_info_machineDeal_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_machineDeal_tv);  //是否机加工
        instrument_order_info_sortInstrument_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_sortInstrument_tv);   //是否测序仪
        instrument_order_info_maxWishValue_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_maxWishValue_tv); //最大预估量
        instrument_order_info_moreInMoreOut_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_moreInMoreOut_tv);    //是否多进多出
        instrument_order_info_oneTimeMaxValue_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_oneTimeMaxValue_tv);  //统一时间最大样品数
        instrument_order_info_autoOrder_tv = (TextView) rootView.findViewById(R.id.instrument_order_info_autoOrder_tv);    //是否可自动预约
    }

}
