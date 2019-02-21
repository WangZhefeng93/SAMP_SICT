package com.ff161224.cc_commander.shareplatform.launch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.RelativeLayout;

import com.ff161224.cc_commander.shareplatform.main.MainActivity;
import com.ff161224.cc_commander.shareplatform.login.LoginActivity;
import com.ff161224.cc_commander.shareplatform.R;

public class LaunchActivity extends Activity {

    //声明相对布局控件变量
    private RelativeLayout activity_launch_relative_layout;

    //设置进入下一个界面的时间。默认为3秒
    private final int SPLASH_DISPLAY_LENGHT = 3000;

    //参数传递对象
    Intent intent;

    //本地存储
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //加载布局文件
        setContentView(R.layout.activity_launch);

        //为布局控件变量绑定控件
        activity_launch_relative_layout = (RelativeLayout)findViewById(R.id.activity_launch_relative_layout);

        //设置启动页图片的宽高适应屏幕
        scaleImage(this, activity_launch_relative_layout, R.drawable.app_launch);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //启动子线程进入项目主界面
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                //首先从本地存储中读取数据
                /*final String pref_student_number = pref.getString("student_number","");
                final String pref_password = pref.getString("password","");
                if (!"".equals(pref_student_number) && !"".equals(pref_password)){      //如果本地存储中有数据，则直接登录
                    //开启子线程。将学号与密码发送给服务器端，在服务器端完成验证后返回登录消息
                    intent = new Intent(LaunchActivity.this,MainActivity.class);
                    finish();
                }else {
                    intent = new Intent(LaunchActivity.this,LoginActivity.class);
                    intent.putExtra("login_model_flag",1);
                    intent.putExtra("forget_password_flag",1);
                    intent.putExtra("pref_student_number",pref_student_number);
                    intent.putExtra("pref_password",pref_password);
                }*/
                intent = new Intent(LaunchActivity.this,MainActivity.class);
                intent.putExtra("login_model_flag",1);
                intent.putExtra("forget_password_flag",1);
                LaunchActivity.this.startActivity(intent);
                LaunchActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGHT);
    }

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
