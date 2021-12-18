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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.Activity.Admin.AdminActivity;
import com.nguyenvanhoa.app_th_android.Activity.GiangVien.GiangVienActivity;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.DangKyDeTai_Activity;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.SinhVienActivity;
import com.nguyenvanhoa.app_th_android.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPass;
    ImageView ivEmail, ivPass;
    TextView tvLogin, tvForgotPass, tvRegister, tvNewUser;
    float v=0;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        AnhXa();
        Animation();

        //fire base auth
        firebaseAuth = FirebaseAuth.getInstance();
        //setup progress dialog
        progressDialog = new ProgressDialog( this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPassword_activity.class));
            }
        });
    }

    String email="",password="";
    private void validateData() {
        email = edtEmail.getText().toString();
        password = edtPass.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches ()){
            Toast.makeText( this,  "Email không hợp lệ...!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText( this, "Nhập mật khẩu...!", Toast.LENGTH_SHORT).show();
        }else {
            loginUser();
        }
    }

    private void loginUser() {
        progressDialog.setMessage("Đang đăng nhập...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        checkUser();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Đăng nhập không thành công... ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không chính xác...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            //check in db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference( "Users");
        ref.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userType = "" + snapshot.child("userType").getValue();
                        //check user type
                        if (userType.equals("sinhvien")) {
                            //this is simple user, open user dashboard
                            startActivity(new Intent(LoginActivity.this, SinhVienActivity.class));
                            finish();
                        }
                        if (userType.equals("admin")){
                            startActivity(new Intent(  LoginActivity.this, AdminActivity.class));
                            finish();
                        }
                        if(userType.equals("giangvien")){
                            startActivity(new Intent(  LoginActivity.this, GiangVienActivity.class));
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void AnhXa(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);

        ivEmail = findViewById(R.id.ivEmail);
        ivPass = findViewById(R.id.ivPass);

        tvLogin = findViewById(R.id.txtLogin);
        tvForgotPass = findViewById(R.id.txtForgotPass);
        tvRegister = findViewById(R.id.txtRegister);
        tvNewUser = findViewById(R.id.txtNewUser);

    }
    private void Animation(){
        edtEmail.setTranslationX(800);
        edtPass.setTranslationX(800);
        ivEmail.setTranslationX(800);
        ivPass.setTranslationX(800);
        tvLogin.setTranslationX(800);
        tvForgotPass.setTranslationX(800);

        tvNewUser.setTranslationY(800);
        tvRegister.setTranslationY(800);

        edtEmail.setAlpha(v);
        edtPass.setAlpha(v);
        ivEmail.setAlpha(v);
        ivPass.setAlpha(v);
        tvLogin.setAlpha(v);
        tvNewUser.setAlpha(v);
        tvRegister.setAlpha(v);
        tvForgotPass.setAlpha(v);

        edtEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        edtPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        ivEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        ivPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        tvLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        tvForgotPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(650).start();

        tvNewUser.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(650).start();
        tvRegister.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(650).start();
    }
}