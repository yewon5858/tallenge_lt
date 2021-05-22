package com.example.tallenge_lt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAlarmAdapter extends RecyclerView.Adapter<ListAlarmAdapter.CustomViewHolder>{
    private ArrayList<ListAlarmData> arrayList;
    private Context context;
    public ListAlarmAdapter(ArrayList<ListAlarmData> arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listandalarmitem,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.checktitle.setText(arrayList.get(position).getCheckTitle());
        holder.checkitem1.setText(arrayList.get(position).getCheckitem1());
        holder.checkitem2.setText(arrayList.get(position).getCheckitem2());
        holder.checkitem3.setText(arrayList.get(position).getCheckitem3());
        holder.checkitem4.setText(arrayList.get(position).getCheckitem4());
        holder.checkitem5.setText(arrayList.get(position).getCheckitem5());
    }

    @Override
    public int getItemCount() {
        return (arrayList!=null?arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView checktitle;
        TextView checkitem1;
        TextView checkitem2;
        TextView checkitem3;
        TextView checkitem4;
        TextView checkitem5;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checktitle=itemView.findViewById(R.id.title_al);
            this.checkitem1=itemView.findViewById(R.id.setitem1_al);
            this.checkitem2=itemView.findViewById(R.id.setitem2_al);
            this.checkitem3=itemView.findViewById(R.id.setitem3_al);
            this.checkitem4=itemView.findViewById(R.id.setitem4_al);
            this.checkitem5=itemView.findViewById(R.id.setitem5_al);

        }
    }
}
