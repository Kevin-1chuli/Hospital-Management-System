package helloworld.service;

import helloworld.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private Connection conn;

    public DepartmentService(Connection conn) {
        this.conn = conn;
    }

    // Add a new department
    public void addDepartment(Department department) throws SQLException {
        String sql = "INSERT INTO departments(department_name, description) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getName());
            stmt.setString(2, department.getDescription());
            stmt.executeUpdate();
        }
    }

    // Get department by ID
    public Department getDepartmentById(int id) throws SQLException {
        String sql = "SELECT * FROM departments WHERE department_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("description")
                );
            }
        }
        return null;
    }

    // Get all departments
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Department d = new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("description")
                );
                departments.add(d);
            }
        }
        return departments;
    }

    // Update a department
    public void updateDepartment(Department department) throws SQLException {
        String sql = "UPDATE departments SET department_name = ?, description = ? WHERE department_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getName());
            stmt.setString(2, department.getDescription());
            stmt.setInt(3, department.getDepartmentId());
            stmt.executeUpdate();
        }
    }

    // Delete a department
    public void deleteDepartment(int id) throws SQLException {
        String sql = "DELETE FROM departments WHERE department_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}