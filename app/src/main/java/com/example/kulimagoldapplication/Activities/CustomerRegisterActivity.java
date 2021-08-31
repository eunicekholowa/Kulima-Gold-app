package com.example.kulimagoldapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kulimagoldapplication.Databases.DBHelper;
import com.example.kulimagoldapplication.R;

public class CustomerRegisterActivity extends AppCompatActivity {

    private Button bRegister;
    private TextView sRegister;
    private EditText Email;
    private EditText Password;
    private EditText rePassword;
    DBHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        bRegister= (Button)findViewById(R.id.bRegister);
        sRegister= (TextView)findViewById(R.id.sRegister);
        Email = (EditText) findViewById(R.id.Email);
        Password= (EditText) findViewById(R.id.Password);
        rePassword= (EditText) findViewById(R.id.rePassword);
        MyDB =new DBHelper(this);

        sRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CustomerRegisterActivity.this, CustomerSigninActivity.class);
                startActivity(intent);
            }
        });
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Email.getText().toString();
                String pass = Password.getText().toString();
                String repass = rePassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(CustomerRegisterActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)) {
                        Boolean checkuser = MyDB.checkEmail(user);
                        if (checkuser == false) {
                            Boolean insert = MyDB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(CustomerRegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), CustomerSigninActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(CustomerRegisterActivity.this, "Registration   failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CustomerRegisterActivity.this, "User already exist! Please Login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CustomerRegisterActivity.this, "password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}