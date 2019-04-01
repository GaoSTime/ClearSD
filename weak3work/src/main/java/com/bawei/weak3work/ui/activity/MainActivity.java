package com.bawei.weak3work.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.bawei.weak3work.R;
import com.bawei.weak3work.ui.fragment.button1;
import com.bawei.weak3work.ui.fragment.button2;
import com.bawei.weak3work.ui.fragment.button3;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.main_raguop)
    RadioGroup mainRaguop;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        list.add(new button1());
        list.add(new button2());
        list.add(new button3());

        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        //点击
        mainRaguop.check(mainRaguop.getChildAt(0).getId());
        mainRaguop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                        mainVp.setCurrentItem(0);
                        break;
                    case R.id.radioButton2:
                        mainVp.setCurrentItem(1);
                        break;
                    case R.id.radioButton3:
                        mainVp.setCurrentItem(2);
                        break;

                }
            }
        });

        //滑动
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mainRaguop.check(mainRaguop.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }
}
