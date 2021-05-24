package com.example.tallenge_lt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.CustomViewHolder> {
    private ArrayList<ChatListData> arrayList;
    private Context context;
    private String nickname;
    public ChatListAdapter(ArrayList<ChatListData> arrayList,Context context,String nickname){
        this.arrayList=arrayList;
        this.context=context;
        this.nickname=nickname;
    }

    @NonNull
    @Override
    public ChatListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chatlistitem,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.CustomViewHolder holder, int position) {
        holder.idimage.setText(arrayList.get(position).getRoomID());
        holder.othernickname.setText(arrayList.get(position).getOther_nickname());
        holder.gochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ChatActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList!=null?arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView idimage;
        TextView othernickname;
        Button gochat;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.idimage=itemView.findViewById(R.id.image_icon);
            this.othernickname=itemView.findViewById(R.id.othernick);
            this.gochat=itemView.findViewById(R.id.goChat);
        }
    }
}
