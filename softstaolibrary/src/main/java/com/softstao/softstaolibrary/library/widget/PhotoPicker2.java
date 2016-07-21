package com.softstao.softstaolibrary.library.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.softstao.softstaolibrary.R;
/**
 * Created by jacob on 15/6/16.
 */
public class PhotoPicker2 extends Dialog {

    public static final int IMAGE_CHOOSE_CODE = 10;
    public static final int CAMERA_REQUEST_CODE = 11;
    public static final int RESULT_REQUEST_CODE = 12;
    public static final String IMAGE_FILE_NAME = "pic.jpg"; // 拍照默认图片名称

    private LinearLayout ll_popup;
    private View parentView;
    private Button takePictureBtn;
    private Button choosePicBtn;
    private Button cancelBtn;

    private Context mContext;
    private Fragment fragment;

    public static String mCurrentPhotoPath;
    public static Uri mCurrentPhotoUri;


    public PhotoPicker2(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public PhotoPicker2(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init();
    }

    public PhotoPicker2(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        init();
    }

    private void init()
    {
        setContentView(R.layout.photo_popupwindow);
        setCanceledOnTouchOutside(true);
//        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.photo_popupwindow, null);
//        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
////                ll_popup.clearAnimation();
//
//            }
//        });

        takePictureBtn = (Button)findViewById(R.id.choose_by_camera);
        takePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        choosePicBtn = (Button)findViewById(R.id.choose_by_local);
        choosePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFromAlbum();
            }
        });

        cancelBtn = (Button)findViewById(R.id.dialog_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void takePicture() {
        Intent intentFromCapture = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            // Error occurred while creating the File
        }
        mCurrentPhotoUri = Uri.fromFile(photoFile);
        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                mCurrentPhotoUri);
        if (fragment != null) {
            fragment.startActivityForResult(intentFromCapture,
                    CAMERA_REQUEST_CODE);
        } else {
            ((Activity) mContext).startActivityForResult(intentFromCapture,
                    CAMERA_REQUEST_CODE);
        }
        dismiss();
    }

    private void loadFromAlbum() {
        if (Environment.getExternalStorageState()
                .equals("mounted")) {
            Intent intentFromGallery = new Intent();
            intentFromGallery.setType("image/*");
            intentFromGallery
                    .setAction(Intent.ACTION_GET_CONTENT);

            if (fragment != null) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                fragment.startActivityForResult(i, IMAGE_CHOOSE_CODE);
            } else {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ((Activity) mContext).startActivityForResult(i, IMAGE_CHOOSE_CODE);
            }
            dismiss();
        }
    }

    public File getImage(Intent data) {
        File tempFile = null;
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            tempFile = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            + "/" + System.currentTimeMillis() + ".jpg");

            BufferedOutputStream bos;
            try {
                tempFile.createNewFile();
                bos = new BufferedOutputStream(new FileOutputStream(tempFile));
                photo.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
        {
            String filePath = null;
            Uri _uri = data.getData();
            Log.d("", "URI = " + _uri);
            if (_uri != null && "content".equals(_uri.getScheme())) {
                Cursor cursor = mContext.getContentResolver().query(_uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
                cursor.moveToFirst();
                filePath = cursor.getString(0);
                cursor.close();
            }
            else {
                filePath = _uri.getPath();
            }

            tempFile = new File(filePath);
        }

        dismiss();

        return tempFile;

    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        if(fragment != null)
        {
            fragment.startActivityForResult(intent, RESULT_REQUEST_CODE);
        }
        else
        {
            ((Activity) mContext).startActivityForResult(intent, RESULT_REQUEST_CODE);
        }
    }

    public void startPhotoZoom2(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        if(fragment != null)
        {
            fragment.startActivityForResult(intent, RESULT_REQUEST_CODE);
        }
        else
        {
            ((Activity) mContext).startActivityForResult(intent, RESULT_REQUEST_CODE);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }



}
