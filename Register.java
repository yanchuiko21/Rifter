//Importing libraries that I need
import java.util.Scanner;

public class Register {

    //Here I collect all the methods that I created in this class and form an independent method which I will use in the first menu in the Main class
    public static void register() {
        displayRegistration();
        User user = checkInput();
        registerUser(user);
    }

    //Method for displaying Registration Page
    public static void displayRegistration(){
        System.out.println("-------------------");
        System.out.println("Register");
        System.out.println("-------------------");
    }

    //Method that ask user about his/her details and check if information was written in a valid form
    public static User checkInput() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        //Creating username
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        //Loop that checks if user type valid username. Will loop until user type valid username
        //Valid: letters from Aa-Zz, min length 3 letter, max length 64 letter
        while (!username.matches("[a-zA-Z]+") || username.length() < 3 || username.length() > 64) {
            System.out.print("Enter a valid username: ");
            username = scanner.nextLine();
        }

        //Creating email
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        //Loop that checks if user type valid email. Will loop until user type valid email
        //Valid: Aa-Zz letters, numbers 0 to 9, dot, underscore, percent symbol, plus or minus sign + @ + Aa-Zz letters, numbers 0 - 9, dot, minus sign + domain has at least 2 letters Aa-Zz
        while (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            System.out.print("Enter a valid email: ");
            email = scanner.nextLine();
        }

        //Creating password
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        //Loop that checks if user type valid password. Will loop until user type valid password
        //Valid: min length 8 symbols, max length 127 symbols
        while (password.length() < 8 || password.length() > 127) {
            System.out.print("Enter a valid password: ");
            password = scanner.nextLine();
        }

        //Creating security number. User need to create a security number(in case if he wants to change password he needs to know security number)
        System.out.print("Enter your security number: ");
        int securityNumber;
        System.out.print("Enter your security number (6 digits): ");
        //Here I check if user type valid security number
        //Valid: six digits
        //This loop checks if user type input that is not an integer.
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter your security number: ");
            scanner.next();
        }
        securityNumber = scanner.nextInt();
        //This loop check if this number is actually have 6 digits
        while (securityNumber < 100000 || securityNumber > 999999) {
            System.out.print("Enter a valid security number (6 digits): ");
            //The same validation loop as before last one loop
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter your security number: ");
                scanner.next();
            }
            securityNumber = scanner.nextInt();
        }
        //Return an object User with details that user input
        return new User(username, email, password, securityNumber);
    }

    //Here I pass user object from the User class to use it within method
    public static void registerUser(User user) {

        //Check if user email already exists in the file by using method from another class called "FileManagement"
        User existingUser = FileManagement.existsEmail(user.getEmail());

        //If email was not existed I save user data to the file
        if (existingUser == null) {
            //Called method from another class called "FileManagement" and passing user object
            FileManagement.save(user);
            System.out.println("\nSuccessful Register");
            Main.pause();
            Main.clearScreen();
            //Go to new menu
            Main.showAppMenu(user);
        //If email was already existed in the file, then it is failed registration
        } else {
            System.out.println("\nFailed Registration(account with this email already exists)");
            Main.pause();
            Main.clearScreen();
            //Go back to the menu
            Main.main(null);
        }
    }
}