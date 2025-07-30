package helloworld.model;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String role;
    private Integer relatedDoctorId; // can be null for non-doctor roles

    public User() {}

    public User(int userId, String username, String passwordHash, String role, Integer relatedDoctorId) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.relatedDoctorId = relatedDoctorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRelatedDoctorId() {
        return relatedDoctorId;
    }

    public void setRelatedDoctorId(Integer relatedDoctorId) {
        this.relatedDoctorId = relatedDoctorId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", relatedDoctorId=" + relatedDoctorId +
                '}';
    }
}

