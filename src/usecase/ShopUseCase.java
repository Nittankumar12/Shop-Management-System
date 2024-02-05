package usecase;
import dao.ShopDao;

import java.sql.SQLException;
import java.util.*;

public class ShopUseCase {
    public ShopUseCase() {
        public boolean LogInStaff(){
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter your ID: ");
            int id = scn.nextInt();
            System.out.println("Enter the password: ");
            String password = scn.nextInt();
            ShopDao su = new ShopDao();
            try{
                
            }
            catch(SQLException e){
                e.printStackTrace();
            }

        }
    }
}
