package com.example.notificationpractise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Medicinedetails> arrayList;

    public CustomAdapter(Context context, ArrayList<Medicinedetails> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.itemlayout,null);

        TextView tvname = (TextView) view.findViewById(R.id.tvname);
        TextView tvtime = (TextView) view.findViewById(R.id.tvtime);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgmedi);

        tvname.setText(arrayList.get(position).getName());
        tvtime.setText(arrayList.get(position).getTime());
        return view;
    }
}
