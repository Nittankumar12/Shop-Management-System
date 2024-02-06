package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.JDBC;
import model.ShopBean;

public class ShopDao {
private Connection connection;
public ShopDao(){
    this.connection = JDBC.getConnection();
}
public ShopBean logInStaff (int id, String password) throws SQLException {
    ShopBean result = null;
    PreparedStatement preparedStatement = this.connection.prepareStatement("select * from shop where shop_id = ? AND shop_password = ?)");
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

}
