package com.nguyenvanhoa.app_th_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.R;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail, edtPass, edtConfirmPass,edtFullName;
    ImageView ivEmail, ivPass, ivConfirmPass,ivFullName;
    TextView tvLogin, tvRegister, tv1;
    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        AnhXa();
        Animation();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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