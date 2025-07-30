package helloworld.service;

import helloworld.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Connection conn;

    public UserService(Connection conn) {
        this.conn = conn;
    }

    // Add new user
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_id, username, password_hash, role, related_doctor_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getRole());
            if (user.getRelatedDoctorId() != null) {
                stmt.setInt(5, user.getRelatedDoctorId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }

    // Retrieve all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("role"),
                        rs.getObject("related_doctor_id") != null ? rs.getInt("related_doctor_id") : null
                );
                users.add(user);
            }
        }
        return users;
    }

    // Find a user by username (e.g., for login)
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("role"),
                            rs.getObject("related_doctor_id") != null ? rs.getInt("related_doctor_id") : null
                    );
                }
            }
        }
        return null;
    }

    // Update user password
    public void updatePassword(int userId, String newHashedPassword) throws SQLException {
        String sql = "UPDATE users SET password_hash = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newHashedPassword);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

    // Delete user
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}

