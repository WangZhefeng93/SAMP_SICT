package com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.basic;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.ff161224.cc_commander.shareplatform.R;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.InstrumentInfoActivity;
import com.ff161224.cc_commander.shareplatform.main.dataInfo.instrumentInfo.create.order.CreateNewInstrumentActivity2;
import com.ff161224.cc_commander.shareplatform.utils.album_camera.ChoosePhotoFromAlbum;
import com.ff161224.cc_commander.shareplatform.utils.album_camera.ChoosePhotoFromCamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CreateNewInstrumentActivity1 extends AppCompatActivity {
    //声明控件变量
    private TextView tittle_create_new_instrument_cancel_tv;    //取消
    private TextView tittle_create_new_instrument_save_tv;      //保存
    private EditText create_new_instrument_ID_et;   //仪器ID
    private EditText create_new_instrument_name_et;  //仪器名称
    private EditText create_new_instrument_typeID_et;   //仪器型号
    private EditText create_new_instrument_assetID_et;  //资产编号
    private Spinner create_new_instrument_type1_spinner;    //仪器大类名称
    private Spinner create_new_instrument_type2_spinner;    //仪器中类名称
    private Spinner create_new_instrument_type3_spinner;    //仪器小类名称
    private EditText create_new_instrument_engName_et;  //仪器英文名称
    private EditText create_new_instrument_makeFactory_et;  //制造商
    private EditText create_new_instrument_saleFactory_et;  //经销商
    private EditText create_new_instrument_country_et;  //国别
    private TextView create_new_instrument_buyDate_tv;  //购买日期
    private EditText create_new_instrument_buyCoast_et; //购置金额
    private EditText create_new_instrument_factoryID_et;    //出厂编号
    private EditText create_new_instrument_dealPerson_et;   //经办领用人
    private EditText create_new_instrument_moneySource_et;  //购置经费来源
    private Spinner create_new_instrument_areaCenterName_spinner;   //所属区域中心
    private Spinner create_new_instrument_unitName_spinner;     //所属单位
    private Spinner create_new_instrument_groupName_spinner;    //所属研究组
    private Spinner create_new_instrument_operatePerson_spinner;    //操作人员
    private EditText create_new_instrument_location_et; //放置地点
    private EditText create_new_instrument_function_et;  //功能描述
    private EditText create_new_instrument_note_et; //备注
    private TextView create_new_instrument_photo_tv;    //点击上传仪器图片
    private ImageView create_new_instrument_photo_imageView;    //仪器图片
    private TextView create_new_instrument_next_tv1;    //下一步

    //声明其他变量
    private Intent intent;
    private CharSequence []items = {"拍照","从相册选择"};
    public static final int CUT_PICTURE = 1;
    public static final int SHOW_PICTURE = 2;
    public Uri imageUri;
    private static int type1Position = 1;
    private static int belongPosition = 3;
    private ArrayAdapter<String> type1Adapter = null;   //仪器大类下拉列表适配器
    private ArrayAdapter<String> type2Adapter = null;   //仪器中类下拉列表适配器
    private ArrayAdapter<String> type3Adapter = null;   //仪器小类下拉列表适配器
    private ArrayAdapter<String> centerAdapter = null;   //仪器所属区域中心下拉列表适配器
    private ArrayAdapter<String> unitAdapter = null;   //仪器所属单位下拉列表适配器
    private ArrayAdapter<String> groupAdapter = null;   //仪器所属研究组下拉列表适配器
    private String[] type1Arrays = new String[]{"北京","河北","天津","辽宁"};   //仪器大类下拉列表选项值
    private String[][] type2Arrays = new String[][]{
            {"东城区", "西城区", "崇文区", "宣武区", "朝阳区", "海淀区", "丰台区", "石景山区", "门头沟区",
                    "房山区", "通州区", "顺义区", "大兴区", "昌平区", "平谷区", "怀柔区", "密云县",
                    "延庆县"},
            {"石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水"},
            {"和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "西青区", "塘沽区", "汉沽区", "大港区",
                    "东丽区" },
            {"沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛"}
    };   //仪器中类下拉列表选项值
    private String[][][] type3Arrays = new String[][][]{
            {
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
            },
            {
                    {"无"},{"路北区","路南区","开平区","古冶区","丰南区","丰润区","曹妃甸区","滦南县","乐亭县","迁西县","玉田县","遵化市","迁安市","滦州市"},
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
            },
            {
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
            },
            {
                    {"和平区","沈河区","大东区","皇姑区","铁西区","苏家屯区","浑南区","沈北区","于洪区","辽中区","新民市","康平县","法库县"},
                    {"中山区","西岗区","沙河口区","甘井子区","旅顺口区","金州区","普兰店区","庄河市","长海县"},
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
                    {"连山区","龙港区","南票区","兴城市","绥中县","建昌县"}
            }
    };   //仪器小类下拉列表选项值
    private String[] centerArrays = new String[]{"北京","河北","天津","辽宁"};   //仪器所属区域中心下拉列表选项值
    private String[][] unitArrays = new String[][]{
            {"东城区", "西城区", "崇文区", "宣武区", "朝阳区", "海淀区", "丰台区", "石景山区", "门头沟区",
                    "房山区", "通州区", "顺义区", "大兴区", "昌平区", "平谷区", "怀柔区", "密云县",
                    "延庆县"},
            {"石家庄", "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水"},
            {"和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "西青区", "塘沽区", "汉沽区", "大港区",
                    "东丽区" },
            {"沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛"}
    };   //仪器所属单位下拉列表选项值
    private String[][][] groupArrays = new String[][][]{
            {
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
            },
            {
                    {"无"},{"路北区","路南区","开平区","古冶区","丰南区","丰润区","曹妃甸区","滦南县","乐亭县","迁西县","玉田县","遵化市","迁安市","滦州市"},
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
            },
            {
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
            },
            {
                    {"和平区","沈河区","大东区","皇姑区","铁西区","苏家屯区","浑南区","沈北区","于洪区","辽中区","新民市","康平县","法库县"},
                    {"中山区","西岗区","沙河口区","甘井子区","旅顺口区","金州区","普兰店区","庄河市","长海县"},
                    {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
                    {"连山区","龙港区","南票区","兴城市","绥中县","建昌县"}
            }
    };   //仪器所属研究组下拉列表选项值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局文件
        setContentView(R.layout.activity_create_new_instrument1);
        //为控件变量与布局文件中的控件进行绑定
        initWidget();
        //FileUtils.init();
        //为取消按钮设置点击监听器
        setOnClickListenerForCancle();
        //为保存按钮设置点击监听器
        setOnClickListenerForSave();
        //为下一步按钮设置点击监听器
        setOnClickListenerForNext();
        //为购置日期按钮设置点击监听器
        setOnClickListenerForBuyDate();
        //为仪器图片按钮设置点击监听器
        setOnClickListenerForInstrumentPicture();
        //设置仪器分类三级联动下拉框
        setSpinnerForInstrumentType();
        //设置区域中心，单位与研究组三级联动
        setSpinnerForInstrumentBelong();
    }

    /**
     * 为回退键设置点击监听器
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            new AlertDialog.Builder(CreateNewInstrumentActivity1.this).setTitle("系统提示")//设置对话框标题
                    .setMessage("点击确定后您将退出新建仪器！")//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
                            intent = new Intent(CreateNewInstrumentActivity1.this, InstrumentInfoActivity.class);
                            startActivity(intent);
                            finish();
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
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置区域中心，单位与研究组三级联动
     */
    private void setSpinnerForInstrumentBelong() {
        //绑定适配器和值
        centerAdapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,centerArrays);
        create_new_instrument_areaCenterName_spinner.setAdapter(centerAdapter);
        create_new_instrument_areaCenterName_spinner.setSelection(3,true);   //设置默认选中项，此处默认选中第1个值

        unitAdapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,unitArrays[3]);
        create_new_instrument_unitName_spinner.setAdapter(unitAdapter);
        create_new_instrument_unitName_spinner.setSelection(0,true);   //默认选中第0个值

        groupAdapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,groupArrays[3][0]);
        create_new_instrument_groupName_spinner.setAdapter(groupAdapter);
        create_new_instrument_groupName_spinner.setSelection(0,true);   //默认选中第0个值

        //为仪器所属区域中心下拉列表设置监听器
        create_new_instrument_areaCenterName_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   //position为当前区域中心选中的值的序号
                unitAdapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,unitArrays[position]);  //将仪器所属单位适配器的值改变为type2Array[position]中的值
                create_new_instrument_unitName_spinner.setAdapter(unitAdapter);    // 设置仪器中类下拉列表的选项内容适配器
                belongPosition = position;   //记录当前仪器所属区域中心序号，留给下面修改仪器所属研究组适配器时用
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //仪器所属单位下拉监听
        create_new_instrument_unitName_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                groupAdapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,groupArrays[belongPosition][position]);
                create_new_instrument_groupName_spinner.setAdapter(groupAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 设置仪器分类三级联动下拉框
     */
    private void setSpinnerForInstrumentType() {
        //绑定适配器和值
        type1Adapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,type1Arrays);
        create_new_instrument_type1_spinner.setAdapter(type1Adapter);
        create_new_instrument_type1_spinner.setSelection(1,true);   //设置默认选中项，此处默认选中第1个值

        type2Adapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,type2Arrays[1]);
        create_new_instrument_type2_spinner.setAdapter(type2Adapter);
        create_new_instrument_type2_spinner.setSelection(1,true);   //默认选中第0个值

        type3Adapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,type3Arrays[1][1]);
        create_new_instrument_type3_spinner.setAdapter(type3Adapter);
        create_new_instrument_type3_spinner.setSelection(0,true);   //默认选中第0个值

        //为仪器大类下拉列表设置监听器
        create_new_instrument_type1_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   //position为当前省级选中的值的序号
                type2Adapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,type2Arrays[position]);  //将仪器中类适配器的值改变为type2Array[position]中的值
                create_new_instrument_type2_spinner.setAdapter(type2Adapter);    // 设置仪器中类下拉列表的选项内容适配器
                type1Position = position;   //记录当前仪器大类序号，留给下面修改仪器小类适配器时用
                Log.d("position1 = ",position+"");
                Log.d("type1Position1 = ",type1Position+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //仪器中类下拉监听
        create_new_instrument_type2_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type3Adapter = new ArrayAdapter<String>(CreateNewInstrumentActivity1.this,android.R.layout.simple_spinner_item,type3Arrays[type1Position][position]);
                create_new_instrument_type3_spinner.setAdapter(type3Adapter);
                Log.d("position2 = ",position+"");
                Log.d("type1Position2 = ",type1Position+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 为仪器图片按钮设置点击监听器
     */
    private void setOnClickListenerForInstrumentPicture() {
        create_new_instrument_photo_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CreateNewInstrumentActivity1.this)
                    .setTitle("上传仪器照片")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0://拍照
                                    Toast.makeText(CreateNewInstrumentActivity1.this, "调用系统自带照相机拍照", Toast.LENGTH_SHORT).show();
                                    imageUri = new ChoosePhotoFromCamera().jump2Camera(CreateNewInstrumentActivity1.this,CUT_PICTURE);
                                    break;
                                case 1://从相册选择
                                    Toast.makeText(CreateNewInstrumentActivity1.this, "使用系统自带相册中的照片", Toast.LENGTH_SHORT).show();
                                    imageUri = new ChoosePhotoFromAlbum().chooseFromAlbum(CreateNewInstrumentActivity1.this,CUT_PICTURE);
                                    break;
                            }
                        }
                    })
                    .create()
                    .show();
                }
            });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CUT_PICTURE:
                if (resultCode == RESULT_OK) {
                    if (data != null){
                        //此处启动裁剪程序
                        Intent intent = new Intent("com.android.camera.action.CROP");
                        intent.setDataAndType(data.getData(), "image/*");
                        intent.putExtra("scale", true);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, SHOW_PICTURE);
                    }else {
                        //此处启动裁剪程序
                        Intent intent = new Intent("com.android.camera.action.CROP");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(imageUri, "image/*");
                        intent.putExtra("scale", true);
                        File outputImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/test/output_image.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
                        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                        startActivityForResult(intent, SHOW_PICTURE);
                    }
                }
                break;
            case SHOW_PICTURE:
                if (resultCode == RESULT_OK) {
                    try {
                        //将output_image.jpg对象解析成Bitmap对象，然后设置到ImageView中显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        create_new_instrument_photo_imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 为购置日期按钮设置点击监听事件
     */
    private void setOnClickListenerForBuyDate() {
        create_new_instrument_buyDate_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerView.Builder(CreateNewInstrumentActivity1.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = new Date(date.getTime());
                        String time = sm.format(d);
                        //Toast.makeText(getApplicationContext(),time, Toast.LENGTH_LONG).show();
                        create_new_instrument_buyDate_tv.setText(time);
                    }
                }).build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
    }

    /**
     * 为取消按钮设置点击监听器
     */
    private void setOnClickListenerForCancle() {
        tittle_create_new_instrument_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CreateNewInstrumentActivity1.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("点击取消，您的修改将无法保存！直接退出新建仪器流程！")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                intent = new Intent(CreateNewInstrumentActivity1.this,InstrumentInfoActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                        Log.i("alertdialog"," 请保存数据！");
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });
    }

    /**
     * 为保存按钮设置点击监听器
     */
    private void setOnClickListenerForSave() {
        tittle_create_new_instrument_save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(CreateNewInstrumentActivity1.this).setTitle("系统提示")//设置对话框标题
                        .setMessage("您确定要保存对仪器基本信息的修改吗？")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                // TODO Auto-generated method stub
                                Toast.makeText(CreateNewInstrumentActivity1.this, "保存修改！", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                        Log.i("alertdialog"," 请保存数据！");
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });
    }

    /**
     * 为下一步按钮设置点击监听器
     */
    private void setOnClickListenerForNext() {
        create_new_instrument_next_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateNewInstrumentActivity1.this,CreateNewInstrumentActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 为控件变量与布局文件中的控件进行绑定
     */
    private void initWidget() {
        tittle_create_new_instrument_cancel_tv = (TextView) findViewById(R.id.tittle_create_new_instrument_cancel_tv);    //取消
        tittle_create_new_instrument_save_tv = (TextView) findViewById(R.id.tittle_create_new_instrument_save_tv);      //保存
        create_new_instrument_ID_et = (EditText) findViewById(R.id.create_new_instrument_ID_et);   //仪器ID
        create_new_instrument_name_et = (EditText) findViewById(R.id.create_new_instrument_name_et);  //仪器名称
        create_new_instrument_typeID_et = (EditText) findViewById(R.id.create_new_instrument_typeID_et);   //仪器型号
        create_new_instrument_assetID_et = (EditText) findViewById(R.id.create_new_instrument_assetID_et);  //资产编号
        create_new_instrument_type1_spinner = (Spinner) findViewById(R.id.create_new_instrument_type1_spinner);    //仪器大类名称
        create_new_instrument_type2_spinner = (Spinner) findViewById(R.id.create_new_instrument_type2_spinner);    //仪器中类名称
        create_new_instrument_type3_spinner = (Spinner) findViewById(R.id.create_new_instrument_type3_spinner);    //仪器小类名称
        create_new_instrument_engName_et = (EditText) findViewById(R.id.create_new_instrument_engName_et);  //仪器英文名称
        create_new_instrument_makeFactory_et = (EditText) findViewById(R.id.create_new_instrument_makeFactory_et);  //制造商
        create_new_instrument_saleFactory_et = (EditText) findViewById(R.id.create_new_instrument_saleFactory_et);  //经销商
        create_new_instrument_country_et = (EditText) findViewById(R.id.create_new_instrument_country_et);  //国别
        create_new_instrument_buyDate_tv = (TextView) findViewById(R.id.create_new_instrument_buyDate_tv);  //购买日期
        create_new_instrument_buyCoast_et = (EditText) findViewById(R.id.create_new_instrument_buyCoast_et); //购置金额
        create_new_instrument_factoryID_et = (EditText) findViewById(R.id.create_new_instrument_factoryID_et);    //出厂编号
        create_new_instrument_dealPerson_et = (EditText) findViewById(R.id.create_new_instrument_dealPerson_et);   //经办领用人
        create_new_instrument_moneySource_et = (EditText) findViewById(R.id.create_new_instrument_moneySource_et);  //购置经费来源
        create_new_instrument_areaCenterName_spinner = (Spinner) findViewById(R.id.create_new_instrument_areaCenterName_spinner);   //所属区域中心
        create_new_instrument_unitName_spinner = (Spinner) findViewById(R.id.create_new_instrument_unitName_spinner);     //所属单位
        create_new_instrument_groupName_spinner = (Spinner) findViewById(R.id.create_new_instrument_groupName_spinner);    //所属研究组
        create_new_instrument_operatePerson_spinner = (Spinner) findViewById(R.id.create_new_instrument_operatePerson_spinner);    //操作人员
        create_new_instrument_location_et = (EditText) findViewById(R.id.create_new_instrument_location_et); //放置地
        create_new_instrument_function_et = (EditText) findViewById(R.id.create_new_instrument_function_et);  //功能描述
        create_new_instrument_note_et = (EditText) findViewById(R.id.create_new_instrument_note_et); //备注
        create_new_instrument_photo_tv = (TextView) findViewById(R.id.create_new_instrument_photo_tv);    //点击上传仪器图片
        create_new_instrument_photo_imageView = (ImageView) findViewById(R.id.create_new_instrument_photo_imageView);    //仪器图片
        create_new_instrument_next_tv1 = (TextView) findViewById(R.id.create_new_instrument_next_tv1);    //下一步
    }
}
