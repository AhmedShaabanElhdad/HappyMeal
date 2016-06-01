package com.example.ahmedabomazin.happymeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {

    String SelectedGendor="";
    boolean check=true;
    EditText edit_firstname;
    EditText edit_lastname;
    EditText edit_middlename;
    EditText edit_password;
    EditText edit_username;
    EditText edit_address;
    EditText edit_city;
    EditText edit_contact_num;
    EditText edit_email;
    EditText edit_uid;

    public Registration() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        edit_firstname=(EditText)findViewById(R.id.edit_firstname);
        edit_lastname=(EditText)findViewById(R.id.edit_lastname);
        edit_middlename=(EditText)findViewById(R.id.edit_middlename);
        edit_password=(EditText)findViewById(R.id.edit_passwords);
        edit_username=(EditText)findViewById(R.id.edit_username);
        edit_address=(EditText)findViewById(R.id.edit_address);
        edit_city=(EditText)findViewById(R.id.edit_city);
        edit_contact_num=(EditText)findViewById(R.id.edit_contact_num);
        edit_email=(EditText)findViewById(R.id.edit_email);
        edit_uid=(EditText)findViewById(R.id.edit_uid);


        final Spinner genorSpinner=(Spinner)findViewById(R.id.spiner_gendor);
        final List<String> genorslist=new ArrayList<String>();
        genorslist.add("Male");
        genorslist.add("Female");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,genorslist);
        genorSpinner.setAdapter(adapter);


        genorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedGendor=genorslist.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genorSpinner.setSelection(0);
                SelectedGendor=genorslist.get(0);
            }
        });

        Button btn_register=(Button)findViewById(R.id.btn_register);
        Button btn_check=(Button)findViewById(R.id.btn_check);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter=new DBAdapter(Registration.this);

                    if (edit_username.getText().length()==0)
                    {
                        edit_username.setError("Please Enter your username");
                        edit_username.requestFocus();
                        return;
                    }

                    dbAdapter.open();
                    if(dbAdapter.getusername(edit_username.getText().toString()).moveToFirst())
                    {
                        edit_username.setError("username is already Exist");
                        edit_username.requestFocus();
                    }
                    dbAdapter.close();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                DBAdapter dbAdapter=new DBAdapter(Registration.this);


                if (check)
                {
                    dbAdapter.open();
                    if(dbAdapter.getusername(edit_username.getText().toString()).moveToFirst())
                    {
                        edit_username.setError("username is already Exist");
                        edit_username.requestFocus();
                        dbAdapter.close();
                        return;
                    }


                    //dbAdapter.open();
                    long rows= dbAdapter.insertContact(edit_firstname.getText().toString(), edit_middlename.getText().toString(), edit_lastname.getText().toString(), SelectedGendor, edit_email.getText().toString(), edit_contact_num.getText().toString(), edit_city.getText().toString(), edit_address.getText().toString(), edit_username.getText().toString(), edit_password.getText().toString(), edit_uid.getText().toString());
                    if(rows>-1)
                        Toast.makeText(Registration.this,"the item added successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Registration.this,"the item addition failed", Toast.LENGTH_SHORT).show();
                    dbAdapter.close();
                }
            }
        });

    }


    public void validate()
    {
        check=true;
        if (edit_firstname.getText().length() == 0) {
            edit_firstname.setError("Please Enter your name");
            edit_firstname.requestFocus();
            check = false;
        }

        if (edit_middlename.getText().length() == 0) {
            edit_middlename.setError("Please Enter your Middle name");
            edit_middlename.requestFocus();
            check = false;
        }

        if (edit_lastname.getText().length() == 0) {
            edit_lastname.setError("Please Enter your Last name");
            edit_lastname.requestFocus();
            check = false;
        }

        if (edit_city.getText().length() == 0) {
            edit_city.setError("Please Enter your City");
            edit_city.requestFocus();
            check = false;
        }

        if (edit_address.getText().length() == 0) {
            edit_address.setError("Please Enter your Address");
            edit_address.requestFocus();
            check = false;
        }

        if (edit_contact_num.getText().length() == 0) {
            edit_contact_num.setError("Please Enter your Contact number");
            edit_contact_num.requestFocus();
            check = false;
        }

        if (edit_email.getText().length() == 0) {
            edit_email.setError("Please Enter your Email");
            edit_email.requestFocus();
            check = false;
        }

        //String emailPattern="^[A-Za-z0-9-\\+]+(\\.)";



        if (edit_password.getText().length() == 0) {
            edit_password.setError("Please Enter the password");
            edit_password.requestFocus();
            check = false;
        }

        if (edit_username.getText().length() == 0) {
            edit_username.setError("Please Enter your username");
            edit_username.requestFocus();
            check = false;
        }
    }
}
