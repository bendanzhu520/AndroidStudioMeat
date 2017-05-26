package com.winorout.meat.found.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.winorout.meat.R;
import com.winorout.meat.found.share.sharedetail.sharedetail;
import com.winorout.meat.found.share.tools.MyRecyclerAdapter;
import com.winorout.meat.found.share.tools.json_analysis;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import android.widget.TextView;

/**
 * Created by Mr-x on 2017/03/22.
 * 该类主要实现了PullLoadMoreRecyclerView这个控件的数据加载和刷新，需要学习请前往：http://blog.csdn.net/mynameishuangshuai/article/details/51153978
 */

/*
* 发现页面下的分享小页面，瀑布流展示分享产品信息
* */
public class Share extends Fragment implements View.OnClickListener{

    private View view;
    private PullLoadMoreRecyclerView sharerecyclerview;
    private static List<json_analysis.ShareItem> list;

    private TextView moveto_top;
    private TextView check_internet;

    private Handler handler;

    private OkHttpClient okHttpClient = new OkHttpClient();
    private MyRecyclerAdapter myrecycleadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.share, null);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initView() {
        sharerecyclerview = (PullLoadMoreRecyclerView) view.findViewById(R.id.sharerecyclerview);
        moveto_top = (TextView)view.findViewById(R.id.moveto_top);
        check_internet = (TextView)view.findViewById(R.id.check_internet);
    }

    private void initData() {

        list = new ArrayList<>();
        sharerecyclerview.setGridLayout(2);//设置列数
        sharerecyclerview.setStaggeredGridLayout(2);//设置为瀑布流
        if (isNetworkConnected(getContext()))
        {
            Request request = new Request
                    .Builder()
                    .url("http://news-at.zhihu.com/api/4/news/latest")
                    .build();
            executeRequest(request);
            check_internet.setVisibility(View.INVISIBLE);
            moveto_top.setVisibility(View.VISIBLE);
            Log.d("check_internet", "yes");
        }else {
            moveto_top.setVisibility(View.INVISIBLE);
            check_internet.setVisibility(View.VISIBLE);
            Log.d("check_internet", "no");
        }
        sharerecyclerview.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            //下拉刷新数据
            @Override
            public void onRefresh() {
                try {
//                    Request request = new Request
//                            .Builder()
//                            .url("http://news-at.zhihu.com/api/4/news/latest")
//                            .build();
//                    executeRequest(request);
                    sharerecyclerview.setPullLoadMoreCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadMore() {
                sharerecyclerview.setPullLoadMoreCompleted();
            }
        });

        /*
        * 接收到信息,再把数据传输到json_analysis类里面进行处理
        * 确认获取到返回数据，再发送信息，刷新界面*/
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 123:
                        myrecycleadapter = new MyRecyclerAdapter(getContext(), list);
                        sharerecyclerview.setAdapter(myrecycleadapter);
                        Log.d("refresh", "refresh");
                        myrecycleadapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int position) {
//                                Toast.makeText(getContext(), "onClick" + position, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), sharedetail.class));
                            }

                            @Override
                            public void onLongClick(int position) {
                                Toast.makeText(getContext(), "onLongClick" + position, Toast.LENGTH_SHORT).show();

                            }
                        });
                        break;
                }
            }
        };

    }

    private void initEvent() {

        moveto_top.setOnClickListener(this);

    }




    public void executeRequest(Request request) {
        //3，将Request封装成Call
        Call call = okHttpClient.newCall(request);
        //4，执行call方法
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("onfailure", "onfliure");
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//               mTv.setText(response.toString());
                //注意这里是string()，而不是toString,注意
                final String str = response.body().string();
                Log.e(TAG, str);
                //OnResponse是运行在子线程中的，所以我们要刷新UI的话，注意要在主线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        try {
                            list.addAll(new json_analysis(str).getData());
                            for (json_analysis.ShareItem item : list) {
                                Log.d(TAG, "Title: " + item.txt);
                            }
                            i = 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (i == 1) {
                            Message message = new Message();
                            message.what = 123;
                            handler.sendMessage(message);
                            i = 0;
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.moveto_top:
                sharerecyclerview.scrollToTop();
                break;
        }
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
}
