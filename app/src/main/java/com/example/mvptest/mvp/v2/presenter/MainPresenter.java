package com.example.mvptest.mvp.v2.presenter;

import android.util.Log;

import com.example.mvptest.mvp.v2.MainContract;
import com.example.mvptest.mvp.v2.basemvp.BasePresenter;
import com.example.mvptest.mvp.v2.basemvp.IBaseView;
import com.example.mvptest.mvp.v2.model.DataModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * presenter 层，承担业务逻辑处理，数据源处理等
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter{

    private MainContract.IMainModel mModel;

    @Override
    public void attach(IBaseView view) {
        super.attach(view);
        mModel = new DataModel();
    }

    @Override
    public void handlerData() {
        if (mView != null) {
            mView.showDialog();
        }
        /**
         * 发起请求，获得回调数据
         */
        mModel.requestBaidu(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                if (mView != null) {
                    mView.success(content);
                }
            }
        });
    }

    @Override
    public void detach() {
        super.detach();
        /**
         * 释放内存、关闭网络请求、关闭线程等操作
         */
        Log.d("==========", "detach: 解除绑定，释放内存");
    }
}
