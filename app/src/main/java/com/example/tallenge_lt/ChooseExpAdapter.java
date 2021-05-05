package com.example.tallenge_lt;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseExpAdapter extends RecyclerView.Adapter<ChooseExpAdapter.CustomViewHolder> {

    private ArrayList<ChooseExpData> arrayList;

    public ChooseExpAdapter(ArrayList<ChooseExpData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ChooseExpAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ch_exp_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseExpAdapter.CustomViewHolder holder, int position) {
        holder.iv_ctf.setImageResource(arrayList.get(position).getIv_ctf());
        holder.tv_exp.setText(arrayList.get(position).getTv_exp());


        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CertifyExpActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });

    }



    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size():0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {


        protected ImageView iv_ctf;
        protected TextView tv_exp;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_ctf = (ImageView)itemView.findViewById(R.id.iv_ch_exp);
            this.tv_exp = (TextView)itemView.findViewById(R.id.tv_exp);
        }
    }
}
