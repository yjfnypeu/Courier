package org.lzh.framework.courier;

import android.os.Bundle;
import android.view.View;

import com.lzh.courier.annoapi.Field;
import com.lzh.courier.annoapi.Params;


/**
 * @author Administrator
 */
@Params(fields = {
        @Field(name = "password", type = String.class, doc = "用户密码")
})
public class SubFragment extends TestFragment {
    public static final String TAG = SubFragment.class.getCanonicalName();
    SubFragmentArgsData requestData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData = SubFragmentArgsData.getArguments(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        password.setText(requestData.getPassword());
    }
}
