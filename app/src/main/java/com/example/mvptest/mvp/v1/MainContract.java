package com.example.mvptest.mvp.v1;

import okhttp3.Callback;

/**
 * 契约接口，可以很直观的看到 M、V、P 层接口中提供的方法
 */
public interface MainContract {
    interface IMainModel {
        void requestBaidu(Callback callback);
    }

    interface IMainView {
        void showDialog();

        void success(String content);
    }

    interface IMainPresenter {
        void handlerData();
    }
}