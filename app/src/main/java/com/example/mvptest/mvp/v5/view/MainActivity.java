package com.example.mvptest.mvp.v5.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mvptest.R;
import com.example.mvptest.mvp.v5.InjectPresenter;
import com.example.mvptest.mvp.v5.MainContract;
import com.example.mvptest.mvp.v5.basemvp.BaseActivity;
import com.example.mvptest.mvp.v5.presenter.MainPresenter;

/**
 * MVP 的写法，Version 5: 依赖注入，解决多个 Presenter 的问题
 */
public class MainActivity extends BaseActivity<MainContract.IMainPresenter> implements MainContract.IMainView{

    private TextView tv;

    @InjectPresenter
    private MainPresenter mPresenter;

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        tv = $(R.id.tv);
    }

    @Override
    protected void initData() {
        //getPresenter().handlerData();
        mPresenter.handlerData();
    }

    @Override
    protected MainContract.IMainPresenter setPresenter() {
        return null;
    }

    @Override
    public void showDialog() {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }

    @Override
    public void success(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "" + content, Toast.LENGTH_SHORT).show();
                tv.setText(content);
            }
        });
    }
}