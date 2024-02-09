package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Connection.JDBC;

import static Main.Main.customerMain;

public class CustomerDao {
    private Connection connection;
    Scanner scn = new Scanner(System.in);
    public CustomerDao(){
        this.connection = JDBC.getConnection();
    }
    public void checkSweetsPrice() throws SQLException {
        String query = "select s_name,s_cost from sweets";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString("s_name") + " " + resultSet.getInt("s_cost"));
        }
    }
    public void registerCustomer() throws SQLException {
        System.out.println("Please enter your details: ");
        System.out.println("Enter your name: ");
        String cust_name = scn.next();
        System.out.println("Enter your contact: ");
        String cust_contact = scn.next();
        System.out.println("Enter your spendings: ");
        int cust_spendings = scn.nextInt();
        String query = "insert into customer(c_name,c_contact,c_spend) values(?,?,?)";
        PreparedStatement preparedStatement  = this.connection.prepareStatement(query);
        preparedStatement.setString(1,cust_name);
        preparedStatement.setString(2,cust_contact);
        preparedStatement.setInt(3,cust_spendings);
        int isWorked = preparedStatement.executeUpdate();
        if(isWorked > 0){
            System.out.println("Registered successfully");
        }else{
            System.out.println("Not able to register");
        }
    }
    public void checkSpendings() throws SQLException {
        String query = "select c_id,c_name,c_spend from customer where c_name = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        System.out.println("Please enter your name");
        String name = scn.next();
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getInt("c_id") + " " + resultSet.getString("c_name") + " " + resultSet.getInt("c_spend"));
        }

    }
    public void placeOrder() throws SQLException {
        System.out.println("Enter your name: ");
        String name = scn.next();
        int id = getCustomerByName(name);
        if(id == -1){
            System.out.println("No customer exists for this id, please exit and register yourself first!!");
            System.exit(0);
        }
        System.out.println("Enter sweet name");
        String sweet_name = scn.next();
        System.out.println("Enter quantity you want: ");
        int quantity = scn.nextInt();
        int sweet_id = getSweetByName(sweet_name);
        if(sweet_id == -1){
            customerMain();
        }
        else{
            int aSweet = checkAvailabilty(sweet_id);
            System.out.println("Available quantity of " + sweet_name + " is: " + aSweet);
            if(aSweet >= quantity){
                order(id,name,sweet_id,sweet_name,quantity);
            }else{
                System.out.println("Quantity is not available");
            }
        }
    }
    public void checkOrder() throws SQLException {
        System.out.println("Enter your name: ");
        String name = scn.next();
        int cId = getCustomerByName(name);
        if(cId == -1){
            System.out.println("you are not an existing customer, register first....");
            System.exit(0);
        }
        String query = "Select order_id,c_id,c_name,sweet_name,sweet_quantity,amount,order_date from orders where c_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,cId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("The orders you have placed are: ");
            System.out.println(resultSet.getInt("order_id") + " " +
                    resultSet.getInt("c_id") + " " +
                    resultSet.getString("c_name") + " " + resultSet.getString("sweet_name") + " " + resultSet.getInt("sweet_quantity") + " " + resultSet.getInt("amount") + " " + resultSet.getTimestamp("order_date"));
        }
    }
    public int getSweetByName(String name) throws SQLException {
        String query = "select s_id from sweets where s_name = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("s_id");
        }else{
            System.out.println("sweet doesn't exists");
        }
        return -1;
    }
    public int checkAvailabilty(int sid) throws SQLException {
      String query = "select s_quantity from sweets where s_id = ?";
      PreparedStatement preparedStatement = this.connection.prepareStatement(query);
      preparedStatement.setInt(1,sid);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
          return resultSet.getInt("s_quantity");
      }
      return -1;
    }
    public void order(int cid, String cname,int sweet_id, String sweet_name, int s_quantity ) throws SQLException {
        String query = "insert into orders(c_id,c_name,sweet_name,sweet_quantity,amount) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,cid);
        preparedStatement.setString(2,cname);
//        preparedStatement.setInt(3,sweet_id);
        preparedStatement.setString(3,sweet_name);
        preparedStatement.setInt(4,s_quantity);
        int am = s_quantity * getSweetCostById(sweet_id);
        preparedStatement.setInt(5,am);
        int isWorked = preparedStatement.executeUpdate();
        if(isWorked > 0){
            System.out.println("Order Placed Successfully");
//            int am = s_quantity * getSweetCostById(sweet_id);
            updateCustomerData(cid,am);
            updateSweets(sweet_id,s_quantity);
        }else{
            System.out.println("Unable to place the order");
        }

    }
    public void updateSweets(int sid, int quantityToReduce) throws SQLException {
        String query = "UPDATE sweets SET s_quantity = s_quantity - ? WHERE s_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,quantityToReduce);
        preparedStatement.setInt(2,sid);
        int rowsAffected = preparedStatement.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Sweets quantity Updated");
        }else{
            System.out.println("Unable to update sweet quantity");
        }

    }
    public void updateCustomerData(int cid, int amount) throws SQLException {
        String sql = "UPDATE customer SET c_spend = c_spend + ? WHERE c_id = ?";

        PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, amount);
            statement.setInt(2, cid);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Customer spendings updated successfully.");
            } else {
                System.out.println("No customer found with the provided ID.");
            }
        }
    public String getCustomerById(int id) throws SQLException {
        String query = "select c_name from customer where c_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getString("c_name");
        }
        return "";

    }
    public int getSweetCostById(int sid) throws SQLException {
        String query = "select s_cost from sweets where s_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,sid);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("s_cost");
        }
        return -1;
    }
    public int getCustomerByName(String name) throws SQLException {
        String query = "select c_id from customer where c_name = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("c_id");
        }
        return -1;

    }
}
