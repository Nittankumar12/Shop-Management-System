package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.util.*;
import Connection.JDBC;
import com.mysql.cj.protocol.Resultset;
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

    String query = "select * from expenses";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery(query);
    while(resultSet.next()){
        System.out.println(resultSet.getInt("expense_id") +" "+resultSet.getInt("sweet_id")  + " " + resultSet.getString("sweet_name") + " " + resultSet.getInt("sweet_quantity") + " " + resultSet.getInt("Total"));
    }
    }
public void updateSweets() throws  SQLException{
    System.out.println("Updating sweets' data");
    System.out.println("Enter the sweet id: ");
    int sid = scn.nextInt();
    System.out.println("Enter the sweet name: ");
    String s_name = scn.next();
    String query1 = "select * from sweets where s_id = ?";
    PreparedStatement ps = this.connection.prepareStatement(query1);
    ps.setInt(1,sid);
    ResultSet resultset = ps.executeQuery();
    if(resultset.next()){
        int prev_q = resultset.getInt("s_quantity");
        int prev_c = resultset.getInt("s_cost");
        System.out.println("Previous quantity is: " + prev_q + " and cost is: " + prev_c);
        System.out.println("updated quantity is: ");
        int new_q = scn.nextInt();

        System.out.println("Updated cost: ");
        int new_c = scn.nextInt();

        String query = "update sweets set s_quantity = ? , s_cost = ? where s_id = ? ";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,new_q);
        preparedStatement.setInt(2,new_c);
        preparedStatement.setInt(3,sid);
        int isWorked = preparedStatement.executeUpdate();
        if(isWorked > 0){
            System.out.println("Data updated successfully!!");
            System.out.println("Now available sweets are: ");
            checkSweets();
        }else{
            System.out.println("Unable to update the data");
        }

        String query2 = "insert into expenses(sweet_id,sweet_name,sweet_quantity,Total) values (?,?,?,?)";
        PreparedStatement prep= this.connection.prepareStatement(query2);
        prep.setInt(1,sid);
        prep.setString(2,s_name);
        int curr_q = (new_q-prev_q);
        prep.setInt(3,curr_q);
        int total_e = curr_q * new_c;
        prep.setInt(4,total_e);
        int worked = prep.executeUpdate();
        if(worked > 0){
            System.out.println("Expenses of sweet" + s_name + " added in expenses");
        }else{
            System.out.println("Not able to add in the expenses");
        }

    }

}
public void addExpenses() throws SQLException{
    String query2 = "insert into expenses(sweet_id,sweet_name,sweet_quantity,Total) values (?,?,?,?)";
    PreparedStatement ps = this.connection.prepareStatement(query2);
    System.out.println("Enter the expenses details: ");
    System.out.println("Enter the sweet id: ");
    int s_id = scn.nextInt();
    System.out.println("Enter the sweet name: ");
    String s_name = scn.next();
    System.out.println("Enter the sweet quantity: ");
    int s_quantity = scn.nextInt();
    System.out.println("Enter the sweet cost: ");
    int s_cost = scn.nextInt();
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
}
public void checkSweets() throws SQLException{
    String query = "Select * from sweets";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery();
    while(resultSet.next()){
        System.out.println(resultSet.getInt("s_id") +" "+resultSet.getString("s_name")  + " " + resultSet.getInt("s_quantity") + " " + resultSet.getInt("s_cost"));
    }
}
public void addCustomer() throws SQLException {
    System.out.println("Please enter customer details: ");
    System.out.println("Enter customer name: ");
    String cust_name = scn.next();
    System.out.println("Enter customer contact: ");
    String cust_contact = scn.next();
    System.out.println("Enter customer spendings: ");
    int cust_spendings = scn.nextInt();
    String query = "insert into customer(c_name,c_contact,c_spend) values(?,?,?)";
    PreparedStatement preparedStatement  = this.connection.prepareStatement(query);
    preparedStatement.setString(1,cust_name);
    preparedStatement.setString(2,cust_contact);
    preparedStatement.setInt(3,cust_spendings);
    int isWorked = preparedStatement.executeUpdate();
    if(isWorked > 0){
        System.out.println("Customer data entered successfully");
    }else{
        System.out.println("Not able to insert customer data ");
    }
}
public void checkCustomers() throws SQLException{
    String query = "Select * from customer";
    PreparedStatement preparedStatement = this.connection.prepareStatement(query);
    ResultSet resultSet = preparedStatement.executeQuery(query);
    while(resultSet.next()){
        System.out.println(resultSet.getInt("c_id") +" "+resultSet.getString("c_name")  + " " + resultSet.getString("c_contact") + " " + resultSet.getInt("c_spend"));

    }
    }
    public void checkOrders() throws  SQLException {
        String query = "Select order_id,c_id,c_name,sweet_name,sweet_quantity,amount,order_date from orders";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
//        preparedStatement.setInt(1,cId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("The orders placed are: ");
            System.out.println(resultSet.getInt("order_id") + " " +
                    resultSet.getInt("c_id") + " " +
                    resultSet.getString("c_name") + " " + resultSet.getString("sweet_name") + " " + resultSet.getInt("sweet_quantity") + " " + resultSet.getInt("amount") + " " + resultSet.getTimestamp("order_date"));
        }
    }
    public void checkProfit() throws SQLException{
        int sum = 0;
        String sql = "SELECT SUM(amount) AS total FROM orders";

        PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                sum = resultSet.getInt("total");
        }
            String sql2 = "select sum(Total) as exp from expenses";
            int exp = 0;
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql2);
            ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            exp = rs.getInt("exp");
        }
        System.out.println("The total order value is: " + sum + " and the total expenses are: " + exp);
        System.out.println("So, the profit made till now is: " + (sum-exp));

    }


}
