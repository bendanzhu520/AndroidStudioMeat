 Command line instructions
Git global setup

git config --global user.name "Administrator"
git config --global user.email "admin@example.com"

Create a new repository

git clone ssh://git@localhost:18083/meihuan/AndroidStudioMeat.git
git clone http://localhost:18081/meihuan/AndroidStudioMeat.git
cd AndroidStudioMeat
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master

Existing folder or Git repository

cd existing_folder
git init
git remote add origin ssh://git@localhost:18083/meihuan/AndroidStudioMeat.git
git remote add origin http://localhost:18081/meihuan/AndroidStudioMeat.git
git add .
git commit
git push -u origin master


# Meat_Master
一款模仿有肉的应用
基本上分包的规则都是按界面来的，用到了第三方的东西。
整体的布局是Fragment+ViewPager，然后第一个界面仍然是Fragment+ViewPager，等于Fragment双重嵌套
用到第三方的东西大致包含以下内容:

    compile 'com.android.support:recyclerview-v7:25.1.1'
    compile 'com.android.support:cardview-v7:25.1.1'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.android.support:design:25.1.1'
    compile 'com.squareup.okhttp3:okhttp:3.5.0'
    compile 'com.squareup.okio:okio:1.5.0'
    compile 'com.google.code.gson:gson:2.2.4'
    compile 'com.wuxiaolong.pullloadmorerecyclerview:library:1.0.4'
    compile files('libs/glide-3.7.0.jar')
    compile files('libs/pinyin4j-2.5.0.jar')

都是可以从github上面下到的，或者系统自带，具体使用自行查询
