package com.nguyenvanhoa.app_th_android.Activity.SinhVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityProfileSinhvienBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Profile_SinhVien_Activity extends AppCompatActivity {
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;

    private ActivityProfileSinhvienBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityProfileSinhvienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        binding.edtNgaySinh.setOnClickListener(new View.OnClickListener() {
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
                        binding.edtNgaySinh.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                    }
                }, nam, thang , ngay);
                datePickerDialog.show();
            }
        });
        loadUserInfo();
        binding.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserInfo();
                progressDialog.setMessage("Đang tải lại dữ liệu");
                progressDialog.show();
                loadUserInfo();
            }
        });
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
                        String classUser = "" + snapshot.child("class").getValue();
                        String dob = "" + snapshot.child("dob").getValue();
                        String gender = "" + snapshot.child("gender").getValue();
                        String nganh = "" + snapshot.child("nganh").getValue();
                        String khoa = "" + snapshot.child("khoa").getValue();

                        binding.tvName.setText(name);
                        binding.edtEmail.setText(email);

                        if(classUser !=""){
                            binding.edtLop.setText(classUser);
                        }
                        if(gender !=""){
                            binding.edtGioiTinh.setText(gender);
                        }
                        if(dob !=""){
                            binding.edtNgaySinh.setText(dob);
                        }
                        if(nganh !=""){
                            binding.edtNganh.setText(nganh);
                        }
                        if(khoa !=""){
                            binding.edtKhoa.setText(khoa);
                        }

                        Glide.with(Profile_SinhVien_Activity.this)
                                .load(profileImage)
                                .placeholder(R.drawable.ic_baseline_person_24)
                                .into(binding.ivPerson);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        progressDialog.dismiss();
    }

    private void updateUserInfo(){
        progressDialog.setMessage("Đang cập nhật thông tin cá nhân...");
        progressDialog.show();

        String classUser = binding.edtLop.getText().toString();
        String dob =  binding.edtNgaySinh.getText().toString();
        String gender =  binding.edtGioiTinh.getText().toString();
        String nganh = binding.edtNganh.getText().toString();
        String khoa = binding.edtKhoa.getText().toString();

        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("class", classUser);
        hashMap.put("gender", gender);
        hashMap.put("dob", dob);
        hashMap.put("nganh", nganh);
        hashMap.put("khoa", khoa);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid())
                .updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(Profile_SinhVien_Activity.this, "Cập nhật thông tin thành công...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Profile_SinhVien_Activity.this, "Cập nhật thông tin không thành công...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}