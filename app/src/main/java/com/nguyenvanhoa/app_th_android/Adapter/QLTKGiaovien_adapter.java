package com.nguyenvanhoa.app_th_android.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoa.app_th_android.Activity.Admin.QLTKGiaoVien_Activity;
import com.nguyenvanhoa.app_th_android.Filter.FilterAccountGiaoVien;
import com.nguyenvanhoa.app_th_android.Filter.FilterAccountSV;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.RowListviewGiaovienBinding;

import java.util.ArrayList;
import java.util.List;

public class QLTKGiaovien_adapter extends BaseAdapter implements Filterable {


    private Context context;
    public ArrayList<Giaovien> arrayList, filterList;
    private int layout;
    private FilterAccountGiaoVien filter;

    public QLTKGiaovien_adapter(Context context, ArrayList<Giaovien> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
        this.filterList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        
        ViewHolder holder;
        if(view == null){
            view  = LayoutInflater.from(context).inflate(R.layout.row_listview_giaovien, viewGroup, false);

            holder = new ViewHolder();
            holder.tvEmail = (TextView) view.findViewById(R.id.tvEmailGV);
            holder.tvMK = (TextView) view.findViewById(R.id.tvMK);
            holder.tvTenGV = (TextView) view.findViewById(R.id.tvTenGV);
            holder.ibDelete = (ImageButton) view.findViewById(R.id.ibDelete);


            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Giaovien model = arrayList.get(i);
        String uid = model.getUid();
        String email = model.getEmail();
        String name = model.getName();
        String password = model.getPassword();
        String profileImage = model.getProfileImage();
        String userType = model.getUserType();
        String timestamp = String.valueOf(model.getTimestamp());
        String phoneNumber = model.getPhoneNumber();
        String dob = model.getDob();
        String CMND = model.getCMND();
        String khoa = model.getKhoa();

        holder.tvEmail.setText(email);
        holder.tvMK.setText(password);
        holder.tvTenGV.setText(name);
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle ("Delete")
                        .setMessage ("Bạn có chắc là muốn xoá tài khoản này?")
                        .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //begin delete
                                Toast.makeText(context, "Đang xoá tài khoản...", Toast.LENGTH_SHORT).show();
                                deleteTaiKhoanGV(model, holder);
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
        return view;
    }

    private void deleteTaiKhoanGV(Giaovien model, ViewHolder holder) {
        //get uid uses
        String uid = model.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Xoá thành công...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Xoá tài khoản không thành công...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterAccountGiaoVien(filterList, this);
        }
        return filter;
    }


    class ViewHolder {
        TextView tvMK, tvEmail, tvTenGV;
        ImageButton ibDelete, ibEdit;
    }
}
