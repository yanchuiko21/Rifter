//Importing libraries that I need
import java.util.Scanner;

public class ChangePassword {

    //Here I collect all the methods that I created in this class and form an independent method which I will use in the first menu in the Main class
    public static void changePassword() {
        displayChangePassword();
        User user = checkInput();
        changePasswordUser(user);
    }

    //Method for displaying Change Password Page
    public static void displayChangePassword() {
        System.out.println("-------------------");
        System.out.println("Change Password");
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

        //Checking security number
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
            //The same validation loop as before last one
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter your security number: ");
                scanner.next();
            }
            securityNumber = scanner.nextInt();
        }
        //Consume the newline character left in the buffer
        scanner.nextLine();

        //Creating new password
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();
        //Loop that checks if user type valid password. Will loop until user type valid password
        //Valid: min length 8 symbols, max length 127 symbols
        while (newPassword.length() < 8 || newPassword.length() > 127) {
            System.out.print("Enter a valid password: ");
            newPassword = scanner.nextLine();
        }
        //Confirmation of password
        System.out.print("Confirm your new password: ");
        String confirmNewPassword = scanner.nextLine();
        //Loop that check if first password that user input is the same as the password in confirmation
        //Loops will work until user type password that is a valid and the confirmation password
        while (!newPassword.equals(confirmNewPassword)) {
            System.out.print("Passwords don't match. Enter your new password: ");
            newPassword = scanner.nextLine();
            while (newPassword.length() < 8 || newPassword.length() > 127) {
                System.out.print("Enter a valid password: ");
                newPassword = scanner.nextLine();
            }
            System.out.print("Confirm your new password: ");
            confirmNewPassword = scanner.nextLine();
        }
        //Return an object User with email, security number and new password
        return new User("", email, newPassword, securityNumber);
    }

    //Here I pass user object from the User class to use it within method
    public static void changePasswordUser(User user) {

        //Check if email and security number that user input exists by using method from another class called "FileManagement"
        User existingUser = FileManagement.existsEmailAndSecurityNumber(user.getEmail(), user.getSecurityNumber());

        //Check If email was already existed
        if (existingUser != null) {
            //Check if new password is equal to password that already existed in the file
            if (existingUser.getPassword().equals(user.getPassword())) {
                System.out.println("\nYou already have this password");
                Main.pause();
                Main.clearScreen();
                //Go back to the menu
                Main.main(null);
                return;
            }
            //Updating password on a new one
            existingUser.setPassword(user.getPassword());
            FileManagement.update(existingUser);
            System.out.println("\nPassword successfully changed");
        //If user typed wrong email or security number programme will say it
        } else {
            System.out.println("\nInvalid email or security number");
        }
        Main.pause();
        Main.clearScreen();
        ////Go back to the menu
        Main.main(null);
    }
}