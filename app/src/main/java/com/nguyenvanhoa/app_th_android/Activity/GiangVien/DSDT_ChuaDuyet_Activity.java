package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.HashMap;

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

        binding.lvDTChuaDuyet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TTDeTai model = arrayList.get(i);
                dialogCustom(model);
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
    private void dialogCustom(TTDeTai model){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_ttdetai);
        dialog.setCanceledOnTouchOutside(false);

        TextView tvTenDT, tvLinhVucDT, tvCNDT, tvSVTG, tvTGTH, tvHuy, tvDuyet;

        tvTenDT = dialog.findViewById(R.id.tvTenDT);
        tvLinhVucDT = dialog.findViewById(R.id.tvLinhVucDT);
        tvCNDT = dialog.findViewById(R.id.tvCNDT);
        tvSVTG = dialog.findViewById(R.id.tvSinhVienTG);
        tvTGTH = dialog.findViewById(R.id.tvTGTH);
        tvHuy = dialog.findViewById(R.id.tvHuy);
        tvDuyet = dialog.findViewById(R.id.tvDuyet);

        tvTenDT.setText(model.getTenDT());
        tvLinhVucDT.setText(model.getLinhvuc());
        tvCNDT.setText(model.getTenCNDT());
        tvSVTG.setText(model.getTenTV());
        tvTGTH.setText(model.getTgThucHien() + " (Tuần)");

        tvHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tvDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("trangThaiDT", "Đã duyệt");

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DanhSachDeTai");
                ref.child(model.getUid())
                        .updateChildren(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(DSDT_ChuaDuyet_Activity.this, "Đã duyệt đề tài "+ model.getTenDT(), Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialog.dismiss();
                                Toast.makeText(DSDT_ChuaDuyet_Activity.this, "Duyệt đề tài không thành công ", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        dialog.show();
    }
}