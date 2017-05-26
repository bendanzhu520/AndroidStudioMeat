package com.winorout.meat.found;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winorout.meat.MainActivity;
import com.winorout.meat.R;
import com.winorout.meat.found.find.Find;
import com.winorout.meat.found.share.Share;
import com.winorout.meat.found.wish.Wish;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mr-x on 2017/03/22.
 */

public class Found extends Fragment implements View.OnClickListener {
    private View view;
    private ViewPager foundmation;
    private FragmentAdapter fragmentadapter;
    private List<Fragment> list;
    private Share share;
    private Wish wish;
    private Find find;

    private TextView sharetxt;
    private TextView wishtxt;
    private TextView findtxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.found, null);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initView() {
        foundmation = (ViewPager) view.findViewById(R.id.foundmation);

        sharetxt = (TextView) view.findViewById(R.id.sharetxt);
        wishtxt = (TextView) view.findViewById(R.id.wishtxt);
        findtxt = (TextView) view.findViewById(R.id.findtxt);
        sharetxt.setTextColor(Color.argb(255, 255, 0, 0));

    }

    private void initData() {
        list = new ArrayList<>();
        share = new Share();
        wish = new Wish();
        find = new Find();
        list.add(share);
        list.add(wish);
        list.add(find);
        fragmentadapter = new FragmentAdapter(getFragmentManager(), list);
        foundmation.setOffscreenPageLimit(2);
        foundmation.setAdapter(fragmentadapter);

    }

    private void initEvent() {

        sharetxt.setOnClickListener(this);
        wishtxt.setOnClickListener(this);
        findtxt.setOnClickListener(this);


        foundmation.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ResetView();
                if (position == 0) {
                    sharetxt.setTextColor(Color.argb(255, 255, 0, 0));
                } else if (position == 1) {
                    wishtxt.setTextColor(Color.argb(255, 255, 0, 0));
                } else {
                    findtxt.setTextColor(Color.argb(255, 255, 0, 0));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        initData();
        ResetView();
        switch (view.getId()) {
            case R.id.sharetxt:
                sharetxt.setTextColor(Color.argb(255, 255, 0, 0));
                foundmation.setCurrentItem(0);
                break;
            case R.id.wishtxt:
                wishtxt.setTextColor(Color.argb(255, 255, 0, 0));
                foundmation.setCurrentItem(1);
                break;
            case R.id.findtxt:
                findtxt.setTextColor(Color.argb(255, 255, 0, 0));
                foundmation.setCurrentItem(2);
                break;
        }
    }

    private void ResetView() {

        sharetxt.setTextColor(Color.argb(255, 0, 0, 0));
        wishtxt.setTextColor(Color.argb(255, 0, 0, 0));
        findtxt.setTextColor(Color.argb(255, 0, 0, 0));

    }


    public class FragmentAdapter extends FragmentPagerAdapter {
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
    public void onResume() {
        super.onResume();
        initData();
        ResetView();
        sharetxt.setTextColor(Color.argb(255, 255, 0, 0));
        foundmation.setCurrentItem(0);
    }

}
