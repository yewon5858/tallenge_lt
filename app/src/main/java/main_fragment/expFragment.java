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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link expFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class expFragment extends Fragment {
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

    public expFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static expFragment newInstance(String param1,String IdToken) {
        expFragment fragment = new expFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 카테고리별 버튼
        Button computer;
        Button stock;
        Button history;
        Button math;


        Log.e("getIdToken",getIdToken);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_exp, container, false);

        // 카테고리별 버튼
        computer = rootView.findViewById( R.id.exp_item1 );
        stock = rootView.findViewById( R.id.exp_item2 );
        history = rootView.findViewById( R.id.exp_item3 );
        math = rootView.findViewById( R.id.exp_item4 );

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        arrayList2 =new ArrayList<String>();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference("tallenge").child("UserAccount");

        computer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();
                        String item_my_interest = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_interest);
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
                                String item = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "컴퓨터" );

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
//                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//
//                    @Override
//                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                        arrayList.clear();
//                        arrayList2.clear();
//                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
//                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
//                            Log.e("user",dataSnapshot.getValue(UserAccount.class)+"");
//                            String uid = user.getIdToken();
//                            Log.e(" user.getIdToken()",uid);
//                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
//                            Log.e(" uid_check",uid_check);
//                            if(uid_check.equals( uid )) {
//                                String item = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
//                                if(item.equals( "true" )) {
//                                    arrayList.add( user );
//                                    arrayList2.add( "컴퓨터" );
//                                    Log.e( "arrayList2로 받은 유저 정보", arrayList2 + "입니다" );
//                                }
//                                // arrayList2 = exp_item.toString();
//                            }
//
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
//                        Log.e("expFragment", String.valueOf(error.toException()));
//                    }
//
//
//                });
            }
        } );

        stock.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();
                        String item_my_interest = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "stock" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_interest);
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
                                String item = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "stock" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "주식" );
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

        history.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();
                        String item_my_interest = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "history" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_interest);
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
                                String item = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "history" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "역사" );
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

        math.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear();
                        arrayList2.clear();String item_my_interest = null;
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "math" ).getValue().toString();
                                Log.e(" item_my_interest",item_my_interest);
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
                                String item = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "math" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "수학" );
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
                String item_my_interest = null;
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    UserAccount user = dataSnapshot.getValue(UserAccount.class);
                    String uid = user.getIdToken();
                    String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                    if(uid_check.equals( "false" )) {
                        item_my_interest = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                        Log.e(" item_my_interest",item_my_interest);
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
                              String item = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                              Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                              if(item.equals( item_my_interest )) {
                                  arrayList.add( user );
                                  arrayList2.add( "컴퓨터" );

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

        //--------------------------------------------------------------------------------------------
//        databaseReference.child( "expdata" ).child( "exp_item" ).addListenerForSingleValueEvent( new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
//                    Exp_item exp_item =dataSnapshot.getValue(Exp_item.class);
//                    Log.e("exp_item",dataSnapshot.getValue(Exp_item.class)+"");
//                        arrayList2.add( exp_item );
//                }
//                adapter2.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        } );
        //----------------------------------------------------------------------------
        adapter = new MyAdapter(arrayList,getActivity().getApplicationContext(),arrayList2);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}