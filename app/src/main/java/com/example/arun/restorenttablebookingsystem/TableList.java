package com.example.arun.restorenttablebookingsystem;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TableList  extends ArrayAdapter<CustmerName> {
    private Activity context;
    List<CustmerName> css;

    public TableList(Activity context, List<CustmerName> coustmer) {
        super(context, R.layout.layout_table, coustmer);
        this.context = context;
        this.css = coustmer;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_table, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        TextView tableHour = (TextView) listViewItem.findViewById(R.id.tableHour);

        CustmerName a = css.get(position);
        textViewName.setText(a.getCsName());
        textViewGenre.setText(a.getCsGenre());
        tableHour.setText("Time: " + a.getCsHour() + ":" + a.getCsMin());
        return listViewItem;
    }
}
