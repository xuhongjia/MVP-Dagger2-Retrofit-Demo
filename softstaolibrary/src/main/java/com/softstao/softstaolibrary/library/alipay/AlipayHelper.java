package com.softstao.softstaolibrary.library.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.softstao.softstaolibrary.library.utils.LZUtils;
import com.softstao.softstaolibrary.library.widget.LZToast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jacob on 15/5/8.
 */
public class AlipayHelper {

    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_CHECK_FLAG = 2;
    public final static String ALIPAY = "com.eg.android.AlipayGphone";
    public enum PAY_STATUS
    {
        SUCCESS,FAIL,CONFIRMING
    }

    private String partnerId;
    private String seller;
    private String tradeNo;
    private String orderTitle;
    private String price;
    private String discription;
    private String notifyURL;
    private String privateKey;
    private PayCallBack payCallBack;

    public Context mContext;
    public static AlipayHelper alipayUtils;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {

                        if(payCallBack != null)
                        {
                            payCallBack.onPayBack(price, PAY_STATUS.SUCCESS);
                        }
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            if(payCallBack != null)
                            {
                                payCallBack.onPayBack(price, PAY_STATUS.CONFIRMING);
                            }

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            if(payCallBack != null)
                            {
                                payCallBack.onPayBack(price, PAY_STATUS.FAIL);
                            }

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    public AlipayHelper(Context context) {
        mContext = context;
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
    public void pay() {

        if(!check()) return;
        // 订单
        String orderInfo = getOrderInfo();

        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity)mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo,true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public boolean check()
    {
        if(!LZUtils.checkApkExist(mContext, ALIPAY))
        {
            if(payCallBack != null)
            {
                payCallBack.alipayNotInsall();
            }
            LZToast.getInstance(mContext).showToast("您还没有安装支付宝");

            return false;
        }

        return true;
    }


    /**
     * create the order info. 创建订单信息
     *
     */
    public String getOrderInfo() {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + partnerId + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + seller + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + tradeNo + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + orderTitle + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + discription + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
//        orderInfo += "&total_fee=" + "\"" + 0.01 + "\"";


        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + notifyURL
                + "\"";


        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, privateKey);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }


    public static void setAlipayUtils(AlipayHelper alipayUtils) {
        AlipayHelper.alipayUtils = alipayUtils;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        mContext = mContext;
    }

    public String getNotifyURL() {
        return notifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String sellerId) {
        this.seller = sellerId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public PayCallBack getPayCallBack() {
        return payCallBack;
    }

    public void setPayCallBack(PayCallBack payCallBack) {
        this.payCallBack = payCallBack;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public interface PayCallBack
    {
        void onPayBack(String price, PAY_STATUS status);
        void alipayNotInsall();
    }
}
