package com.winorout.meat.found.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winorout.meat.R;
import com.winorout.meat.found.find.advertisement.Advertisement;
import com.winorout.meat.found.find.tools.BannerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr-x on 2017/03/22.
 */
/*
* 该类用来展示发现下的小发现页面的详细情况，展示一些其他的东西
* */
public class Find extends Fragment {

    private BannerLayout bannerLayout;
    private List<Integer> res;
    private List<String> titles;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find, null);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initView() {

        bannerLayout = (BannerLayout) view.findViewById(R.id.banner1);

    }

    private void initData() {

        List<Integer> res = new ArrayList<>();
        res.add(R.drawable.lunbo1);
        res.add(R.drawable.lunbo2);
        res.add(R.drawable.lunbo3);
        titles = new ArrayList<>();
        titles.add("广告一");
        titles.add("广告二");
        titles.add("广告三");
        if (bannerLayout != null) {
            bannerLayout.setViewRes(res, titles);
        }

    }

    private void initEvent() {

        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 0)
                {
                    startActivity(new Intent(getContext(), Advertisement.class));
                }
            }
        });
    }
}
