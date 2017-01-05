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
    /**
     * Setup database connection
     */
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        plus = (ImageView) findViewById(R.id.imageView1);
        mEdit = (EditText) findViewById(R.id.editText1);
        mEdit.requestFocus();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Initialize array with dummy counter
        final ListViewItem[] items = new ListViewItem[20];

        //Fill the list with dummy data
        for (int i=0; i<items.length;i++){
            items[i] = new ListViewItem("ff");
        }


        // addValueEventListner that update the list with new values

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int childcount=0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    items[childcount]=new ListViewItem(postSnapshot.getKey().toString());
                    childcount++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        //Listener on the plus image to add new item (initially not checked)
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
                mDatabase.child(mEdit.getText().toString()).setValue("!check");
                mEdit.setText("");
            }
        });


        //Binding the array with the list view through the custom adapter
        CustomAdapter customAdapter = new CustomAdapter(this, R.id.text, items);
        listView.setAdapter(customAdapter);

    }
}
