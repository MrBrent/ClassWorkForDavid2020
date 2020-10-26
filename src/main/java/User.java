public class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String username;
    private final String password;
    private boolean loggedIn = false;

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public void logIn() {
        loggedIn = true;
    }

    public void logOut() {
        loggedIn = false;
    }
}
