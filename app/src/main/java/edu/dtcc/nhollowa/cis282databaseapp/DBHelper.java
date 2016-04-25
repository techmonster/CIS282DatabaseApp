package edu.dtcc.nhollowa.cis282databaseapp;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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



                final ResultSet rs;
                if (st != null) {
                    rs = st.executeQuery(sql1);

                    while (rs.next()) {
                        System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" +rs.getString(3)
                                + "\t" +rs.getString(4));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
