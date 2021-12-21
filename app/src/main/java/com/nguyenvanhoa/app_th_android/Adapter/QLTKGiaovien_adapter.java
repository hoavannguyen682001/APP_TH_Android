package com.nguyenvanhoa.app_th_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.R;

import java.util.List;

public class QLTKGiaovien_adapter extends BaseAdapter {

    private Context context_view;
    private List<Giaovien> list_giaovien;
    private int layout;

    public QLTKGiaovien_adapter(Context context_view, List<Giaovien> list_giaovien, int layout) {
        this.context_view = context_view;
        this.list_giaovien = list_giaovien;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list_giaovien.size();
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
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context_view).inflate(R.layout.row_listview_giaovien,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtTen_GV = (TextView) convertView.findViewById(R.id.txt_ten_tk);
            viewHolder.txt_MK = (TextView) convertView.findViewById(R.id.txt_mk);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Giaovien event = list_giaovien.get(i);
        viewHolder.txtTen_GV.setText(event.getTen_gv());
        viewHolder.txt_MK.setText(event.getMk_gv());
        return convertView;
    }
    public class ViewHolder {
        TextView txtTen_GV, txt_MK;
        ImageButton img_del, img_edit;
    }
}
