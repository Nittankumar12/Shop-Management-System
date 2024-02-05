package Main;

import usecase.ShopUseCase;

import java.util.*;
public class Main {
    public  static void startProject(){

        ShopUseCase shopU = new ShopUseCase();


        System.out.println("WELCOME TO OUR SHOP");
        System.out.println("Choose one:\n1.Customer\n2.Staff");

        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        switch(choice){
            case 1:
                System.out.println("WELCOME DEAR!!");
                System.out.println("Available sweets are: ");
                showSweets();
                break;
            case 2:
                System.out.println();
                System.out.println("Hello Employee: ");
                System.out.println("Choose:\n1.Add Sweets" +
                        "\n2.Update Sweets Data\n" +
                        "3.Check Earnings\n" +
                        "4.Check profit\n" +
                        "5.Check Orders\n" +
                        "6.Add Expenses\n" +
                        "7.Add Customers\n" +
                        "8.Check Customers\n" +
                        "9.Check Expenses\n" +
                        "10. Exit");
                int ch = scn.nextInt();
                switch (ch){
                    case 1:
//                        addSweets();
                        break;
                    case 2:
//                        updateSweetsData();
                        break;
                    case 3:
//                        checkEarnings();
                        break;
                    case 4:
//                        checkProfit();
                        break;
                    case 5:
//                        checkOrders();
                        break;
                    case 6:
//                        addExpenses();
                        break;
                    case 7:
//                        addCustomer();
                        break;
                    case 8:
//                        checkCustomers();
                        break;
                    case 9:
//                        checkExpenses();
                        break;
                    case 10:
                        System.out.println("Thanks");
                        return;
                    default:
                        System.out.println("Wrong Choice");
                }
                break;
            default:
                System.out.println("Wrong Choice!! ");



        }
    }
    public static void main(String[] args) {
        startProject();
    }
    public static void showSweets(){
        String query = "Select * from sweets";

    }
}