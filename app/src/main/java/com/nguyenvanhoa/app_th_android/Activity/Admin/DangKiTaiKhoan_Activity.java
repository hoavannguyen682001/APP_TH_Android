package com.nguyenvanhoa.app_th_android.Activity.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoa.app_th_android.Activity.RegisterActivity;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.SinhVienActivity;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityDangkytkBinding;

import java.util.HashMap;

public class DangKiTaiKhoan_Activity extends AppCompatActivity {

    private ActivityDangkytkBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDangkytkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //fire base auth
        firebaseAuth = FirebaseAuth.getInstance();
        //setup progress dialog
        progressDialog = new ProgressDialog( this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.taoTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    String fullName = "", email = "", password = "", confirmPass = "";
    private void validateData() {

        email = binding.edtEmail.getText().toString();
        password = binding.edtMatkhau.getText().toString();
        confirmPass = binding.edtNhaplaiMK.getText().toString();
        fullName = binding.edtTenGv.getText().toString();

        //validate data
        if (TextUtils.isEmpty(fullName)) {
            Toast.makeText(this, "Nhập tên giáo viên...!", Toast.LENGTH_SHORT).show();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches ()){
            Toast.makeText( this,  "Email không hợp lệ...!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText( this, "Nhập mật khẩu...!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(confirmPass)){
            Toast.makeText( this, "Xác nhận mật khẩu...!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DangKiTaiKhoan_Activity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUserInfo() {
        progressDialog.setMessage("Đang lưu thông tin tài khoản...");
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
        hashMap.put("userType", "giangvien");
        hashMap.put("timestamp", timestamp);
        hashMap.put("phoneNumber", "");
        hashMap.put("dob", "");
        hashMap.put("CMND", "");
        hashMap.put("khoa", "");
        //set data to db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess (Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(DangKiTaiKhoan_Activity.this, "Tạo tài khoản thành công...!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), QLTKGiaoVien_Activity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(DangKiTaiKhoan_Activity.this, "Tạo tài khoản thất bại. Lỗi: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
