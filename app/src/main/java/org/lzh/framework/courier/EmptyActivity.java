package org.lzh.framework.courier;

import android.app.Activity;
import android.os.Bundle;

import com.lzh.courier.annoapi.Params;
// 只添加@Params,
@Params
public class EmptyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
