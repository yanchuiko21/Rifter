//Importing libraries that I need
import java.util.List;

public class Leaderboard {

    //Here I collect all the methods that I created in this class and form an independent method which I will use in the second menu in the Main class
    public static void leaderboard() {
        //Here I get data from the leaderboard by using method from another class called "FileManagement"
        //After which it converts to a List of String arrays
        List<String[]> leaderboard = FileManagement.getLeaderboard();
        displayLeaderboard();
        displayTable(leaderboard);
    }

    //Method for displaying Leaderboard Page
    public static void displayLeaderboard(){
        System.out.println("-------------------");
        System.out.println("Leaderboard");
        System.out.println("-------------------");
    }

    //Here I display the leaderboard by passing List of String arrays to this method
    public static void displayTable(List<String[]> leaderboard){

        int maxNameLength = 0;

        //For-each loop which will iterates through data in the leaderboard. It will take the name and assign it to the "name" variable
        for (String[] values : leaderboard) {
            String name = values[0];
            //Calculating maximum length of the name
            //By finding the length of the longest name, the code can then format the output of the table so that each name column has the same width(to make table more accurate)
            maxNameLength = Math.max(maxNameLength, name.length());
        }

        //For-each loop which will display the table
        for (String[] values : leaderboard) {
            String name = values[0];
            //Displaying username with score
            //Here I use printf with the help of I format the output of the table(to make table more structured)
            //%- stands for left-justification
            //s for indicating that that is a String
            //%s string specifier for string for printf
            System.out.printf("%-" + (maxNameLength + 1) + "s%s VRC\n", name + " - ", values[2]);
        }
        //Print an empty line
        System.out.println();
        Main.pause();
        Main.clearScreen();
    }
}