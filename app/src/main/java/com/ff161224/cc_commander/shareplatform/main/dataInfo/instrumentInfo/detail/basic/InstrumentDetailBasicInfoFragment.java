package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.detail.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.edit.EditInstrumentBasicInfoActivity;

public class InstrumentDetailBasicInfoFragment extends Fragment {
    //定义属性
    private TextView edit_instrument_detail_basic_info_tv;  //编辑仪器基本信息ID
    private TextView instrument_basic_info_ID_tv;   //仪器ID
    private TextView instrument_basic_info_name_tv; //仪器名称
    private TextView instrument_basic_info_typeID_tv;   //仪器型号
    private TextView instrument_basic_info_arpID_tv;    //资产编号
    private TextView instrument_basic_info_type1_tv;    //仪器大类名称
    private TextView instrument_basic_info_type2_tv;    //仪器中类名称
    private TextView instrument_basic_info_type3_tv;    //仪器小类名称
    private TextView instrument_basic_info_engName_tv;  //仪器英文名称
    private TextView instrument_basic_info_makeFactory_tv;  //制造商
    private TextView instrument_basic_info_saleFactory_tv;  //经销商
    private TextView instrument_basic_info_country_tv;  //国别
    private TextView instrument_basic_info_buyDate_tv;  //购置日期
    private TextView instrument_basic_info_buyCoast_tv; //购置金额
    private TextView instrument_basic_info_factoryID_tv;    //出厂编号
    private TextView instrument_basic_info_dealPerson_tv;   //经办领用人
    private TextView instrument_basic_info_moneySource_tv;  //购置经费来源
    private TextView instrument_basic_info_areaCenterName_tv;   //所属区域中心
    private TextView instrument_basic_info_unitName_tv; //所属单位名称
    private TextView instrument_basic_info_groupName_tv;    //所属研究组
    private TextView instrument_basic_info_operatePerson_tv;    //仪器操作人员
    private TextView instrument_basic_info_location_tv; //仪器放置地点
    private TextView instrument_basic_info_function_tv; //功能描述
    private TextView instrument_basic_info_note_tv; //备注
    private ImageView instrument_basic_info_instrument_imgv;    //仪器照片

    private View rootView = null;
    private Intent intent;

    //构造方法
    public InstrumentDetailBasicInfoFragment() {
        // Required empty public constructor
    }

    //接收传递参数
    public static InstrumentDetailBasicInfoFragment newInstance(){
        InstrumentDetailBasicInfoFragment fragment = new InstrumentDetailBasicInfoFragment();
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
            rootView = inflater.inflate(R.layout.fragment_instrument_detail_basic_info, container, false);

            //初始化控件信息，绑定控件变量
            intiWidget();

            //为编辑按钮设置点击监听器
            setClickListenerForEdit();

            return rootView;
        }else {
            return rootView;
        }
    }

    /**
     * 为编辑按钮设置点击监听器
     */
    private void setClickListenerForEdit() {
        edit_instrument_detail_basic_info_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), EditInstrumentBasicInfoActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    /**
     * 初始化控件信息，绑定控件变量
     */
    private void intiWidget() {
        edit_instrument_detail_basic_info_tv = (TextView) rootView.findViewById(R.id.edit_instrument_detail_basic_info_tv); //编辑仪器基本信息ID
        instrument_basic_info_ID_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_ID_tv);   //仪器ID
        instrument_basic_info_name_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_name_tv); //仪器名称
        instrument_basic_info_typeID_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_typeID_tv);   //仪器型号
        instrument_basic_info_arpID_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_arpID_tv);    //资产编号
        instrument_basic_info_type1_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_type1_tv);    //仪器大类名称
        instrument_basic_info_type2_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_type2_tv);    //仪器中类名称
        instrument_basic_info_type3_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_type3_tv);    //仪器小类名称
        instrument_basic_info_engName_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_engName_tv);  //仪器英文名称
        instrument_basic_info_makeFactory_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_makeFactory_tv);  //制造商
        instrument_basic_info_saleFactory_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_saleFactory_tv);  //经销商
        instrument_basic_info_country_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_country_tv);  //国别
        instrument_basic_info_buyDate_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_buyDate_tv);  //购置日期
        instrument_basic_info_buyCoast_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_buyCoast_tv); //购置金额
        instrument_basic_info_factoryID_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_factoryID_tv);    //出厂编号
        instrument_basic_info_dealPerson_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_dealPerson_tv);   //经办领用人
        instrument_basic_info_moneySource_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_moneySource_tv);  //购置经费来源
        instrument_basic_info_areaCenterName_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_areaCenterName_tv);   //所属区域中心
        instrument_basic_info_unitName_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_unitName_tv); //所属单位名称
        instrument_basic_info_groupName_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_groupName_tv);    //所属研究组
        instrument_basic_info_operatePerson_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_operatePerson_tv);    //仪器操作人员
        instrument_basic_info_location_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_location_tv); //仪器放置地点
        instrument_basic_info_function_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_function_tv); //功能描述
        instrument_basic_info_note_tv = (TextView) rootView.findViewById(R.id.instrument_basic_info_note_tv); //备注
        instrument_basic_info_instrument_imgv = (ImageView) rootView.findViewById(R.id.instrument_basic_info_instrument_imgv);    //仪器照片
    }

}
