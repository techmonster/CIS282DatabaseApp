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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DBHelper helper = new DBHelper();
        helper.connect();

    }

    public class DBHelper {

        public void connect() {
            new MyTask().execute();
        }

        public class MyTask extends AsyncTask<Void, Void, Void> {
            public AsyncResponse delegate = null;

            // @Override
            private void onPostExecute(String result){
                delegate.processFinish(result);
            }
            @Override
            protected Void doInBackground(Void... voids) {

                String url = "jdbc:mysql://phpmyadmin.cdgwdgkn5fuv.us-west-2.rds.amazonaws.com:3306/cis282_project";
                String user = "manager";
                String password = "manager";
                Connection connection = null;


                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Driver loaded :)");

                } catch (ClassNotFoundException e) {
                    System.out.println("Driver not loaded :(");
                    // e.printStackTrace();
                }

                try {
                    connection = DriverManager.getConnection(url, user, password);
                    System.out.println("Connection Succeeded :)");
                } catch (SQLException e) {
                    System.out.println("Connection Failed :(");
                    // e.printStackTrace();
                }

                try {
                    Statement st = null;
                    if (connection != null) {
                        st = connection.createStatement();
                    }
                    String sql1 = "SELECT * FROM PROJECT";
                    String sql2 = "SELECT * FROM EMPLOYEE";
                    String sql3 = "SELECT * FROM TASK";
                    String sql4 = "SELECT * FROM TASK_EMPLOYEE";


                    String result = "";

                    ArrayList<String> returnedData = new ArrayList<>();
                    final ResultSet rs;
                    if (st != null) {
                        rs = st.executeQuery(sql2);

                        returnedData = populateData(rs);

                        for (String s : returnedData) {
                            result += s;
                        }


                    }
                    System.out.print(result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public ArrayList<String> populateData(ResultSet set){

                String result;
                ArrayList<String> data = new ArrayList<>();
                try{
                    while (set.next()) {
                        result = null;
                        result += set.getInt(1) + "\t";
                        //result += rsmd.getColumnName(2) + ":" + set.getInt(2) + "\n";
                        result += set.getString(2) + "\t";
                        result += set.getString(3) + "\n";

                        data.add(result);
                    }

                } catch (Exception e){
                    System.out.println(e);
                }
                return data;

            }
        }

    }
}
