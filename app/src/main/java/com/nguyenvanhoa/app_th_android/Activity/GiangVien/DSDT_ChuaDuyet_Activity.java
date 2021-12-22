package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.Activity.Admin.QLTKGiaoVien_Activity;
import com.nguyenvanhoa.app_th_android.Adapter.DSDeTai_ChuaDuyet_Adapter;
import com.nguyenvanhoa.app_th_android.Adapter.QLTKGiaovien_adapter;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.Model.TTDeTai;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityDsdtChuaDuyetBinding;

import java.util.ArrayList;

public class DSDT_ChuaDuyet_Activity extends AppCompatActivity {

    private ArrayList<TTDeTai> arrayList;
    private DSDeTai_ChuaDuyet_Adapter adapter;

    private ActivityDsdtChuaDuyetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDsdtChuaDuyetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadDSDT_ChuaDuyet();
        binding.edtTimKiemDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    adapter.getFilter().filter(charSequence);
                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadDSDT_ChuaDuyet() {
        arrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance ().getReference( "DanhSachDeTai");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear arraylist before adding data into it
                arrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //get data
                    TTDeTai model = ds.getValue(TTDeTai.class);
                    if(model.getTrangThaiDT().equals("Chờ duyệt")){
                        arrayList.add(model);
                    }
                }
                //setup adapter
                adapter = new DSDeTai_ChuaDuyet_Adapter(DSDT_ChuaDuyet_Activity.this, arrayList,R.layout.row_dsdt_chuaduyet);
                binding.lvDTChuaDuyet.setAdapter( adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}