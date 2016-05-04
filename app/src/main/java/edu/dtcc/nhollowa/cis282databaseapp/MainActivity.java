package edu.dtcc.nhollowa.cis282databaseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

   // DBHelper.MyTask asyncTask = new DBHelper.MyTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DBHelper helper = new DBHelper();
        helper.delegate = this;
        helper.connect("employee");


        /*for(int i = 0; i < test.size(); i++) {
            System.out.print(test.get(i));
        }*/

    }


    public void processFinish(ArrayList<String> output){
        for(int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i));
        }
    }



}
