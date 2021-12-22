package com.nguyenvanhoa.app_th_android.Filter;

import android.widget.Filter;

import com.nguyenvanhoa.app_th_android.Adapter.QLTKGiaovien_adapter;
import com.nguyenvanhoa.app_th_android.Adapter.QLTKSinhvien_adapter;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.Model.Sinhvien;

import java.util.ArrayList;

public class FilterAccountGiaoVien extends Filter {

    ArrayList<Giaovien> filterList;
    QLTKGiaovien_adapter adapter;

    public FilterAccountGiaoVien(ArrayList<Giaovien> filterList, QLTKGiaovien_adapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults ();
        if (charSequence != null && charSequence.length() > 0){
            //change to upper case, or lower case to avoid case sensitivity
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<Giaovien> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size(); i++){
                //validate
                if (filterList.get(i).getEmail().toUpperCase().contains(charSequence)) {
                    //add to filtered List
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }else {
            results.count = filterList.size();
            results.values = filterList;
        }
            return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.arrayList = (ArrayList<Giaovien>)filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
