package com.nguyenvanhoa.app_th_android.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.nguyenvanhoa.app_th_android.R;

public class ForgotPassword_activity extends AppCompatActivity {

    EditText emailQuenMK;
    TextView tvSubmit;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);

        AnhXa();
        //fire base auth
        firebaseAuth = FirebaseAuth.getInstance();
        //setup progress dialog
        progressDialog = new ProgressDialog( this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }
    String email="";
    private void validateData() {
        //get data i.e. email
        email =  emailQuenMK.getText().toString().trim();
        //validate data e.g. shouldn't empty and should be valid format
        if (email.isEmpty()) {
            Toast.makeText(this, "Nhập email...", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher (email).matches ()){
            Toast.makeText(this, "Email không hợp lệ...", Toast.LENGTH_SHORT).show();
        }
        else {
            recoverPassword();
        }
    }

    private void recoverPassword() {
        progressDialog.setMessage("Email xác nhận đang được gửi đến "+email);
        progressDialog.show();

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(ForgotPassword_activity.this, "Đã gửi email xác nhận đến "+email, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(ForgotPassword_activity.this, "Thất bại khi gửi email xác nhận đến "+email, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void AnhXa(){
        emailQuenMK = findViewById(R.id.emailQuenMK);
        tvSubmit = findViewById(R.id.tvSubmit);
    }

}