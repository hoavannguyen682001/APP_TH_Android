package com.nguyenvanhoa.app_th_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Model.Sinhvien;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;
import java.util.List;

public class QLTKSinhvien_adapter extends BaseAdapter {

    private Context context_view;
    private ArrayList<Sinhvien> arrayList;
    private int layout;

    public QLTKSinhvien_adapter(Context context_view, ArrayList<Sinhvien> arrayList, int layout) {
        this.context_view = context_view;
        this.arrayList = arrayList;
        this.layout = layout;
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

            holder.tvEmailSV = (TextView) convertView.findViewById(R.id.tvEmailSV);
            holder.tvMKSV = (TextView) convertView.findViewById(R.id.tvMKSV);
            holder.tvTenSV = (TextView) convertView.findViewById(R.id.tvTenSV);
            holder.ibDelete = (ImageButton) convertView.findViewById(R.id.ibDeleteSV);
            holder.ibEdit = (ImageButton) convertView.findViewById(R.id.ibEditSV);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Sinhvien model = arrayList.get(position);
        String email = model.getEmail();
        String name = model.getName();
        String password = model.getPassword();

        holder.tvEmailSV.setText(email);
        holder.tvMKSV.setText(password);
        holder.tvTenSV.setText(name);
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

        return convertView;
    }
    public class ViewHolder {
        TextView tvEmailSV, tvMKSV, tvTenSV;
        ImageButton ibEdit, ibDelete;
    }
}
