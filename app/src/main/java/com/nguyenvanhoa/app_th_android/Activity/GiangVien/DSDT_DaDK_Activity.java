package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.Adapter.DSDeTai_ChuaDuyet_Adapter;
import com.nguyenvanhoa.app_th_android.Adapter.DSDeTai_DaDK_Adapter;
import com.nguyenvanhoa.app_th_android.Model.TTDeTai;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityDsdtDaDkBinding;

import java.util.ArrayList;

public class DSDT_DaDK_Activity extends AppCompatActivity {

    private ArrayList<TTDeTai> arrayList;
    private DSDeTai_DaDK_Adapter adapter;
    private ActivityDsdtDaDkBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDsdtDaDkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadDanhSachDT();

        binding.edtTimKiemDK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    adapter.getFilter().filter(charSequence);
                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadDanhSachDT() {
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
                    if(model.getTrangThaiDT().equals("Đã duyệt")){
                        arrayList.add(model);
                    }
                }
                //setup adapter
                adapter = new DSDeTai_DaDK_Adapter(DSDT_DaDK_Activity.this, R.layout.row_dsdt_dadk,arrayList);
                binding.lvTTDT.setAdapter( adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}