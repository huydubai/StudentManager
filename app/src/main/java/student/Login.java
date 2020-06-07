package student;

public class Login {
    private int id;
    private String UserName;
    private String Password;

    public Login() {
    }

    public Login(int id, String userName, String password) {
        this.id = id;
        UserName = userName;
        Password = password;
    }

    public Login(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
