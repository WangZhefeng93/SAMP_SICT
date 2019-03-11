package com.ff161224.cc_commander.shareplatform.utils.album_camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

/**
 * Created by xiaobaixiaoda on 2019/3/3.
 * 该工具类的作用是对调用安卓系统相册，选择照片，进行裁剪得到Bitmap的系列操作进行了封装
 */

public class ChoosePhotoFromAlbum {
    public static Uri chooseFromAlbum(Activity activity,int CUT_PICTURE){
        File outputImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        //此处调用了图片选择器
        //如果直接写intent.setDataAndType("image/*");
        //调用的是系统图库
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, CUT_PICTURE);
        return imageUri;
    }
}
