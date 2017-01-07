package com.example.baselabdallah.todo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    /**
     * Firebase authentication
     */
    private FirebaseAuth mAuth;
    /**
     * Firebase authentication
     */
    private FirebaseAuth.AuthStateListener mAuthListener;
    /**
     * Edit text for entering email
     */
    private EditText email;
    /**
     * Edit text for entering password
     */
    private EditText password;
    /**
     * Button to start signing in
     */
    private Button signin;
    /**
     * Button to start registeration
     */
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.sign_in);
        register = (Button) findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        //Listener to check the authentication state (Signed in or Signed out)
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in, direct to the main activity with an extra
                    // variable which is the user id
                    System.out.println("onAuthStateChanged:signed_in:" + user.getUid());
                    Intent next=new Intent(Login.this, TODOMain.class);
                    next.putExtra("uid", user.getUid());
                    startActivity(next);
                } else {
                    // User is signed out
                    System.out.println("onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        //Sign in button on click listener
        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                signin(email.getText().toString(), password.getText().toString());
            }
        });
        //Regesteration button on click listener
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                register(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /**
     * Handle signing in process
     * @param email String email
     * @param password String password
     */
    public void signin (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println( "signInWithEmail:onComplete:" + task.isSuccessful());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    /**
     * Handle regesteration process
     * @param email String email
     * @param password String password
     */
    public void register (String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("createUserWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}
