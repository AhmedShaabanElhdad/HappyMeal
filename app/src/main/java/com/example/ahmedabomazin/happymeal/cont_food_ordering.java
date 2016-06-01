package com.example.ahmedabomazin.happymeal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class cont_food_ordering extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_cont_food_ordering,container,false);
        Button btn_order=(Button)view.findViewById(R.id.btn_order);

        EditText editcost=(EditText)view.findViewById(R.id.edit_get_cost);
        editcost.setText(((MainActivity)getActivity()).cost);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Fragment vieworder = new vieworder();
                getFragmentManager().beginTransaction().replace(R.id.maincontent, vieworder).commit();
            }
        });



        return view;
    }
}
