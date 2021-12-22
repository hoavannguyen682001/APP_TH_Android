package com.nguyenvanhoa.app_th_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoa.app_th_android.Activity.Admin.QLTKGiaoVien_Activity;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.RowListviewGiaovienBinding;

import java.util.ArrayList;
import java.util.List;

public class QLTKGiaovien_adapter extends BaseAdapter {


    private Context context;
    private ArrayList<Giaovien> arrayList;
    private int layout;

    public QLTKGiaovien_adapter(Context context, ArrayList<Giaovien> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
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
            holder.ibEdit = (ImageButton) view.findViewById(R.id.ibEdit);

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

            }
        });
        holder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }


    class ViewHolder {
        TextView tvMK, tvEmail, tvTenGV;
        ImageButton ibDelete, ibEdit;
    }
}
