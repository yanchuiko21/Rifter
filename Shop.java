//Importing libraries that I need
import java.util.InputMismatchException;
import java.util.Scanner;

public class Shop {

    //Here I collect all the methods that I created in this class and form an independent method which I will use in the second menu in the Main class
    public static void shop(User user) {
        Scanner scanner = new Scanner(System.in);
        displayShop(user);
        userChoice(user, scanner);
    }

    //Method for displaying Shop Page. There a three shops in which user could spend his/her VRC
    public static void displayShop(User user){

        //Here I use method from another class called "FileManagement"
        int balance = FileManagement.getUserBalance(user);

        //Display the Shop Page with three stores with different items that will be very interesting for the people of Cambridge to see
        System.out.println("-------------------");
        System.out.println("Shop");
        System.out.println("-------------------");
        System.out.println("Cambridge Goods For Study:           Cambridge Souvenirs:           Cambridge Brand Clothes:");
        System.out.println("Pen - 10 VRC(1)                      Poster - 25 VRC(4)             Scarf - 40 VRC(7)");
        System.out.println("Eraser - 15 VRC(2)                   Book - 30 VRC(5)               Hat - 45 VRC(8)");
        System.out.println("Notebook - 20 VRC(3)                 Umbrella - 35 VRC(6)           Sweatshirt - 50 VRC(9)");

        System.out.println("\nBack to Menu(0)");
        System.out.println("\nBalance: " + balance + " VRC");
        System.out.print("Choose an option: ");
    }

    //Method which ask user to choose which item he/she wants to buy
    public static void userChoice(User user, Scanner scanner) {

        //Loop which will work until user type a valid choice
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                //If user choose 0, then he will come back to the menu
                if (choice == 0) {
                    Main.clearScreen();
                    return;
                }

                //If user types some other numbers besides 1-9, then programme continue the loop
                if (choice < 1 || choice > 9) {
                    System.out.print("Invalid choice. Choose an option: ");
                    continue;
                }

                //User can choose from nine items in the shop. Each item has its own price
                switch (choice) {
                    //With the help of method from class called "FileManagement" user purchase item that he chose
                    case 1 -> FileManagement.buyItem(user, 10, "Pen");
                    case 2 -> FileManagement.buyItem(user, 15, "Eraser");
                    case 3 -> FileManagement.buyItem(user, 20, "Notebook");
                    case 4 -> FileManagement.buyItem(user, 25, "Poster");
                    case 5 -> FileManagement.buyItem(user, 30, "Book");
                    case 6 -> FileManagement.buyItem(user, 35, "Umbrella");
                    case 7 -> FileManagement.buyItem(user, 40, "Scarf");
                    case 8 -> FileManagement.buyItem(user, 45, "Hat");
                    case 9 -> FileManagement.buyItem(user, 50, "Sweatshirt");
                }
                //Ask user if he wants to buy something else or go back to the menu
                System.out.print("Choose another item or go back to the menu: ");
            //Check if user type something else instead of numbers
            } catch (InputMismatchException e) {
                System.out.print("Invalid choice. Choose an option: ");
                scanner.nextLine();
            }
        }
    }
}