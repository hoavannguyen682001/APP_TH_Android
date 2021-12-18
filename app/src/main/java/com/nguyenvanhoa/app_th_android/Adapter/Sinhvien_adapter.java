package com.nguyenvanhoa.app_th_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.Model.Sinhvien;
import com.nguyenvanhoa.app_th_android.R;

import java.util.List;

public class Sinhvien_adapter extends BaseAdapter {

    private Context context_view;
    private List<Sinhvien> list_sinhvien;
    private int layout;

    public Sinhvien_adapter(Context context_view, List<Sinhvien> list_sinhvien, int layout) {
        this.context_view = context_view;
        this.list_sinhvien = list_sinhvien;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list_sinhvien.size();
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
       Sinhvien_adapter.ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context_view).inflate(R.layout.row_listview_sinhvien,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtTk_sinhvien= (TextView) convertView.findViewById(R.id.txt_tk_sinhvien);
            viewHolder.txtMk_sinhvien = (TextView) convertView.findViewById(R.id.txt_mk_sinhvien);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sinhvien sukien = list_sinhvien.get(position);
        viewHolder.txtTk_sinhvien.setText(sukien.getTk_sinhvien());
        viewHolder.txtMk_sinhvien.setText(sukien.getMk_sinhvien());
        return convertView;
    }
    public class ViewHolder {
        TextView txtTk_sinhvien, txtMk_sinhvien;
        ImageButton xoa, sua;
    }
}
