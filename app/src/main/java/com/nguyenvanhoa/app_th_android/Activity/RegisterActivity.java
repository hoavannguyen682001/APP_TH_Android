package com.nguyenvanhoa.app_th_android.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.SinhVienActivity;
import com.nguyenvanhoa.app_th_android.R;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail, edtPass, edtConfirmPass,edtFullName;
    ImageView ivEmail, ivPass, ivConfirmPass,ivFullName;
    TextView tvLogin, tvRegister, tv1;
    float v=0;

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
        setContentView(R.layout.activity_register);
        AnhXa();
        Animation();
        //fire base auth
        firebaseAuth = FirebaseAuth.getInstance();
        //setup progress dialog
        progressDialog = new ProgressDialog( this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    String fullName = "", email = "", password = "", confirmPass = "";
    private void validateData() {

        email = edtEmail.getText().toString();
        password = edtPass.getText().toString();
        confirmPass = edtConfirmPass.getText().toString();
        fullName = edtFullName.getText().toString();

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches ()){
            Toast.makeText( this,  "Email không hợp lệ...!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText( this, "Nhập mật khẩu...!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(confirmPass)){
                Toast.makeText( this, "Xác nhận mật khẩu...!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(fullName)){
                Toast.makeText( this,  "Nhập tên của bạn...!", Toast.LENGTH_SHORT).show();
        }else if (!password.equals(confirmPass)){
                Toast.makeText( this,"Mật khẩu không khớp...!",Toast.LENGTH_SHORT).show();
        }else{
            createUserAccount();
        }
    }

    private void createUserAccount() {
        //show progress
        progressDialog.setMessage ("Đang tạo tài khoản...");
        progressDialog.show();
        //create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess (AuthResult authResult) {
                        //account creation success, now add in firebase realtime database
                        updateUserInfo();
                    }
                    })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //account creating failed
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUserInfo() {
        progressDialog.setMessage("Lưu thông tin người dùng...");
        //timestamp
        Long timestamp = System.currentTimeMillis();
        //get current user uid, since user is registered so we can get now
        String uid = firebaseAuth.getUid();
        //setup data to add in db
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("email", email);
        hashMap.put("name", fullName);
        hashMap.put("password",password);
        hashMap.put("profileImage", "");
        hashMap.put("userType", "sinhvien");
        hashMap.put("timestamp", timestamp);
        hashMap.put("class", "");
        hashMap.put("gender", "");
        hashMap.put("dob", "");
        hashMap.put("nganh", "");
        hashMap.put("khoa", "");
        //set data to db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess (Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công...!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SinhVienActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Tạo tài khoản thất bại. Lỗi: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void AnhXa(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        edtFullName = findViewById(R.id.edtFullName);

        ivEmail = findViewById(R.id.ivEmail);
        ivPass = findViewById(R.id.ivPass);
        ivConfirmPass = findViewById(R.id.ivConfirmPass);
        ivFullName = findViewById(R.id.ivFullName);

        tvLogin = findViewById(R.id.tvLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tv1 = findViewById(R.id.txt1);

    }
    private void Animation(){
        edtEmail.setTranslationX(800);
        edtPass.setTranslationX(800);
        edtConfirmPass.setTranslationX(800);
        ivEmail.setTranslationX(800);
        ivPass.setTranslationX(800);
        ivConfirmPass.setTranslationX(800);
        tvRegister.setTranslationX(800);
        ivFullName.setTranslationX(800);
        edtFullName.setTranslationX(800);


        tv1.setTranslationY(800);
        tvLogin.setTranslationY(800);

        edtEmail.setAlpha(v);
        edtPass.setAlpha(v);
        ivEmail.setAlpha(v);
        ivPass.setAlpha(v);
        tvLogin.setAlpha(v);
        tv1.setAlpha(v);
        tvRegister.setAlpha(v);
        edtConfirmPass.setAlpha(v);
        ivConfirmPass.setAlpha(v);
        edtFullName.setAlpha(v);
        ivFullName.setAlpha(v);

        edtEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        edtPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        edtConfirmPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        edtFullName.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        ivEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        ivPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        ivConfirmPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        ivFullName.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        tvRegister.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();

        tv1.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        tvLogin.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
    }
}