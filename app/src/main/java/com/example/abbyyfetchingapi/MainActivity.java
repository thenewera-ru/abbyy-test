package com.example.abbyyfetchingapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private EditText email;
    private EditText pass;
    private Button btnLogin;


    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.login_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPass = pass.getText().toString().trim();
                boolean _ok = true;
                if (TextUtils.isEmpty(mEmail)) {
                    email.setError("Required field...");
                    _ok = false;
                }
                if (TextUtils.isEmpty(mPass)) {
                    pass.setError("Required field...");
                    _ok = false;
                }
                if (!_ok) {
                    return;
                } else {
                    mDialog.setMessage("Processing...");
                    mDialog.show();
                    mAuth.signInWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                mDialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                                mDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });


    }
}
