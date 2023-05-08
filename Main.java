//Importing libraries that I need
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //Creating a Scanner object. Make it static to use with methods in this class
    static Scanner scanner = new Scanner(System.in);

    //This is first key menu in my programme
    public static void main(String[] args) {

        //Welcome user to my programme and display the choices that user have. Ask user to choose one of them
        System.out.println("-----------------");
        System.out.println("Welcome to Rifter");
        System.out.println("-----------------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Change Password");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        //This loop checks user input. The programme will ask for input until the user writes valid input(1,2,3,4)
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    //Case if user chose Register. Programme cleans screen and go to Register Page
                    case 1 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Here I call register method from another class "Register"
                        Register.register();
                    }

                    //Case if user chose Login. Programme cleans screen and go to Login Page
                    case 2 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Here I call login method from another class "Login"
                        Login.login();
                    }

                    //Case if user chose Change Password. Programme cleans screen and go to Change Password Page
                    case 3 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Here I call changePassword method from another class "ChangePassword"
                        ChangePassword.changePassword();
                    }

                    //Case if user chose Exit. Programme cleans screen and go to Exit Page
                    case 4 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Displaying Exit Page where I say goodbye to my user
                        System.out.println("-------------------");
                        System.out.println("Exit");
                        System.out.println("-------------------");
                        System.out.println("Thank you for using Rifter. Have a good day!");
                        //Here I use exit method to indicate that process was done without any errors
                        System.exit(0);
                    }
                    //If user type other numbers
                    default -> System.out.print("Invalid choice. Choose an option: ");
                }
            //If user type something else instead of numbers
            } catch (InputMismatchException e) {
                System.out.print("Invalid choice. Choose an option: ");
                scanner.nextLine();
            }
        }
    }

    //This is the second key menu in my programme
    //Here I pass user object from the User class to use it within method
    public static void showAppMenu(User user) {

        //Displaying the menu where user can choose between five options. Ask him for the input
        System.out.println("-------------------");
        System.out.println("Menu");
        System.out.println("-------------------");
        System.out.println("1. Quiz Game");
        System.out.println("2. Shop");
        System.out.println("3. Leaderboard");
        System.out.println("4. Profile");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");

        //This loop checks user input. The programme will ask for input until the user writes valid input(1,2,3,4,5)
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    //Case if user chose Quiz Game. Programme cleans screen and go to Quiz Game Page
                    case 1 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Here I call quizGame method from another class "Quiz Game" with already passed user as an argument
                        QuizGame.quizGame(user);
                        //Go back to the menu
                        showAppMenu(user);
                    }

                    //Case if user chose Shop. Programme cleans screen and go to Shop Page
                    case 2 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Here I call shop method from another class "Shop" with already passed user as an argument
                        Shop.shop(user);
                        //Go back to the menu
                        showAppMenu(user);
                    }

                    //Case if user chose Leaderboard. Programme cleans screen and go to Leaderboard Page
                    case 3 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Here I call shop method from another class "Leaderboard" with already passed user as an argument
                        Leaderboard.leaderboard();
                        //Go back to the menu
                        showAppMenu(user);
                    }

                    //Case if user chose Profile. Programme cleans screen and go to Profile Page
                    case 4 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Here I call shop method from another class "Profile" with already passed user as an argument
                        Profile.profile(user);
                        //Go back to the menu
                        showAppMenu(user);
                    }

                    //Case if user chose Logout. Programme cleans screen and go to previous menu
                    case 5 -> {
                        //Clean the screen in purpose to go to new page
                        clearScreen();
                        //Go back to the first menu
                        Main.main(null);
                    }
                    //If user type other numbers
                    default -> System.out.print("Invalid choice. Choose an option: ");
                }
            //If user type something else instead of numbers
            } catch (InputMismatchException e) {
                System.out.print("Invalid choice. Choose an option: ");
                scanner.nextLine();
            }
        }
    }

    //Here I create a pause method to make my console app more user-friendly
    public static void pause() {
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    //Here I create a method that clear the screen. In fact this method is not clearing the screen, it's simply makes 1000 backspaces:)
    public static void clearScreen() {
        for(int clear = 0; clear < 1000; clear++) {
            System.out.println("\b") ;
        }
    }
}