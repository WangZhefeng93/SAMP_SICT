package com.ff161224.cc_commander.shareplatform.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by xiaobaixiaoda on 2019/3/3.
 */

public class ChoosePhotoFromCamera {
    public static Uri jump2Camera(Activity activity,int CUT_PICTURE){
        //创建File对象，用于存储拍照后的图片
        //将此图片存储于SD卡的根目录下
        File outputImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/output_image.jpg");
        outputImage.getParentFile().mkdirs();
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Uri imageUri = FileUtils.getUriForFile(activity,outputImage);
        Uri imageUri = FileProvider.getUriForFile(activity, "com.ff161224.cc_commander.shareplatform.fileprovider", outputImage);
        //隐式调用照相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //拍下的照片会被输出到output_image.jpg中去
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, CUT_PICTURE);
        return imageUri;
    }
}
