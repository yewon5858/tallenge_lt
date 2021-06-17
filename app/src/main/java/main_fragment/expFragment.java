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
import com.google.firebase.database.annotations.NotNull;

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
    private View rootView; // View 구현
    // TODO: Rename and change types of parameters
    private String mParam1; // 매개변수 입력
    private String mParam2;

    public expFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    // 매개변수로 code와 IdToken을 받음(code 부분을 보고 어떤 화면을 보여주는지 달라짐)
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

        // recyclerView 구현
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        arrayList2 =new ArrayList<String>();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference("tallenge").child("UserAccount");

        // expFragment의 컴퓨터 카테고리를 선택하면
        computer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스의 UserAccount 위치에서 값을 찾는다.
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        arrayList.clear(); 
                        arrayList2.clear();

                        String item_my_int = null; //현재 사용자의 관심분야
                        String item_my_exp = null; //현재 사용자의 전문분야(사용x)
                        //모든 유저에 접근하여 값을 비교한다
                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class); 
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid; // 현재 로그인한 유저의 IdToken과 비교
                            if(uid_check.equals( "false" )) { // 현재 로그인한 유저와 일치한다면 true
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                                    

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
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                // 나의 관심 분야가 존재하고 나의 관심분야와 다른 사용자의 전문분야와 일치할 때,
                                if(item_my_int.equals("true") & item_exp.equals( item_my_int )) {  
                                    // 해당 하는 다른 사용자의 user 정보와 해당하는 분야에 대한 정보를 List에 받아 MyAdapter를 통해 recyclerView를 구현
                                    arrayList.add( user ); 
                                    arrayList2.add( "컴퓨터" );
                                    Log.e( "arrayList2로 받은 유저 정보", arrayList2 + "입니다" );
                                }
                                // arrayList2 = exp_item.toString();
                            }

                        }
                        adapter.notifyDataSetChanged(); // Myadapter에 다시 보냄(데이터 갱신)
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Log.e("expFragment", String.valueOf(error.toException()));
                    }


                });
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

                        String item_my_int = null;
                        String item_my_exp = null;

                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "stock" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "stock" ).getValue().toString();


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
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "stock" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "stock" ).getValue().toString();
                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                if(item_my_int.equals("true") & item_exp.equals( item_my_int )) {
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

                        String item_my_int = null;
                        String item_my_exp = null;


                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "history" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "history" ).getValue().toString();


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
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "history" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "history" ).getValue().toString();
                                Log.e( "item.equals()", item_int.equals( item_my_int ) + "입니다" );
                                Log.e( "item.equals()", item_int.equals( item_my_int ) + "입니다" );

                                if(item_my_int.equals("true") & item_exp.equals( item_my_int )) {
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
                        arrayList2.clear();

                        String item_my_int = null;
                        String item_my_exp = null;


                        for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                            UserAccount user = dataSnapshot.getValue(UserAccount.class);
                            String uid = user.getIdToken();
                            String uid_check = getIdToken.equals( uid ) ? "false" : uid;
                            if(uid_check.equals( "false" )) {
                                item_my_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "math" ).getValue().toString();
                                item_my_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "math" ).getValue().toString();

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
                                String item_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "math" ).getValue().toString();
                                String item_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "math" ).getValue().toString();
                                Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                                if(item_my_int.equals("true") & item_exp.equals( item_my_int )) {
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

        //-------------------default-------------------------------------
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
                        item_my_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                        item_my_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();


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
                        String item_exp = dataSnapshot.child( "expdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                        String item_int = dataSnapshot.child( "interestdata" ).child( "Exp_item" ).child( "computer" ).getValue().toString();
                        Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                        Log.e( "item.equals()", item_exp.equals( item_my_int ) + "입니다" );
                        if(item_my_int.equals("true") & item_exp.equals( item_my_int )) {
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

        // MyAdapter를 통해서 recyclerView를 구현하고 해당 프래그먼트를 return 하여 화면에 보여줌
        adapter = new MyAdapter(arrayList,getActivity().getApplicationContext(),arrayList2);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}