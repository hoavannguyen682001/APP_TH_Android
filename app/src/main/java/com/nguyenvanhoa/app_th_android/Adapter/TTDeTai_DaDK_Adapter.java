package com.nguyenvanhoa.app_th_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Model.TTDeTai;
import com.nguyenvanhoa.app_th_android.R;

import java.util.List;
import java.util.zip.Inflater;


public class TTDeTai_DaDK_Adapter extends BaseAdapter {
    private  Context context;
    private  int layout;
    private  List<TTDeTai> deTaiList;

    public TTDeTai_DaDK_Adapter(Context context, int layout, List<TTDeTai> deTaiList) {
        this.context = context;
        this.layout = layout;
        this.deTaiList = deTaiList;
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
            //Thong tin de tai da duyet
            holder.tvTenDT = view.findViewById(R.id.tenDT);
            holder.tvTenCN = view.findViewById(R.id.tenCN);
            holder.tvNgayDK = view.findViewById(R.id.ngayDK);
            holder.ivChiTietDT = view.findViewById(R.id.chiTietDT);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        TTDeTai deTai = deTaiList.get(i);

        holder.tvTenDT.setText(deTai.getTenDT());
        holder.tvTenCN.setText(deTai.getTenCNDT());
        holder.tvNgayDK.setText(deTai.getTgDK());
        return view;
    }

    private class ViewHolder{
        TextView tvTenDT, tvTenCN, tvNgayDK;
        ImageView ivChiTietDT;

    }
}
