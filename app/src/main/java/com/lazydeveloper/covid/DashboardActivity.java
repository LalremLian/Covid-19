package com.lazydeveloper.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class DashboardActivity extends AppCompatActivity
{
    //Variables................................................................
    Boolean backPress = false; //This one is for backButton....................

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    LinearLayout layout;

    CardView live;
    CardView learnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        layout = findViewById(R.id.layout1);
        live = findViewById(R.id.liveCard);
        learnMore = findViewById(R.id.learnCard);

        //Custom ToolBar........................................................
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        //navigationView.setNavigationItemSelectedListener(this);

        live.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
            startActivity(intent);
        });
        learnMore.setOnClickListener(v ->
        {
            Intent intent = new Intent(DashboardActivity.this,SymptomsActivity.class);
            startActivity(intent);
        });
    }
    //For back button.........................................................
    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(backPress)
        {
            super.onBackPressed();
            return;
        }
        else
        {
            Snackbar snackbar = Snackbar.make(layout,"Press again to Exit.",Snackbar.LENGTH_SHORT);
            snackbar.show();
            backPress = true;
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    backPress = false;
                }
            },2000);
        }
    }

    //For DrawerMenu............................................................
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.about:
                ///Intent intent2 = new Intent(this,ProfileActivity.class);
                //startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}