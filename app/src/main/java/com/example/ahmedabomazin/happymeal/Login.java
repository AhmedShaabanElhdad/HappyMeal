package com.example.ahmedabomazin.happymeal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView txt_go_to_register=(TextView)findViewById(R.id.txt_go_to_register);
        txt_go_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),Registration.class));
            }
        });

        Button btn_login=(Button)findViewById(R.id.btn_login);
        final EditText uid=(EditText)findViewById(R.id.edit_username);
        final EditText password=(EditText)findViewById(R.id.edit_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DBAdapter dbAdapter= new DBAdapter(getBaseContext());

                try {
                    dbAdapter.open();
                    Cursor cursor = dbAdapter.getusername(uid.getText().toString(), password.getText().toString());

                    if (cursor.moveToFirst()) {
                        Toast.makeText(Login.this,cursor.getString(1), Toast.LENGTH_SHORT).show();
                        Bundle bundle=new Bundle();
                        bundle.putString("userid",uid.getText().toString());
                        Intent intent= new Intent(getBaseContext(), MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(Login.this,"incorrect", Toast.LENGTH_SHORT).show();
                    dbAdapter.close();
                }
                catch(Exception e)
                {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
