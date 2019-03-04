package com.songtaeheon.posting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.songtaeheon.posting.DataModel.UserInfo;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "TAGMainActivity";
    private FirebaseAuth mAuth;
    public static UserInfo currentUser;

    Button buttonToShare;

    EditText editText_email;
    EditText editText_password;
    Button button1;

    EditText editText_email2;
    EditText editText_password2;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity : onCreate");
        mAuth = FirebaseAuth.getInstance();
        currentUser = new UserInfo();


        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "회원가입버튼 클릭");
                createUser(editText_email.getText().toString(), editText_password.getText().toString());
            }
        });

        editText_email2 = findViewById(R.id.editText_email2);
        editText_password2 = findViewById(R.id.editText_password2);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "로그인버튼 클릭");
                signIn(editText_email2.getText().toString(), editText_password2.getText().toString());
            }
        });

        buttonToShare = findViewById(R.id.button);
        buttonToShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "ButtonToShare is clicked : move to ShareActivity");
                moveToShareActivity();
            }
        });

    }

    private void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            buttonToShare.setText("signed");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        currentUser.eMail = email;
    }


    public void moveToShareActivity(){
        Intent intent = new Intent(this, ShareActivity.class);
        startActivity(intent);
    }


}
