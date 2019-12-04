package com.example.navslider;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.app_title);
        //get ListView
        expandableListView = findViewById(R.id.lvExp);

        prepareListData();

        expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        expandableListView.setAdapter(expandableListAdapter);

        //below code is to expand all groups by default
//        for (int i = 0; i < listDataHeader.size(); i++) {
//            expandableListView.expandGroup(i);
//        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.setScrimColor(Color.TRANSPARENT);
//        drawer.setStatusBarBackgroundColor(Color.TRANSPARENT);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        View headerView= LayoutInflater.from(this).inflate(R.layout.app_header, null);
//        navigationView.addHeaderView(headerView);
//        navigationView.getHeaderView(0).setVisibility(View.GONE);
//        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        //below code is to expand all groups by default and make group header non clickable.
//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,int  groupPosition, long id) {
//                return true;
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Home");
        listDataHeader.add("Population");
        listDataHeader.add("Performance");
        listDataHeader.add("Action center");

        // Adding child data
        List<String> home = new ArrayList<String>();
        List<String> population = new ArrayList<String>();
        List<String> actionCenter = new ArrayList<String>();

        List<String> performance = new ArrayList<String>();
        performance.add("Summary");
        performance.add("Scorecard");
        performance.add("Scorecard Trending");
        performance.add("Medical Cost");
        performance.add("YoY performance");

        listDataChild.put(listDataHeader.get(0), home); // Header, Child data
        listDataChild.put(listDataHeader.get(1), population);
        listDataChild.put(listDataHeader.get(2), performance);
        listDataChild.put(listDataHeader.get(3), actionCenter);
    }

}
