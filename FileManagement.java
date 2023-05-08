//Importing libraries that I need
import java.io.*;
import java.util.*;

public class FileManagement {

    //Creating constants for the files
    private static final String USER = "user.csv";
    private static final String BALANCE = "balance.csv";
    private static final String INVENTORY = "inventory.csv";
    //Creating constants for headers of the file
    private static final String USER_HEADER = "Username,Email,Password,Security Number\n";
    private static final String BALANCE_HEADER = "Email,Balance\n";

    //Method which checks if user email exists in the user.csv file or not
    //At the end returns User object with data
    public static User existsEmail(String email) {
        try {
            //Creating a file object
            File file = new File(USER);

            //If file doesn't exist, then programme create it by using "createNewFile" method
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                //If file was not created for some reason, then programme tell the problem
                if (!fileCreated) {
                    System.err.println("Failed to create file: " + USER);
                }
                return null;
            }

            //Create an object called "reader" to read a file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            //Loop which reads file until the programme reached the end
            while ((line = reader.readLine()) != null) {
                //Here I use comma as a delim
                String[] cells = line.split(",");
                //Check if line has at least four cells and the second(cells[1]) is email
                if (cells.length >= 4 && cells[1].equals(email)) {
                    //Closing reader object
                    reader.close();
                    //Returning a new User object
                    return new User(cells[0], cells[1], cells[2], Integer.parseInt(cells[3]));
                }
            }
            //Closing reader object
            reader.close();
            //If the IOException occurs, then programme provide more information about the error
        } catch (IOException e) {
            e.printStackTrace();
        }
        //If user was not found in the "user.csv" file
        return null;
    }

    //This method is actually for "ChangePassword" class that checks if such email with such security number exists
    public static User existsEmailAndSecurityNumber(String email, int securityNumber) {
        try {
            //Creating a file object
            File file = new File(USER);

            //If file doesn't exist, then programme create it by using "createNewFile" method
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                //If file was not created for some reason, then programme tell the problem
                if (!fileCreated) {
                    System.err.println("Failed to create file: " + USER);
                }
                return null;
            }

            //Create an object called "reader" to read a file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            //Loop which reads file until the programme reached the end
            while ((line = reader.readLine()) != null) {
                //Here I use comma as a delim
                String[] cells = line.split(",");
                //Check if line contains at least four cells. And check email and security number that user input exists in the "user.csv" file
                if (cells.length >= 4 && cells[1].equals(email) && Integer.parseInt(cells[3]) == securityNumber) {
                    //Closing reader object
                    reader.close();
                    //Returning a new User object
                    return new User(cells[0], cells[1], cells[2], Integer.parseInt(cells[3]));
                }
            }
            //Closing reader object
            reader.close();
            //If the IOException occurs, then programme provide more information about the error
        } catch (IOException e) {
            e.printStackTrace();
        }
        //If user was not found in the "user.csv" file
        return null;
    }

    //Method to save data to the "user.csv" file
    public static void save(User user) {
        try {
            //Creating a file object
            File file = new File(USER);

            //If file doesn't exist, then programme create it by using "createNewFile" method
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                //If file was not created for some reason, then programme tell the problem
                if (!fileCreated) {
                    System.err.println("Failed to create file: " + USER);
                }
                //Creating a writer object
                FileWriter writer = new FileWriter(file);
                //Write the headers to the file "user.csv"
                writer.write(USER_HEADER);
                //Closing writer object
                writer.close();
            //If file is empty, then programme write the headers to the file
            } else if (file.length() == 0) {
                //Creating a writer object
                FileWriter writer = new FileWriter(file);
                //Write the headers to the file "user.csv"
                writer.write(USER_HEADER);
                //Closing writer object
                writer.close();
            }
            //Creating a writer object
            FileWriter writer = new FileWriter(file, true);
            //Append user data to the file "user.csv". Each information is separated by comma
            writer.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getSecurityNumber() + "\n");
            //Closing writer object
            writer.close();
        //If the IOException occurs, then programme provide more information about the error
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method for updating "user.csv" file
    public static void update(User user) {
        try {
            //Creating a file object
            File file = new File(USER);

            //If file doesn't exist, then programme tell the problem
            if (!file.exists()) {
                System.err.println("File not found: " + USER);
                return;
            }

            //Create an object called "reader" to read a file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            //Creating a buffer object for updating data
            StringBuilder buffer = new StringBuilder();

            //Loop which reads the file and update if it needs to update
            while ((line = reader.readLine()) != null) {
                //Here I use comma as a delim
                String[] cells = line.split(",");
                //Check if line contains at least four cells. And check if second cell(cells[1]) is actually equal to user email
                if (cells.length >= 4 && cells[1].equals(user.getEmail())) {
                    //Updating file with new data
                    buffer.append(user.getUsername()).append(",").append(user.getEmail()).append(",").append(user.getPassword()).append(",").append(user.getSecurityNumber()).append("\n");
                //Append the line unchanged
                } else {
                    buffer.append(line).append("\n");
                }
            }
            //Closing buffer object
            reader.close();
            //Creating a writer object
            FileWriter writer = new FileWriter(file);
            //Writing the updated data
            writer.write(buffer.toString());
            //Closing writer object
            writer.close();
        //If the IOException occurs, then programme provide more information about the error
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method for updating user balance in the "balance.csv" file
    public static void updateBalance(String email, int VRC) {
        try {
            //Creating a file object
            File file = new File(BALANCE);

            //If file doesn't exist, then programme tell create it
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                //If file doesn't create, then programme tell the problem
                if (!fileCreated) {
                    System.err.println("Failed to create file: " + BALANCE);
                }
                //Creating a writer object
                FileWriter writer = new FileWriter(file);
                //Write the headers to the file "balance.csv"
                writer.write(BALANCE_HEADER);
                //Closing writer object
                writer.close();
            //If file is empty, then programme write the headers to the file
            } else if (file.length() == 0) {
                //Creating a writer object
                FileWriter writer = new FileWriter(file);
                //Write the headers to the file "balance.csv"
                writer.write(BALANCE_HEADER);
                //Closing writer object
                writer.close();
            }

            //Create an object called "reader" to read a file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            //Creating a buffer object for updating data
            StringBuilder buffer = new StringBuilder();
            boolean found = false;

            //Loop which reads the file and update if it needs to update
            while ((line = reader.readLine()) != null) {
                //Here I use comma as a delim
                String[] cells = line.split(",");
                //Check if line contains at least two cells. And check if first cell(cells[0]) is actually equal to user email
                if (cells.length >= 2 && cells[0].equals(email)) {
                    int existingVRC = Integer.parseInt(cells[1]);
                    int totalVRC = existingVRC + VRC;
                    //Updating the amount of VRC
                    buffer.append(email).append(",").append(totalVRC).append("\n");
                    found = true;
                //Append the line unchanged
                } else {
                    buffer.append(line).append("\n");
                }
            }

            //If was not found
            if (!found) {
                buffer.append(email).append(",").append(VRC).append("\n");
            }

            //Closing reader object
            reader.close();
            //Creating a writer object
            FileWriter writer = new FileWriter(file);
            //Writing the updated data
            writer.write(buffer.toString());
            //Closing writer object
            writer.close();
        //If the IOException occurs, then programme provide more information about the error
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method for updating "inventory.csv"" with "balance.csv"
    public static void buyItem(User user, int itemPrice, String itemName) {
        try {
            //Creating a file object
            File file = new File(BALANCE);

            //If file doesn't exist, then programme will create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //Create an object called "bufferReader" to read a file
            BufferedReader bufferReader = new BufferedReader(new FileReader(file));
            String line = bufferReader.readLine();

            //Creating a list to store updated data
            List<String> lines = new ArrayList<>();
            boolean found = false;

            //Loop which reads "balance.csv" line by line
            while (line != null) {
                //Here I use comma as a delim
                String[] values = line.split(",");

                //Check if current line relates to the user which is making the transaction
                if (values[0].equals(user.getEmail())) {
                    int VRC = Integer.parseInt(values[1]);
                    //Check if user have enough VRC to buy such item
                    if (VRC >= itemPrice) {
                        VRC -= itemPrice;
                        values[1] = Integer.toString(VRC);
                        System.out.println("Purchase successful. You have " + VRC + " VRC remaining.");
                        found = true;

                        //Creating a inventoryFile object
                        File inventoryFile = new File(INVENTORY);

                        //If file doesn't exist, then programme will create one
                        if (!inventoryFile.exists()) {
                            inventoryFile.createNewFile();
                        }

                        //Create an object called "reader" to read a file
                        BufferedReader reader = new BufferedReader(new FileReader(inventoryFile));

                        //Creating list to store lines of the file
                        List<String> list = new ArrayList<>();
                        boolean foundInventory = false;
                        String lineInventory = reader.readLine();

                        //Loop which will iterate each line and check if user already has an inventory
                        while (lineInventory != null) {
                            //Here I use comma as a delim
                            String[] valueInventory = lineInventory.split(",");
                            //Check if first element in the array is equal to username of the user who is buying item
                            if (valueInventory[0].equals(user.getUsername())) {
                                //Create a new array that has one more element than the original valueInventory array
                                valueInventory = Arrays.copyOf(valueInventory, valueInventory.length + 1);
                                //Assign the itemName to the last element of the new valueInventory array
                                valueInventory[valueInventory.length - 1] = itemName;
                                //Update the lineInventory
                                lineInventory = String.join(",", valueInventory);
                                foundInventory = true;
                            }
                            //Adding line to list
                            list.add(lineInventory);
                            //Reading line
                            lineInventory = reader.readLine();
                        }
                        //Close reader object
                        reader.close();

                        //Check if user does not have an inventory
                        if (!foundInventory) {
                            //Add new line to list
                            list.add(user.getUsername() + "," + itemName);
                        }

                        //Creating a printWriter object for writing data to file
                        PrintWriter printWriter = new PrintWriter(inventoryFile);

                        //For-each loop which write lines to the file
                        for (String l : list) {
                            printWriter.println(l);
                        }
                        //Close printWriter object
                        printWriter.close();
                    //If user does not have enough VRC to buy the item, then programme will tell the problem
                    } else {
                        System.out.println("Purchase failed. You don't have enough VRC.");
                        found = true;
                    }
                }
                //Add line value to the list
                lines.add(String.join(",", values));
                //Read line from file
                line = bufferReader.readLine();
            }
            //Close buffer reader object
            bufferReader.close();

            //If found is false, then programme will tell the user that purchase was failed and why
            if (!found) {
                System.out.println("Purchase failed. You don't have enough VRC.");
                return;
            }

            //Creating pw(printWriter) object
            PrintWriter pw = new PrintWriter(file);

            //For-each loop which write lines to the file
            for (String l : lines) {
                pw.println(l);
            }
            //Close pw object
            pw.close();
        //If the IOException occurs, then programme provide information why
        } catch (IOException e) {
            System.out.println("An error occurred while processing the purchase.");
        //If the NumberFormatException occurs, then programme provide information why
        } catch (NumberFormatException e) {
            System.out.println("Invalid VRC value in the input file.");
        }
    }

    //Method which returns a list of user balance sorted in descending order(by balance)
    public static List<String[]> getLeaderboard() {
        //Creating a ArrayList to store user balance
        List<String[]> leaderboard = new ArrayList<>();
        try {
            //Creating a balanceFile object
            File balanceFile = new File(BALANCE);

            boolean fileExists = balanceFile.exists();
            boolean fileIsEmpty = !fileExists || balanceFile.length() == 0;

            //If file doesn't exist, then programme will create it
            if (!fileExists) {
                balanceFile.createNewFile();
            }

            //Creating a userFile object
            File userFile = new File(USER);

            //Create an object userReader
            BufferedReader userReader = new BufferedReader(new FileReader(userFile));

            //Create a map to store user email-username mapping
            Map<String, String> userEmailUsername = new HashMap<>();
            String userLine;

            //Loop which reads lines in file and add the email-username mappings to the map
            while ((userLine = userReader.readLine()) != null) {
                String[] userCells= userLine.split(",");
                if (userCells.length >= 4) {
                    userEmailUsername.put(userCells[1], userCells[0]);
                }
            }
            userReader.close();

            //Creating a balanceReader object
            BufferedReader balanceReader = new BufferedReader(new FileReader(balanceFile));

            //Create array to store the headers
            String[] header = {"Email", "Balance"};

            //If file is empty, then write the header
            if (fileIsEmpty) {
                //Create a writer object
                FileWriter writer = new FileWriter(balanceFile);
                //Writing the header row to the CSV file using a FileWriter object
                writer.write(String.join(",", header) + "\n");
                //Close writer object
                writer.close();
            }
            //Skip the header
            balanceReader.readLine();
            String balanceLine;

            //Loop which reads lines in file and add the username, email, balance to leaderboard.
            while ((balanceLine = balanceReader.readLine()) != null) {
                String[] values = balanceLine.split(",");
                String[] row = new String[3];
                row[0] = userEmailUsername.getOrDefault(values[0], null);
                row[1] = values[0];
                row[2] = values[1];
                leaderboard.add(row);
            }
            balanceReader.close();

            //Here I sort leaderboard by balance in descending order
            leaderboard.sort((s1, s2) -> {
                int vrc1 = Integer.parseInt(s1[2]);
                int vrc2 = Integer.parseInt(s2[2]);
                return Integer.compare(vrc2, vrc1);
            });
        //If the IOException occurs, then programme provide information why
        } catch (IOException e) {
            System.out.println("An error occurred while displaying the leaderboard.");
        //If the NumberFormatException occurs, then programme provide information why
        } catch (NumberFormatException e) {
            System.out.println("Invalid VRC value in the input file.");
        }
        return leaderboard;
    }

    //Method for getting user balance for "Shop" and "Profile" classes
    public static int getUserBalance(User user) {
        try {
            //Creating a file object
            File file = new File(BALANCE);

            //If file doesn't exist, then programme create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //Creating bufferReader object
            BufferedReader bufferReader = new BufferedReader(new FileReader(file));
            String line = bufferReader.readLine();

            //Loop which reads "balance.csv" line by line
            while (line != null) {
                //Here I use comma as a delim
                String[] values = line.split(",");
                //Checking if the first value(values[0]) in the array is equal to the email of the user
                if (values[0].equals(user.getEmail())) {
                    //Second value is the balance
                    int vrc = Integer.parseInt(values[1]);
                    //Close bufferReader object
                    bufferReader.close();
                    return vrc;
                }
                //Read line from file
                line = bufferReader.readLine();
            }
            //Close bufferReader object
            bufferReader.close();
            //If the IOException occurs, then programme provide information why
        } catch (IOException e) {
            System.out.println("An error occurred while processing the user balance.");
            //If the NumberFormatException occurs, then programme provide information why
        } catch (NumberFormatException e) {
            System.out.println("Invalid VRC value in the input file.");
        }
        return 0;
    }

    //Method for getting user inventory for "Profile" class
    public static Map<String, Integer> getUserInventory(User user) {
        try {
            //Creating a inventoryFile object
            File inventoryFile = new File(INVENTORY);

            //If file doesn't exist, then programme will create it
            if (!inventoryFile.exists()) {
                inventoryFile.createNewFile();
            }

            //Create a bufferReader object
            BufferedReader bufferReader = new BufferedReader(new FileReader(inventoryFile));
            String line = bufferReader.readLine();

            //Creating a TreeMap to store the item names and number of items
            Map<String, Integer> itemCount = new TreeMap<>();

            //Loop which reads "inventory.csv" line by line
            while (line != null) {
                //Here I use comma as a delim
                String[] values = line.split(",");
                //Checking if the first value in the array(values[0]) is equal to the actual username
                if (values[0].equals(user.getUsername())) {
                    //Looping over the remaining array values
                    for (int i = 1; i < values.length; i++) {
                        String itemName = values[i];
                        //Getting the item's name and adding it to the map with a count of 1 or, if it already exists, increasing its count.
                        itemCount.put(itemName, itemCount.getOrDefault(itemName, 0) + 1);
                    }
                }
                //Read line from file
                line = bufferReader.readLine();
            }
            //Close buffer reader object
            bufferReader.close();
            return itemCount;
            //If the IOException occurs, then programme provide information why
        } catch (IOException e) {
            System.out.println("An error occurred while processing the inventory.");
            return null;
        }
    }
}