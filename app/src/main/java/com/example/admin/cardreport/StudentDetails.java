package com.example.admin.cardreport;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetails extends AppCompatActivity {
    EditText editname,editsurname,edtgender,edtemail,edtcourse,editID,edittest1,edittest2,edittest3;
    Button btns;
    DatabaseHelper myDB;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        Bundle b = getIntent().getExtras();
        Cursor cr ;
        int stdId = -1;
        String temp = "";

        myDB = new DatabaseHelper(this);
        if(b != null){
            temp = (String)b.get("StudentId");
            stdId = Integer.valueOf(temp);


            editname =(EditText)findViewById(R.id.name);
            editsurname =(EditText)findViewById(R.id.surname);
            editID = (EditText)findViewById(R.id.stu_num);
            edittest1 = (EditText)findViewById(R.id.test1);
            edittest2 = (EditText)findViewById(R.id.test2);
            edittest3 = (EditText)findViewById(R.id.test3);
            edtemail = (EditText)findViewById(R.id.s_email);
            edtcourse = (EditText)findViewById(R.id.s_course);
            edtgender = (EditText)findViewById(R.id.s_gender);
            btns =(Button)findViewById(R.id.add);
            String stdName,stdSurname,stdgender,stdemail,stdcourse,stdTest1,stdTest2,stdTest3;
            //Toast.makeText(StudentDetails.this,String.valueOf(stdId),Toast.LENGTH_SHORT).show();
            cr = myDB.getStudentById(stdId);
            //Toast.makeText(StudentDetails.this,String.valueOf(cr.getCount()),Toast.LENGTH_SHORT).show();
/*
* 4 email
* 5 course
* 6 faculty
* */
            if(cr.getCount() > 0){
                cr.moveToFirst();
                stdName = cr.getString(1);
                stdSurname = cr.getString(2);
                stdgender = cr.getString(3);
                stdemail = cr.getString(4);
                stdcourse = cr.getString(5);
                stdTest1 = String.valueOf(cr.getInt(10));
                stdTest2 = String.valueOf(cr.getInt(11));
                stdTest3 = String.valueOf(cr.getInt(12));

                editID.setText(temp);
                editname.setText(stdName);
                editsurname.setText(stdSurname);
                edtgender.setText(stdgender);
                edtemail.setText(stdemail);
                edtcourse.setText(stdcourse);
                edittest1.setText(stdTest1);
                edittest2.setText(stdTest2);
                edittest3.setText(stdTest3);
            }
            else{
                Toast.makeText(StudentDetails.this,"No Student Found",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

}



