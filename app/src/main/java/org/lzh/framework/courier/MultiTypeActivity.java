package org.lzh.framework.courier;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lzh.courier.annoapi.Field;
import com.lzh.courier.annoapi.FieldType;
import com.lzh.courier.annoapi.Params;

import org.lzh.framework.courier.model.User;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

@Params(fields = {
        @Field(name = "admin", type = User.class),
        @Field(name = "listUser", type = User.class, fieldType = FieldType.list),
        @Field(name = "arrayUser", type = User.class, fieldType = FieldType.array),
        @Field(name = "setUser", type = User.class, fieldType = FieldType.set)
})
public class MultiTypeActivity extends Activity {

    @Bind(R.id.userInfos)
    TextView userInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);
        ButterKnife.bind(this);
        MultiTypeActivityArgsData arguments = MultiTypeActivityArgsData.getArguments(getIntent());
        StringBuffer buffer = new StringBuffer();
        buffer.append("admin:" + arguments.getAdmin())
                .append("\n")
                .append("listUser:" + arguments.getListUser())
                .append("\n")
                .append("arrsyUser:" + Arrays.toString(arguments.getArrayUser()));
        userInfos.setText(buffer.toString());
    }
}
