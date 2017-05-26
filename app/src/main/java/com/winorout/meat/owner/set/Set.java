package com.winorout.meat.owner.set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.winorout.meat.R;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mr-x on 2017/03/30.
 */

/*
* 用户信息设置界面
* */

public class Set extends Activity implements View.OnClickListener{

    private TextView backto_owner;
    private TextView quitsign;

    private SharedPreferences sharepreference;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        backto_owner = (TextView)findViewById(R.id.backto_owner);
        quitsign = (TextView)findViewById(R.id.quitsign);

    }

    private void initData() {

        sharepreference = getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharepreference.edit();

    }

    private void initEvent() {

        backto_owner.setOnClickListener(this);
        quitsign.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.backto_owner:
                finish();
                break;
            case R.id.quitsign:
                editor.putString("SignState", "NoSign");
                editor.commit();
                finish();
                break;

        }

    }
}
