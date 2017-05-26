package com.winorout.meat.signin.chosecountry.tools;

import java.util.List;

/**
 * Created by Mr-x on 2017/04/15.
 */

/*
* 一个用来判断字符串和数据源是否有匹配的工具
* */

public class matchingtool {

    private String str;           //这是要拿来匹配的字符串
    private List<String> list;   //这是我们字符匹配的数据源
    private int position = -1;  //这里是返回的位置，初始值为-1，意思是如果没有匹配到合适的字符串，则为-1

    //传入要匹配的字符串和数据源
    public matchingtool(String str, List<String> list)
    {
        this.str = str;
        this.list = list;
        calculationposition();//这就是我们的核心算法，用来计算是否有匹配的字符串
    }

    private void calculationposition() {

        int k;  //用来标记的，如果为0，表示前面还没有匹配的，如果为1，则表示前面已经有匹配的选项，不会将返回的位置往后移
        int aggregate[] = new int[1000];   //用来保存符合条件的字符串的位置
        int h;     //用来给aggregate的长度重新设置的
        int length = list.size();   //用来保存每次循环的次数
        List<String> lists = list;   //每次用来保存新的数据源

        for (int i = 0; i < str.trim().length(); i++)
        {
            k = 0;    //这里要将k每次都重新设置为0，这样是为了单独判断str的每个字符
            h = 0;    //每次都要讲数据里面的数据重新录入
            for (int j = 0; j < length; j++)
            {
                if (str.substring(i, i + 1).equals(lists.get(j).substring(i, i + 1)))
                {
                    aggregate[h++] = j;   //这里弄了一个数组把单个字符匹配的字符串的位置保存起来，以便下一次的循环再此筛选
                    lists.add(h++, lists.get(j));  //这里把符合条件的字符串给保存的起来，以便下次判断
                    if (k == 0)
                    {
                        position = j; //在此之前还没有匹配的字符串，可以录入位置
                        k = 1;
                    }
                }
            }
            length = aggregate.length - 1;  //每次循环过后把新的长度返回给下一次，层层筛选
        }

    }

    //用来返回匹配成功或不成功法人位置
    public int getposition()
    {
        return position;
    }

}
