package com.winorout.meat.privateletter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winorout.meat.R;
import com.winorout.meat.privateletter.share_wish.Share_wish;

import android.widget.LinearLayout;

/**
 * Created by Mr-x on 2017/03/22.
 */
/*
* 四大Fragment之一的私信，包含四个主要按钮功能和聊天记录
* */

public class Privateletter extends Fragment implements View.OnClickListener{

    private View view;
    private LinearLayout registration_management;
    private LinearLayout comments_about_me;
    private LinearLayout thumbsup_about_me;
    private LinearLayout invitation_about_me;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.privateletter, null);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initView() {

        registration_management = (LinearLayout)view.findViewById(R.id.registration_management);
        comments_about_me = (LinearLayout)view.findViewById(R.id.comments_about_me);
        thumbsup_about_me = (LinearLayout)view.findViewById(R.id.thumbsup_about_me);
        invitation_about_me = (LinearLayout)view.findViewById(R.id.invitation_about_me);

    }

    private void initData() {

    }

    private void initEvent() {

        registration_management.setOnClickListener(this);
        comments_about_me.setOnClickListener(this);
        thumbsup_about_me.setOnClickListener(this);
        invitation_about_me.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), Share_wish.class);
        Bundle bundle = new Bundle();
        String title = null;
        int i = 0;
        switch (view.getId())
        {
            case R.id.registration_management:
                title = "报名管理";
                i = 1;
                break;
            case R.id.comments_about_me:
                title = "评论我的";
                i = 1;
                break;
            case R.id.thumbsup_about_me:
                title = "点赞我的";
                i = 1;
                break;
            case R.id.invitation_about_me:
                title = "邀请我的";
                i = 1;
                break;
        }
        //这里是为了判断是否为跳转到页面的那四个按钮之一
        if (i == 1){
            bundle.putString("title", title);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
