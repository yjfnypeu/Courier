package org.lzh.framework.courier;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lzh.courier.annoapi.Field;
import com.lzh.courier.annoapi.Params;

import butterknife.Bind;
import butterknife.ButterKnife;
// 含带参数的跳转。
@Params(fields = {
        @Field(name = "username",type = String.class,doc = "用户名",defValue = "admin"),
        @Field(name = "password",type = String.class,doc = "密码"),
})
public class ParamsActivity extends Activity {

    @Bind(R.id.username)
    TextView username;
    @Bind(R.id.password)
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
        ButterKnife.bind(this);
        // 获取传参
        ParamsActivity_Dispatcher.ArgsData data = ParamsActivity_Dispatcher.getArguments(getIntent());
        username.setText(data.getUsername());
        password.setText(data.getPassword());

    }
}
