package helloworld.service;

import helloworld.model.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineService {
    private final Connection conn;

    public MedicineService(Connection conn) {
        this.conn = conn;
    }

    public void addMedicine(Medicine medicine) throws SQLException {
        String sql = "INSERT INTO medicines (medicine_id, name, description, price, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, medicine.getMedicineId());
            stmt.setString(2, medicine.getName());
            stmt.setString(3, medicine.getDescription());
            stmt.setDouble(4, medicine.getPrice());
            stmt.setInt(5, medicine.getStock());
            stmt.executeUpdate();
        }
    }

    public List<Medicine> getAllMedicines() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                medicines.add(medicine);
            }
        }
        return medicines;
    }

    public void updateStock(int medicineId, int newStock) throws SQLException {
        String sql = "UPDATE medicines SET stock = ? WHERE medicine_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, medicineId);
            stmt.executeUpdate();
        }
    }
}

