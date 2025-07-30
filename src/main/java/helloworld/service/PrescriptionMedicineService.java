package helloworld.service;

import helloworld.model.PrescriptionMedicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionMedicineService {
    private final Connection conn;

    public PrescriptionMedicineService(Connection conn) {
        this.conn = conn;
    }

    public void addPrescriptionMedicine(PrescriptionMedicine pm) throws SQLException {
        String sql = "INSERT INTO prescription_medicines (id, prescription_id, medicine_id, dosage) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pm.getId());
            stmt.setInt(2, pm.getPrescriptionId());
            stmt.setInt(3, pm.getMedicineId());
            stmt.setString(4, pm.getDosage());
            stmt.executeUpdate();
        }
    }

    public List<PrescriptionMedicine> getAllPrescriptionMedicines() throws SQLException {
        List<PrescriptionMedicine> list = new ArrayList<>();
        String sql = "SELECT * FROM prescription_medicines";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PrescriptionMedicine pm = new PrescriptionMedicine(
                        rs.getInt("id"),
                        rs.getInt("prescription_id"),
                        rs.getInt("medicine_id"),
                        rs.getString("dosage")
                );
                list.add(pm);
            }
        }
        return list;
    }
}

