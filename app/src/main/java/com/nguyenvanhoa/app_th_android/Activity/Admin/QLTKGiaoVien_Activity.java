package com.nguyenvanhoa.app_th_android.Activity.Admin;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoa.app_th_android.Adapter.QLTKGiaovien_adapter;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityQltkGiaovienBinding;

import java.util.ArrayList;
import java.util.List;

public class QLTKGiaoVien_Activity extends AppCompatActivity {

    private ArrayList<Giaovien> arrayList;
    private QLTKGiaovien_adapter adapter;

    private ActivityQltkGiaovienBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityQltkGiaovienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadTKGiaoVien();

    }

    private void loadTKGiaoVien() {
        arrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance ().getReference( "Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear arraylist before adding data into it
                arrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //get data
                    Giaovien model = ds.getValue(Giaovien.class);
                    if(model.getUserType().equals("giangvien")){
                        //add to arrayist
                        arrayList.add(model);
                    }

                }
                //setup adapter
                adapter = new QLTKGiaovien_adapter(QLTKGiaoVien_Activity.this, arrayList,R.layout.row_listview_giaovien);
                binding.lvGiaovien.setAdapter( adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
