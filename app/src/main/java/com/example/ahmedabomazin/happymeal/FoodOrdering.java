package com.example.ahmedabomazin.happymeal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FoodOrdering extends Fragment {

    String type;
    String price_item;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Continue Ordering");




        View view=inflater.inflate(R.layout.activity_food_ordering,container,false);


        final List<String> price=new ArrayList<String>();
        price.add("80");
        price.add("240");

        final Spinner spinner_type1=(Spinner)view.findViewById(R.id.spinner_type1);
        List<String> type1list=new ArrayList<String>();
        type1list.add("Veg");
        type1list.add("NonVeg");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,type1list);
        spinner_type1.setAdapter(adapter);


        spinner_type1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                type=String.valueOf(position + 1);
                price_item=price.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_type1.setSelection(0);
                type=String.valueOf(1);
                price_item=price.get(0);

            }
        });


        final Spinner spinner_type2=(Spinner)view.findViewById(R.id.spinner_type2);
        List<String> type2list=new ArrayList<String>();
        type2list.add("Simple");
        type2list.add("Delux");
        type2list.add("Maharaja");
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,type2list);
        spinner_type2.setAdapter(adapter2);


        spinner_type2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_type2.setSelection(0);

            }
        });


        final Spinner spinner_subscribe=(Spinner)view.findViewById(R.id.spinner_subscribe);
        List<String> subscribe_time=new ArrayList<String>();
        subscribe_time.add("Today");
        subscribe_time.add("Monthly");
        subscribe_time.add("Quaterly");
        subscribe_time.add("Yearly");
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,subscribe_time);
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

        final Spinner spinner_time=(Spinner)view.findViewById(R.id.spinner_time);
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
        ArrayAdapter<String> adapter4=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,timelist);
        spinner_time.setAdapter(adapter4);


        spinner_type2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_time.setSelection(0);

            }
        });

        final Spinner spinner_am_pm=(Spinner)view.findViewById(R.id.spinner_am_pm);
        List<String> am_pm_list=new ArrayList<String>();
        am_pm_list.add("AM");
        am_pm_list.add("PM");
        ArrayAdapter<String> adapter5=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,am_pm_list);
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




        Button btn_food_calculate =(Button)view.findViewById(R.id.btn_food_calculate);
        final EditText edit_food_id=(EditText)view.findViewById(R.id.edit_food_id);
        final EditText edit_food_address=(EditText)view.findViewById(R.id.edit_food_address);
        final EditText edit_food_quality=(EditText)view.findViewById(R.id.edit_food_quality);
        final Calendar calendar=Calendar.getInstance();

        edit_food_id.setText(((MainActivity) getActivity()).userid);






        btn_food_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                long check = 0;
                if (edit_food_quality.getText().length() == 0) {
                    edit_food_quality.setError("Please Enter your quality");
                    edit_food_quality.requestFocus();
                    return;
                }
                if (edit_food_address.getText().length() == 0) {
                    edit_food_address.setError("Please Enter your quality");
                    edit_food_address.requestFocus();
                    return;
                }

                OrderDBadapter orderDBadapter=new OrderDBadapter(getActivity());

                try
                {
                    orderDBadapter.open();
                    try {

                        check = orderDBadapter.insertContact(edit_food_id.getText().toString(), type, calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR), edit_food_address.getText().toString(), price_item);
                        orderDBadapter.close();
                    }
                    catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                if(check > -1) {
                    Toast.makeText(getActivity(), "The order saved successfully", Toast.LENGTH_SHORT).show();
                    String total_price = String.valueOf(Integer.parseInt(edit_food_quality.getText().toString()) * Integer.parseInt(price_item));
                    ((MainActivity)getActivity()).cost=total_price;

                    Fragment cont_food_ordering = new cont_food_ordering();
                    //EditText edit_get_cost =(EditText)getActivity().findViewById(R.id.edit_get_cost);
                    //edit_get_cost.setText(total_price);
                    getFragmentManager().beginTransaction().replace(R.id.maincontent, cont_food_ordering).commit();



                }
            }
        });

        return view;
    }

}
