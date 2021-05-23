package com.example.tallenge_lt;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChooseExpAdapter extends RecyclerView.Adapter<ChooseExpAdapter.CustomViewHolder> implements OnIntentReceived{
    private static final Object RESULT_LOAD_IMAGE = 1001;
    private ArrayList<ChooseExpData> arrayList;
    private Context context;
    int requestcode;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("tallenge").child("CertifyImg").child(user.getUid());

    @Override
    public void onIntent(Intent i, int resultCode) {

    }

    public interface OnClickImageListener{
        void onClick();
    }

    /*
    @Override
    public void onIntent(Intent i, int resultCode) {
        // //
        i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    }
    */

    // Somewhere in here, mContext.startActivityForResult(MyActivity.REQUEST_CODE);
    //
    /*
    public interface OnItemClickEventListener {
        void onItemClick(View a_view, int a_position);
    }
    private OnItemClickEventListener mItemClickListener;


     */

    /*
    public void setOnItemClickListener(OnItemClickEventListener a_listener) {
        mItemClickListener = a_listener;
        Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivity;
    }


     */

    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.
    public ChooseExpAdapter(ArrayList<ChooseExpData> arrayList) {
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public ChooseExpAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ch_exp_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseExpAdapter.CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getIv_ctf())
                .into(holder.imageView);
        holder.tv_bc.setText(arrayList.get(position).getTv_big_category());
        holder.tv_sc.setText(arrayList.get(position).getTv_small_category());
        holder.tv_certified.setText(arrayList.get(position).getCertified());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                v.getContext().startActivity(in);
                changeImage(holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition());

                /*
                requestcode = position;
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activity.startActivityForResult(pickPhoto, 1);


                 */
                //Intent intent = new Intent(v.getContext(),CertifyExpActivity.class);
                //v.getContext().startActivity(intent);
            }
        });


    }
public void changeImage(int position){
    try {
        ChooseExpData chooseExpData;
        arrayList.get(position).setIv_ctf("https://firebasestorage.googleapis.com/v0/b/tallenge-lt.appspot.com/o/correct.png?alt=media&token=80df04b7-ad60-4bbe-8000-69704d5a3eff");
        arrayList.get(position).setCertified("인증됨");
        myRef.child("computer").setValue(arrayList.get(position));
        //arrayList.remove(arrayList.get(position));
        //arrayList.add(arrayList.get(position),chooseExpData);
        //notifyDataSetChanged(position);


    } catch (IndexOutOfBoundsException ex) {
        ex.printStackTrace();
    }

}
    /*
    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK & null != data) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

     */

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_bc;   //큰 카테고리
        TextView tv_sc;   //작은 카테고리
        TextView tv_certified;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView= itemView.findViewById(R.id.iv_ch_exp);
            this.tv_bc = itemView.findViewById(R.id.tv_exp);
            this.tv_sc = itemView.findViewById(R.id.tv_small_exp);
            this.tv_certified  = itemView.findViewById(R.id.certified);

        }
    }

}







/*
    private ArrayList<ChooseExpData> arrayList;
    private Context context;

    public ChooseExpAdapter(ArrayList<ChooseExpData> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context = context;
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
        holder.iv_ctf.setImage//setImageResource(arrayList.get(position).getIv_ctf());
        //holder.tv_exp.setText(arrayList.get(position).getTv_exp());
//holder.iv

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

 */
