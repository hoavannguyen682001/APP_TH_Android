package com.nguyenvanhoa.app_th_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nguyenvanhoa.app_th_android.R;

public class DangKyDeTai_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dang_ky_de_tai);
    }
}