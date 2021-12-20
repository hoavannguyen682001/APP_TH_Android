package com.nguyenvanhoa.app_th_android.Activity.GiangVien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Adapter.DSDeTai_DaDK_Adapter;
import com.nguyenvanhoa.app_th_android.Model.TTDeTai;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;

public class DSDT_DaDK_Activity extends AppCompatActivity {

    private ListView lvTTDT;
    private ArrayList<TTDeTai> arrayList;
    private DSDeTai_DaDK_Adapter adapter;

    private TextView tvTenDT, tvTenCN, tvNgayDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dsdt_da_dk);

        AnhXa();

        setArrayList();

        adapter = new DSDeTai_DaDK_Adapter(this, R.layout.row_dsdt_dadk,arrayList);
        lvTTDT.setAdapter(adapter);

    }

    private void AnhXa(){
        tvTenDT = findViewById(R.id.tenDT);
        tvTenCN = findViewById(R.id.tenCN);
        tvNgayDK = findViewById(R.id.ngayDK);
        lvTTDT = findViewById(R.id.lvTTDT);
    }
    private void setArrayList(){
        arrayList = new ArrayList<>();

        arrayList.add(new TTDeTai("De tai 1","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 1","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 1","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 1","Nguyen van A","17/12/2021"));
        arrayList.add(new TTDeTai("De tai 1","Nguyen van A","17/12/2021"));
    }
}