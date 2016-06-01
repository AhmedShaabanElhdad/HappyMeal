package com.example.ahmedabomazin.happymeal;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vieworder extends Fragment
{
    List<Order> list=new ArrayList<Order>();
    ListView listView;
    myadapter adapter;


    List<Order> list2=new ArrayList<Order>();
    ListView listView2;
    myadapter adapter2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_vieworder,container,false);
        listView =(ListView)view.findViewById(R.id.listview_order);
        listView2 =(ListView)view.findViewById(R.id.listview_Dabba_order);



        OrderDBadapter dbAdapter=new OrderDBadapter(getActivity());
        try {
            dbAdapter.open();
            Cursor c = dbAdapter.getContact(getActivity().getIntent().getExtras().getString("userid"));
            if (c.moveToFirst())
            {
                do {
                    //Toast.makeText(this,"id: " + c.getString(0) + "\n Name: " + c.getString(1) + "\n Email: " + c.getString(2), Toast.LENGTH_LONG).show();
                    list.add(new Order(c.getString(0), c.getString(1),c.getString(2), c.getString(3),c.getString(4)));
                } while (c.moveToNext());
                dbAdapter.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




        adapter=new myadapter();



        listView.setAdapter(adapter);





        DabaaDBadapter dbAdapter2=new DabaaDBadapter(getActivity());
        try {
            dbAdapter2.open();
            Cursor c = dbAdapter2.getContact(getActivity().getIntent().getExtras().getString("userid"));
            if (c.moveToFirst())
            {
                do {
                    //Toast.makeText(this,"id: " + c.getString(0) + "\n Name: " + c.getString(1) + "\n Email: " + c.getString(2), Toast.LENGTH_LONG).show();
                    list2.add(new Order(c.getString(0), c.getString(1),c.getString(2), c.getString(3),c.getString(4)));
                } while (c.moveToNext());
                dbAdapter.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




        adapter2=new myadapter();



        listView2.setAdapter(adapter2);



        return view;
    }


    class myadapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view=null;
            view= getActivity().getLayoutInflater().inflate(R.layout.tabel,parent,false);

            TextView list_order_type=(TextView)view.findViewById(R.id.list_order_type);
            TextView list_order_address=(TextView)view.findViewById(R.id.list_order_address);
            TextView list_order_date=(TextView)view.findViewById(R.id.list_order_date);
            TextView list_order_price=(TextView)view.findViewById(R.id.list_order_price);

            Order custom_row=list.get(position);

            list_order_type.setText(""+custom_row.getType());
            list_order_address.setText(custom_row.getAddress());
            list_order_date.setText(custom_row.getDate());
            list_order_price.setText(custom_row.getPrice());

            return view;
        }
    }
}
