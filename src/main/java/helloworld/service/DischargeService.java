package helloworld.service;

import helloworld.model.Discharge;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DischargeService {
    private final Connection conn;

    public DischargeService(Connection conn) {
        this.conn = conn;
    }

    public void addDischarge(Discharge discharge) throws SQLException {
        String sql = "INSERT INTO discharges (admission_id, discharge_date, diagnosis) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, discharge.getAdmissionId());
            stmt.setDate(2, Date.valueOf(discharge.getDischargeDate()));
            stmt.setString(3, discharge.getDiagnosis());
            stmt.executeUpdate();
        }

        // Optionally update discharge date in admissions table
        String updateAdmission = "UPDATE admissions SET discharge_date = ? WHERE admission_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateAdmission)) {
            stmt.setDate(1, Date.valueOf(discharge.getDischargeDate()));
            stmt.setInt(2, discharge.getAdmissionId());
            stmt.executeUpdate();
        }
    }

    public List<Discharge> getAllDischarges() throws SQLException {
        List<Discharge> discharges = new ArrayList<>();
        String sql = "SELECT * FROM discharges";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Discharge discharge = new Discharge(
                        rs.getInt("discharge_id"),
                        rs.getInt("admission_id"),
                        rs.getDate("discharge_date").toLocalDate(),
                        rs.getString("diagnosis")
                );
                discharges.add(discharge);
            }
        }
        return discharges;
    }
}

