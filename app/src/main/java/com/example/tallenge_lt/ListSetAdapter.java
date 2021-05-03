package com.example.tallenge_lt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListSetAdapter extends BaseAdapter {
    private Context context;
    int layout;
    private ArrayList<Set> set;
    LayoutInflater inf;

    public ListSetAdapter(Context context,int layout,ArrayList<Set> set){
        this.context=context;
        this.set =set;
        this.layout=layout;
        inf =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return set.size();
    }

    @Override
    public Object getItem(int i) {
        return set.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null) {
            view = inf.inflate(layout, null);
        }
        TextView chatitem = (TextView)view.findViewById(R.id.btn_chatitem);
        Set m = set.get(i);
        chatitem.setText(m.title);
        return view;
    }

}
