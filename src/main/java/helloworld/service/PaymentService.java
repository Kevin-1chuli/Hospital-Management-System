package helloworld.service;

import helloworld.model.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private final Connection conn;

    public PaymentService(Connection conn) {
        this.conn = conn;
    }

    public void addPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (bill_id, amount, payment_date, method) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getBillId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(4, payment.getMethod());
            stmt.executeUpdate();
        }
    }

    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("bill_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date").toLocalDate(),
                        rs.getString("method")
                );
                payments.add(payment);
            }
        }
        return payments;
    }

    public List<Payment> getPaymentsByBillId(int billId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE bill_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Payment payment = new Payment(
                            rs.getInt("payment_id"),
                            rs.getInt("bill_id"),
                            rs.getDouble("amount"),
                            rs.getDate("payment_date").toLocalDate(),
                            rs.getString("method")
                    );
                    payments.add(payment);
                }
            }
        }
        return payments;
    }
}

