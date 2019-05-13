package com.example.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mis_item.R;

public class ValueMainActivity extends AppCompatActivity {
    Button sample1, sample2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_main);
        init();

    }

    private void init() {
        sample1 = findViewById(R.id.sample1);
        sample2 = findViewById(R.id.sample2);

        sample1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValueMainActivity.this,SimpleValueAnimActivity.class);
                startActivity(intent);
            }
        });
        sample2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValueMainActivity.this,ValueAnimatorActivity.class);
                startActivity(intent);
            }
        });
    }
}
