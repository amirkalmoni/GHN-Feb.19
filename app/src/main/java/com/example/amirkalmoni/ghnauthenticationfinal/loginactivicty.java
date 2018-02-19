package com.example.amirkalmoni.ghnauthenticationfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivicty extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView textViewForgotPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivicty);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivicty.class ));

        }




        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTexPassword);
        buttonSignIn = findViewById(R.id.buttonLogin);
        textViewSignup = findViewById(R.id.textViewSignUp);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);

    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Enter an email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //email is empty
            Toast.makeText(this,"Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Chill....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivicty.class ));

                }
                else{

                    Toast.makeText(loginactivicty.this,"Wrong Password or Email",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();
            startActivity(new Intent(this, SignUpActivity.class ));
        }

        if(view == textViewForgotPassword){
            finish();
            startActivity(new Intent(this, ForgotPasswordActivity.class ));
        }

    }
}
