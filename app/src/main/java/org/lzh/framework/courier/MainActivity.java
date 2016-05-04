package org.lzh.framework.courier;

import android.app.Activity;
import android.os.Bundle;

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
        EmptyActivity_Dispatcher.create().requestCode(-1).start(MainActivity.this);
    }

    @OnClick(R.id.toParamsActivity)
    void toParamaActivity () {
        ParamsActivity_Dispatcher.create()
                .setUsername("123456")
                .setPassword("111111")
                .requestCode(-1)
                .start(this);
    }

    @OnClick(R.id.toParentActivity)
    void toParentActivity() {
//        ParentActivity_Dispatcher.create()
//                .setUsername("ParentActivity pass : username")
//                .start(this);
    }

    @OnClick(R.id.toSubActivity)
    void toSubActivity() {
        SubActivity_Dispatcher.create().setUsername("SubActivity pass : username")
                .setPassword("SubActivity pass : password")
                .start(this);
    }

    @OnClick(R.id.toParentFragment)
    void toParentFragment () {
//        TestFragment build = TestFragment_Builder.create().setUsername("TestFragment pass : username")
//                .build();
//
//        getFragmentManager().beginTransaction()
//                .replace(R.id.frag_layout, build)
//                .commit();
        Bundle bundle = new Bundle();
    }

    @OnClick(R.id.toSubFragment)
    void toSubFragment () {
        SubFragment build = SubFragment_Builder.create().setUsername("SubFragment pass : username")
                .setPassword("SubFragment pass : password")
                .build();
        getFragmentManager().beginTransaction()
                .replace(R.id.frag_layout,build)
                .commit();
    }
}
