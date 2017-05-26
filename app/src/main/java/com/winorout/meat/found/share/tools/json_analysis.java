package com.winorout.meat.found.share.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mr-x on 2017/03/31.
 * 该类主要用来实现给出url，读取出网络数据的功能，为Share界面的数据展示服务,
 * 用的okhttp网络框架，具体用法请前往：http://blog.csdn.net/lmj623565791/article/details/47911083
 * 依赖库:    compile 'com.squareup.okhttp3:okhttp:3.5.0'
 *            compile 'com.squareup.okio:okio:1.5.0'
 */

public class json_analysis {
    OkHttpClient okHttpClient = new OkHttpClient();
    private Bitmap[] bit;
    String str;
    private Handler handler;

    public json_analysis(String str) {
        this.str = str;
    }

    public List<ShareItem> getData() {
        List<ShareItem> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(str);
            String str1 = jsonObject.getString("stories");
            JSONArray jsonArray = new JSONArray(str1);
            Log.d("jsonlength", "" + jsonArray.length() + "");
            for (int i = 0; i < jsonArray.length(); i++) {
                ShareItem shareItem = new ShareItem();
                JSONObject jsonObject1 = (JSONObject) jsonArray.opt(i);
                Log.d("jsonobject", jsonObject1 + "");
                shareItem.txt = jsonObject1.getString("title");
                shareItem.img = jsonObject1.getString("images").replace("\\/\\/", "//").replace("\\/", "/").replace("[", "").replace("]", "").replace("\"", "");
                Log.d("shareitem", shareItem.txt + "");
                Log.d("PicURL", shareItem.img);
//                Request request = new Request
//                        .Builder()
//                        .url(jsonObject1.getString("images").replace("\\/\\/", "//").replace("\\/", "/").replace("[", "").replace("]", "").replace("\"", ""))
//                        .build();
//                doDownloadImageView(list, i, request);
                list.add(shareItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("exception", e.toString() + "");
        }
        return list;
    }

    public class ShareItem {
        public String img;
        public String txt;
    }

//    public void doDownloadImageView(final List<ShareItem> list, final int position, Request str) {
//        final Bitmap[] bitmap = {null};
//        Call call = okHttpClient.newCall(str);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                InputStream is = response.body().byteStream();
//                Log.d("getpic", BitmapFactory.decodeStream(is) + "");
//                list.get(position).img = BitmapFactory.decodeStream(is);
//            }
//        });
//    }

}
