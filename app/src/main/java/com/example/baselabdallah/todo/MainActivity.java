package com.example.baselabdallah.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    /**
     * Varaible representing the list view filled with todo objects
     */
    private ListView listView;
    /**
     * The add todo button
     */
    private ImageView plus;
    /**
     * The title of the todo object
     */
    private EditText mEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        plus = (ImageView) findViewById(R.id.imageView1);
        mEdit = (EditText) findViewById(R.id.editText1);
        final ListViewItem[] items = new ListViewItem[1];
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(mEdit.getText().toString());
                myRef.setValue(false);
                mEdit.setText("");
            }
        });
        for (int i=0; i<items.length;i++){
            items[i] = new ListViewItem("ff");
        }

        //Binding the array with the list view through the custom adapter
        CustomAdapter customAdapter = new CustomAdapter(this, R.id.text, items);
        listView.setAdapter(customAdapter);

    }
}
