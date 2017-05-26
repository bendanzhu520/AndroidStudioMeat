package com.winorout.meat.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.winorout.meat.R;
import android.widget.LinearLayout;

import com.winorout.meat.signin.chosecountry.ChoseCountry;
import com.winorout.meat.signin.register.Register;
import com.winorout.meat.signin.tools.FastBlur;
import android.widget.EditText;

/**
 * Created by Mr-x on 2017/03/23.
 */

public class Sign_in extends Activity implements View.OnClickListener{

    private TextView backtohomepage;
    private TextView sign_in;
    private TextView forget_password;
    private TextView chose_country_btn;

    private EditText phone_number;
    private EditText password;

    private Button register_btn;

    private SharedPreferences sharepreferences;
    private SharedPreferences.Editor editor;
    private LinearLayout register_pic;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        backtohomepage = (TextView)findViewById(R.id.backtohomepage);
        sign_in = (TextView)findViewById(R.id.sign_in);
        forget_password = (TextView)findViewById(R.id.forget_password);
        register_btn = (Button)findViewById(R.id.register_btn);
        chose_country_btn = (TextView)findViewById(R.id.chose_country_btn);

        phone_number = (EditText)findViewById(R.id.phone_number);
        password = (EditText)findViewById(R.id.password);
        register_pic = (LinearLayout)findViewById(R.id.register_pic);
    }

    private void initData() {

        sharepreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharepreferences.edit();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 128:
                        register_pic.setBackground((Drawable) msg.obj);
                }
            }
        };
        //下面这一步给背景添加了一个模糊图片
        new Thread(){
            @Override
            public void run() {
                super.run();
                //这里这些操作不能少，具体为什么不知道，但是少了就会报错
                Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cheer);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(mBitmap,
                        mBitmap.getWidth() / 5,
                        mBitmap.getHeight() / 5,
                        false);
                Bitmap blurBitmap = FastBlur.doBlur(scaledBitmap, 6, true);
                Drawable drawable = new BitmapDrawable(blurBitmap);
                Message message = new Message();
                message.what = 128;
                message.obj = drawable;
                handler.sendMessage(message);
            }
        }.start();

    }

    private void initEvent() {

        backtohomepage.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        forget_password.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        chose_country_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.backtohomepage:
                finish();
                break;
            case R.id.sign_in:
                if (phone_number.getText().toString().length() == 11){
                    if (password.getText().toString().length() > 5 && password.getText().toString().length() < 16){
                        Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
                        editor.putString("SignState", "Sign");
                        editor.commit();
                        finish();
                    }else {
                        Toast.makeText(this, "请输入正确的密码!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "请输入正确的号码!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_password:
                startActivity(new Intent(Sign_in.this, Register.class));
                break;
            case R.id.register_btn:
                startActivity(new Intent(Sign_in.this, Register.class));
                break;
            case R.id.chose_country_btn:
                startActivity(new Intent(Sign_in.this, ChoseCountry.class));
                break;
        }

    }
}
