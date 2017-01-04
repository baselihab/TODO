package com.example.baselabdallah.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    /**
     * Varaible representing the list view filled with todo objects
     */
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        //Filling the array with testing text
        final ListViewItem[] items = new ListViewItem[10];
        for(int i=0; i<items.length; i++){
            items[i] = new ListViewItem("Test");
        }
        //Binding the array with the list view through the custom adapter
        CustomAdapter customAdapter = new CustomAdapter(this, R.id.text, items);
        listView.setAdapter(customAdapter);

    }
}
