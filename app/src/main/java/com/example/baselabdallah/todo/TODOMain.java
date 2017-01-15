package com.example.baselabdallah.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;

public class TODOMain extends AppCompatActivity {
    /**
     * Varaible representing the list view filled with todo objects
     */
    @BindView(R.id.listview) ListView listView;
    /**
     * The add todo button
     */
    @BindView(R.id.imageView1) ImageView plus;
    /**
     * The title of the todo object
     */
    @BindView(R.id.editText1) EditText mEdit;
    /**
     * Setup database connection
     */
    private DatabaseReference mDatabase;
    /**
     * List of items shown in the list
     */
    private ListViewItem[] items= new ListViewItem[0];
    /**
     * The user id of the current authenticated usser
     */
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        ButterKnife.bind(this);
        mEdit.requestFocus();
        // getting the user id from the intent
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            uid =(String) b.get("uid");
        }
        //Setup database connection
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://todo-49029.firebaseio.com/users/"+uid);

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
    }

    //Listener on the plus image to add new item
    @OnClick(R.id.imageView1)
    public void add(View view) {
        // TODO submit data to server...
        if (!mEdit.getText().toString().equals("")){
            // Write a message to the database
            mDatabase.child(mEdit.getText().toString()).setValue("!");
            mEdit.setText("");
        }
    }
    //Listener to delete items from list on long click
    @OnItemLongClick(R.id.listview)
        public boolean delete(AdapterView<?> parent, View view, int position, long id){
        // TODO submit data to server...
        String key= items[position].getText();
        mDatabase.child(key).removeValue();
        return false;
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

