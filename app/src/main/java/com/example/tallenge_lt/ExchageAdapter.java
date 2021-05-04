package com.example.tallenge_lt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
//교환리스트 선택 어댑터
public class ExchageAdapter extends BaseAdapter {

    private Context context;
    private List<ExchangeList> exchangelist;

    public ExchageAdapter(Context context, List<ExchangeList> exchangelist) {
        this.context = context;
        this.exchangelist = exchangelist;
    }

    @Override
    public int getCount() {
        return exchangelist.size();
    }

    @Override
    public Object getItem(int i) {
        return exchangelist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context,R.layout.exchangelist,null);
        TextView exchage=(TextView) v.findViewById(R.id.exchange);

        exchage.setText(exchangelist.get(i).setExchange());
        return v;
    }

}

