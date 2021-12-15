package com.nguyenvanhoa.app_th_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.nguyenvanhoa.app_th_android.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Profile_SinhVien_Activity extends AppCompatActivity {

    ImageView iconCalendar;
    EditText edtNgaySinh;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile_sinhvien);

        AnhXa();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        iconCalendar.setOnClickListener(new View.OnClickListener() {
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
        iconCalendar = (ImageView) findViewById(R.id.iconCalendar);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
    }

}