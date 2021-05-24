package main_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallenge_lt.Catagory_item.Exp_item;
import com.example.tallenge_lt.R;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class ExpitemAdapter extends RecyclerView.Adapter<ExpitemAdapter.ExpitemViewHolder>{

    private ArrayList<Exp_item> arrayList;
    private Context context;

    public ExpitemAdapter(ArrayList<Exp_item> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NotNull
    @Override
    public ExpitemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.catagory_item,parent,false);
        ExpitemAdapter.ExpitemViewHolder holder = new ExpitemAdapter.ExpitemViewHolder( v );

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExpitemAdapter.ExpitemViewHolder holder, int position) {
        Exp_item exp_item = arrayList.get( position );

        if (exp_item.getComputer().equals("컴퓨터")) {
                   holder.tv_item0.setText( exp_item.getComputer() );
                }else{ holder.tv_item0.setText( "" );}
//                if (exp_item.getHistory().equals("역사")) {
//                   holder.tv_item1.setText( exp_item.getHistory() );
//                }else{ holder.tv_item1.setText( "" );}
//                if (exp_item.getStock().equals("주식")) {
//                   holder.tv_item2.setText( exp_item.getStock() );
//               }else{ holder.tv_item2.setText( "" );}
//               if (exp_item.getMath().equals( "수학" )) {
//                  holder.tv_item3.setText( exp_item.getMath() );
//              }else{ holder.tv_item3.setText( "" );}
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ExpitemViewHolder extends RecyclerView.ViewHolder {
            TextView tv_item0; //전문분야-컴퓨터
//            TextView tv_item1;
//            TextView tv_item2;
//            TextView tv_item3;

        public ExpitemViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );

             tv_item0 = itemView.findViewById( R.id.tv_item0 );
//             tv_item1 = itemView.findViewById( R.id.tv_item1 );
//             tv_item2 = itemView.findViewById( R.id.tv_item2 );
//             tv_item3 = itemView.findViewById( R.id.tv_item3 );
        }
    }
}
