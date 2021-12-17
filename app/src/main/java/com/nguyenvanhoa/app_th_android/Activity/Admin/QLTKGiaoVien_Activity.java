package com.nguyenvanhoa.app_th_android.Activity.Admin;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenvanhoa.app_th_android.Adapter.Giaovien_adapter;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;

public class QLTKGiaoVien_Activity extends AppCompatActivity {
    private TextView txtTen_tk,txtMatkhau;
    private ArrayList<Giaovien> array_giaovien;
    private Giaovien_adapter giaovien_adapter;
    private ListView lv_giaovien;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qltk_giaovien);

        Anhxa();
        giaovien_adapter = new Giaovien_adapter(this, setArrayList(), R.layout.row_listview_giaovien);
        lv_giaovien.setAdapter(giaovien_adapter);
    }

    private void Anhxa() {
        txtTen_tk = (TextView) findViewById(R.id.txt_ten_tk);
        txtMatkhau= (TextView) findViewById(R.id.txt_mk);
        lv_giaovien = (ListView) findViewById(R.id.lv_giaovien);
    }
    private ArrayList setArrayList(){
        array_giaovien = new ArrayList<>();

        array_giaovien.add(new Giaovien("giaovien_1@gmail.com","123"));
        array_giaovien.add(new Giaovien("giaovien_2@gmail.com","123"));
        array_giaovien.add(new Giaovien("giaovien_3@gmail.com","123"));
        array_giaovien.add(new Giaovien("giaovien_4@gmail.com","123"));
        array_giaovien.add(new Giaovien("giaovien_5@gmail.com","123"));
        array_giaovien.add(new Giaovien("giaovien_6@gmail.com","123"));
        array_giaovien.add(new Giaovien("giaovien_7@gmail.com","123"));
        return array_giaovien;
    }
}
