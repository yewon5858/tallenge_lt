package com.example.tallenge_lt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SetCheckListAdapter extends RecyclerView.Adapter<SetCheckListAdapter.CustomViewHolder> {

    private ArrayList<SetCheckListData> arrayList;
    private Context context;
    public SetCheckListAdapter(ArrayList<SetCheckListData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public SetCheckListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.setcheckitem,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SetCheckListAdapter.CustomViewHolder holder, int position) {
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
        EditText checktitle;
        EditText checkitem1;
        EditText checkitem2;
        EditText checkitem3;
        EditText checkitem4;
        EditText checkitem5;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checktitle=itemView.findViewById(R.id.settitle);
            this.checkitem1=itemView.findViewById(R.id.setitem1);
            this.checkitem2=itemView.findViewById(R.id.setitem2);
            this.checkitem3=itemView.findViewById(R.id.setitem3);
            this.checkitem4=itemView.findViewById(R.id.setitem4);
            this.checkitem5=itemView.findViewById(R.id.setitem5);
        }
    }
//    public void SaveCheck(String checkTitle,String checkitem1,String checkitem2,String checkitem3,String checkitem4,String checkitem5){
//        if(checkTitle.length()!=0 &&(checkitem1.length()!=0||checkitem2.length()!=0||checkitem3.length()!=0||checkitem4.length()!=0||checkitem5.length()!=0)) {
//
//        }
//    }

}
