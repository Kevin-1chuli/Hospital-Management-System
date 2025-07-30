package helloworld.service;

import helloworld.model.MedicalRecord;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {
    private final Connection conn;

    public MedicalRecordService(Connection conn) {
        this.conn = conn;
    }

    public void addMedicalRecord(MedicalRecord record) throws SQLException {
        String sql = "INSERT INTO medical_records (record_id, patient_id, doctor_id, record_date, notes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getRecordId());
            stmt.setInt(2, record.getPatientId());
            stmt.setInt(3, record.getDoctorId());
            stmt.setDate(4, Date.valueOf(record.getRecordDate()));
            stmt.setString(5, record.getNotes());
            stmt.executeUpdate();
        }
    }

    public List<MedicalRecord> getAllMedicalRecords() throws SQLException {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MedicalRecord record = new MedicalRecord(
                        rs.getInt("record_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("record_date").toLocalDate(),
                        rs.getString("notes")
                );
                records.add(record);
            }
        }
        return records;
    }

    public MedicalRecord getRecordById(int recordId) throws SQLException {
        String sql = "SELECT * FROM medical_records WHERE record_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MedicalRecord(
                            rs.getInt("record_id"),
                            rs.getInt("patient_id"),
                            rs.getInt("doctor_id"),
                            rs.getDate("record_date").toLocalDate(),
                            rs.getString("notes")
                    );
                }
            }
        }
        return null;
    }
}

