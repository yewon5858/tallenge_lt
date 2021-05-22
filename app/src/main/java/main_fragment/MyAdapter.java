package main_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallenge_lt.R;
import com.example.tallenge_lt.UserAccount;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<UserAccount> arrayList;
    private Context context;

    public MyAdapter(ArrayList<UserAccount> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
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
        UserAccount user = arrayList.get(position);

        holder.tv_id.setText( user.getEmailId() );
        holder.tv_nickname.setText( user.getNicname() );
    }

    @Override
    public int getItemCount() {
        //삼항연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_nickname;
      //  TextView tv_interest;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );

            tv_id = itemView.findViewById( R.id.tv_id );
            tv_nickname = itemView.findViewById( R.id.tv_nickname );
           // tv_interest = itemView.findViewById( R.id.tv_interest );
        }
    }
}
