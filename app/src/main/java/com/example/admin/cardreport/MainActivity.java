package com.example.admin.cardreport;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button btnReg, btnLogin;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Context ctx = this;
    Cursor cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);


        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        btnReg = (Button) findViewById(R.id.button_reg);
        btnLogin = (Button) findViewById(R.id.button_login);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper = new DatabaseHelper(ctx);

                int counter = 0;
                String s = "";
                Boolean userfound = false;

                String userUserName = username.getText().toString();
                String userPassword = password.getText().toString().trim();
                String DBPass = "";
                String occupation = "";
                int StudentId = -1;
                if(userUserName != "" && userPassword != ""){
                    Cursor cr = dbHelper.getStudentByUsername(userUserName);

                    if(cr.getCount() > 0){
                        cr.moveToFirst();
                        StudentId = cr.getInt(0);
                        DBPass = cr.getString(8).trim(); //get the password
                        occupation = cr.getString(9); // used to check if the person logged in is a Lecturer or Student


                        if(DBPass.equals(userPassword)){

                            if(occupation.equals("Student")){
                                Intent i = new Intent(MainActivity.this,StudentDetails.class);

                                i.putExtra("StudentId",String.valueOf(StudentId));
                                startActivity(i);
                            }
                            else if(occupation.equals("Lecturer")){
                                Intent i = new Intent(MainActivity.this,Lecturer_home.class);
                                startActivity(i);
                            }



                        }else{
                            Toast.makeText(getBaseContext(),"Password entered is not correct",Toast.LENGTH_LONG).show();
                            password.selectAll();
                        }
                    }else{
                        Toast.makeText(getBaseContext(),"Username Not found" ,Toast.LENGTH_SHORT).show();
                        username.selectAll();
                    }

                }
                else{
                    Toast.makeText(getBaseContext(),"Username or Password cannot be blank",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
