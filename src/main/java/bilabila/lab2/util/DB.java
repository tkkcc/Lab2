package bilabila.lab2.util;

import java.sql.*;


public class DB {
    public static Connection createConnection() {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB", "root", "0");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static PreparedStatement prepare(Connection con,String sql){
        PreparedStatement ps =null;
        try {
            ps=con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static void close(Statement stmt){
        if (stmt==null) return;
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs){
        if (rs==null) return;
        try {
            rs.close();
            rs=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection con){
        if (con==null) return;
        try {
            con.close();
            con=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
