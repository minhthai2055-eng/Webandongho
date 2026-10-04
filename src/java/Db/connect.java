package Db;
import java.sql.*;
public class connect {
    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/watchluxury";
        String user = "root";
        String pass = ""; 
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
    public static void main(String[] args) {
        try{
            System.out.println(new connect().getConnection());
        }catch(Exception e){
            
        }
    }
}
