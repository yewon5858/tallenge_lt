package com.example.tallenge_lt;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import main_fragment.expFragment;
import main_fragment.hobFragment;
import main_fragment.lanFragment;
import main_fragment.spoFragment;

public class MainActivity extends AppCompatActivity  {

    private final String TAG = this.getClass().getSimpleName();
    Context mContext;
    private ViewPager2 mViewPager;
    private MyViewPagerAdapter myPagerAdapter;
    private TabLayout tabLayout;


    private String[] titles = new String[]{"전문분야", "취미", "언어","스포츠"};


    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mContext = MainActivity.this;

        code = "";
        Log.e( TAG, code );

        Fragment frag1 = new expFragment().newInstance(code);
        Fragment frag2 = new hobFragment().newInstance(code);
        Fragment frag3 = new lanFragment().newInstance(code);
        Fragment frag4 = new spoFragment().newInstance(code);


        mViewPager = findViewById(R.id.viewPager2_container);
        tabLayout = findViewById(R.id.tabLayout);


        myPagerAdapter = new MyViewPagerAdapter(this);
        myPagerAdapter.addFrag(frag1);
        myPagerAdapter.addFrag(frag2);
        myPagerAdapter.addFrag(frag3);
        myPagerAdapter.addFrag(frag4);

        mViewPager.setAdapter(myPagerAdapter);

        //displaying tabs
        new TabLayoutMediator(tabLayout, mViewPager, (tab, position) -> tab.setText(titles[position])).attach();
    }

}
