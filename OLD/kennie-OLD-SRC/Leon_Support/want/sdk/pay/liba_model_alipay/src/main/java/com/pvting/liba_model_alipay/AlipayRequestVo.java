package com.pvting.liba_model_alipay;

/**
 * <b>Project:</b> alipay_demo<br>
 * <b>Create Date:</b> 2016/3/23<br>
 * <b>Author:</b> Peiweiwei<br>
 * <b>Description:</b> <br>
 */
public class AlipayRequestVo {
    //订单简述
    public String subject;
    //订单详细
    public String body;
    //订单价格
    public String price;
    //订单号
    public String tradeNo;
    //通知地址
    public String notifyUrl;
    //回调
    public CallBack callBack;

    public AlipayRequestVo(String subject, String body, String price, String tradeNo,String notifyUrl,CallBack callBack) {
        this.subject = subject;
        this.body = body;
        this.price = price;
        this.tradeNo = tradeNo;
        this.notifyUrl = notifyUrl;
        this.callBack = callBack;
    }
}
