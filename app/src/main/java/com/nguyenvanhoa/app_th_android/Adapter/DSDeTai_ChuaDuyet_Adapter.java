package com.nguyenvanhoa.app_th_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Filter.FilterAccountSV;
import com.nguyenvanhoa.app_th_android.Filter.FilterDanhSachDT_CD;
import com.nguyenvanhoa.app_th_android.Model.TTDeTai;
import com.nguyenvanhoa.app_th_android.R;

import java.util.ArrayList;
import java.util.List;

public class DSDeTai_ChuaDuyet_Adapter extends BaseAdapter implements Filterable {
    private Context context;
    private  int layout;
    public ArrayList<TTDeTai> deTaiList, filterList;
    private FilterDanhSachDT_CD filter;

    public DSDeTai_ChuaDuyet_Adapter(Context context, ArrayList<TTDeTai> deTaiList, int layout) {
        this.context = context;
        this.layout = layout;
        this.deTaiList = deTaiList;
        this.filterList = deTaiList;
    }

    @Override
    public int getCount() {
        return deTaiList.size();
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);

            holder = new ViewHolder();
            //Thong tin de tai chua duyet
            holder.tvTenDT_CD = view.findViewById(R.id.tenDT_CD);
            holder.tvTenCN_CD = view.findViewById(R.id.tenCN_CD);
            holder.tvNgayDK_CD = view.findViewById(R.id.ngayDK_CD);
            holder.ivChiTietDT_CD = view.findViewById(R.id.chiTietDT_CD);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        TTDeTai deTai = deTaiList.get(i);

        //Thong tin de tai chua duyet
        holder.tvTenDT_CD.setText(deTai.getTenDT());
        holder.tvTenCN_CD.setText(deTai.getTenCNDT());
        holder.tvNgayDK_CD.setText(deTai.getTgDK());
        holder.ivChiTietDT_CD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterDanhSachDT_CD(filterList, this);
        }
        return filter;
    }

    private class ViewHolder{
        TextView tvTenDT_CD, tvTenCN_CD, tvNgayDK_CD;
        ImageView ivChiTietDT_CD;
    }
}
