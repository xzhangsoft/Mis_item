package com.example.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mis_item.R;

import java.util.ArrayList;
import java.util.List;

public class LayoutAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_anim);

        ListView listView = findViewById(R.id.listview);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("layoutAnimation" + i);
        }
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1
        ,list);
        listView.setAdapter(arrayAdapter);

        LayoutAnimationController lac= new LayoutAnimationController(AnimationUtils.loadAnimation(this,R.anim.anim_layout_list));
        lac.setOrder(LayoutAnimationController.ORDER_REVERSE);


        listView.setLayoutAnimation(lac);
        listView.startLayoutAnimation();
    }
}
