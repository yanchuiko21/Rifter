//This class serves as a blueprint for creating User Objects
public class User {

    //User have four attributes(username, email, password, security number)
    private final String username;
    private final String email;
    private String password;
    private final int securityNumber;

    //Here is a constructor with four parameters(username, email, password, security number) for initializing User object
    public User(String username, String email, String password, int securityNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.securityNumber = securityNumber;
    }

    //Method for getting username
    public String getUsername() {
        return this.username;
    }

    //Method for getting email
    public String getEmail() {
        return this.email;
    }

    //Method for getting password
    public String getPassword() {
        return this.password;
    }

    //Method for getting security number
    public int getSecurityNumber() {
        return securityNumber;
    }

    //This is additional method for setting password in the class called "ChangePassword"
    public void setPassword(String password) {
        this.password = password;
    }
}