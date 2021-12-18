package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Adapter.TTDeTai_ChuaDuyet_Adapter;
import com.nguyenvanhoa.app_th_android.Adapter.TTDeTai_DaDK_Adapter;
import com.nguyenvanhoa.app_th_android.Model.TTDeTai;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;

public class DSDT_ChuaDuyet_Activity extends AppCompatActivity {
    private ListView lvTTDT_CD;
    private ArrayList<TTDeTai> arrayList;
    private TTDeTai_ChuaDuyet_Adapter adapter;

    private TextView tvTenDT_CD, tvTenCN_CD, tvNgayDK_CD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dsdt_chua_duyet);

        AnhXa();

        setArrayList();

        adapter = new TTDeTai_ChuaDuyet_Adapter(this, R.layout.row_dsdt_chuaduyet,arrayList);
        lvTTDT_CD.setAdapter(adapter);

    }

    private void AnhXa(){
        tvTenDT_CD = findViewById(R.id.tenDT_CD);
        tvTenCN_CD = findViewById(R.id.tenCN_CD);
        tvNgayDK_CD = findViewById(R.id.ngayDK_CD);
        lvTTDT_CD = findViewById(R.id.lvDTChuaDuyet);
    }
    private void setArrayList(){
        arrayList = new ArrayList<>();

        arrayList.add(new TTDeTai("De tai 2","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 2","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 2","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 2","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 2","Nguyen van A","17/12/2021"));
    }
}