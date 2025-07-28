package helloworld.service;

import helloworld.model.Patient;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class PatientService {
    private Connection conn;

    public PatientService(Connection conn) {
        this.conn = conn;
    }


    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients(first_name, last_name, gender, dob, phone, email, address, blood_group, registered_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getGender());
            stmt.setDate(4, Date.valueOf(patient.getDOB()));
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getBloodGroup());
            stmt.setDate(9, Date.valueOf(patient.getRegisteredDate()));
            stmt.executeUpdate();
        }
    }

    // ✅ Retrieve patient by ID
    public Patient getPatientByID(int id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("blood_group"),
                        rs.getDate("registered_date").toLocalDate()
                );
            }
        }
        return null;
    }
    public List<Patient> getAllPatients() throws SQLException{
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("bloodGroup"),
                        rs.getDate("registered_date").toLocalDate()
                );
                patients.add(p);
            }
        }

        return patients;
    }
    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patients SET firstName=?, lastName=?, gender=?, dob=?, phone=?, email=?, address=?, bloodGroup=? WHERE patientId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getGender());
            stmt.setDate(4, Date.valueOf(patient.getDOB()));
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getBloodGroup());
            stmt.setInt(9, patient.getPatientID());
            stmt.executeUpdate();
        }
    }
    public void deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patients WHERE patientId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
        }
    }
}
