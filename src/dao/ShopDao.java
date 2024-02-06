package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.util.*;
import Connection.JDBC;
import model.ShopBean;

public class ShopDao {
private Connection connection;
Scanner scn = new Scanner(System.in);
public ShopDao(){
    this.connection = JDBC.getConnection();
}
public ShopBean logInStaff (int id, String password) throws SQLException {
    ShopBean result = null;
    String query = "select * from shop where shop_id = ? AND shop_password = ?";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    preparedStatement.setInt(1,id);
    preparedStatement.setString(2,password);
    ResultSet resultSet = preparedStatement.executeQuery();
    if(resultSet.next()){
        int i = resultSet.getInt("shop_id");
        String n = resultSet.getString("shop_name");
        String c = resultSet.getString("shop_contact");
        String a = resultSet.getString("shop_address");
        result = new ShopBean(i,n,a,c);
    }
    return result;
}

public void addSweets() throws SQLException {
    String query = "insert into sweets values(?,?,?,?)";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    System.out.println("Please enter the sweets' data");
    System.out.println("Enter sweet id: ");
    int s_id = scn.nextInt();
    System.out.println("Enter sweet name: ");
    String s_name = scn.next();
    System.out.println("Enter sweet quantity: ");
    int s_quantity = scn.nextInt();
    System.out.println("Enter sweet cost (per kg): ");
    int s_cost = scn.nextInt();
    preparedStatement.setInt(1,s_id);
    preparedStatement.setString(2,s_name);
    preparedStatement.setInt(3,s_quantity);
    preparedStatement.setInt(4,s_cost);
    int isWorked = preparedStatement.executeUpdate();
    if(isWorked > 0){
        System.out.println("Data Inserted Successfully");
        String query2 = "insert into expenses(sweet_id,sweet_name,sweet_quantity,Total) values (?,?,?,?)";
        PreparedStatement ps = this.connection.prepareStatement(query2);
        ps.setInt(1,s_id);
        ps.setString(2,s_name);
        ps.setInt(3,s_quantity);
        int total_e = s_cost * s_quantity;
        ps.setInt(4,total_e);
        int worked = ps.executeUpdate();
        if(worked > 0){
            System.out.println("Expenses of sweet" + s_name + " added in expenses");
        }else{
            System.out.println("Not able to add in the expenses");
        }
    }else{
        System.out.println("Sweets data not inserted");
    }


}

public void checkExpenses() throws  SQLException{

}

public void updateSweets() throws  SQLException{

}

}
