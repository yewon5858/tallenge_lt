package com.example.tallenge_lt;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListSetAdapter extends RecyclerView.Adapter<ListSetAdapter.CustomViewHolder > {

    private ArrayList<ListSet> arrayList;
    private Context context;

    public ListSetAdapter(ArrayList<ListSet> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    // 뷰홀더 선언
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView checktitle;
//        TextView checkitem1;
//        TextView checkitem2;
//        TextView checkitem3;
//        TextView checkitem4;
//        TextView checkitem5;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checktitle=itemView.findViewById(R.id.checkTitle);
//            this.checkitem1=itemView.findViewById(R.id.checkitem_1);
//            this.checkitem2=itemView.findViewById(R.id.checkitem_2);
//            this.checkitem3=itemView.findViewById(R.id.checkitem_3);
//            this.checkitem4=itemView.findViewById(R.id.checkitem_4);
//            this.checkitem5=itemView.findViewById(R.id.checkitem_5);
        }
    }
    @NonNull
    @Override
    public ListSetAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.settitlelist,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);
        return holder;
    }
    //체크리스트 제목 출력 및 터치시 화면 전환
    @Override
    public void onBindViewHolder(@NonNull ListSetAdapter.CustomViewHolder holder, int position) {
        holder.checktitle.setText(arrayList.get(position).getChecktitle());
        holder.checktitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),SetCheckListActivity.class);
                intent.putExtra("checkTitle",arrayList.get(position).getChecktitle());
                v.getContext().startActivity(intent);
            }
        });
//        holder.checkitem1.setText(arrayList.get(position).getCheckitem1());
//        holder.checkitem2.setText(arrayList.get(position).getCheckitem2());
//        holder.checkitem3.setText(arrayList.get(position).getCheckitem3());
//        holder.checkitem4.setText(arrayList.get(position).getCheckitem4());
//        holder.checkitem5.setText(arrayList.get(position).getCheckitem5());
//

    }

    @Override
    public int getItemCount() {
        return (arrayList!=null?arrayList.size():0);

    }
//    private Context context;
//    int layout;
//    private ArrayList<Set> set;
//    LayoutInflater inf;
//
//    public ListSetAdapter(Context context,int layout,ArrayList<Set> set){
//        this.context=context;
//        this.set =set;
//        this.layout=layout;
//        inf =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//
//    @Override
//    public int getCount() {
//        return set.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return set.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (view==null) {
//            view = inf.inflate(layout, null);
//        }
//        TextView chatitem = (TextView)view.findViewById(R.id.btn_chatitem);
//        Set m = set.get(i);
//        chatitem.setText(m.title);
//        return view;
//    }

}
