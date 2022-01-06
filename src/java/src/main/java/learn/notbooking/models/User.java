package learn.notbooking.models;

public class User {
    private int userId;
    private String userName;
    private String passwordHash;
    private int userRoleId;

    public User() {
    }

    public User(int userId, String userName, String passwordHash, int userRoleId) {
        this.userId = userId;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
}
