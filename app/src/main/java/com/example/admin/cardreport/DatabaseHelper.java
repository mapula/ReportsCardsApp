package com.example.admin.cardreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import STUDENTS.CLASS.cardreport.Students;

import static com.example.admin.cardreport.R.id.email;

/**
 * Created by admin on 2016/11/22.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";

    private static final String TABLE_NAME = "Students";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_SURNAME = "Surname";
    private static final String COLUMN_COURSE = "Course" ;
    private static final String COLUMN_GENDER = "Gender";
    private  static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_EMAIL = "Email";
    private  static final String COLUMN_FACULTY = "Faculty";
    private static final String COLUMN_TEST1 = "Test1";
    private static final String COLUMN_TEST2 = "Test2";
    private  static final String COLUMN_TEST3 = "Test3";
    private  static final String COLUMN_TEST4 = "Test4";

//    Students students = new Students();


    public DatabaseHelper(Context context )  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE = "create table Students(id integer primary key autoincrement," +
                "name text not null ,surname text not null ,gender text not null ,email not null, course text not null" +
                " ,faculty text not null ,username not null, password text not null,occupation,test1 integer, test2 integer, test3 integer, test4 integer);";

        //String s = "CREATE TABLE " + TABLE_NAME + "("+ COLUMN_ID + " interger PRIMARY KEY AUTOINCREMENT, "
          //     + COLUMN_NAME+" TEXT, "+ COLUMN_SURNAME+" TEXT, "+COLUMN_EMAIL+ " TEXT, " + COLUMN_GENDER
            //    + " TEXT, " + COLUMN_COURSE + " TEXT, " +COLUMN_FACULTY+ " TEXT, " + COLUMN_USERNAME + " TEXT, "
              //  + COLUMN_PASSWORD + " TEXT, " + COLUMN_TEST1 + " integer, "+COLUMN_TEST2
                //+" integer, "+ COLUMN_TEST3+" integer, " + COLUMN_TEST4 + " integer);";
        //db.execSQL(s);
        db.execSQL(TABLE_CREATE);

    }

    public Cursor getStudentById(int StudentNo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr;

//        cr = db.rawQuery("select * from Students",null);
        cr = db.query("Students",null,"id=?",new String[]{String.valueOf(StudentNo)},null,null,null);
        return  cr;
    }

    public Cursor getStudentByUsername(String uName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr;


        cr = db.query("Students",null,"username=?",new String[]{uName},null,null,null);
        return  cr;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr;

        cr = db.rawQuery("select * from Students",null);
        //cr = db.query("Students",null,"id=?",new String[]{String.valueOf(StudentNo)},null,null,null);
        return  cr;
    }

    public boolean UpdateStudentMarks(int StudentId,int test1, int test2, int test3)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues data = new ContentValues();

        data.put("test1",test1);
        data.put("test2",test2);
        data.put("test3",test3);
        return db.update("Students",data,"id = " + StudentId,null) > 0;
    }

    public boolean DeleteStudentRecord(int StudentId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete("Students","id = " + StudentId,null) > 0;
    }



    public boolean AddNewStudent(String name,String surname ,String username, String gender, String course,String faculty,String Email, String occupation,String password) {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        values.put("name", name);
        values.put("surname", surname);
        values.put("gender", gender);
        values.put("faculty",faculty);
        values.put("course", course);
        values.put("email", Email);
        values.put("password", password);
        values.put("username", username);
        values.put("occupation",occupation);

       long results = db.insert(TABLE_NAME,null,values);
        db.close();

        return (results > 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        //db.execSQL(query);
        //onCreate(db);
    }

}