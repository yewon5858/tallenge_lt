package com.example.tallenge_lt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//교환리스트 선택 어댑터
public class ExchageAdapter extends RecyclerView.Adapter<ExchageAdapter.CustomViewHolder> {

    private Context context;
    private List<ExchangeList> arraylist;

    public ExchageAdapter(List<ExchangeList> arraylist,Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exchangelist,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.checktitle.setText(arraylist.get(position).getChecktitle());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return (arraylist!=null?arraylist.size():0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView checktitle;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checktitle=itemView.findViewById(R.id.exchange);
        }
    }
}

