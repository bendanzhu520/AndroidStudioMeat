package com.winorout.meat.signin.chosecountry.tools;

/**
 * Created by Mr-x on 2017/04/14.
 */


public class Country_name {
    //姓名
    private String name;
    //拼音
    private String pinyin;
    //拼音首字母
    private String headerWord;

    public Country_name(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinyin(name);
        headerWord = pinyin.substring(0, 1);
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderWord() {
        return headerWord;
    }
}
