package com.winorout.meat.signin.chosecountry;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.winorout.meat.R;
import com.winorout.meat.signin.chosecountry.tools.Letter_Adapter;
import com.winorout.meat.signin.chosecountry.tools.Country_name;
import com.winorout.meat.signin.chosecountry.tools.Alphabet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mr-x on 2017/04/14.
 */

public class ChoseCountry extends AppCompatActivity implements Alphabet.onWordsChangeListener, AbsListView.OnScrollListener, View.OnClickListener {

    private Handler handler;
    private List<Country_name> list;
    private TextView contacts;
    private ListView contacts_list;
    private Alphabet word;
    private TextView backto_signin;
    private EditText search_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chosecountry);
        initView();
        initData();
        initEvent();

        //设置列表点击滑动监听
        handler = new Handler();
        word.setOnWordsChangeListener(this);
    }

    private void initView() {

        contacts = (TextView) findViewById(R.id.contacts);
        word = (Alphabet) findViewById(R.id.words);
        contacts_list = (ListView) findViewById(R.id.contacts_list);
        backto_signin = (TextView) findViewById(R.id.backto_signin);
        search_country = (EditText) findViewById(R.id.search_country);
    }

    /**
     * 初始化联系人列表信息
     */
    private void initData() {
        list = new ArrayList<>();
        list.add(new Country_name("阿尔巴尼亚"));
        list.add(new Country_name("阿尔及利亚"));
        list.add(new Country_name("阿富汗"));
        list.add(new Country_name("阿根廷"));
        list.add(new Country_name("阿拉伯联合酋长国"));
        list.add(new Country_name("阿鲁巴岛"));
        list.add(new Country_name("阿曼"));
        list.add(new Country_name("埃及"));
        list.add(new Country_name("埃色俄比亚"));
        list.add(new Country_name("爱尔兰"));
        list.add(new Country_name("爱沙尼亚"));
        list.add(new Country_name("安道尔"));
        list.add(new Country_name("安哥拉"));
        list.add(new Country_name("安圭拉岛"));
        list.add(new Country_name("安提瓜和巴布达"));
        list.add(new Country_name("奥地利"));
        list.add(new Country_name("澳大利亚"));
        list.add(new Country_name("澳门"));
        list.add(new Country_name("巴巴多斯"));
        list.add(new Country_name("巴布亚新几内亚"));
        list.add(new Country_name("巴哈尔群岛"));
        list.add(new Country_name("巴基斯坦"));
        list.add(new Country_name("巴拉圭"));
        list.add(new Country_name("巴勒斯坦领土"));
        list.add(new Country_name("巴林"));
        list.add(new Country_name("巴拉马"));
        list.add(new Country_name("巴西"));
        list.add(new Country_name("白俄罗斯"));
        list.add(new Country_name("百慕大群岛"));
        list.add(new Country_name("保加利亚"));
        list.add(new Country_name("贝宁"));
        list.add(new Country_name("比利时"));
        list.add(new Country_name("冰岛"));
        list.add(new Country_name("波多黎各"));
        list.add(new Country_name("波兰"));
        list.add(new Country_name("波利尼西亚(法属)"));
        list.add(new Country_name("波斯尼亚和黑赛格维那"));
        list.add(new Country_name("玻利维亚"));
        list.add(new Country_name("伯利兹"));
        list.add(new Country_name("博茨瓦纳"));
        list.add(new Country_name("不丹"));
        list.add(new Country_name("布基纳法索"));
        list.add(new Country_name("布隆迪"));
        list.add(new Country_name("朝鲜"));
        list.add(new Country_name("赤道几内亚"));
        list.add(new Country_name("丹麦"));
        list.add(new Country_name("德国"));
        list.add(new Country_name("东帝汶"));
        list.add(new Country_name("多哥"));
        list.add(new Country_name("多米尼加共和国"));
        list.add(new Country_name("多米尼克"));
        list.add(new Country_name("俄罗斯"));
        list.add(new Country_name("尼瓜多尔"));
        list.add(new Country_name("法国"));
        list.add(new Country_name("法落群岛"));
        list.add(new Country_name("菲律宾"));
        list.add(new Country_name("斐济"));
        list.add(new Country_name("芬兰"));
        list.add(new Country_name("佛得角"));
        list.add(new Country_name("冈比亚"));
        list.add(new Country_name("刚果共和国"));
        list.add(new Country_name("刚果民主共和国"));
        list.add(new Country_name("哥伦比亚"));
        list.add(new Country_name("哥斯达黎加"));
        list.add(new Country_name("格林纳达"));
        list.add(new Country_name("格陵兰岛"));
        list.add(new Country_name("格鲁吉亚"));
        list.add(new Country_name("古巴"));
        list.add(new Country_name("瓜德罗普岛"));
        list.add(new Country_name("关岛"));
        list.add(new Country_name("圭亚那"));
        list.add(new Country_name("圭亚那(法属)"));
        list.add(new Country_name("哈萨克斯坦"));
        list.add(new Country_name("海地"));
        list.add(new Country_name("韩国"));
        list.add(new Country_name("荷兰"));
        list.add(new Country_name("荷属安的列斯群岛"));
        list.add(new Country_name("黑山共和国"));
        list.add(new Country_name("洪都拉斯"));
        list.add(new Country_name("基里巴斯"));
        list.add(new Country_name("吉布提"));
        list.add(new Country_name("吉尔吉斯斯坦"));
        list.add(new Country_name("几内亚"));
        list.add(new Country_name("几内亚比绍"));
        list.add(new Country_name("加拿大"));
        list.add(new Country_name("加纳"));
        list.add(new Country_name("加蓬"));
        list.add(new Country_name("柬埔寨"));
        list.add(new Country_name("捷克共和国"));
        list.add(new Country_name("津巴布韦"));
        list.add(new Country_name("喀麦隆"));
        list.add(new Country_name("卡塔尔"));
        list.add(new Country_name("开曼群岛"));
        list.add(new Country_name("科摩罗"));
        list.add(new Country_name("科特迪瓦"));
        list.add(new Country_name("科威特"));
        list.add(new Country_name("克罗地亚"));
        list.add(new Country_name("肯尼亚"));
        list.add(new Country_name("库克群岛"));
        list.add(new Country_name("拉脱群岛"));
        list.add(new Country_name("莱索托"));
        list.add(new Country_name("老挝"));
        list.add(new Country_name("黎巴嫩"));
        list.add(new Country_name("立陶宛"));
        list.add(new Country_name("利比里亚"));
        list.add(new Country_name("利比亚"));
        list.add(new Country_name("列支敦士登"));
        list.add(new Country_name("法属留尼汪岛"));
        list.add(new Country_name("卢森堡"));
        list.add(new Country_name("卢旺达"));
        list.add(new Country_name("罗马尼亚"));
        list.add(new Country_name("马达加斯加"));
        list.add(new Country_name("马尔代夫"));
        list.add(new Country_name("马耳他"));
        list.add(new Country_name("马拉维"));
        list.add(new Country_name("马来西亚"));
        list.add(new Country_name("马里"));
        list.add(new Country_name("马其顿"));
        list.add(new Country_name("马提尼克"));
        list.add(new Country_name("毛里求斯"));
        list.add(new Country_name("毛里塔尼亚"));
        list.add(new Country_name("美国"));
        list.add(new Country_name("蒙古"));
        list.add(new Country_name("蒙特塞拉特岛"));
        list.add(new Country_name("孟加拉国"));
        list.add(new Country_name("秘鲁"));
        list.add(new Country_name("密克罗尼西亚"));
        list.add(new Country_name("缅甸"));
        list.add(new Country_name("摩尔多瓦"));
        list.add(new Country_name("摩洛哥"));
        list.add(new Country_name("摩纳哥"));
        list.add(new Country_name("莫桑比克"));
        list.add(new Country_name("墨西哥"));
        list.add(new Country_name("纳米比亚"));
        list.add(new Country_name("南非"));
        list.add(new Country_name("南苏丹"));
        list.add(new Country_name("尼泊尔"));
        list.add(new Country_name("尼加拉瓜"));
        list.add(new Country_name("尼日尔"));
        list.add(new Country_name("尼日利亚"));
        list.add(new Country_name("挪威"));
        list.add(new Country_name("帕劳"));
        list.add(new Country_name("葡萄牙"));
        list.add(new Country_name("日本"));
        list.add(new Country_name("瑞典"));
        list.add(new Country_name("瑞士"));
        list.add(new Country_name("萨尔瓦多"));
        list.add(new Country_name("萨摩亚"));
        list.add(new Country_name("塞尔维亚"));
        list.add(new Country_name("塞拉利昂"));
        list.add(new Country_name("塞内加尔"));
        list.add(new Country_name("塞浦路斯"));
        list.add(new Country_name("塞舌尔"));
        list.add(new Country_name("沙特阿拉伯"));
        list.add(new Country_name("圣多美和普林西比"));
        list.add(new Country_name("圣基茨和尼维斯"));
        list.add(new Country_name("圣卢西亚"));
        list.add(new Country_name("圣马力诺"));
        list.add(new Country_name("省皮埃尔和米克隆岛"));
        list.add(new Country_name("圣文森特和格林纳丁斯"));
        list.add(new Country_name("斯里兰卡"));
        list.add(new Country_name("斯洛伐克"));
        list.add(new Country_name("斯洛文尼亚"));
        list.add(new Country_name("斯威士兰"));
        list.add(new Country_name("苏丹"));
        list.add(new Country_name("苏里南"));
        list.add(new Country_name("所罗门群岛"));
        list.add(new Country_name("索马里"));
        list.add(new Country_name("塔吉克斯坦"));
        list.add(new Country_name("台湾"));
        list.add(new Country_name("泰国"));
        list.add(new Country_name("坦桑尼亚"));
        list.add(new Country_name("汤加"));
        list.add(new Country_name("特克斯和凯克斯群岛"));
        list.add(new Country_name("特立尼达，多巴哥"));
        list.add(new Country_name("突尼斯"));
        list.add(new Country_name("土耳其"));
        list.add(new Country_name("土库曼斯坦"));
        list.add(new Country_name("瓦努阿图"));
        list.add(new Country_name("危地马拉"));
        list.add(new Country_name("维尔京群岛(美属)"));
        list.add(new Country_name("维尔京群岛(英属)"));
        list.add(new Country_name("委内瑞拉"));
        list.add(new Country_name("文莱"));
        list.add(new Country_name("乌干达"));
        list.add(new Country_name("乌克兰"));
        list.add(new Country_name("乌拉圭"));
        list.add(new Country_name("鸟兹别克斯坦"));
        list.add(new Country_name("西班牙"));
        list.add(new Country_name("希腊"));
        list.add(new Country_name("香港"));
        list.add(new Country_name("新加坡"));
        list.add(new Country_name("新克里多尼亚"));
        list.add(new Country_name("新西兰"));
        list.add(new Country_name("匈牙利"));
        list.add(new Country_name("叙利亚"));
        list.add(new Country_name("牙买加"));
        list.add(new Country_name("亚美尼亚"));
        list.add(new Country_name("也门"));
        list.add(new Country_name("伊拉克"));
        list.add(new Country_name("伊朗"));
        list.add(new Country_name("以色列"));
        list.add(new Country_name("意大利"));
        list.add(new Country_name("印度"));
        list.add(new Country_name("印度尼西亚"));
        list.add(new Country_name("英国"));
        list.add(new Country_name("约旦"));
        list.add(new Country_name("越南"));
        list.add(new Country_name("赞比亚"));
        list.add(new Country_name("泽西"));
        list.add(new Country_name("乍得"));
        list.add(new Country_name("直布罗陀"));
        list.add(new Country_name("智利"));
        list.add(new Country_name("中非共和国"));
        list.add(new Country_name("中国"));
        Letter_Adapter adapter = new Letter_Adapter(this, list);
        contacts_list.setAdapter(adapter);
        contacts_list.setOnScrollListener(this);

        //对集合排序
        Collections.sort(list, new Comparator<Country_name>() {
            @Override
            public int compare(Country_name lhs, Country_name rhs) {
                //根据拼音进行排序
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }

    private void initEvent() {

        backto_signin.setOnClickListener(this);
        search_country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    //手指按下字母改变监听回调
    @Override
    public void wordsChange(String words) {
        updateWord(words);
        updateListView(words);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当滑动列表的时候，更新右侧字母列表的选中状态
        word.setTouchIndex(list.get(firstVisibleItem).getHeaderWord());
    }

    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        for (int i = 0; i < list.size(); i++) {
            String headerWord = list.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                contacts_list.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }

    /**
     * 更新中央的字母提示
     *
     * @param words 首字母
     */
    private void updateWord(String words) {
        contacts.setText(words);
        contacts.setVisibility(View.VISIBLE);
        //清空之前的所有消息
        handler.removeCallbacksAndMessages(null);
        //1s后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                contacts.setVisibility(View.GONE);
            }
        }, 500);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.backto_signin:
                finish();
                break;
        }

    }
}
