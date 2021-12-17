package com.nguyenvanhoa.app_th_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nguyenvanhoa.app_th_android.Model.ThongBao;
import com.nguyenvanhoa.app_th_android.R;

import java.util.List;

public class ThongBao_Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ThongBao> list;

    public ThongBao_Adapter(Context context, int layout, List<ThongBao> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder viewHolder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);

            viewHolder = new ViewHolder();

            viewHolder.tvDate = (TextView) view.findViewById(R.id.tvDate);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        ThongBao listTB = list.get(i);
        viewHolder.tvDate.setText(listTB.getDateTime());
        viewHolder.tvTitle.setText(listTB.getTitle());

        return view;
    }

    private class ViewHolder{
        TextView tvDate, tvTitle;
    }
}
