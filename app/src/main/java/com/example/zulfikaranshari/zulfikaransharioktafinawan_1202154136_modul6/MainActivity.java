package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private FirebaseAuth mAuth;
    private String TAG = "com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi firebase
        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();


        //inisialisasi View
        mEmailField = (EditText) findViewById(R.id.emailField);
        mPasswordField = (EditText) findViewById(R.id.passwordField);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnRegister = (Button) findViewById(R.id.btnRegister);


    }

    public void registeruser(View view) {
        //mengambil String email dan password
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        //pengecekkan apakah field email atau password kosong atau tidak
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            //menampilkan toast jika kosong
            Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
            //jika tidak kosong maka akan melakukan pengecekkan jumlah karakter password
        } else {
            if (password.length()>=8){
                //membuat user dengan parameter username dan password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //jika berhasil maka akan menampilkan toast bahwa register berhasil
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(MainActivity.this, "Register Sucess", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                //jika gagal maka akan menampilkan toast bahwa register gagal
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Register failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }else{
                //menampilkan toast jika password kurang dari 8 karakter
                Toast.makeText(MainActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void loginUser(View view) {
        //mengambil email dan password dari edittext
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        //melakukan pengecekkan apakah field email dan password kosong atau tidak
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            //menampilkan toast jika field kosong
            Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            //sign in dengan email dan password
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //jika berhasil maka akan menampilkan toast dan berpindah ke TimeLineActivity
                            if (task.isSuccessful()) {

                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(MainActivity.this, TimelineActivity.class));
                            } else {
                                //jika gagal maka akan menampilkan toast bahwa login gagal
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
        }
    }
}
