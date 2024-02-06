package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static String url = "jdbc:mysql://127.0.0.1:3306/Shop";

    private static String user = "root";

    private static String password = "nittan";

    private static Connection con;

    static{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        try {
            con =DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static Connection getConnection() {

        return con;

    }
}