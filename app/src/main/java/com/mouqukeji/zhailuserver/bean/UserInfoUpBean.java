package com.mouqukeji.zhailuserver.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoUpBean {

    @SerializedName("return")
    private List<?> returnX;

    public List<?> getReturnX() {
        return returnX;
    }

    public void setReturnX(List<?> returnX) {
        this.returnX = returnX;
    }
}
