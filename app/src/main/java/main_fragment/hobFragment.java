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
 * Use the {@link hobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class hobFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserAccount> arrayList;
    private ArrayList<String> arrayList2;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static String getIdToken;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private View rootView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public hobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment hobFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static hobFragment newInstance(String param1,String IdToken) {
        hobFragment fragment = new hobFragment();
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_hob, container, false);
        // 카테고리별 버튼
        Button music;//음악
        Button Tarot;//타로
        Button Resin;//레진공예
        Button Drawing;//그림

        Log.e("getIdToken",getIdToken);

        // 카테고리별 버튼
        music = rootView.findViewById( R.id.exp_item1 );
        Tarot = rootView.findViewById( R.id.exp_item2 );
        Resin = rootView.findViewById( R.id.exp_item3 );
        Drawing = rootView.findViewById( R.id.exp_item4 );

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        arrayList2 =new ArrayList<String>();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference("tallenge").child("UserAccount");

        music.setOnClickListener( new View.OnClickListener() {
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
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Hobby_item" ).child( "music" ).getValue().toString();
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
                                String item = dataSnapshot.child( "expdata" ).child( "Hobby_item" ).child( "music" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "음악" );

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

        Tarot.setOnClickListener( new View.OnClickListener() {
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
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Hobby_item" ).child( "tarot" ).getValue().toString();
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
                                String item = dataSnapshot.child( "expdata" ).child( "Hobby_item" ).child( "tarot" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "타로" );

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

        Resin.setOnClickListener( new View.OnClickListener() {
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
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Hobby_item" ).child( "resin" ).getValue().toString();
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
                                String item = dataSnapshot.child( "expdata" ).child( "Hobby_item" ).child( "resin" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "레진공예" );

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
        Drawing.setOnClickListener( new View.OnClickListener() {
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
                                item_my_interest = dataSnapshot.child( "interestdata" ).child( "Hobby_item" ).child( "drawing" ).getValue().toString();
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
                                String item = dataSnapshot.child( "expdata" ).child( "Hobby_item" ).child( "drawing" ).getValue().toString();
                                Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                                if(item.equals( item_my_interest )) {
                                    arrayList.add( user );
                                    arrayList2.add( "그림" );

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


//----------------default---------------------------------------------------------------------------
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
                        item_my_interest = dataSnapshot.child( "interestdata" ).child( "Hobby_item" ).child( "music" ).getValue().toString();
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
                        String item = dataSnapshot.child( "expdata" ).child( "Hobby_item" ).child( "music" ).getValue().toString();
                        Log.e( "item.equals()", item.equals( item_my_interest ) + "입니다" );
                        if(item.equals( item_my_interest )) {
                            arrayList.add( user );
                            arrayList2.add( "음악" );

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
       // ----------------------------------------------------------------------------------------------
        adapter = new MyAdapter(arrayList,getActivity().getApplicationContext(),arrayList2);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}