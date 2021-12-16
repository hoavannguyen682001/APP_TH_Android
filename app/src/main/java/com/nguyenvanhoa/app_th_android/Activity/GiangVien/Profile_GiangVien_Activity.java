package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.nguyenvanhoa.app_th_android.R;

public class Profile_GiangVien_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile_giang_vien);
    }
}