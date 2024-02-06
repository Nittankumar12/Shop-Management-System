package usecase;
import dao.ShopDao;
import model.ShopBean;

import java.sql.SQLException;
import java.util.*;

public class ShopUseCase {
        public boolean LogInStaff(){
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter your ID: ");
            int id = scn.nextInt();
            System.out.println("Enter the password: ");
            String password = scn.next();
            ShopDao su = new ShopDao();
            try{
                ShopBean staff = su.logInStaff(id,password);
                System.out.println();
                System.out.println("Welcome " + staff.getShop_name() + "!");
                return true;
            }
            catch(SQLException e){
                e.printStackTrace();
                return false;
            }

        }
}
