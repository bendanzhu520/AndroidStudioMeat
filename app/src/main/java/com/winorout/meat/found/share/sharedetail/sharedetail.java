package com.winorout.meat.found.share.sharedetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.winorout.meat.R;
import com.winorout.meat.found.share.sharedetail.tools.ShareUtil;
import com.winorout.meat.signin.Sign_in;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mr-x on 2017/04/11.
 */
/*
* 该类用来展示分享页面的详细情况，包含用户和展示品的详情和自己的支持和关注情况
* */
public class sharedetail extends Activity implements View.OnClickListener {

    private TextView backtoshare;  //返回上一页面
    private TextView follow; //关注按钮
    private TextView add_drumstick;//点击增加鸡腿
    private TextView drumsticks; //鸡腿总量
    private TextView reward_btn;  //赏按钮
    private TextView popcancel;  //取消支付按钮
    private TextView makesure_reward;//确认支付按钮
    private TextView rewardstxt;
    private TextView application_registration_btn; //申请报名按钮
    private TextView application_txt;
    private TextView sharedetail_btn;  //软件信息分享按钮

    private Button application_cacel_btn;
    private Button application_sure_btn;

    //用来显示倒数计时的时间
    private TextView hour1;
    private TextView hour2;
    private TextView minute1;
    private TextView minute2;
    private TextView second1;
    private TextView second2;

    private EditText sendcomment;  //添加评论

    private LinearLayout sharedetail;
    private LinearLayout sharedetailbottom;
    private LinearLayout send_message;

    private SharedPreferences followsharepreference;
    private SharedPreferences.Editor followeditor;
    private SharedPreferences drumstickpreference;
    private SharedPreferences.Editor drumstickditor;
    private SharedPreferences rewardsshareprefence;
    private SharedPreferences.Editor rewardseditor;

    private View rewardpopview;
    private PopupWindow popupWindow;
    private View registrationpopview;
    protected PopupWindow application_registration_popwindow;

    private SharedPreferences sharepreferences;
    private SharedPreferences.Editor editor;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedetail);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        rewardpopview = LayoutInflater.from(sharedetail.this).inflate(R.layout.reward_popwindow, null);
        popcancel = (TextView)rewardpopview.findViewById(R.id.popcancel);
        makesure_reward = (TextView)rewardpopview.findViewById(R.id.makesure_reward);
        registrationpopview = LayoutInflater.from(sharedetail.this).inflate(R.layout.application_registration_popwindow, null);
        backtoshare = (TextView) findViewById(R.id.backtoshare);
        add_drumstick = (TextView) findViewById(R.id.add_drumstick);
        drumsticks = (TextView) findViewById(R.id.drumsticks);
        follow = (TextView) findViewById(R.id.follow);
        reward_btn = (TextView)findViewById(R.id.reward_btn);
        rewardstxt = (TextView)findViewById(R.id.rewardstxt);
        sharedetail_btn = (TextView)findViewById(R.id.sharedetail_btn);
        application_registration_btn = (TextView)findViewById(R.id.application_registration_btn);
        application_txt = (TextView)registrationpopview.findViewById(R.id.application_txt);

        hour1 = (TextView)findViewById(R.id.hour1);
        hour2 = (TextView)findViewById(R.id.hour2);
        minute1 = (TextView)findViewById(R.id.minute1);
        minute2 = (TextView)findViewById(R.id.minute2);
        second1 = (TextView)findViewById(R.id.second1);
        second2 = (TextView)findViewById(R.id.second2);

        application_cacel_btn = (Button)registrationpopview.findViewById(R.id.application_cacel_btn);
        application_sure_btn = (Button)registrationpopview.findViewById(R.id.application_sure_btn);

        sendcomment = (EditText)findViewById(R.id.sendcomment);

        sharedetail = (LinearLayout) findViewById(R.id.sharedetail);
        sharedetailbottom = (LinearLayout) findViewById(R.id.sharedetailbottom);
        send_message = (LinearLayout) findViewById(R.id.send_message);

    }

    private void initData() {

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case 126:
                        //实时更新当前时间
                        Calendar c = Calendar.getInstance();
                        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
                        String minute = String.valueOf(c.get(Calendar.MINUTE));
                        String second = String.valueOf(c.get(Calendar.SECOND));
                        if (hour.length() == 1) {
                            hour1.setText("0");
                            hour2.setText(hour);
                        }else {
                            hour1.setText("" + hour.substring(0,1));
                            hour2.setText("" + hour.substring(1,2));
                        }
                        if (minute.length() == 1) {
                            minute1.setText("0");
                            minute2.setText(minute);
                        }else {
                            minute1.setText("" + minute.substring(0,1));
                            minute2.setText("" + minute.substring(1,2));
                        }
                        if (second.length() == 1) {
                            second1.setText("0");
                            second2.setText(second);
                        }else {
                            second1.setText("" + second.substring(0,1));
                            second2.setText("" + second.substring(1,2));
                        }
                        break;
                }
            }
        };
        sharepreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharepreferences.edit();
        followsharepreference = getSharedPreferences("follow", Context.MODE_PRIVATE);
        followeditor = followsharepreference.edit();
        drumstickpreference = getSharedPreferences("drumstick", Context.MODE_PRIVATE);
        drumstickditor = drumstickpreference.edit();
        rewardsshareprefence = getSharedPreferences("rewards", Context.MODE_PRIVATE);
        rewardseditor = rewardsshareprefence.edit();
        follow.setText(followsharepreference.getString("follow_state", "关注"));
        drumsticks.setText(drumstickpreference.getString("drumstick_state", "0"));
        rewardstxt.setText(rewardsshareprefence.getString("rewards_state", "0"));

        updatetime();//动态更新倒计时时间

    }

    private void initEvent() {

        backtoshare.setOnClickListener(this);
        follow.setOnClickListener(this);
        add_drumstick.setOnClickListener(this);
        sendcomment.setOnClickListener(this);
        reward_btn.setOnClickListener(this);
        popcancel.setOnClickListener(this);
        makesure_reward.setOnClickListener(this);
        application_registration_btn.setOnClickListener(this);
        application_cacel_btn.setOnClickListener(this);
        application_sure_btn.setOnClickListener(this);
        sharedetail_btn.setOnClickListener(this);
        //用来监听用户软键盘有没有出现，出现则隐藏底栏，没出现则显示
        sharedetail.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //比较Activity根布局与当前布局的大小
                int heightDiff = sharedetail.getRootView().getHeight() - sharedetail.getHeight();
                //其实这个heightDiff换成dp更靠谱一些
                if (heightDiff > 100) {
                    //大小超过100时，一般为显示虚拟键盘事件
                    sharedetailbottom.setVisibility(View.GONE);
                    send_message.setVisibility(View.VISIBLE);
                } else {
                    //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
                    sharedetailbottom.setVisibility(View.VISIBLE);
                    send_message.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.backtoshare:
                finish();
                break;
            case R.id.follow:
                if (followsharepreference.getString("follow_state", "关注").equals("关注")) {
                    follow.setText("取消关注");
                } else {
                    follow.setText("关注");
                }
                followeditor.putString("follow_state", follow.getText().toString());
                followeditor.commit();
                break;
            case R.id.add_drumstick:
                int number = Integer.parseInt(drumstickpreference.getString("drumstick_state", "0"));
                drumstickditor.putString("drumstick_state", "" + (number + 1));
                drumstickditor.commit();
                drumsticks.setText("" + number);
                break;
            case R.id.sendcomment:
                break;
            case R.id.reward_btn:
                if (sharepreferences.getString("SignState", "NoSign").equals("Sign")) {
                    popupWindow = new PopupWindow(rewardpopview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                    popupWindow.setBackgroundDrawable(new ColorDrawable());
                    popupWindow.showAtLocation(rewardpopview, Gravity.BOTTOM | Gravity.START, sharedetail.this.getResources().getDisplayMetrics().heightPixels - 50, sharedetail.this.getResources().getDisplayMetrics().widthPixels);
                }else {
                    startActivity(new Intent(sharedetail.this, Sign_in.class));
                }break;
            case R.id.popcancel:
                popupWindow.dismiss();
                break;
            case R.id.makesure_reward:
                Toast.makeText(this, "您已成功转账至18326177...", Toast.LENGTH_SHORT).show();
                int rewards = Integer.parseInt(rewardsshareprefence.getString("rewards_state", "0"));
                rewardseditor.putString("rewards_state", rewards + 1 + "");
                rewardseditor.commit();
                rewardstxt.setText("" + (rewards + 1));
                break;
            case R.id.application_registration_btn:
                if (sharepreferences.getString("SignState", "Nosign").equals("Sign")){
                    if (application_registration_btn.getText().toString().equals("申请报名")){
                        application_registration_popwindow = new PopupWindow(registrationpopview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                        application_registration_popwindow.setBackgroundDrawable(new ColorDrawable());
                        application_registration_popwindow.showAtLocation(registrationpopview, Gravity.BOTTOM | Gravity.START, 260, 100);
                        application_txt.setText("申请该分享需要消耗1积分，您确定要申请吗？");
                        application_sure_btn.setText("确定");
                    }else {
                        application_registration_popwindow = new PopupWindow(registrationpopview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                        application_registration_popwindow.setBackgroundDrawable(new ColorDrawable());
                        application_registration_popwindow.showAtLocation(registrationpopview, Gravity.BOTTOM | Gravity.START, 260, 100);
                        application_txt.setText("是否确认放弃?");
                        application_sure_btn.setText("确认放弃");
                    }
                }else {
                    startActivity(new Intent(sharedetail.this, Sign_in.class));
                }
                break;
            case R.id.application_cacel_btn:
                application_registration_popwindow.dismiss();
                break;
            case R.id.application_sure_btn:
                if (application_sure_btn.getText().toString().equals("确定")) {
                    application_txt.setText("报名成功，快私信Ta申请理由吧!");
                    application_sure_btn.setText("给Ta私信");
                    application_registration_btn.setText("取消报名");
                }else if(application_sure_btn.getText().toString().equals("给Ta私信")){
                    Toast.makeText(this, "私信功能暂未开启", Toast.LENGTH_SHORT).show();
                    application_registration_popwindow.dismiss();
                }else {
                    application_registration_popwindow.dismiss();
                    application_registration_btn.setText("申请报名");
                }
                break;
            case R.id.sharedetail_btn:
                ShareUtil shareUtil = new ShareUtil(sharedetail.this);
                shareUtil.shareText(null, null, "-来自有肉的分享-", "分享标题", "分享主题");
                break;

        }
    }

    private void updatetime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 126;
                handler.sendMessage(msg);
            }
        }, 500, 1000);

    }

}
