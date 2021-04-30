package com.example.tallenge_lt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ListSetAdapter extends BaseAdapter {
    private Context context;
    private  List<ListSet> listSet;

    public ListSetAdapter(Context context,List<ListSet> listSet){
        this.context=context;
        this.listSet=listSet;
    }


    @Override
    public int getCount() {
        return listSet.size();
    }

    @Override
    public Object getItem(int i) {
        return listSet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context,R.layout.settitlelist,null);
        Button btn_chatitem=(Button) v.findViewById(R.id.btn_chatitem);
        btn_chatitem.setText(listSet.get(i).setListset());
        return v;
    }
}