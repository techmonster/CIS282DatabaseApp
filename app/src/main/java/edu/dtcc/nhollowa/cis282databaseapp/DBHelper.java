package edu.dtcc.nhollowa.cis282databaseapp;

import android.os.AsyncTask;
import android.widget.Switch;
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
    public AsyncResponse delegate = null;

    public void connect(String tableName) {
        new MyTask().execute(tableName);
    }


    public class MyTask extends AsyncTask<String, Void, ArrayList<String>> {



        protected ArrayList<String> doInBackground(String... tableName) {

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
                e.printStackTrace();



                // e.printStackTrace();
            }

            ArrayList<String> returnedData = new ArrayList<>();

            try {
                Statement st = null;
                if (connection != null) {
                    st = connection.createStatement();
                }
                String query;

                String tempName = tableName[0].toLowerCase();

                switch (tempName)
                {
                    case "project":
                        query = "SELECT * FROM PROJECT";
                        break;
                    case "employee":
                        query = "SELECT * FROM EMPLOYEE";
                        break;
                    case "task":
                        query = "SELECT * FROM TASK";
                        break;
                    case "task_employee":
                        query = "SELECT * FROM TASK_EMPLOYEE";
                        break;
                    default:
                        query = "SELECT * FROM PROJECT";
                        break;

                }
                //String sql1 = "SELECT * FROM PROJECT";
                //String sql2 = "SELECT * FROM EMPLOYEE";
                //String sql3 = "SELECT * FROM TASK";
                //String sql4 = "SELECT * FROM TASK_EMPLOYEE";


                //String result = "";


                final ResultSet rs;
                if (st != null) {
                    rs = st.executeQuery(query);

                    populateData(rs, tempName, returnedData);

                    /*for (String s : returnedData) {
                        result += s;
                    }*/

                }
                 //System.out.print(result);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return returnedData;
        }

        protected void onPostExecute(ArrayList<String> result){
            delegate.processFinish(result);
        }

        public void populateData(ResultSet set, String tableName, ArrayList<String> data){

            //ArrayList<String> data = new ArrayList<>();
            try{
                if(tableName.equals("project")) {
                    while (set.next()) {
                        String result = set.getInt(1) + "\t";
                        //result += rsmd.getColumnName(2) + ":" + set.getInt(2) + "\n";
                        result += set.getString(2) + "\t";
                        result += set.getString(3) + "\t";
                        result += set.getInt(4) + "\n";

                        // result = set.getString(1);
                        data.add(result);
                    }
                }
                if(tableName.equals("employee")) {
                    while (set.next()) {
                        String result = set.getInt(1) + "\t";
                        //result += rsmd.getColumnName(2) + ":" + set.getInt(2) + "\n";
                        result += set.getString(2) + "\t";
                        result += set.getString(3) + "\n";

                        // result = set.getString(1);
                        data.add(result);
                    }
                }
                if(tableName.equals("task")) {
                    while (set.next()) {
                        String result = set.getInt(1) + "\t";
                        //result += rsmd.getColumnName(2) + ":" + set.getInt(2) + "\n";
                        result += set.getInt(2) + "\t";
                        result += set.getString(3) + "\t";
                        result += set.getString(4) + "\t";
                        result += set.getInt(5) + "\n";

                        // result = set.getString(1);
                        data.add(result);
                    }
                }
                if(tableName.equals("task_employee")) {
                    while (set.next()) {
                        String result = set.getInt(1) + "\t";
                        //result += rsmd.getColumnName(2) + ":" + set.getInt(2) + "\n";
                        result += set.getInt(2) + "\n";


                        // result = set.getString(1);
                        data.add(result);
                    }
                }

            } catch (Exception e){
                System.out.println(e);
            }

        }
    }

}
