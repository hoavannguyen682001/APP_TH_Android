package com.nguyenvanhoa.app_th_android.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoa.app_th_android.Activity.SinhVien.Profile_SinhVien_Activity;
import com.nguyenvanhoa.app_th_android.Filter.FilterAccountSV;
import com.nguyenvanhoa.app_th_android.Model.Sinhvien;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QLTKSinhvien_adapter extends BaseAdapter implements Filterable {

    private Context context_view;
    public ArrayList<Sinhvien> arrayList, filterList;
    private int layout;
    private FilterAccountSV filter;

    public QLTKSinhvien_adapter(Context context_view, ArrayList<Sinhvien> arrayList, int layout) {
        this.context_view = context_view;
        this.arrayList = arrayList;
        this.layout = layout;
        this.filterList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context_view).inflate(R.layout.row_listview_sinhvien,parent,false);
            holder = new ViewHolder();

            holder.tvEmailSV = (EditText) convertView.findViewById(R.id.tvEmailSV);
            holder.tvMKSV = (EditText) convertView.findViewById(R.id.tvMKSV);
            holder.tvTenSV = (EditText) convertView.findViewById(R.id.tvTenSV);
            holder.ibDelete = (ImageButton) convertView.findViewById(R.id.ibDeleteSV);


            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Sinhvien model = arrayList.get(position);
        String uid = model.getUid();
        String email = model.getEmail();
        String name = model.getName();
        String password = model.getPassword();

        holder.tvEmailSV.setText(email);
        holder.tvMKSV.setText(password);
        holder.tvTenSV.setText(name);
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context_view);
                builder.setTitle ("Delete")
                        .setMessage ("Bạn có chắc là muốn xoá tài khoản này?")
                        .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //begin delete
                            Toast.makeText(context_view, "Đang xoá tài khoản...", Toast.LENGTH_SHORT).show();
                            deleteTaiKhoanSV(model, holder);
                        }
                    })
                        .setNegativeButton("Trở về", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                        .show();
            }
        });

        return convertView;
    }


    private void deleteTaiKhoanSV(Sinhvien model, ViewHolder holder) {
        //get uid uses
        String uid = model.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context_view, "Xoá thành công...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context_view, "Xoá tài khoản không thành công...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterAccountSV(filterList, this);
        }
        return filter;
    }

    public class ViewHolder {
        EditText tvEmailSV, tvMKSV, tvTenSV;
        ImageButton ibEdit, ibDelete;
    }
}
