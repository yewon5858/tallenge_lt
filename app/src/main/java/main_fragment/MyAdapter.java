package main_fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallenge_lt.R;
import com.example.tallenge_lt.UserAccount;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<UserAccount> arrayList; // user의 정보를 받은 배열 리스트 [user1(의 사용자 정보),user2,...]
    private Context context;
    private  ArrayList<String> arrayList2;

    public MyAdapter(ArrayList<UserAccount> arrayList, Context context,ArrayList<String> arrayList2){
        this.arrayList = arrayList;
        this.context = context;
        this.arrayList2 = arrayList2;

    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.catagory_item,parent,false);
            MyViewHolder holder = new MyViewHolder( v );

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {
        UserAccount user = arrayList.get( position );
        String user_item = arrayList2.get( position );
        Log.e("arrayList로 받은 유저 정보",user.toString());
        holder.tv_id.setText( user.getEmailId() );
        holder.tv_nickname.setText( user.getNicname() );
        Log.e("arrayList2로 받은 유저 정보",user_item.equals( "false" )+"입니다");

        if (user_item.equals( "false" ) != true) {
            holder.tv_item0.setText( user_item );
        }else{ holder.tv_item0.setText( "nodata" );}

        // if (exp_item.getComputer().toString() == "컴퓨터") {
        //            holder.tv_item0.setText( exp_item.getComputer() );
        //        }else{ holder.tv_item0.setText( "" );}
        //        if (exp_item.getHistory().toString() == "역사") {
        //            holder.tv_item1.setText( exp_item.getHistory() );
        //        }else{ holder.tv_item0.setText( "" );}
        //        if (exp_item.getStock().toString() == "주식") {
        //            holder.tv_item2.setText( exp_item.getStock() );
        //        }else{ holder.tv_item0.setText( "" );}
        //        if (exp_item.getMath().toString() == "수학") {
        //            holder.tv_item3.setText( exp_item.getMath() );
        //        }else{ holder.tv_item0.setText( "" );}
    }

    @Override
    public int getItemCount() {
        //삼항연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_nickname;
        TextView tv_item0; //해당 카테고리의 전문분야 중 1개만

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );

            tv_id = itemView.findViewById( R.id.tv_id );
            tv_nickname = itemView.findViewById( R.id.tv_nickname );
            tv_item0 = itemView.findViewById( R.id.tv_item0 );


        }
    }
}
