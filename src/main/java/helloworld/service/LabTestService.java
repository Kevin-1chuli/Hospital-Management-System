package helloworld.service;


import helloworld.model.Lab_test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LabTestService {
    private final Connection conn;

    public LabTestService(Connection conn) {
        this.conn = conn;
    }

    // Add new lab test
    public void addLabTest(Lab_test test) throws SQLException {
        String sql = "INSERT INTO lab_tests (lab_test_id, patient_id, doctor_id, test_name, test_date, result, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, test.getLabTestId());
            stmt.setInt(2, test.getPatientId());
            stmt.setInt(3, test.getDoctorId());
            stmt.setString(4, test.getTestName());
            stmt.setDate(5, Date.valueOf(test.getTestDate()));
            stmt.setString(6, test.getResult());
            stmt.setString(7, test.getStatus());
            stmt.executeUpdate();
        }
    }

    // Retrieve all lab tests
    public List<Lab_test> getAllLabTests() throws SQLException {
        List<Lab_test> tests = new ArrayList<>();
        String sql = "SELECT * FROM lab_tests";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Lab_test test = new Lab_test(
                        rs.getInt("lab_test_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("test_name"),
                        rs.getDate("test_date").toLocalDate(),
                        rs.getString("result"),
                        rs.getString("status")
                );
                tests.add(test);
            }
        }
        return tests;
    }

    // Update lab test result and status
    public void updateLabTestResult(int labTestId, String result, String status) throws SQLException {
        String sql = "UPDATE lab_tests SET result = ?, status = ? WHERE lab_test_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, result);
            stmt.setString(2, status);
            stmt.setInt(3, labTestId);
            stmt.executeUpdate();
        }
    }

    // Delete lab test by ID
    public void deleteLabTest(int labTestId) throws SQLException {
        String sql = "DELETE FROM lab_tests WHERE lab_test_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, labTestId);
            stmt.executeUpdate();
        }
    }
}

