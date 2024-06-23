package com.uphq.ulb_gis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.uphq.ulb_gis.R;
import com.uphq.ulb_gis.models.SpinnerData;

import java.util.ArrayList;
import java.util.List;

public class MasterSpinnerAdapter extends ArrayAdapter<SpinnerData> {

    public MasterSpinnerAdapter(Context context, ArrayList<SpinnerData> yearItems) {
        super(context, 0, yearItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_master_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.text_distname);
        SpinnerData yearItem = getItem(position);
        if (yearItem != null) {
            textView.setText(yearItem.getMasterName());
        }
        return convertView;
    }
}
