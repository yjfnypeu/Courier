package org.lzh.framework.courier;

import android.os.Bundle;

import com.lzh.courier.annoapi.Field;
import com.lzh.courier.annoapi.Params;


@Params(fields = {
        @Field(name = "password",type = String.class)
})
public class SubActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SubActivity_Dispatcher.ArgsData data = SubActivity_Dispatcher.getArguments(getIntent());
        password.setText(data.getPassword());
    }
}
