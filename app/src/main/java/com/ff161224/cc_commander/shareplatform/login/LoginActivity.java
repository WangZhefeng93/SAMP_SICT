package com.ff161224.cc_commander.shareplatform.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ff161224.cc_commander.shareplatform.main.MainActivity;
import com.ff161224.cc_commander.shareplatform.R;

public class LoginActivity extends AppCompatActivity {
    //声明控件变量
    private AutoCompleteTextView student_number_at;     //用户名、邮箱或手机号输入框
    private EditText password_edt;      //密码输入框
    private ProgressBar login_progress;     //登录进度条
    private LinearLayout login_ll;      //整个登录界面线性布局
    private Button login_in_button;     //登录按钮
    private TextView login_tip;     //"正在登录，请稍后......"提示文本框
    private TextView quick_login_tv;    //手机快速登录提示文本框
    private TextView forget_password_tv;    //忘记密码提示文本框
    private TextView get_identify_code_tv;  //获取验证码提示文本框
    private View mLoginFormView;        //滚动视图线性布局

    //用户输入的学号
    String student_number = "";
    //用户输入的密码
    String password = "";
    //本地存储学号
    String pref_student_number = "";
    //本地存储密码
    String pref_password = "";

    //参数传递对象
    Intent intent;

    //登录模式标志位：1代表用户名和密码登录模式。-1代表手机号和验证码登录模式
    int login_model_flag;

    //忘记密码模式标志位：1代表正常登录模式。-1代表忘记密码找回模式
    int forget_password_flag;

    //本地存储
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收登录模式标志位参数
        intent = getIntent();
        login_model_flag = intent.getIntExtra("login_model_flag",1);
        forget_password_flag = intent.getIntExtra("forget_password_flag",1);
        //pref_student_number = intent.getStringExtra("pref_student_number");
        //pref_password = intent.getStringExtra("pref_password");
        //去掉标题栏
        getSupportActionBar().hide();
        //加载布局
        setContentView(R.layout.activity_login);
        //为控件变量绑定控件
        login_ll = (LinearLayout)findViewById(R.id.login_ll);   //整个登录界面线性布局
        student_number_at = (AutoCompleteTextView)findViewById(R.id.student_number_at);     //用户名、邮箱或手机号输入框
        password_edt = (EditText)findViewById(R.id.password_edt);   //密码输入框
        login_in_button = (Button)findViewById(R.id.login_in_button);   //登录按钮
        login_progress = (ProgressBar)findViewById(R.id.login_progress);    //登录进度条
        mLoginFormView = findViewById(R.id.login_form);     //滚动视图线性布局
        login_tip = (TextView)findViewById(R.id.login_tip);     //"正在登录，请稍后......"提示文本框
        quick_login_tv = (TextView)findViewById(R.id.quick_login_tv);   //手机快速登录提示文本框
        forget_password_tv = (TextView)findViewById(R.id.forget_password_tv);   //忘记密码提示文本框
        get_identify_code_tv = (TextView)findViewById(R.id.get_identify_code_tv);   //获取验证码提示文本框
        //设置启动页图片的宽高适应屏幕
        scaleImage(this, login_ll, R.drawable.login_background2);
        //初始输入框设置为空
        student_number_at.setText("");
        password_edt.setText("");
        if (login_model_flag > 0 && forget_password_flag > 0) {     //说明是采用用户名或邮箱加密码的形式登录

            //设置输入提示
            student_number_at.setHint("请输入用户名或邮箱");
            student_number_at.setHintTextColor(Color.WHITE);
            student_number_at.setTextSize(20);
            password_edt.setHint("请输入密码");
            password_edt.setHintTextColor(Color.WHITE);
            password_edt.setTextSize(20);
            //不显示获取验证码按钮
            get_identify_code_tv.setVisibility(View.GONE);
            //为手机快速登录按钮设置点击监听器
            quick_login_tv.setText("手机快速登录");
            quick_login_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(LoginActivity.this,LoginActivity.class);
                    intent.putExtra("login_model_flag",-1);
                    intent.putExtra("forget_password_flag",1);
                    startActivity(intent);
                    finish();
                }
            });
            //忘记密码
            forget_password_tv.setText("忘记密码");
            forget_password_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(LoginActivity.this,LoginActivity.class);
                    intent.putExtra("login_model_flag",-1);
                    intent.putExtra("forget_password_flag",-1);
                    startActivity(intent);
                    finish();
                }
            });
            //为登录按钮设置点击监听器
            login_in_button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    View focusView = null;
                    //获取用户输入的学号
                    student_number = student_number_at.getText().toString();
                    //获取用户输入的密码
                    password = password_edt.getText().toString();
                    //判断学号是否为空
                    if(!isEmptyStudentNumber(student_number,focusView,login_model_flag))
                        return;
                    //判断密码是否为空
                    if(!isEmptyPassword(password,focusView,login_model_flag))
                        return;
                    //判断密码是否至少为6位
                    if(!isPasswordLength(password,focusView))
                        return;
                    //如果验证通过，则显示进度条，进入显示登录等待状态
                    mLoginFormView.setVisibility(View.GONE);
                    login_progress.setVisibility(View.VISIBLE);
                    login_tip.setVisibility(View.VISIBLE);

                    //记住账号密码，实现下次自动登录
                    /*if ("".equals(pref_student_number) && "".equals(pref_password))
                        remember(student_number,password);*/

                    //开启子线程。将学号与密码发送给服务器端，在服务器端完成验证后返回登录消息
                    intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else if (login_model_flag < 0 && forget_password_flag > 0){    //说明是采用的手机加验证码的形式登录
            //设置输入提示
            student_number_at.setHint("请输入手机号");
            student_number_at.setHintTextColor(Color.WHITE);
            student_number_at.setTextSize(20);
            password_edt.setHint("请输入验证码");
            password_edt.setHintTextColor(Color.WHITE);
            password_edt.setTextSize(20);
            //显示获取验证码按钮
            get_identify_code_tv.setVisibility(View.VISIBLE);
            //为获取验证码按钮设置监听器
            get_identify_code_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    View focusView = null;
                    //获取用户输入的手机号
                    student_number = student_number_at.getText().toString();
                    if(isEmptyStudentNumber(student_number,focusView,login_model_flag))
                        Toast.makeText(LoginActivity.this, "验证码已发送至您的手机，请注意查收！", Toast.LENGTH_SHORT).show();
                }
            });
            //设置切换账号或邮箱登录模式按钮
            quick_login_tv.setText("账号或邮箱登录");
            quick_login_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(LoginActivity.this,LoginActivity.class);
                    intent.putExtra("login_model_flag",1);
                    intent.putExtra("forget_password_flag",1);
                    startActivity(intent);
                    finish();
                }
            });
            //设置忘记密码按钮不可见
            forget_password_tv.setVisibility(View.INVISIBLE);
            //设置登录按钮提示
            login_in_button.setText("登录");
            //显示获取验证码按钮
            get_identify_code_tv.setVisibility(View.VISIBLE);
            //为获取验证码按钮设置监听器
            get_identify_code_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    View focusView = null;
                    //获取用户输入的手机号
                    student_number = student_number_at.getText().toString();
                    if(isEmptyStudentNumber(student_number,focusView,login_model_flag))
                        Toast.makeText(LoginActivity.this, "验证码已发送至您的手机，请注意查收！", Toast.LENGTH_SHORT).show();
                }
            });
            //为登录按钮设置点击监听器
            login_in_button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    View focusView = null;
                    //获取用户输入的学号
                    student_number = student_number_at.getText().toString();

                    //获取用户输入的密码
                    password = password_edt.getText().toString();

                    //判断手机号是否为空
                    if(!isEmptyStudentNumber(student_number,focusView,login_model_flag))
                        return;

                    //判断手机号是否符合规则

                    //判断验证码是否为空
                    if(!isEmptyPassword(password,focusView,login_model_flag))
                        return;

                    //判断验证码是否正确

                    //开启子线程。将学号与密码发送给服务器端，在服务器端完成验证后返回登录消息
                    intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else if (forget_password_flag < 0){
            //设置输入提示
            student_number_at.setHint("请输入手机号");
            student_number_at.setHintTextColor(Color.WHITE);
            student_number_at.setTextSize(20);
            password_edt.setHint("请输入验证码");
            password_edt.setHintTextColor(Color.WHITE);
            password_edt.setTextSize(20);
            //显示获取验证码按钮
            get_identify_code_tv.setVisibility(View.VISIBLE);
            //为获取验证码按钮设置监听器
            get_identify_code_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    View focusView = null;
                    //获取用户输入的手机号
                    student_number = student_number_at.getText().toString();
                    if(isEmptyStudentNumber(student_number,focusView,login_model_flag))
                        Toast.makeText(LoginActivity.this, "验证码已发送至您的手机，请注意查收！", Toast.LENGTH_SHORT).show();
                    //else
                        //Toast.makeText(LoginActivity.this, "请先输入手机号，再获取验证码", Toast.LENGTH_SHORT).show();
                }
            });
            //设置登录按钮提示
            login_in_button.setText("重置密码");
            //设置切换账号或邮箱登录模式按钮
            quick_login_tv.setText("账号或邮箱登录");
            quick_login_tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(LoginActivity.this,LoginActivity.class);
                    intent.putExtra("login_model_flag",1);
                    intent.putExtra("forget_password_flag",1);
                    startActivity(intent);
                    finish();
                }
            });
            //为登录按钮设置点击监听器
            login_in_button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    View focusView = null;
                    //获取用户输入的手机号
                    student_number = student_number_at.getText().toString();

                    //获取用户输入的验证码
                    password = password_edt.getText().toString();

                    //判断手机号是否为空
                    if(!isEmptyStudentNumber(student_number,focusView,login_model_flag))
                        return;

                    //判断手机号是否符合规则

                    //判断验证码是否为空
                    if(!isEmptyPassword(password,focusView,login_model_flag))
                        return;

                    //判断验证码是否正确

                    //开启子线程。将学号与密码发送给服务器端，在服务器端完成验证后返回登录消息
                }
            });
        }
    }

    //实现在账号和密码登录的时候完成记住密码操作，下次直接到主页，无需登录
    private void remember(String student_number, String password) {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //调用SharedPreferences对象的edit()方法获取一个SharedPreferences.Editor对象
        editor = pref.edit();
        //向SharedPreferences.Editor对象添加数据
        editor.putString("student_number",student_number);
        editor.putString("password",password);
        //调用commit()方法将添加的数据提交，完成数据存储
        editor.commit();
    }

    //判断学号是否为空
    private boolean isEmptyStudentNumber(String student_number,View focusView,int login_model_flag){
        if (TextUtils.isEmpty(student_number)) {
            if (login_model_flag > 0){
                student_number_at.setError("用户名或邮箱不能为空！");
            }else if (login_model_flag < 0){
                student_number_at.setError("手机号不能为空！");
            }
            focusView = student_number_at;
            return false;
        } else
            return true;
    }

    //判断密码是否为空
    private boolean isEmptyPassword(String password,View focusView,int login_model_flag){
        if (TextUtils.isEmpty(password)) {
            if (login_model_flag > 0){
                password_edt.setError("密码不能为空！");
            }else if (login_model_flag < 0){
                password_edt.setError("验证码不能为空！");
            }
            focusView = password_edt;
            return false;
        } else
            return true;
    }

    //判断密码是否至少为6位
    private boolean isPasswordLength(String password,View focusView){
        if (password.length() >= 6)
            return true;
        else{
            password_edt.setError("密码至少为6位！");
            focusView = password_edt;
            return false;
        }
    }

    //处理背景图片
    public static void scaleImage(final Activity activity, final View view, int drawableResId) {

        // 获取屏幕的高宽
        Point outSize = new Point();
        activity.getWindow().getWindowManager().getDefaultDisplay().getSize(outSize);

        // 解析将要被处理的图片
        Bitmap resourceBitmap = BitmapFactory.decodeResource(activity.getResources(), drawableResId);

        if (resourceBitmap == null) {
            return;
        }

        // 开始对图片进行拉伸或者缩放

        // 使用图片的缩放比例计算将要放大的图片的高度
        int bitmapScaledHeight = Math.round(resourceBitmap.getHeight() * outSize.x * 1.0f / resourceBitmap.getWidth());

        // 以屏幕的宽度为基准，如果图片的宽度比屏幕宽，则等比缩小，如果窄，则放大
        final Bitmap scaledBitmap = Bitmap.createScaledBitmap(resourceBitmap, outSize.x, bitmapScaledHeight, false);

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //这里防止图像的重复创建，避免申请不必要的内存空间
                if (scaledBitmap.isRecycled())
                    //必须返回true
                    return true;


                // 当UI绘制完毕，我们对图片进行处理
                int viewHeight = view.getMeasuredHeight();


                // 计算将要裁剪的图片的顶部以及底部的偏移量
                int offset = Math.abs((scaledBitmap.getHeight() - viewHeight)) / 2;


                // 对图片以中心进行裁剪，裁剪出的图片就是非常适合做引导页的图片了
                Bitmap finallyBitmap = Bitmap.createBitmap(scaledBitmap, 0, offset, scaledBitmap.getWidth(),
                        scaledBitmap.getHeight() - offset * 2);


                if (!finallyBitmap.equals(scaledBitmap)) {//如果返回的不是原图，则对原图进行回收
                    scaledBitmap.recycle();
                    System.gc();
                }


                // 设置图片显示
                view.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), finallyBitmap));
                return true;
            }
        });
    }
}