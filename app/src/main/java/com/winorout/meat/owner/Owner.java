package com.winorout.meat.owner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winorout.meat.R;
import com.winorout.meat.owner.editorinformation.EditorinFormation;
import com.winorout.meat.owner.set.Set;
import com.winorout.meat.signin.Sign_in;

import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Mr-x on 2017/03/22.
 */

/*
* 四大Fragment之一的我界面，包含头像切换，用户登录，用户信息设置和用户详情等功能
* 使用了CollapsingToolbarLayout，AppBarLayout和CoordinatorLayout，具体使用网上查询，因为我找的关于这些都很零碎，没办法，改天自己写一个算了
* 依赖库：    compile 'com.android.support:design:25.1.1'
* */

public class Owner extends Fragment implements View.OnClickListener {

    private View view;
    private TextView log_on;
    private Toolbar set;

    private ImageView user_head;
    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径

    private LinearLayout mygift;
    private LinearLayout myapplicationform;
    private LinearLayout mylove;
    private LinearLayout myfabulous;
    private LinearLayout mywallet;
    private LinearLayout myadress;
    private LinearLayout myreputation;
    private LinearLayout myintegral;
    private LinearLayout myhomepage;

    private LinearLayout sign_back;
    private LinearLayout nosign_back;
    private LinearLayout editorinfo;

    private SharedPreferences sharepreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.owner, null);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initView() {

        sharepreference = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);

        log_on = (TextView) view.findViewById(R.id.log_on);
        set = (Toolbar) view.findViewById(R.id.set);

        user_head = (ImageView) view.findViewById(R.id.user_head);

        mygift = (LinearLayout) view.findViewById(R.id.mygift);
        myapplicationform = (LinearLayout) view.findViewById(R.id.myapplicationform);
        mylove = (LinearLayout) view.findViewById(R.id.mylove);
        myfabulous = (LinearLayout) view.findViewById(R.id.myfabulous);
        mywallet = (LinearLayout) view.findViewById(R.id.mywallet);
        myadress = (LinearLayout) view.findViewById(R.id.myadress);
        myreputation = (LinearLayout) view.findViewById(R.id.myreputation);
        myintegral = (LinearLayout) view.findViewById(R.id.myintegral);
        myhomepage = (LinearLayout) view.findViewById(R.id.myhomepage);
        editorinfo = (LinearLayout) view.findViewById(R.id.editorinfo);

        sign_back = (LinearLayout) view.findViewById(R.id.sign_back);
        nosign_back = (LinearLayout) view.findViewById(R.id.nosign_back);
        if (sharepreference.getString("SignState", "NoSign").equals("Sign")) {
            sign_back.setVisibility(View.VISIBLE);
            nosign_back.setVisibility(View.GONE);
        } else {
            sign_back.setVisibility(View.GONE);
            nosign_back.setVisibility(View.VISIBLE);
        }
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            user_head.setImageDrawable(drawable);
        } else {
/**
 * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
 *
 */
        }

    }

    private void initData() {

    }

    private void initEvent() {

        log_on.setOnClickListener(this);
        set.setOnClickListener(this);

        user_head.setOnClickListener(this);

        mygift.setOnClickListener(this);
        myapplicationform.setOnClickListener(this);
        mylove.setOnClickListener(this);
        myfabulous.setOnClickListener(this);
        mywallet.setOnClickListener(this);
        myadress.setOnClickListener(this);
        myreputation.setOnClickListener(this);
        myintegral.setOnClickListener(this);
        myhomepage.setOnClickListener(this);
        editorinfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.log_on:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.set:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    startActivity(new Intent(getContext(), Set.class));
                }
                break;
            case R.id.mygift:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.myapplicationform:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mylove:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.myfabulous:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mywallet:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.myadress:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.myreputation:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.myintegral:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.myhomepage:
                if (!sharepreference.getString("SignState", "NoSign").equals("Sign")) {
                    startActivity(new Intent(getActivity(), Sign_in.class));
                } else {
                    Toast.makeText(getActivity(), "您已经登录了...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.editorinfo:
                startActivity(new Intent(getContext(), EditorinFormation.class));
                break;
            case R.id.user_head:
                //此处是获取用户头像和设置过程，详解请见:http://blog.csdn.net/wanxuedong/article/details/68969304
                showTypeDialog();
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharepreference.getString("SignState", "NoSign").equals("Sign")) {
            sign_back.setVisibility(View.VISIBLE);
            nosign_back.setVisibility(View.GONE);
        } else {
            sign_back.setVisibility(View.GONE);
            nosign_back.setVisibility(View.VISIBLE);
        }
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
/**
 * 上传服务器代码
 */
                        setPicToView(head);// 保存在SD卡中
                        user_head.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
// aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
// outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
// 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
