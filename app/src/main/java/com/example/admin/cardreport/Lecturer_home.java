package com.example.admin.cardreport;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lecturer_home extends AppCompatActivity {
    EditText edtname,edtsurname,edtID,edttest1,edttest2,edttest3,edtcourse,edtemail;
    Button btnsearch,btnupdate,btnview,btndelete;
    String StudentId;
    DatabaseHelper myDB;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_home);

        edtname = (EditText) findViewById(R.id.lec_name);
        edtsurname = (EditText) findViewById(R.id.lec_surname);
        edtID  = (EditText) findViewById(R.id.lec_stu_num);
        edtcourse = (EditText) findViewById(R.id.lec_course);
        edtemail = (EditText) findViewById(R.id.lec_email);
        edttest1  = (EditText) findViewById(R.id.lec_test1);
        edttest2 = (EditText) findViewById(R.id.lec_test2);
        edttest3 = (EditText) findViewById(R.id.lec_test3);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnsearch = (Button) findViewById(R.id.btnSearch);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btnview = (Button) findViewById(R.id.btnview);

        SetOnclickListeners();
    }

    private void SetOnclickListeners()
    {
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new DatabaseHelper(ctx);
                Cursor cr = myDB.getAllData();
                StringBuffer sb = new StringBuffer();;
                String stdName,stdSurname,stdCourse,stdemail,occupation,stdTest1,stdTest2,stdTest3;

                if(cr.getCount() > 0)
                {
                    cr.moveToFirst();


                    do
                    {

                        stdName = cr.getString(1);
                        stdSurname = cr.getString(2);
                        stdemail = cr.getString(4);
                        stdCourse = cr.getString(5);
                        occupation = cr.getString(9);

                        stdTest1 = String.valueOf(cr.getInt(10));
                        stdTest2 = String.valueOf(cr.getInt(11));
                        stdTest3 = String.valueOf(cr.getInt(12));

                        sb.append("Name :" + stdName + "\n");
                        sb.append("Surname :" + stdSurname + "\n");
                        sb.append("Email :" + stdemail + "\n");
                        sb.append("Occupation :" + occupation + "\n");
                        sb.append("Test1 :" + stdTest1 + "\n");
                        sb.append("Test2 :" + stdTest2 + "\n");
                        sb.append("Test3 :" + stdTest3 + "\n\n");


                    }while(cr.moveToNext());





                }

                AlertDialog.Builder build = new AlertDialog.Builder(ctx);
                build.setCancelable(true);
                build.setTitle("Data");
                build.setMessage(sb.toString());
                build.show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new DatabaseHelper(ctx);
                Boolean updated = false;
                String test1,test2,test3;
                test1 = edttest1.getText().toString();
                test2 = edttest2.getText().toString();
                test3 = edttest3.getText().toString();
                int t1 = 0;
                int t2 = 0;
                int t3 = 0;


                int stdid = Integer.valueOf(StudentId);
                try
                {
                    t1 = Integer.valueOf(edttest1.getText().toString());
                    t2 = Integer.valueOf(edttest2.getText().toString());
                    t3 = Integer.valueOf(edttest3.getText().toString());
                    updated = myDB.UpdateStudentMarks(stdid,t1,t2,t3);

                    if(updated == true)
                    {
                        edtname.setText("");
                        edtsurname.setText("");
                        edttest3.setText("");
                        edttest2.setText("");
                        edtcourse.setText("");
                        edtemail.setText("");
                        edtID.setText("");
                        edttest1.setText("");
                        btndelete.setEnabled(true);
                        btnview.setEnabled(true);
                        btnupdate.setEnabled(true);
                        Toast.makeText(getBaseContext(),"Student's Marks updated successfully",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"An error has occured, student marks could not be updated.",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex)
                {
                    Toast.makeText(getBaseContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new DatabaseHelper(ctx);
                StudentId = edtID.getText().toString();
                int stdId = Integer.valueOf(StudentId);
                Cursor cr = myDB.getStudentById(stdId);
                String stdName,stdSurname,stdCourse,stdemail,stdTest1,stdTest2,stdTest3;
/*
* 4 email
* 5 course
* 6 faculty
* */
                if(cr.getCount() > 0)
                {
                    cr.moveToFirst();
                    stdName = cr.getString(1);
                    stdSurname = cr.getString(2);
                    stdemail = cr.getString(4);
                    stdCourse = cr.getString(5);

                    stdTest1 = String.valueOf(cr.getInt(10));
                    stdTest2 = String.valueOf(cr.getInt(11));
                    stdTest3 = String.valueOf(cr.getInt(12));

                    edtname.setText(stdName);
                    edtsurname.setText(stdSurname);
                    edtemail.setText(stdemail);
                    edtcourse.setText(stdCourse);

                    edttest1.setText(stdTest1);
                    edttest2.setText(stdTest2);
                    edttest3.setText(stdTest3);

                    btndelete.setEnabled(true);
                    btnview.setEnabled(true);
                    btnupdate.setEnabled(true);
                    //Toast.makeText(getBaseContext(),String.valueOf(cr.getCount()),Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getBaseContext(),"Student Not found",Toast.LENGTH_LONG).show();
                    edtID.selectAll();
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new DatabaseHelper(ctx);
                Boolean deleted = false;
                StudentId = edtID.getText().toString();
                int stdId = Integer.valueOf(StudentId);

                deleted = myDB.DeleteStudentRecord(stdId);

                if(deleted == true)
                {
                    Toast.makeText(getBaseContext(),"Student has been successfully deleted.",Toast.LENGTH_SHORT).show();
                }else
                {
                    edtname.setText("");
                    edtsurname.setText("");
                    edttest3.setText("");
                    edttest2.setText("");
                    edtcourse.setText("");
                    edtemail.setText("");
                    edtID.setText("");
                    edttest1.setText("");
                    btndelete.setEnabled(true);
                    btnview.setEnabled(true);
                    btnupdate.setEnabled(true);
                    Toast.makeText(getBaseContext(),"An error has occured, student could not be deleted.",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
