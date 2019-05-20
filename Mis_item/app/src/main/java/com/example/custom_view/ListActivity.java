package com.example.custom_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.mis_item.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        listView = (MyListView) findViewById(R.id.listView1);
//        listView = (ListView) findViewById(R.id.listView1);
        for (int i = 0; i < 3; i++) {
            //定义一个临时的hashMap
            HashMap<String, String> hashMap = new HashMap<String, String>();
            //存入姓名键值对
            hashMap.put("user_name", "user_" + i);
            //存入ID
            hashMap.put("user_id", "100" + i);
            //讲hashMap存入List
            list.add(hashMap);
        }

        SimpleAdapter listAdpter = new SimpleAdapter(
                this,
                list,
                R.layout.user,
                new String[]{"user_name", "user_id"},
                new int[]{R.id.user_name, R.id.user_id}
        );

        listView.setAdapter(listAdpter);

    }
}
