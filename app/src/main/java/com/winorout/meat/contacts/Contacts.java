package com.winorout.meat.contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winorout.meat.R;

/**
 * Created by Mr-x on 2017/03/22.
 */

public class Contacts extends Fragment{

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contacts, null);
        return view;
    }
}
