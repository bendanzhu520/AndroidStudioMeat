package com.winorout.meat.privateletter.share_wish;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import com.winorout.meat.R;
import android.widget.TextView;

/**
 * Created by Mr-x on 2017/04/12.
 */
/*
* 私信点击进入的详情界面，私信的四个按钮共享这一个类和界面
* */

public class Share_wish extends Activity implements View.OnClickListener{

    private LinearLayout backto_privateletter;
    private TextView share_wish_title;
    private TextView privatedetail_share_btn;
    private TextView privatedetail_wish_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_wish);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        backto_privateletter = (LinearLayout)findViewById(R.id.backto_privateletter);
        share_wish_title = (TextView)findViewById(R.id.share_wish_title);
        privatedetail_share_btn = (TextView)findViewById(R.id.privatedetail_share_btn);
        privatedetail_wish_btn = (TextView)findViewById(R.id.privatedetail_wish_btn);
        privatedetail_share_btn.setBackgroundResource(R.drawable.private_share_wish_btn);
        privatedetail_wish_btn.setBackground(null);
        share_wish_title.setText(getIntent().getExtras().get("title").toString());

    }

    private void initData() {

    }

    private void initEvent() {

        backto_privateletter.setOnClickListener(this);
        privatedetail_share_btn.setOnClickListener(this);
        privatedetail_wish_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.backto_privateletter:
                finish();
                break;
            case R.id.privatedetail_share_btn:
                privatedetail_share_btn.setBackgroundResource(R.drawable.private_share_wish_btn);
                privatedetail_wish_btn.setBackground(null);
                break;
            case R.id.privatedetail_wish_btn:
                privatedetail_share_btn.setBackground(null);
                privatedetail_wish_btn.setBackgroundResource(R.drawable.private_share_wish_btn);
                break;
        }

    }
}
