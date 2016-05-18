package org.lzh.framework.courier;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lzh.courier.annoapi.Field;
import com.lzh.courier.annoapi.Params;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
@Params(
        inherited = false,
        fields = {
        @Field(name = "username", type = CharSequence.class, defValue = "\"username\"",doc = "用户名")
}
)
public class TestFragment extends Fragment {

    @Bind(R.id.username)
    Button username;
    @Bind(R.id.password)
    Button password;

    TestFragment_Builder.ArgsData requestData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData = TestFragment_Builder.getArguments(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_parent, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        username.setText(requestData.getUsername());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
