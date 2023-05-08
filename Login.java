//Importing libraries that I need
import java.util.Scanner;

public class Login {

    //Here I collect all the methods that I created in this class and form an independent method which I will use in the first menu in the Main class
    public static void login() {
        displayLogin();
        User user = checkInput();
        loginUser(user);
    }

    //Method for displaying Login Page
    public static void displayLogin() {
        System.out.println("-------------------");
        System.out.println("Login");
        System.out.println("-------------------");
    }

    //Method that ask user about his/her details and check if information was written in a valid form
    public static User checkInput() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        //Checking email
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        //Loop that checks if user type valid email. Will loop until user type valid email
        //Valid: Aa-Zz letters, numbers 0 to 9, dot, underscore, percent symbol, plus or minus sign + @ + Aa-Zz letters, numbers 0 - 9, dot, minus sign + domain has at least 2 letters Aa-Zz
        while (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            System.out.print("Enter a valid email: ");
            email = scanner.nextLine();
        }

        //Checking password
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        //Loop that checks if user type valid password. Will loop until user type valid password
        //Valid: min length 8 symbols, max length 127 symbols
        while (password.length() < 8 || password.length() > 127) {
            System.out.print("Enter a valid password: ");
            password = scanner.nextLine();
        }
        //Return an object User with email and password
        return new User("", email, password, 0);
    }

    //Here I pass user object from the User class to use it within method
    public static void loginUser(User user) {

        //Check if user email already exists in the file by using method from another class called "FileManagement"
        User existingUser = FileManagement.existsEmail(user.getEmail());

        //Check if such email exists and user password matches with the stored password in the file
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            System.out.println("\nSuccessful Login");
            Main.pause();
            Main.clearScreen();
            //Go to new menu
            Main.showAppMenu(existingUser);
        //If the email address does not exist or the password does not match, then it is failed login
        } else {
            System.out.println("\nFailed Login(wrong email or password)");
            Main.pause();
            Main.clearScreen();
            //Go back to the menu
            Main.main(null);
        }
    }
}