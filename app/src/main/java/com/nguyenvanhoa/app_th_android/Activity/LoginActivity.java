package com.nguyenvanhoa.app_th_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Activity.GiangVien.GiangVienActivity;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.DangKyDeTai_Activity;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.SinhVienActivity;
import com.nguyenvanhoa.app_th_android.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPass;
    ImageView ivEmail, ivPass;
    TextView tvLogin, tvForgotPass, tvRegister, tvNewUser;
    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        AnhXa();
        Animation();
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GiangVienActivity.class));
            }
        });
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPassword_activity.class));
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