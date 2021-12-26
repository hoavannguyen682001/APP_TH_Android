package com.nguyenvanhoa.app_th_android.Activity.SinhVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityThongTinDeTaiBinding;

public class ThongTinDeTai_Activity extends AppCompatActivity {

    private ActivityThongTinDeTaiBinding binding;
    public static String uid;
    FirebaseAuth firebaseAuth;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityThongTinDeTaiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        loadIDbyUID();

        if(TextUtils.isEmpty(uid)){
            Toast.makeText(ThongTinDeTai_Activity.this, "Bạn chưa đăng ký đề tài...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadTTDeTai() ;
        }

//        Toast.makeText(ThongTinDeTai_Activity.this, "ababa "+id, Toast.LENGTH_SHORT).show();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void loadTTDeTai() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DanhSachDeTai");
        ref.child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String tenDT = "" + snapshot.child("tenDT").getValue();
                        String tenCNDT = "" + snapshot.child("tenCNDT").getValue();
                        String tenTV = "" + snapshot.child("tenTV").getValue();
                        String tenGV = "" + snapshot.child("tenGV").getValue();
                        String trangThaiDT = "" + snapshot.child("trangThaiDT").getValue();

                        binding.tvTenDT.setText(tenDT);
                        binding.tvChuNhiemDT.setText(tenCNDT);
                        binding.tvSinhVienTG.setText(tenTV);
                        binding.tvGiaoVienHD.setText(tenGV);
                        binding.tvTrangThaiDT.setText(trangThaiDT);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private void loadIDbyUID(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DanhSachDeTai");
        ref.child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        uid = (String) snapshot.child("uid").getValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}