package com.lazydeveloper.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazydeveloper.covid.adapter.AdapterPager;

public class OnBoardingActivity extends AppCompatActivity {

    //Variables...............
    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;

    TextView[] dots;
    AdapterPager adapterPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //Hooks..........................
        backbtn = findViewById(R.id.back);
        nextbtn = findViewById(R.id.next);
        skipbtn = findViewById(R.id.skip);

        mSLideViewPager = (ViewPager) findViewById(R.id.viewPager1);        //viewPager................
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layput);

        adapterPager = new AdapterPager(this);

        mSLideViewPager.setAdapter(adapterPager);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

        backbtn.setOnClickListener(v ->
        {
            if (getitem(0) > 0)
            {
                mSLideViewPager.setCurrentItem(getitem(-1),true);
            }
        });
        nextbtn.setOnClickListener(v ->
        {
            if (getitem(0) < 2)
                mSLideViewPager.setCurrentItem(getitem(1),true);
            else {
                Intent i = new Intent(OnBoardingActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        skipbtn.setOnClickListener(v ->
        {
            Intent i = new Intent(OnBoardingActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void setUpindicator(int position)
    {
        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);

            if (position > 0) {

                backbtn.setVisibility(View.VISIBLE);

            } else
                backbtn.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getitem(int i)
    {
        return mSLideViewPager.getCurrentItem() + i;
    }
}