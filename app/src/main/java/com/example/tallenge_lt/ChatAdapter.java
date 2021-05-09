

package com.example.tallenge_lt;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<ChatData> mDataset;
    private String myNickName;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_nickname;
        public TextView TextView_msg;

        public MyViewHolder(View v){
            super(v);
            TextView_nickname=v.findViewById(R.id.tv_nickname);
            TextView_msg=v.findViewById(R.id.tv_msg);
        }
    }
    public ChatAdapter(List<ChatData> myDataset, Context context, String myNickName){

        mDataset=myDataset;
        this.myNickName=myNickName;
    }
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //새 뷰생성.
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatview,parent,false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        ChatData chat=mDataset.get(position);
        holder.TextView_nickname.setText(chat.getNickname());
        holder.TextView_msg.setText(chat.getMsg());
        if(chat.getNickname()!=null &&this.myNickName!=null && chat.getNickname().equals(this.myNickName)) {
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else{
            holder.TextView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

        }

    }
    @Override
    public int getItemCount() {
        return mDataset==null? 0: mDataset.size();
    }

    public ChatData getChat(int position){
        return mDataset!=null? mDataset.get(position):null;
    }

    public void addChat(ChatData chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //데이터 갱신
    }
}
