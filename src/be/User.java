package be;

public class User {
    private int id;
    private UserType type;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public User(int id, UserType type, String email, String password, String name, String phoneNumber) {
        this.id = id;
        this.type = type;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(UserType type, String email, String password){
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public User(int id,String name){
        this.id = id;
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public String getTypeToString(){
        return this.type.toString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "type=" + type +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
