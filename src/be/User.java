package be;

public class User {
    private UserType type;
    private String email;
    private String password;

    public User(UserType type, String email, String password){
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public User(UserType userType,String email){
        this.type = userType;
        this.email = email;
    }

    public User(String email,String password){
        this.email = email;
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
