package com.winorout.meat.signin.register;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.winorout.meat.R;

/**
 * Created by Mr-x on 2017/04/13.
 */

public class Register extends Activity implements View.OnClickListener{

    private TextView log_on;
    private TextView backto_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        log_on = (TextView)findViewById(R.id.log_on);
        backto_signin = (TextView)findViewById(R.id.backto_signin);

    }

    private void initData() {

    }

    private void initEvent() {

        log_on.setOnClickListener(this);
        backto_signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.log_on:
                finish();break;
            case R.id.backto_signin:
                finish();break;
        }

    }
}
