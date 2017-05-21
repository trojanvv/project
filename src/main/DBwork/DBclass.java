package main.DBwork;

import java.sql.*;

/**
 * Created by virus on 16.04.2017.
 */
public class DBclass {
    private String urlDB = "jdbc:postgresql://localhost:5432/project_of";
    private String login = "postgres";
    private String password = "1357911";

    private Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;

    public DBclass(){
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDB, login, password);
            stat = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ResultSet query (String sql){
        try {
            rs = stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public String insert (String sql){
        try {
            if (stat.executeUpdate(sql) == 1)
                return "true insert";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "false insert";
    }
    public String update (String sql){
        try {
            if (stat.execute(sql))
                return "true update";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "false update";
    }
    public void close (){
        try {
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
