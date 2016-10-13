package com.softstao.softstaolibrary.library.widget.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softstao.softstaolibrary.R;
import com.softstao.softstaolibrary.library.model.ShareContent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * Created by xuhon on 2016/9/6.
 */
public class ShareDialog extends Dialog {
    private Context mContext;
    private ShareContent shareContent;
    public ShareDialog(Context context) {
        super(context);
        initView(context);
    }

    public ShareDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected ShareDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        setContentView(R.layout.share_layout);
        setCanceledOnTouchOutside(true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        findViewById(R.id.other_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOtherClick();
            }
        });
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        findViewById(R.id.wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinShare();
            }
        });
        findViewById(R.id.circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleShare();
            }
        });
    }

    public void onOtherClick()
    {
        dismiss();
    }

    public void onCancel()
    {
        dismiss();
    }

    public void weixinShare()
    {
        openShare(SHARE_MEDIA.WEIXIN);
    }

    public void circleShare()
    {
        openShare(SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    private void openShare(SHARE_MEDIA share_media)
    {
        if(shareContent == null)
        {
            Toast.makeText(getContext(), "请设置分享的内容", Toast.LENGTH_SHORT).show();
            return;
        }
        new ShareAction((Activity)mContext)
                .setPlatform(share_media)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Toast.makeText(getContext(), " 分享成功啦", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Toast.makeText(getContext(), "分享错误", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Toast.makeText(getContext(), "分享取消", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                })
                .withText(shareContent.content.equals("")?shareContent.title:shareContent.content)
                .withTitle(shareContent.title)
                .withTargetUrl(shareContent.reseller!=null
                        ?shareContent.url+"&reseller="+ shareContent.reseller:shareContent.url)
                .withMedia(new UMImage(getContext(),shareContent.imageUrl))
                .share();
    }

    public ShareContent getShareContent() {
        return shareContent;
    }

    public void setShareContent(ShareContent shareContent) {
        this.shareContent = shareContent;
    }

}
