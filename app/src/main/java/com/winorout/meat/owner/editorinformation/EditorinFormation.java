package com.winorout.meat.owner.editorinformation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.winorout.meat.R;

/**
 * Created by Mr-x on 2017/03/30.
 */

/*
* 用来编辑用户的一些详细资料
* */

public class EditorinFormation extends Activity implements View.OnClickListener{

    private TextView backto_owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editorinformation);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        backto_owner = (TextView)findViewById(R.id.backto_owner);
    }

    private void initData() {

    }

    private void initEvent() {

        backto_owner.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.backto_owner:
                finish();
                break;
        }

    }
}
