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

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivicty.class ));

        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = findViewById(R.id.buttonSignup);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTexPassword);

        textViewSignin = findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
        buttonRegister.clearFocus();

    }

    private void registerUser(){
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

        progressDialog.setMessage("Registering User....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //USER IS SUCCESSFULLY REGISTERED AND CAN LOGIN
                    Toast.makeText(SignUpActivity.this, "User is Registered Successfully ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }


                else {
                    Toast.makeText(SignUpActivity.this,"User is not registered, try again",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view == buttonRegister){
            registerUser();
        }

        if(view ==textViewSignin){
            startActivity(new Intent(this,loginactivicty.class));

        }

    }
}

