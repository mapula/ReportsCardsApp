package com.example.admin.cardreport;

/**
 * Created by admin on 2016/11/21.
 */

public class Contact {
    int id,age;
    String name ,email,username,password;

    public void setId(int id){

        this.id = id;

    }
    public  int getId(){
        return  this.id;

    }
    public  void setAge(int age){
        this.age = age;
    }
    public  int getAge(){
        return  this.age;
    }

    public  void setName (String name){
        this.name = name;
    }
    public String getName(){

        return  this.name;
    }
    public  void setEmail (String email){
        this.email = email;
    }
    public String getEmail(){

        return  this.email;
    }
    public  void setPassword (String password){
        this.password = password;
    }
    public String getPassword(){

        return  this.password;
    }
    public  void setUsername (String username){
        this.username = username;
    }
    public String getUsername(){

        return  this.username;
    }
}
