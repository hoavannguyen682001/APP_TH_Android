package com.nguyenvanhoa.app_th_android.Activity.SinhVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.nguyenvanhoa.app_th_android.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Profile_SinhVien_Activity extends AppCompatActivity {

    EditText edtNgaySinh;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_sinhvien);

        AnhXa();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Profile_SinhVien_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        edtNgaySinh.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                    }
                }, nam, thang , ngay);
                datePickerDialog.show();
            }
        });
    }

    private void AnhXa(){
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
    }

}