package com.example.amirkalmoni.ghnauthenticationfinal;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private EditText editTextEmail;
    private Button buttonForgotPassword;
    private Button buttonExit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        progressDialog = new ProgressDialog(this);
        buttonForgotPassword = findViewById(R.id.buttonForgotPassword);




    }


    private void FPW() {

        String email = editTextEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Enter an email address", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Chill....");
        progressDialog.show();

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Email with reset link sent! ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this, "Email not recognized, try again ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view == buttonForgotPassword)
        {
            FPW();
        }




    }
}
