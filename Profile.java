//Importing libraries that I need
import java.util.Map;

public class Profile {

    //Here I collect all the methods that I created in this class and form an independent method which I will use in the second menu in the Main class
    public static void profile(User user) {
        //Using method from FileManagement class, assign balance of the user(that was taken from "balance.csv")
        int balance = FileManagement.getUserBalance(user);
        displayProfile();
        userNameEmailBalance(user, balance);
        userInventory(FileManagement.getUserInventory(user), user);
    }

    //Method for displaying Profile Page
    public static void displayProfile() {
        System.out.println("-------------------");
        System.out.println("Profile");
        System.out.println("-------------------");
    }

    //Displaying users data(name,email,balance)
    public static void userNameEmailBalance(User user, int balance) {
        System.out.println("Name: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Balance: " + balance + " VRC");
    }

    //Method which takes a Map of item names,their quantities and User object
    //It prints out the user inventory in alphabetical order
    public static void userInventory(Map<String, Integer> counterOfItems, User user) {
        System.out.println("Inventory:");

        //If inventory is not empty(means that user has already bought some items), then programme will display an inventory in alphabetical order
        if (!counterOfItems.isEmpty()) {
            //For-each loop outputs the item name and quantity of item after iterating over each element in the inventory
            for (Map.Entry<String, Integer> entry : counterOfItems.entrySet()) {
                //Here programme prints the name of the item with quantity in parentheses
                System.out.println("- " + entry.getKey() + " (" + entry.getValue() + ")");
            }
        //If the user hasn't bought anything yet, then programme will display that no items purchased yet
        } else {
            System.out.println("No items purchased yet");
        }
        //Print empty line
        System.out.println();
        Main.pause();
        Main.clearScreen();
        //Go back to menu
        Main.showAppMenu(user);
    }
}