package com.winorout.meat.found.find.advertisement;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.winorout.meat.R;

/**
 * Created by Mr-x on 2017/03/29.
 */

/*
* 广告信息的详细展示
* */

public class Advertisement extends Activity {

    private WebView adervertisementone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertisement);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

        adervertisementone = (WebView)findViewById(R.id.adervertisementone);

    }

    private void initData() {

        if (isNetworkConnected(Advertisement.this)){
            WebSettings wSet = adervertisementone.getSettings();
            wSet.setJavaScriptEnabled(true);
            adervertisementone.loadUrl("http://www.eb89.com/web.php?id=RXtzevL");

        }else {
            Toast.makeText(this, "请检查你的网络", Toast.LENGTH_SHORT).show();
        }


    }

    private void initEvent() {

    }



    /*
    * 判断当前有无网络连接,详情请见:http://blog.csdn.net/qq_23255751/article/details/51038609
    * */

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adervertisementone.destroy();
    }
}
