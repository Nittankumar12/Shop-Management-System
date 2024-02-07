package Main;

import dao.CustomerDao;
import dao.ShopDao;
import usecase.ShopUseCase;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.*;
public class Main {

    static Scanner scn = new Scanner(System.in);
    public static void customerMain() throws SQLException {
        System.out.println("Please select from the following: ");
        System.out.println("1.Check Sweets and their price: " +
                "\n2.Register new Customer" +
                "\n3.Check my spendings" +
                "\n4.Place an order" +
                "\n5.Check my order" +
                "\n0.Exit");
        int ch = scn.nextInt();
        CustomerDao cDao = new CustomerDao();
        switch(ch){
            case 1:
                cDao.checkSweetsPrice();
                customerMain();
            case 2:
                cDao.registerCustomer();
                customerMain();
            case 3:
                cDao.checkSpendings();
                customerMain();
            case 4:
                cDao.placeOrder();
                customerMain();
            case 5:
                cDao.checkOrder();
                customerMain();
            case 0:
                System.exit(0);
            default:
                System.out.println("Enter a valid choice");
        }

    }
    public static void shopMain() throws SQLException {
        System.out.println("Choose:\n1.Add Sweets" +
                "\n2.Update Sweets Data\n" +
                "3.Check Sweets\n" +
                "4.Check profit\n" +
                "5.Check Orders\n" +
                "6.Add Expenses\n" +
                "7.Add Customers\n" +
                "8.Check Customers\n" +
                "9.Check Expenses\n" +
                "10. Exit");
        int ch = scn.nextInt();
        ShopDao shopDao = new ShopDao();
        switch (ch){
            case 1:
                shopDao.addSweets();
                break;
            case 2:
                shopDao.updateSweets();
                break;
            case 3:
                shopDao.checkSweets();
                break;
            case 4:
//                        checkProfit();
                break;
            case 5:
//                        checkOrders();
                break;
            case 6:
                shopDao.addExpenses();
                break;
            case 7:
                shopDao.addCustomer();
                break;
            case 8:
                shopDao.checkCustomers();
                break;
            case 9:
                shopDao.checkExpenses();
                break;
            case 10:
                System.out.println("Thanks");
                System.exit(0);
//                return;
            default:
                System.out.println("Wrong Choice");
        }
    }
    public  static void startProject() throws SQLException {

        ShopUseCase shopU = new ShopUseCase();

        System.out.println("WELCOME TO OUR SHOP");
        System.out.println("Choose one:\n1.Customer\n2.Staff");

        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        switch(choice){
            case 1:
                System.out.println("WELCOME DEAR!!");
                customerMain();
                break;
            case 2:
                System.out.println();
                System.out.println("Please Enter your login id and password");
                while(shopU.LogInStaff()){
                    shopMain();
                }
                while(!shopU.LogInStaff()){
                    shopAuthentication();
                }
                break;
            default:
                System.out.println("Wrong Choice!! ");
        }
    }
    public static void shopAuthentication() throws SQLException {
        ShopUseCase su = new ShopUseCase();
        if(su.LogInStaff()){
            shopMain();
        }
    }
    public static void main(String[] args) throws SQLException {
        startProject();
    }
}