package com.softstao.softstaolibrary.library.utils;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import com.softstao.softstaolibrary.library.widget.LZToast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/27.
 */
public class SaveBitmapFile {
    private final static String ALBUM_PATH
            = Environment.getExternalStorageDirectory() + "/DCIM/";
    private String mSaveMessage;
    private String mFileName;
    private Application application;
//    private Context mContext;
    private Bitmap bitmap;
    private static SaveBitmapFile _SaveBitmapFile=null;
    public static SaveBitmapFile getInstence(Bitmap bitmap, Application application){
        if(_SaveBitmapFile==null){
            _SaveBitmapFile=new SaveBitmapFile();
        }
        _SaveBitmapFile.bitmap=bitmap;
        _SaveBitmapFile.application = application;
        return _SaveBitmapFile;
    }
    private Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==-1)
            {
                LZToast.getInstance(application).showToast(mSaveMessage);
            }
        }
    };

    public void saveFile(Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(ALBUM_PATH);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File(ALBUM_PATH + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(application.getApplicationContext().getContentResolver(),
                    myCaptureFile.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        application.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(ALBUM_PATH)));
        bos.flush();
        bos.close();
    }
    private Runnable saveFileRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                mFileName =new Date().getTime()+ ".jpg";
                saveFile(bitmap, mFileName);
                mSaveMessage = "图片保存成功";
            } catch (IOException e) {
                mSaveMessage = "图片保存失败";
                e.printStackTrace();
            }
            messageHandler.sendEmptyMessage(-1);
        }
    };

    public Runnable getSaveFileRunnable() {
        return saveFileRunnable;
    }

    public void setSaveFileRunnable(Runnable saveFileRunnable) {
        this.saveFileRunnable = saveFileRunnable;
    }

}
