package com.mouqukeji.zhailuserver.model.activity;


import com.mouqukeji.zhailuserver.bean.TokenBean;
import com.mouqukeji.zhailuserver.contract.activity.SplashContract;
import com.mouqukeji.zhailuserver.net.BaseHttpResponse;
import com.mouqukeji.zhailuserver.net.RetrofitManager;

import io.reactivex.Observable;

public class SplashModel implements SplashContract.Model {


 @Override
 public Observable<BaseHttpResponse<TokenBean>> getToken(String token) {
  return RetrofitManager.getInstance().getRequestService().getToken(token);
 }
}
