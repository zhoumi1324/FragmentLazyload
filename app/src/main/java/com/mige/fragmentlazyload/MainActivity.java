package com.mige.fragmentlazyload;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab ;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private String[]titles={"page1","page2","page3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = findViewById(R.id.table_layout);
        viewPager = findViewById(R.id.view_pager);

        fragments=new ArrayList<>(3);
        fragments.add(MyFragment.newInstance(1));
        fragments.add(MyFragment.newInstance(2));
        fragments.add(MyFragment.newInstance(3));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setupWithViewPager(viewPager);
        tab.setTabTextColors(Color.GRAY,Color.RED);
    }

}
