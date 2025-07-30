package helloworld.service;

import helloworld.model.Inventory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final Connection conn;

    public InventoryService(Connection conn) {
        this.conn = conn;
    }

    public void addInventoryItem(Inventory item) throws SQLException {
        String sql = "INSERT INTO inventory (item_id, item_name, quantity, unit, last_updated) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getItemId());
            stmt.setString(2, item.getItemName());
            stmt.setInt(3, item.getQuantity());
            stmt.setString(4, item.getUnit());
            stmt.setTimestamp(5, Timestamp.valueOf(item.getLastUpdated()));
            stmt.executeUpdate();
        }
    }

    public List<Inventory> getAllInventoryItems() throws SQLException {
        List<Inventory> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Inventory item = new Inventory(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getString("unit"),
                        rs.getTimestamp("last_updated").toLocalDateTime()
                );
                items.add(item);
            }
        }
        return items;
    }

    public void updateInventoryQuantity(int itemId, int newQuantity, LocalDateTime updatedDate) throws SQLException {
        String sql = "UPDATE inventory SET quantity = ?, last_updated = ? WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setDate(2, Date.valueOf(String.valueOf(updatedDate)));
            stmt.setInt(3, itemId);
            stmt.executeUpdate();
        }
    }
}

