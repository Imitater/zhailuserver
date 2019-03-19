package com.mouqukeji.zhailuserver.bean;

public class DeleteBean {

    /**
     * errcode : 10000
     * errdetail : null
     * errmsg : OK
     */

    private int errcode;
    private Object errdetail;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public Object getErrdetail() {
        return errdetail;
    }

    public void setErrdetail(Object errdetail) {
        this.errdetail = errdetail;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
