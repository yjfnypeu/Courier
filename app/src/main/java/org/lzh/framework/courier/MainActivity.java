package org.lzh.framework.courier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.lzh.framework.courier.model.User;

import java.util.Arrays;
import java.util.HashSet;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.toEmptyParamsActivity)
    void toEmptyActivity() {
        // start跳转支持以Activity/Context/Fragment/V4Fragment进行跳转
        EmptyActivityDispatcher.create().requestCode(-1).start(MainActivity.this);
    }

    @OnClick(R.id.toParamsActivity)
    void toParamaActivity () {
        ParamsActivityDispatcher.create()
                .setUsername("123456")
                .setPassword("111111")
                .requestCode(-1)
                .start(this);

    }

    @OnClick(R.id.toMultiTypeActivity)
    void toMultiTypeActivity () {
        MultiTypeActivityDispatcher.create()
                .setAdmin(new User("张三","密码0"))
                .setArrayUser(new User[]{new User("李四", "密码1")})
                .setListUser(Arrays.asList(new User[]{new User("王五","密码")}))
                .setSetUser(new HashSet<User>())
                .start(this);
    }

    @OnClick(R.id.toParentActivity)
    void toParentActivity() {
        ParentActivityDispatcher.create()
                .setUsername("ParentActivity pass : username")
                .start(this);
    }

    @OnClick(R.id.toSubActivity)
    void toSubActivity() {
        SubActivityDispatcher.create()
                .setUsername("SubActivity pass : username")
                .setPassword("SubActivity pass : password")
                .start(this);
    }

    @OnClick(R.id.toParentFragment)
    void toParentFragment () {
        TestFragment build = TestFragmentBuilder.create().setUsername("TestFragment pass : username")
                .build();

        getFragmentManager().beginTransaction()
                .replace(R.id.frag_layout, build)
                .commit();
    }

    @OnClick(R.id.toSubFragment)
    void toSubFragment () {
        SubFragment build = SubFragmentBuilder.create()
                .setPassword("SubFragment pass : password")
                .build();



        getFragmentManager().beginTransaction()
                .replace(R.id.frag_layout,build)
                .commit();
    }
}
