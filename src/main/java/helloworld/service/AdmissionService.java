package helloworld.service;

import helloworld.model.Admission;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdmissionService {
    private final Connection conn;

    public AdmissionService(Connection conn) {
        this.conn = conn;
    }

    // Add new admission
    public void addAdmission(Admission admission) {
        String sql = "INSERT INTO admissions (patient_id, doctor_id, department_id, room_number, admission_date, discharge_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, admission.getPatientID());
            stmt.setInt(2, admission.getDoctor_id());
            stmt.setInt(3, admission.getDepartmentId());
            stmt.setInt(4, admission.getRoom_number());
            stmt.setDate(5, Date.valueOf(admission.getAdmissionDate()));

            if (admission.getDischargeDate() != null) {
                stmt.setDate(6, Date.valueOf(admission.getDischargeDate()));
            } else {
                stmt.setNull(6, Types.DATE);
            }

            stmt.executeUpdate();
            System.out.println("✅ Admission successfully recorded.");
        } catch (SQLException e) {
            System.out.println("❌ Error adding admission: " + e.getMessage());
        }
    }

    // View all admissions with details
    public void displayAllAdmissionsWithDetails() {
        String sql = "SELECT a.id, a.admission_date, a.discharge_date, a.room_number, " +
                "p.first_name AS patient_first, p.last_name AS patient_last, " +
                "d.first_name AS doctor_first, d.last_name AS doctor_last, dep.name AS department_name " +
                "FROM admissions a " +
                "JOIN patients p ON a.patient_id = p.id " +
                "JOIN doctors d ON a.doctor_id = d.id " +
                "JOIN departments dep ON a.department_id = dep.id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== Admissions ===");
            while (rs.next()) {
                System.out.println("Admission ID: " + rs.getInt("id"));
                System.out.println("Admission Date: " + rs.getDate("admission_date"));
                System.out.println("Discharge Date: " + rs.getDate("discharge_date"));
                System.out.println("Room Number: " + rs.getInt("room_number"));
                System.out.println("Patient: " + rs.getString("patient_first") + " " + rs.getString("patient_last"));
                System.out.println("Doctor: " + rs.getString("doctor_first") + " " + rs.getString("doctor_last"));
                System.out.println("Department: " + rs.getString("department_name"));
                System.out.println("------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching admissions: " + e.getMessage());
        }
    }

    // (Optional) Get list of admissions
    public List<Admission> getAllAdmissions() {
        List<Admission> admissions = new ArrayList<>();
        String sql = "SELECT * FROM admissions";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Admission admission = new Admission(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getInt("department_id"),
                        rs.getInt("room_number"),
                        rs.getDate("admission_date").toLocalDate(),
                        rs.getDate("discharge_date") != null ? rs.getDate("discharge_date").toLocalDate() : null
                );
                admissions.add(admission);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving admissions: " + e.getMessage());
        }

        return admissions;
    }
}
