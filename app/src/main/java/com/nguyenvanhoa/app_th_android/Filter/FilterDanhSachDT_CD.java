package com.nguyenvanhoa.app_th_android.Filter;

import android.widget.Filter;

import com.nguyenvanhoa.app_th_android.Activity.GiangVien.DSDT_ChuaDuyet_Activity;
import com.nguyenvanhoa.app_th_android.Adapter.DSDeTai_ChuaDuyet_Adapter;
import com.nguyenvanhoa.app_th_android.Adapter.QLTKGiaovien_adapter;
import com.nguyenvanhoa.app_th_android.Model.Giaovien;
import com.nguyenvanhoa.app_th_android.Model.TTDeTai;

import java.util.ArrayList;

public class FilterDanhSachDT_CD extends Filter {

    ArrayList<TTDeTai> filterList;
    DSDeTai_ChuaDuyet_Adapter adapter;

    public FilterDanhSachDT_CD(ArrayList<TTDeTai> filterList, DSDeTai_ChuaDuyet_Adapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults ();
        if (charSequence != null && charSequence.length() > 0){
            //change to upper case, or lower case to avoid case sensitivity
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<TTDeTai> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size(); i++){
                //validate
                if (filterList.get(i).getTenDT().toUpperCase().contains(charSequence)) {
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
        adapter.deTaiList = (ArrayList<TTDeTai>)filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
