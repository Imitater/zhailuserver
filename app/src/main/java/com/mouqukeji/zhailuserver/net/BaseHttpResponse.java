package com.mouqukeji.zhailuserver.net;


public class BaseHttpResponse<T> {
    public int code;
    public String message;
    public T data;
}
