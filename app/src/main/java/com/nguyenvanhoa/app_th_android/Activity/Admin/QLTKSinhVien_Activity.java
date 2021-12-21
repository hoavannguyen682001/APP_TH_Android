package com.nguyenvanhoa.app_th_android.Activity.Admin;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenvanhoa.app_th_android.Adapter.QLTKSinhvien_adapter;
import com.nguyenvanhoa.app_th_android.Model.Sinhvien;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;

public class QLTKSinhVien_Activity extends AppCompatActivity {

    private TextView txtTk_sv,txtMk_sv;
    private ArrayList<Sinhvien> array_sinhvien;
    private QLTKSinhvien_adapter sinhvien_adapter;
    private ListView lv_sinhvien;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qltk_sinhvien);

        Anhxa();
        sinhvien_adapter = new QLTKSinhvien_adapter(this, setArrayList(), R.layout.row_listview_sinhvien);
        lv_sinhvien.setAdapter(sinhvien_adapter);
    }

    private void Anhxa() {
        txtTk_sv = (TextView) findViewById(R.id.txt_tk_sinhvien);
        txtMk_sv= (TextView) findViewById(R.id.txt_mk_sinhvien);
        lv_sinhvien = (ListView) findViewById(R.id.lv_sinhvien);
    }
    private ArrayList setArrayList(){
        array_sinhvien = new ArrayList<>();

        array_sinhvien.add(new Sinhvien("sinhvien_1@gmail.com","sinhvien123"));
        array_sinhvien.add(new Sinhvien("sinhvien_2@gmail.com","sinhvien123"));
        array_sinhvien.add(new Sinhvien("sinhvien_3@gmail.com","sinhvien123"));
        array_sinhvien.add(new Sinhvien("sinhvien_4@gmail.com","sinhvien123"));
        array_sinhvien.add(new Sinhvien("sinhvien_5@gmail.com","sinhvien123"));
        array_sinhvien.add(new Sinhvien("sinhvien_6@gmail.com","sinhvien123"));
        array_sinhvien.add(new Sinhvien("sinhvien_7@gmail.com","sinhvien123"));
        return array_sinhvien;
    }
}
