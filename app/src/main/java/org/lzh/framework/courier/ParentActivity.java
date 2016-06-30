package org.lzh.framework.courier;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.lzh.courier.annoapi.Field;
import com.lzh.courier.annoapi.Params;

import butterknife.Bind;
import butterknife.ButterKnife;

@Params(fields = {
        @Field(name = "username", type = String.class)
})
public class ParentActivity extends Activity {

    @Bind(R.id.username)
    Button username;
    @Bind(R.id.password)
    Button password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        ButterKnife.bind(this);

        ParentActivityArgsData data = ParentActivityArgsData.getArguments(getIntent());
        username.setText(data.getUsername());

    }
}
