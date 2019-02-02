package com.mouqukeji.zhailuserver.base;


import com.mouqukeji.zhailuserver.bean.ErrorBean;
import com.mouqukeji.zhailuserver.ui.widget.MultipleStatusView;

public interface IBaseView {
    /**
     * show loading message
     *
     * @param msg
     */
    void showLoading(MultipleStatusView multipleStatusView, String msg);

    /**
     * hide loading
     */
    void hideLoading();

    /**
     * show business error :网络异常及数据错误等异常情况
     */
    void showBusinessError(ErrorBean error);

    void showException(ErrorBean error);
}
