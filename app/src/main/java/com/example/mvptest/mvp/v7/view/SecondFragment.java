package com.example.mvptest.mvp.v7.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mvptest.R;
import com.example.mvptest.mvp.v7.InjectPresenter;
import com.example.mvptest.mvp.v7.SecondContract;
import com.example.mvptest.mvp.v7.basemvp.BaseFragment;
import com.example.mvptest.mvp.v7.presenter.SecondPresenter;

/**
 * @author pk
 * @description
 * @date 7/18/22 3:34 PM
 */
public class SecondFragment extends BaseFragment implements SecondContract.ISecondView {
    private TextView tvFragment;

    @InjectPresenter
    private SecondPresenter mPresenter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        tvFragment = $(R.id.tv_fragment);
    }


    @Override
    protected void initData() {
        mPresenter.handlerData();
    }

    @Override
    public void showDialog() {
//        Toast.makeText(getContext(), "this is Fragment", Toast.LENGTH_SHORT).show();
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }


    @SuppressWarnings("ConstantConditions")
    @Override
    public void success(final String content) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "" + content, Toast.LENGTH_SHORT).show();
                tvFragment.setText(content);
            }
        });
    }

}
