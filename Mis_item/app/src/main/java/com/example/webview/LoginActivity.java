package com.example.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mis_item.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout start_ctrl;

    private EditText usename, password;
    private Button login;

    private String valueFromIndex;
    private TextView testIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getIntentData();


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        start_ctrl = findViewById(R.id.start_ctrl);
        //设置动画，从自身位置的最下端向上滑动了自身的高度，持续时间为500ms
//        final TranslateAnimation ctrlAnimation = new TranslateAnimation(
//                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
//                TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
//        ctrlAnimation.setDuration(200l);     //设置动画的过渡时间
//        start_ctrl.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                start_ctrl.setVisibility(View.VISIBLE);
//                start_ctrl.startAnimation(ctrlAnimation);
//            }
//        }, 2000);

        bindInit();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("fromIndex");
        if (data != null && !data.equals("")) {
            valueFromIndex = data.getString("value");
            if(valueFromIndex != null && !valueFromIndex.equals("")) {
                testIndex.setText(valueFromIndex);
            }
            Log.v("testIndex", valueFromIndex);
        }

    }

    private void bindInit() {
        usename = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login_btn);

        testIndex = findViewById(R.id.testIndex);


        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                Bundle data = new Bundle();
                data.putString("username", usename.getText().toString());
                data.putString("password", password.getText().toString());
                Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
                intent.putExtra("user", data);
                startActivity(intent);
                break;
        }
    }

}
