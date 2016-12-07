package com.example.admin.cardreport;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity {
    DatabaseHelper myDB;
    EditText edtName;
    EditText edtSurname;
    EditText edtUsername;
    EditText edtPassword;
    EditText edtConfirm;
    EditText edtFaculty;
    Button bRegister;
    EditText edtCourse;
    EditText edtGender;
    EditText edtEmail;
    Spinner spnOccupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDB = new DatabaseHelper(this);

        spnOccupation = (Spinner) findViewById(R.id.occupation);
        edtName = (EditText) findViewById(R.id.name);
        edtSurname = (EditText) findViewById(R.id.surname);
        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        edtConfirm = (EditText) findViewById(R.id.confirm);
        edtFaculty = (EditText) findViewById(R.id.faculty);
        bRegister = (Button) findViewById(R.id.btnregister);
        edtEmail = (EditText) findViewById(R.id.email);
        edtConfirm = (EditText) findViewById(R.id.confirm);
        edtCourse = (EditText) findViewById(R.id.course);
        edtGender = (EditText) findViewById(R.id.genda);



        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertNewStudent();

            }
        });

    }

    public void InsertNewStudent() {


        String name = edtName.getText().toString();
        String surname = edtSurname.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String confirm = edtConfirm.getText().toString();
        String email = edtEmail.getText().toString();
        String faculty = edtFaculty.getText().toString();
        String course = edtCourse.getText().toString();
        String gender = edtGender.getText().toString();
        String occupation = spnOccupation.getSelectedItem().toString();
        Boolean inserted = false;

        //Toast.makeText(getApplicationContext(),occupation,Toast.LENGTH_SHORT).show();

        myDB = new DatabaseHelper(this);

        inserted = myDB.AddNewStudent(name,surname,username,gender,course,faculty,email,occupation,password);

        if(inserted == true){
            edtName.setText("");
            edtSurname.setText("");
            edtUsername.setText("");
            edtPassword.setText("");
            edtConfirm.setText("");
            edtEmail.setText("");
            edtFaculty.setText("");
            edtCourse.setText("");
            edtGender.setText("");
            Toast.makeText(getApplicationContext(),"Student Inserted Successfully",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(),"There was a problem, Student not inserted.",Toast.LENGTH_SHORT).show();
        }
    }
}
