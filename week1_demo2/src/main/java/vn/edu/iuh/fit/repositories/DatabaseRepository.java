package vn.edu.iuh.fit.repositories;



import vn.edu.iuh.fit.entities.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/mariadb_1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.err.println("Lỗi khi kết nối đến cơ sở dữ liệu.");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Đóng kết nối đến cơ sở dữ liệu.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Lỗi khi đóng kết nối đến cơ sở dữ liệu.");
            }
        }
    }

    public void addAccount(Account acc){
        PreparedStatement statement = null;
        try {
            statement  = connection.prepareStatement("INSERT INTO mydb.account VALUES(?,?,?,?,?,?)");
            statement.setString(1, acc.getAccount_id());
            statement.setString(2,acc.getFull_name());
            statement.setString(3,acc.getPassword());
            statement.setString(4,acc.getEmail());
            statement.setString(5,acc.getPhone());
            statement.setInt(6,acc.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(statement !=null){
            System.err.println("Add account success!!!!");
        }else{
            System.err.println("Add account false!!!!");
        }
    }
    public void delAccountById(String id){
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE from mydb.account where account_id=?");
            statement.setString(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(statement != null){
            System.err.println("Delete account success!!!");
        }else{
            System.err.println("Delete account false!!!");
        }
    }
    public void updateAccount(Account account){
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("update mydb.account set full_name=?,password=?,email=?,phone=?,status=? where account_id=?");
            statement.setString(1,account.getFull_name());
            statement.setString(2,account.getPassword());
            statement.setString(3,account.getEmail());
            statement.setString(4,account.getPhone());
            statement.setInt(5,account.getStatus());
            statement.setString(6,account.getAccount_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(statement != null){
            System.err.println("Update account success!!!");
        }else{
            System.err.println("Update account false!!!");
        }
    }
//public String findAccByUserPass(String user,String pas) {
//
//    PreparedStatement statement = null;
//    String userName = null;
//    try {
//        statement = connection.prepareStatement("SELECT * FROM mydb.account where account_id=? and password=?");
//        statement.setString(1, user);
//        statement.setString(2, pas);
//        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            userName = rs.getString(1);
//
//        }
//
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//    return userName;
//}
}