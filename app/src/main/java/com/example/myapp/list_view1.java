package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class list_view1 extends AppCompatActivity {
    ListView listViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view1);
        listViews=findViewById(R.id.lst);
        final ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("abraham");
        arrayList.add("benon");
        arrayList.add("namanya");
        arrayList.add("MPikireho");
        arrayList.add("Loyce");
        arrayList.add("criscent");
        arrayList.add("sarah");
        arrayList.add("abel");
        arrayList.add("Lauben");
        arrayList.add("kellen");
        arrayList.add("Bridget");
        arrayList.add("namesake");
        arrayList.add("facebooker");
        arrayList.add("watsaPPer");
        arrayList.add("Collins");
        arrayList.add("Elijah");

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listViews.setAdapter(arrayAdapter);
        listViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}

