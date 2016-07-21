package com.softstao.softstaolibrary.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.softstao.softstaolibrary.R;
/**
 * Created by chaochen on 14-9-22.
 */
public class LZImageLoadTool {

    public static LZImageLoadTool imageLoadTool;
    public static ImageLoader imageLoader;

    public static LZImageLoadTool getInstance(Context ctx)
    {
        if(imageLoadTool == null)
        {
            ImageLoader.getInstance().init(getConfig(ctx));
            imageLoader = ImageLoader.getInstance();
            imageLoadTool = new LZImageLoadTool();
        }
        return  imageLoadTool;
    }

    private static ImageLoaderConfiguration getConfig(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default.denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024))
                .memoryCacheSize(50 * 1024 * 1024)
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .defaultDisplayImageOptions(options) // default
                .build();

        return config;
    }

    public static DisplayImageOptions options = new DisplayImageOptions
            .Builder()
            .showImageOnLoading(R.drawable.default_img)
            .showImageForEmptyUri(R.drawable.default_img)
            .showImageOnFail(R.drawable.default_img)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .considerExifParams(true)
            .build();

    public LZImageLoadTool() {
    }

    public void loadImage(ImageView imageView, String url) {
        imageLoader.displayImage(url, imageView, options);
    }

    public void loadImage(ImageView imageView, String url, DisplayImageOptions imageOptions) {
        imageLoader.displayImage(url, imageView, imageOptions);
    }

    public void loadImageFromUrl(ImageView imageView, String url) {
        imageLoader.displayImage(url, imageView, options);
    }

    public void loadImageFromUrl(ImageView imageView, String url, DisplayImageOptions displayImageOptions) {
        imageLoader.displayImage(url, imageView, displayImageOptions);
    }


    public void loadImageFromUrl(ImageView imageView, String url, DisplayImageOptions displayImageOptions,ImageLoadingListener listener) {
        imageLoader.displayImage(url, imageView, displayImageOptions,listener);
    }
}

