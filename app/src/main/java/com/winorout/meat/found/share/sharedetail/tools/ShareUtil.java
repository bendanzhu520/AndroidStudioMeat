package com.winorout.meat.found.share.sharedetail.tools;

/**
 * Created by Mr-x on 2017/04/13.
 */

/*
* 用来在不同软件间分享信息的工具类，详情请见:http://blog.csdn.net/zh_ang_lei/article/details/52385678
* */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;

public class ShareUtil {
    private Context context;

    public ShareUtil(Context context) {
        this.context = context;
    }

    /**
     * 分享文字
     * @param packageName
     * @param content
     * @param title
     * @param subject
     */
    public void shareText(String packageName,String className,String content,String title,String subject){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if(stringCheck(className) && stringCheck(packageName)){
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        }else if(stringCheck(packageName)){
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if(null != title && !TextUtils.isEmpty(title)){
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if(null != subject && !TextUtils.isEmpty(subject)){
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        context.startActivity(chooserIntent);
    }

    /**
     * 分享网页
     */
    public void shareUrl(String packageName,String className,String content,String title,String subject){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if(stringCheck(className) && stringCheck(packageName)){
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        }else if(stringCheck(packageName)){
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if(null != title && !TextUtils.isEmpty(title)){
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if(null != subject && !TextUtils.isEmpty(subject)){
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        context.startActivity(chooserIntent);
    }

    /**
     * 分享图片
     */
    public void shareImg(String packageName,String className,File file){
        if(file.exists()){
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            if(stringCheck(packageName) && stringCheck(className)){
                intent.setComponent(new ComponentName(packageName, className));
            }else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(chooserIntent);
        }else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享音乐
     */
    public void shareAudio(String packageName,String className,File file){
        if(file.exists()){
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("audio/*");
            if(stringCheck(packageName) && stringCheck(className)){
                intent.setComponent(new ComponentName(packageName, className));
            }else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(chooserIntent);
        }else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享视频
     */
    public void shareVideo(String packageName,String className,File file){
        setIntent("video/*", packageName, className, file);
    }

    public void setIntent(String type,String packageName,String className,File file){
        if(file.exists()){
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType(type);
            if(stringCheck(packageName) && stringCheck(className)){
                intent.setComponent(new ComponentName(packageName, className));
            }else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(chooserIntent);
        }else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享多张图片和文字至朋友圈
     * @param title
     * @param packageName
     * @param className
     * @param file 图片文件
     */
    public void shareImgToWXCircle(String title,String packageName,String className, File file){
        if(file.exists()){
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(packageName, className);
            intent.setComponent(comp);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra("Kdescription", title);
            context.startActivity(intent);
        }else{
            Toast.makeText(context, "文件不存在", Toast.LENGTH_LONG).show();
        }


    }
    /**
     * 是否安装分享app
     * @param packageName
     */
    public boolean checkInstall(String packageName){
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "请先安装应用app", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 跳转官方安装网址
     */
    public void toInstallWebView(String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static boolean stringCheck(String str){
        if(null != str && !TextUtils.isEmpty(str)){
            return true;
        }else {
            return false;
        }
    }
}
