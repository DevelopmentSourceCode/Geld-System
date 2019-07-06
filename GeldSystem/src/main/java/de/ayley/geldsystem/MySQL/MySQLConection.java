package de.ayley.geldsystem.MySQL;

import de.ayley.geldsystem.API.EinfacheMethoden;
import de.ayley.geldsystem.API.Strings;

import java.sql.*;

public class MySQLConection {

    private String Host;
    private String Database;
    private String User;
    private String Password;

    public Connection con;

    private String Prefix = Strings.Prefix;

    public MySQLConection(String host, String database, String user, String password) {
        this.Host = host;
        this.Database = database;
        this.User = user;
        this.Password = password;

        connect();
    }

    public void connect(){
        try {
            if (con != null && !con.isClosed()) {
                return;
            }

            synchronized (this) {
                if (con != null && !con.isClosed()) {
                    return;
                }
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://" + Host + ":3306/" + Database, User, Password);
                    EinfacheMethoden.consolMessage(Prefix + "MySQL ist verbunden!!!");
                }catch (ClassNotFoundException e){

                }

            }
        }catch (SQLException e){

        }
    }

    public void close() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {

        }
    }

    public void update(String statment) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(statment);
            st.close();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
    }

    public ResultSet querry(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }


    public boolean isConnected(){
        return (con != null);
    }

}
