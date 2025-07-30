package helloworld.service;

import helloworld.model.Bill;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillService {
    private final Connection conn;

    public BillService(Connection conn) {
        this.conn = conn;
    }

    // Add a new bill
    public void addBill(Bill bill) {
        String sql = "INSERT INTO bills (patient_id, bill_date, total_amount, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bill.getPatientId());
            stmt.setDate(2, Date.valueOf(bill.getBillingDate()));
            stmt.setDouble(3, bill.getAmount());
            stmt.setString(4, bill.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Failed to add bill: " + e.getMessage());
        }
    }

    // Get all bills
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bill bill = new Bill(
                        rs.getInt("bill_id"),
                        rs.getInt("patient_id"),
                        rs.getDate("bill_date").toLocalDate(),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );
                bills.add(bill);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to retrieve bills: " + e.getMessage());
        }
        return bills;
    }

    // Get bills by patient ID
    public List<Bill> getBillsByPatientId(int patientId) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE patient_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill(
                        rs.getInt("bill_id"),
                        rs.getInt("patient_id"),
                        rs.getDate("bill_date").toLocalDate(),
                        rs.getDouble("amount"),
                        rs.getString("status")
                );
                bills.add(bill);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to retrieve bills by patient ID: " + e.getMessage());
        }
        return bills;
    }

    // Update bill status
    public void updateBillStatus(int billId, String newStatus) {
        String sql = "UPDATE bills SET status = ? WHERE bill_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, billId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Failed to update bill status: " + e.getMessage());
        }
    }
}

