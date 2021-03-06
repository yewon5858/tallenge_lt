package com.example.tallenge_lt;

import android.content.Context;
import android.content.Intent;
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
    //체크리스트 제목만 출력+제목 클릭시 화면 전환
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.checktitle.setText(arraylist.get(position).getChecktitle());
        holder.checktitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ListAndAlarmActivity.class);
                intent.putExtra("Title",arraylist.get(position).getChecktitle());
                v.getContext().startActivity(intent);
            }
        });
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

