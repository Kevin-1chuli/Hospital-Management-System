package helloworld.service;

import helloworld.model.Doctor;
import helloworld.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DoctorService {

    private Connection conn;

    public DoctorService(Connection conn){
        this.conn = conn;
    }
    public void addDoctor(Doctor doctor)throws SQLException{
        String sql = "INSERT INTO doctors(first_name,last_name,gender,phone,email,specialization,department) VALUES(?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,doctor.getFirst_name());
            stmt.setString(2, doctor.getLast_name());
            stmt.setString(3,doctor.getGender());
            stmt.setString(4,doctor.getPhone());
            stmt.setString(5,doctor.getEmail());
            stmt.setString(6,doctor.getSpecialization());
            stmt.setString(7,doctor.getDepartment());
            stmt.executeUpdate();
        }
    }
    public Doctor getDoctorByID(int id)throws SQLException{
        String sql = "SELECT * FROM WHERE doctors_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Doctor(
                        rs.getInt("doctors_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("specialization"),
                        rs.getString("department")

                );
            }
        }
        return null;
    }

    public List<Doctor> getAllDoctors() throws SQLException{
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try(Statement stmt  = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Doctor  d=  new  Doctor(
                   rs.getInt("doctors_id"),
                   rs.getString("first_name"),
                   rs.getString("last_name"),
                   rs.getString("gender"),
                   rs.getString("phone"),
                   rs.getString("email"),
                   rs.getString("specialization"),
                   rs.getString("department")
                );
                doctors.add(d);
            }
        }
        return doctors;
    }

    public void updateDoctors(Doctor doctor) throws SQLException {
        String sql = "UPDATE doctors SET first_name = ?, last_name = ?, gender = ?, phone = ?, email = ?, specialization = ?, department = ? WHERE doctors_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFirst_name());
            stmt.setString(2, doctor.getLast_name());
            stmt.setString(3, doctor.getGender());
            stmt.setString(4, doctor.getPhone());
            stmt.setString(5, doctor.getEmail());
            stmt.setString(6, doctor.getSpecialization());
            stmt.setString(7, doctor.getDepartment());
            stmt.setInt(8, doctor.getDoctors_id());

            stmt.executeUpdate();
        }
    }
    public void deleteDoctor(int doctors_id) throws SQLException{
        String sql = "DELETE  FROM doctors WHERE doctors_id = ?";
        try(PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setInt(1,doctors_id);
            stmt.executeUpdate();
        }
    }

}
