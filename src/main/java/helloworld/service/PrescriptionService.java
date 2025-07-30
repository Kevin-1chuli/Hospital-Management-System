package helloworld.service;

import helloworld.model.Prescription;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionService {
    private final Connection conn;

    public PrescriptionService(Connection conn) {
        this.conn = conn;
    }

    public void addPrescription(Prescription prescription) throws SQLException {
        String sql = "INSERT INTO prescriptions (prescription_id, patient_id, doctor_id, prescription_date, notes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prescription.getPrescriptionId());
            stmt.setInt(2, prescription.getPatientId());
            stmt.setInt(3, prescription.getDoctorId());
            stmt.setDate(4, Date.valueOf(prescription.getPrescriptionDate()));
            stmt.setString(5, prescription.getNotes());
            stmt.executeUpdate();
        }
    }

    public List<Prescription> getAllPrescriptions() throws SQLException {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Prescription prescription = new Prescription(
                        rs.getInt("prescription_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("prescription_date").toLocalDate(),
                        rs.getString("notes")
                );
                prescriptions.add(prescription);
            }
        }
        return prescriptions;
    }
}

