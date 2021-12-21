package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.Profile_SinhVien_Activity;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityProfileGiangVienBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Profile_GiangVien_Activity extends AppCompatActivity {

    private ActivityProfileGiangVienBinding binding;
    private SimpleDateFormat simpleDateFormat;
    private Calendar calendar;
    private FirebaseAuth firebaseAuth;

    EditText edtNgaySinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityProfileGiangVienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadDateOfBirth();
            }
        });
        loadUserInfo();
    }

    private void loadDateOfBirth() {
        calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        binding.edtNgaySinh.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                    }
                }, nam, thang , ngay);
        datePickerDialog.show();
    }

    private void loadUserInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String email = "" + snapshot.child("email").getValue();
                        String name = "" + snapshot.child("name").getValue();
                        String profileImage = "" + snapshot.child("profileImage").getValue();
                        String timestamp = "" + snapshot.child("timestamp").getValue();
                        String uid = "" + snapshot.child("uid").getValue();
                        String userType = "" + snapshot.child("userType").getValue();
                        String CMND = "" + snapshot.child("CMND").getValue();
                        String dob = "" + snapshot.child("dob").getValue();
                        String phoneNumber = "" + snapshot.child("phoneNumber").getValue();
                        String khoa = "" + snapshot.child("khoa").getValue();

                        binding.tvName.setText(name);
                        binding.edtEmail.setText(email);

                        if(CMND !=""){
                            binding.edtCMDN.setText(CMND);
                        }
                        if(phoneNumber !=""){
                            binding.edtSDT.setText(phoneNumber);
                        }
                        if(dob !=""){
                            binding.edtNgaySinh.setText(dob);
                        }
                        if(khoa !=""){
                            binding.edtKhoa.setText(khoa);
                        }

                        Glide.with(Profile_GiangVien_Activity.this)
                                .load(profileImage)
                                .placeholder(R.drawable.ic_baseline_person_24)
                                .into(binding.ivPerson);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}