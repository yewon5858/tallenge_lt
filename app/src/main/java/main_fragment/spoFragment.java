package main_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallenge_lt.R;
import com.example.tallenge_lt.UserAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class spoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserAccount> arrayList;
    private ArrayList<String> arrayList2;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static String getIdToken;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private View rootView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public spoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment spoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static spoFragment newInstance(String param1,String IdToken) {
        spoFragment fragment = new spoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        getIdToken = IdToken;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_spo, container, false);
        // 카테고리별 버튼
        Button swim;
        Button pilates;
        Button ht;
        Button badminton;

        // 카테고리별 버튼
        swim = rootView.findViewById( R.id.exp_item1 );
        pilates = rootView.findViewById( R.id.exp_item2 );
        ht = rootView.findViewById( R.id.exp_item3 );
        badminton = rootView.findViewById( R.id.exp_item4 );

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        arrayList2 =new ArrayList<String>();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference("tallenge").child("UserAccount");

        swim.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();
                        String item_my_int = null;
                        String item_my_exp = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_int);
                                Log.e(" item_my_exp",item_my_exp);
                                break;
                            }
                        }
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            Log.e("user",dataSnapshot.getValue(UserAccount.class)+"");
                            String uid = user.getIdToken();
                            Log.e(" user.getIdToken()",uid);
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            Log.e(" uid_check",uid_check);

                            if(uid_check.equals( uid )) {
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();

                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                Log.e( "item.equals(exp)", item_int.equals( item_my_exp ) + "입니다" );

                                if(item_my_int.equals("false") & item_exp.equals( item_my_int )) {
                                    arrayList.add( user );
                                    arrayList2.add( "수영" );

                                    Log.e( "arrayList2로 받은 유저 정보", arrayList2 + "입니다" );
                                }
                                // arrayList2 = exp_item.toString();
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Log.e("expFragment", String.valueOf(error.toException()));
                    }


                });

            }
        } );

        pilates.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();
                        String item_my_int = null;
                        String item_my_exp = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "pilates" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "pilates" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_int);
                                Log.e(" item_my_exp",item_my_exp);
                                break;
                            }
                        }
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            Log.e("user",dataSnapshot.getValue(UserAccount.class)+"");
                            String uid = user.getIdToken();
                            Log.e(" user.getIdToken()",uid);
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            Log.e(" uid_check",uid_check);
                            if(uid_check.equals( uid )) {
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "pilates" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "pilates" ).getValue().toString();

                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                Log.e( "item.equals(exp)", item_int.equals( item_my_exp ) + "입니다" );

                                if(item_my_int.equals("false") & item_exp.equals( item_my_int )) {
                                    arrayList.add( user );
                                    arrayList2.add( "필라테스" );
                                    Log.e( "arrayList2로 받은 유저 정보", arrayList2 + "입니다" );
                                }
                                // arrayList2 = exp_item.toString();
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Log.e("expFragment", String.valueOf(error.toException()));
                    }


                });
            }
        } );

        ht.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();
                        String item_my_int = null;
                        String item_my_exp = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "ht" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "ht" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_int);
                                break;
                            }
                        }
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            Log.e("user",dataSnapshot.getValue(UserAccount.class)+"");
                            String uid = user.getIdToken();
                            Log.e(" user.getIdToken()",uid);
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            Log.e(" uid_check",uid_check);
                            if(uid_check.equals( uid )) {
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "ht" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "ht" ).getValue().toString();

                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                Log.e( "item.equals(exp)", item_int.equals( item_my_exp ) + "입니다" );

                                if(item_my_int.equals("false") & item_exp.equals( item_my_int )) {
                                    arrayList.add( user );
                                    arrayList2.add( "홈트" );
                                    Log.e( "arrayList2로 받은 유저 정보", arrayList2 + "입니다" );
                                }
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Log.e("expFragment", String.valueOf(error.toException()));
                    }


                });
            }
        } );

        badminton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();
                        String item_my_int = null;
                        String item_my_exp = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "badminton" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "badminton" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_int);
                                Log.e(" item_my_exp",item_my_exp);
                                break;
                            }
                        }
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            Log.e("user",dataSnapshot.getValue(UserAccount.class)+"");
                            String uid = user.getIdToken();
                            Log.e(" user.getIdToken()",uid);
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            Log.e(" uid_check",uid_check);
                            if(uid_check.equals( uid )) {
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "badminton" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "badminton" ).getValue().toString();

                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                Log.e( "item.equals(exp)", item_int.equals( item_my_exp ) + "입니다" );

                                if(item_my_int.equals("false") & item_exp.equals( item_my_int )) {
                                    arrayList.add( user );
                                    arrayList2.add( "배드민턴" );
                                    Log.e( "arrayList2로 받은 유저 정보", arrayList2 + "입니다" );
                                }
                                // arrayList2 = exp_item.toString();
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Log.e("expFragment", String.valueOf(error.toException()));
                    }


                });
            }
        } );

        //-------------------완성본 및 default-------------------------------------
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                arrayList2.clear();
                String item_my_int = null;
                String item_my_exp = null;
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    UserAccount user = dataSnapshot.getValue(UserAccount.class);
                    String uid = user.getIdToken();
                    String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                    if(uid_check.equals( "false" )) {
                        item_my_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();
                        item_my_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();
                        Log.e(" item_my_interest",item_my_int);
                        Log.e(" item_my_exp",item_my_exp);
                        break;
                    }
                }
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    UserAccount user = dataSnapshot.getValue(UserAccount.class);
                    Log.e("user",dataSnapshot.getValue(UserAccount.class)+"");
                    String uid = user.getIdToken();
                    Log.e(" user.getIdToken()",uid);
                    String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                    Log.e(" uid_check",uid_check);

                    if(uid_check.equals( uid )) {
                        String item_exp = dataSnapshot.child( "expdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();
                        String item_int = dataSnapshot.child( "interestdata" ).child( "Spo_item" ).child( "swim" ).getValue().toString();

                        Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                        Log.e( "item.equals(exp)", item_int.equals( item_my_exp ) + "입니다" );

                        if(item_my_int.equals("false") & item_exp.equals( item_my_int )) {
                            arrayList.add( user );
                            arrayList2.add( "수영" );

                            Log.e( "arrayList2로 받은 유저 정보", arrayList2 + "입니다" );
                        }
                        // arrayList2 = exp_item.toString();
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e("expFragment", String.valueOf(error.toException()));
            }


        });
        //----------------------------------------------------------------------------
        adapter = new MyAdapter(arrayList,getActivity().getApplicationContext(),arrayList2);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}