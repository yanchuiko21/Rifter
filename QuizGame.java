//Importing libraries that I need
import java.util.*;

public class QuizGame {

    //Creating scanner object
    private static final Scanner scanner = new Scanner(System.in);
    //Creating "random" object to generate random number
    private static final Random random = new Random();
    //Creating constant that is a reward for the correct answer in my game
    private static final int VRC_REWARD = 10;

    //Here I use HashMap with the help of which I store a collections of pairs questions with answers in the form of a String
    //Creating an object called "QUESTIONS_AND_ANSWERS". It is written like constant because I use key word "final"
    //The point of game is to answer on random questions about Cambridge and earn my currency called "VRC"
    private static final HashMap<String, String> QUESTIONS_AND_ANSWERS = new HashMap<>() {{

        //Here are questions about famous people that are related to Cambridge
        put("What is the name of the famous physicist who formulated the laws of motion and universal gravitation while at Cambridge University?", "Isaac Newton");
        put("What is the name of the famous mathematician associated with Cambridge University?", "Isaac Newton");
        put("What is the name of the famous physicist who was a professor at Cambridge University?", "Stephen Hawking");
        put("What is the name of the famous Cambridge University alumni who wrote 'A Brief History of Time'?", "Stephen Hawking");
        put("What is the name of the famous biologist who was a professor at Cambridge University?", "Charles Darwin");
        put("What is the name of the famous economist who was a professor at Cambridge University?", "Robert Malthus");
        put("What is the name of the famous composer who studied at Cambridge University?", "William Boyce");
        put("What is the name of the famous actor who studied at Cambridge University?", "Stephen Fry");

        //Here are questions about the culture of Cambridge
        put("What is the name of the famous science park in Cambridge?", "Cambridge Science Park");
        put("What is the name of the famous chapel in King's College?", "King's College Chapel");
        put("What is the name of the famous market in the center of Cambridge?", "Market Square");
        put("What is the name of the famous bridge in Cambridge that is a popular spot for punting?", "The Bridge of Sighs");
        put("What is the name of the famous literary society that was founded at Cambridge University?", "Cambridge Apostles");
        put("What is the name of the famous annual boat race between Oxford and Cambridge?", "The Boat Race");
        put("What is the name of the largest college in Cambridge by student population?", "Churchill College");
        put("What is the name of the oldest college in Cambridge?", "Peterhouse");
        put("What is the name of the famous bookstore in Cambridge?", "Heffers");
        put("What river flows through Cambridge?", "Cam");

        //Here are questions that are also related to culture, but requiring only a number in the answer
        put("When was the University of Cambridge founded?", "1209");
        put("How many Nobel laureates have studied or worked at Cambridge?", "121");
        put("How many pubs are located in Cambridge city center?", "110");
        put("How many churches are located in Cambridge city?", "39");
        put("How many colleges are in Cambridge University?", "31");
        put("How many large parks does Cambridge have?", "30");
        put("How many acres does Parker's Piece, a large public park in Cambridge, cover?", "25");
        put("How many bridges cross the River Cam in Cambridge?", "25");
        put("How many museums are located in Cambridge?", "12");
        put("How many punting companies are there in Cambridge?", "7");
        put("How many international airports are located near Cambridge?", "4");
        put("How many theaters are located in Cambridge?", "3");
    }};

    //Here I collect all the methods that I created in this class and form an independent method which I will use in the second menu in the Main class
    public static void quizGame(User user) {
        displayQuizGame();
        int score = 0;
        boolean playAgain = true;

        //Loop which will work until user choose the second option(Back to Menu)
        while (playAgain) {
            //Get random question from the "QUESTIONS_AND_ANSWERS"
            String question = randomQuestion();
            //Get answer for the random question
            String answer = getAnswer(question);
            //Get user answer
            String userAnswer = askAnswer(question);

            //Check if user answer is equal to the real correct answer. Ignoring the case ("Peterhouse" or "peterhouse")
            if (userAnswer.equalsIgnoreCase(answer)) {
                //Give user reward for the correct answer
                score += VRC_REWARD;
                System.out.println("Correct! You earned " + VRC_REWARD + " VRC.");
            //If user type user answer is not match with the correct answer, then I display the correct answer
            //Thus, after knowing the answer to the question, he will play the game more and more to get this question and earn more VRC
            } else {
                System.out.println("Incorrect. The correct answer is: " + answer + ".");
            }
            //Ask user if he wants to play again
            playAgain = displayPlayAgain(score);
        }
        //Here I update user balance with the help of method from the class called "FileManagement"
        FileManagement.updateBalance(user.getEmail(), score);
        Main.pause();
        Main.clearScreen();
        //Go back to menu
        Main.showAppMenu(user);
    }

    //Method for displaying Quiz Game Page
    private static void displayQuizGame() {
        System.out.println("------------------------");
        System.out.println("Quiz Game");
        System.out.println("------------------------");
    }

    //Method which select a random question from "QUESTIONS_AND_ANSWERS" and return it as a String
    private static String randomQuestion() {
        //Here the ArrayList constructor create a new list called "keys" which contains keys from "QUESTIONS_AND_ANSWERS"
        List<String> keys = new ArrayList<>(QUESTIONS_AND_ANSWERS.keySet());
        //Here I generate a random number from to 0 to size of my list(I have 30 questions that mean programme generate random number from 0 to 30)
        int question = random.nextInt(keys.size());
        //Randomly chosen question return as a String
        return keys.get(question);
    }

    //Method which returns answer to the question in form of a String
    //It takes question from the "QUESTIONS_AND_ANSWERS" using get method
    private static String getAnswer(String question) {
        return QUESTIONS_AND_ANSWERS.get(question);
    }

    //Method which ask that question that was randomly chosen
    private static String askAnswer(String question) {
        System.out.println(question);
        System.out.print("Enter your answer: ");
        return scanner.nextLine();
    }

    //Method which display a choice in which user can choose what to do(play again or go back to menu)
    private static boolean displayPlayAgain(int score) {
        //Loop which will work until user choose the valid answer(1,2)
        while (true) {
            System.out.print("Play again(1) or Back to Menu(2): ");
            String userChoice = scanner.nextLine();

            //If user choose first option, then he will see next question
            if (userChoice.equals("1")) {
                Main.clearScreen();
                System.out.println("-------------------");
                System.out.println("Next question:");
                System.out.println("-------------------");
                return true;
            //If user choose second option, then he will see the total amount of VRC that he earns and go back to menu
            } else if (userChoice.equals("2")) {
                System.out.println("-------------------");
                System.out.println("You earned " + score + " VRC.");
                System.out.println("Thank you for playing!");
                return false;
            //If user types something other than 1 or 2
            } else {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }
    }
}