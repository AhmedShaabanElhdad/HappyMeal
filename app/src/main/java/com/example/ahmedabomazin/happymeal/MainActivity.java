package com.example.ahmedabomazin.happymeal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    public static String userid;
    public static String cost;
    public static int i=0;

    DrawerLayout drawerLayout;
    RelativeLayout sideDrawer;
    List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getExtras().getString("userid")!=null)
        {
            userid=getIntent().getExtras().getString("userid");
        }
        else
        {
            startActivity(new Intent(this,Login.class));
        }



        /*final Spinner spinner_type1=(Spinner)findViewById(R.id.main_spinner_type1);
        List<String> type1list=new ArrayList<String>();
        type1list.add("Veg");
        type1list.add("NonVeg");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item,type1list);
        spinner_type1.setAdapter(adapter);


        spinner_type1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_type1.setSelection(0);

            }
        });






        final Spinner spinner_subscribe=(Spinner)findViewById(R.id.main_spinner_subscribe);
        List<String> subscribe_time=new ArrayList<String>();
        subscribe_time.add("Today");
        subscribe_time.add("Monthly");
        subscribe_time.add("Quaterly");
        subscribe_time.add("Yearly");
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,subscribe_time);
        spinner_subscribe.setAdapter(adapter3);


        spinner_subscribe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_subscribe.setSelection(0);

            }
        });

        final Spinner spinner_time=(Spinner)findViewById(R.id.main_spinner_time);
        List<String> timelist=new ArrayList<String>();
        timelist.add("9:00");
        timelist.add("9:30");
        timelist.add("10:00");
        timelist.add("10:30");
        timelist.add("11:00");
        timelist.add("11:30");
        timelist.add("12:00");
        timelist.add("12:30");
        timelist.add("1:00");
        timelist.add("1:30");
        timelist.add("2:00");
        timelist.add("2:30");
        timelist.add("3:00");
        timelist.add("3:30");
        ArrayAdapter<String> adapter4=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,timelist);
        spinner_time.setAdapter(adapter4);


        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_time.setSelection(0);

            }
        });

        final Spinner spinner_am_pm=(Spinner)findViewById(R.id.main_spinner_am_pm);
        List<String> am_pm_list=new ArrayList<String>();
        am_pm_list.add("AM");
        am_pm_list.add("PM");
        ArrayAdapter<String> adapter5=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,am_pm_list);
        spinner_am_pm.setAdapter(adapter5);


        spinner_am_pm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_am_pm.setSelection(0);

            }
        });

        */


        Fragment Dabaa_order = new Dabaa_order();
        getFragmentManager().beginTransaction().replace(R.id.maincontent, Dabaa_order).commit();





        /*===============================================================================================*/
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        sideDrawer=(RelativeLayout)findViewById(R.id.drawer);

        ListView listView=(ListView)findViewById(R.id.navlist);


        list.add("Food Order");
        list.add("Order");
        list.add("View order");
        list.add("Log out");
        ArrayAdapter<String> listadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(listadapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                findselectedView(position);
            }
        });
    }

    public void findselectedView(int position)
    {
        if(position==0) {
            Fragment FoodOrdering = new FoodOrdering();
            getFragmentManager().beginTransaction().replace(R.id.maincontent, FoodOrdering).commit();
            setTitle(list.get(position));
        }

        else if(position ==1) {
            Fragment Dabaa_order = new Dabaa_order();
            getFragmentManager().beginTransaction().replace(R.id.maincontent, Dabaa_order).commit();
            setTitle(list.get(position));
        }
        else if(position ==2) {
            Fragment vieworder = new vieworder();
            getFragmentManager().beginTransaction().replace(R.id.maincontent, vieworder).commit();
            setTitle(list.get(position));
        }

        else if(position ==3) {

            startActivity(new Intent(MainActivity.this,Login.class));
        }


        drawerLayout.closeDrawer(sideDrawer);

    }


}
