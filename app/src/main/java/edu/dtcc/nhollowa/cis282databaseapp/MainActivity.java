package edu.dtcc.nhollowa.cis282databaseapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

   // DBHelper.MyTask asyncTask = new DBHelper.MyTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DBHelper helper = new DBHelper();
        helper.connect("task");
        System.out.println(helper.getCurrentResult());

    }


    /*void processFinish(String output){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
    }*/



}
