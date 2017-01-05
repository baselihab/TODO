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
    /**
     * List of items shown in the list
     */
    private ListViewItem[] items= new ListViewItem[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        plus = (ImageView) findViewById(R.id.imageView1);
        mEdit = (EditText) findViewById(R.id.editText1);
        mEdit.requestFocus();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // addValueEventListner that update the list with new values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Get the size of snapshot
                int size = (int) dataSnapshot.getChildrenCount();

                //Initialize array with number of children
                items = new ListViewItem[size];

                //Fill the list
                int childcount=0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    items[childcount]=new ListViewItem(postSnapshot.getKey());
                    childcount++;
                }
                createList(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
            }
        });

        //Listener on the plus image to add new item (initially not checked)
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mEdit.getText().toString().equals("")){
                    // Write a message to the database
                    mDatabase.child(mEdit.getText().toString()).setValue("!check");
                    mEdit.setText("");
                }
            }
        });

    }

    /**
     *
     * @param n binding the array with the list view through the custom adapter
     */

    public void createList (ListViewItem[] n){
        CustomAdapter customAdapter = new CustomAdapter(this, R.id.text, n);
        listView.setAdapter(customAdapter);
    }
}

