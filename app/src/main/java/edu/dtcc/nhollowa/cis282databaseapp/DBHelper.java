package edu.dtcc.nhollowa.cis282databaseapp;

import android.os.AsyncTask;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by anndroid on 4/25/16.
 */
public class DBHelper {

    public void connect() {
        new myTask().execute();
    }

    private class myTask extends AsyncTask<Void, Void, Void> {
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
