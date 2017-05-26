package com.winorout.meat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

import com.winorout.meat.contacts.Contacts;
import com.winorout.meat.found.Found;
import com.winorout.meat.found.share.Share;
import com.winorout.meat.myview.RedRect;
import com.winorout.meat.myview.mydrawerlayout;
import com.winorout.meat.owner.Owner;
import com.winorout.meat.privateletter.Privateletter;
import com.winorout.meat.signin.Sign_in;
import android.widget.ArrayAdapter;

import android.support.v4.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

/*
* 软件首页，包含五个底部按钮，和四个Fragment
* 利用了recyclerview，依赖库：    compile 'com.android.support:recyclerview-v7:25.1.1'
* */

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private HomePager viewPager;
    private Found found;
    private Privateletter privateletter;
    private Contacts contacts;
    private Owner owner;
    private FragmentAdapter fragmentaddapter;
    private List<Fragment> list;

    private LinearLayout first;
    private LinearLayout second;
    private LinearLayout third;
    private LinearLayout four;
    private LinearLayout homepage_head;

    private TextView findpic;
    private TextView privateletterpic;
    private TextView contactspic;
    private TextView ownerpic;
    private TextView findtxt;
    private TextView privatelettertxt;
    private TextView contactstxt;
    private TextView ownertxt;
    private TextView screen;
    private TextView close_menu;

    private RedRect registerpage;
    private long clickback = 0;

    private mydrawerlayout main_drawerLayout;

    private ComponentName mDefault;
    private ComponentName mDouble11;
    private ComponentName mDouble12;
    private PackageManager mPm;

    /*
    * 用来保存用户当前是否已经登录*/
    private SharedPreferences sharepreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferencesiconstate;
    private SharedPreferences.Editor editoriconstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        viewPager = (HomePager) findViewById(R.id.viewpager);
        first = (LinearLayout)findViewById(R.id.first);
        second = (LinearLayout)findViewById(R.id.second);
        third = (LinearLayout)findViewById(R.id.third);
        four = (LinearLayout)findViewById(R.id.four);
        homepage_head = (LinearLayout)findViewById(R.id.homepage_head);

        findpic = (TextView)findViewById(R.id.findpic);
        privateletterpic = (TextView)findViewById(R.id.privateletterpic);
        contactspic = (TextView)findViewById(R.id.contactspic);
        ownerpic = (TextView)findViewById(R.id.ownerpic);
        screen = (TextView)findViewById(R.id.screen);

        findtxt = (TextView)findViewById(R.id.findtxt);
        privatelettertxt = (TextView)findViewById(R.id.privatelettertxt);
        contactstxt = (TextView)findViewById(R.id.contactstxt);
        ownertxt = (TextView)findViewById(R.id.ownertxt);
        close_menu = (TextView)findViewById(R.id.close_menu);

        main_drawerLayout = (mydrawerlayout)findViewById(R.id.main_drawerLayout);

        findpic.setBackgroundResource(R.drawable.find_green);
        findtxt.setTextColor(Color.argb(255, 200, 0, 0));

        registerpage = (RedRect)findViewById(R.id.registerpage);
    }

    private void initData() {
        list = new ArrayList<>();
        found = new Found();
        privateletter = new Privateletter();
        contacts = new Contacts();
        owner = new Owner();
        list.add(found);
        list.add(privateletter);
        list.add(contacts);
        list.add(owner);
        fragmentaddapter = new FragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setOffscreenPageLimit(3);//设置页面缓存个数
        viewPager.setAdapter(fragmentaddapter);
        main_drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        sharepreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        sharedPreferencesiconstate = getSharedPreferences("iconstate", Context.MODE_PRIVATE);
        editoriconstate = sharedPreferencesiconstate.edit();

        mDefault = getComponentName();
        mDouble11 = new ComponentName(
                getBaseContext(),
                "com.winorout.meat.Test1");
        mDouble12 = new ComponentName(
                getBaseContext(),
                "com.winorout.meat.Test2");
        mPm = getApplicationContext().getPackageManager();

    }

    private void initEvent() {

        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        four.setOnClickListener(this);
        screen.setOnClickListener(this);
        registerpage.setOnClickListener(this);
        close_menu.setOnClickListener(this);

    }

    private void ResetView()
    {
        findpic.setBackgroundResource(R.drawable.find_grey);
        privateletterpic.setBackgroundResource(R.drawable.privateletter_grey);
        contactspic.setBackgroundResource(R.drawable.contacts_grey);
        ownerpic.setBackgroundResource(R.drawable.owner_grey);
        findtxt.setTextColor(Color.argb(255, 140, 140, 140));
        privatelettertxt.setTextColor(Color.argb(255, 140, 140, 140));
        contactstxt.setTextColor(Color.argb(255, 140, 140, 140));
        ownertxt.setTextColor(Color.argb(255, 140, 140, 140));
    }

    @Override
    public void onClick(View view) {
        ResetView();
        switch (view.getId())
        {
            case R.id.first:
                findpic.setBackgroundResource(R.drawable.find_green);
                findtxt.setTextColor(Color.argb(255, 200, 0, 0));
                viewPager.setCurrentItem(0);
                homepage_head.setVisibility(View.VISIBLE);
                break;
            case R.id.second:
                if (!sharepreferences.getString("SignState", "NoSign").equals("Sign"))
                {
                    startActivity(new Intent(MainActivity.this, Sign_in.class));
                }else {
                    privateletterpic.setBackgroundResource(R.drawable.privateletter_green);
                    privatelettertxt.setTextColor(Color.argb(255, 200, 0, 0));
                    viewPager.setCurrentItem(1);
                    homepage_head.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.third:
                if (!sharepreferences.getString("SignState", "NoSign").equals("Sign"))
                {
                    startActivity(new Intent(MainActivity.this, Sign_in.class));
                }else {
                    contactspic.setBackgroundResource(R.drawable.contacts_green);
                    contactstxt.setTextColor(Color.argb(255, 200, 0, 0));
                    viewPager.setCurrentItem(2);
                    homepage_head.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.four:
                ownerpic.setBackgroundResource(R.drawable.owner_green);
                ownertxt.setTextColor(Color.argb(255, 200, 0, 0));
                viewPager.setCurrentItem(3);
                homepage_head.setVisibility(View.GONE);
                break;
            case R.id.registerpage:
                if (!sharepreferences.getString("SignState", "NoSign").equals("Sign")){
//                    startActivity(new Intent(MainActivity.this, Sign_in.class));
                    //动态更改软件图标，详情请见:http://android.jobbole.com/85321/
                    if (sharedPreferencesiconstate.getString("states", "no").equals("no"))
                    {
                        disableComponent(mDefault);
                        enableComponent(mDouble11);
                        disableComponent(mDouble12);
                        editoriconstate.putString("states", "yes");
                        editoriconstate.commit();
                    }else {
                        disableComponent(mDefault);
                        disableComponent(mDouble11);
                        enableComponent(mDouble12);
                        editoriconstate.putString("states", "no");
                        editoriconstate.commit();
                    }
                    Toast.makeText(this, "退出软件看看APP图标，稍等几秒...", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "您已经登录...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.screen:
                main_drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.close_menu:
                main_drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }

    }

    public class FragmentAdapter extends FragmentPagerAdapter
    {
        private List<Fragment> list;
        public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if (System.currentTimeMillis() - clickback > 2000)
            {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                clickback = System.currentTimeMillis();
            }else
            {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
