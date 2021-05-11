package com.example.tallenge_lt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChooseExpAdapter extends RecyclerView.Adapter<ChooseExpAdapter.CustomViewHolder> {

    private ArrayList<ChooseExpData> arrayList;
    private Context context;

    public ChooseExpAdapter(ArrayList<ChooseExpData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ChooseExpAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //뷰홀더 만들기

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ch_exp_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseExpAdapter.CustomViewHolder holder, int position) {  //실제 매칭
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getIv_ctf())
                .into(holder.iv_ctf);
        holder.tv_exp.setText(arrayList.get(position).getTv_exp());

//밑쪽 아직안함
        /*
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
        */

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


        ImageView iv_ctf;
        TextView tv_exp;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_ctf = itemView.findViewById(R.id.iv_ch_exp);
            this.tv_exp = itemView.findViewById(R.id.tv_exp);
        }
    }
}
