package helloworld;

import helloworld.model.Appointment;
import helloworld.model.Department;
import helloworld.model.Patient;
import helloworld.model.Doctor;
import helloworld.service.AppointmentService;
import helloworld.service.DepartmentService;
import helloworld.service.PatientService;
import helloworld.service.DoctorService;

import java.time.LocalDate;

import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementApp {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException {
         String url = "jdbc:mysql://localhost:3306/hospital_db";
         String username = "root";
         String password = "";

         Class.forName("com.mysql.cj.jdbc.Driver");


        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PatientService patientService = new PatientService(conn);
            DoctorService doctorService = new DoctorService(conn);
            AppointmentService appointmentService = new AppointmentService(conn);
            DepartmentService departmentService = new DepartmentService(conn);

            System.out.println("Welcome to Hospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Register Doctor");
            System.out.println("3. Schedule Appointments");
            System.out.println("4. View All patients");
            System.out.println("5. View All Doctors");
            System.out.println("6. View All Appointments");
            System.out.println("7. Register department");
            System.out.println("8. View All Departments");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Register Patient
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter gender (M/F): ");
                    String gender = scanner.nextLine();

                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    LocalDate dob = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter blood group: ");
                    String bloodGroup = scanner.nextLine();

                    LocalDate registeredDate = LocalDate.now();

                    Patient newPatient = new Patient(0, firstName, lastName, gender, dob,
                            email, phone, address, bloodGroup, registeredDate);

                    patientService.addPatient(newPatient);
                    System.out.println("✅ Patient registered successfully!");
                    break;

                case 2:
                    // Register Doctor
                    System.out.print("Enter first name: ");
                    String dFirstName = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String dLastName = scanner.nextLine();

                    System.out.print("Enter gender (M/F): ");
                    String dGender = scanner.nextLine();

                    System.out.print("Enter phone number: ");
                    String dPhone = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String dEmail = scanner.nextLine();

                    System.out.print("Enter specialization: ");
                    String specialization = scanner.nextLine();

                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();

                    Doctor newDoctor = new Doctor(0, dFirstName, dLastName, dGender,
                            dPhone, dEmail, specialization, department);

                    doctorService.addDoctor(newDoctor);
                    System.out.println("✅ Doctor registered successfully!");
                    break;

                case 3:
                    // Schedule Appointment
                    System.out.print("Enter patient ID: ");
                    int patientId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter doctor ID: ");
                    int doctorId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter appointment date and time (yyyy-MM-ddTHH:mm): ");
                    LocalDateTime appointmentDateTime = LocalDateTime.parse(scanner.nextLine());

                    System.out.print("Enter reason for appointment: ");
                    String reason = scanner.nextLine();

                    Appointment newAppointment = new Appointment(0, patientId, doctorId, appointmentDateTime, reason);
                    appointmentService.addAppointment(newAppointment);
                    System.out.println("✅ Appointment scheduled successfully!");
                    break;

                case 4:
                    // View all patients
                    List<Patient> patients = patientService.getAllPatients();
                    System.out.println("--- Registered Patients ---");
                    for (Patient p : patients) {
                        System.out.println(p);
                    }
                    break;

                case 5:
                    // View all doctors
                    List<Doctor> doctors = doctorService.getAllDoctors();
                    System.out.println("--- Registered Doctors ---");
                    for (Doctor d : doctors) {
                        System.out.println(d);
                    }
                    break;

                case 6:
                    // View all appointments
                    List<Appointment> appointments = appointmentService.getAllAppointments();
                    System.out.println("--- Scheduled Appointments ---");
                    for (Appointment a : appointments) {
                        System.out.println("ID: " + a.getAppointmentId()
                                + ", Patient ID: " + a.getPatientId()
                                + ", Doctor ID: " + a.getDoctorId()
                                + ", Date & Time: " + a.getAppointmentDate()
                                + ", Reason: " + a.getReason());
                    }
                    break;

                case 7:
                    // Register Department
                    System.out.print("Enter department name: ");
                    String deptName = scanner.nextLine();

                    System.out.print("Enter department description: ");
                    String deptDescription = scanner.nextLine();

                    Department newDepartment = new Department(deptName, deptDescription);
                    departmentService.addDepartment(newDepartment);
                    System.out.println("✅ Department registered successfully!");
                    break;

                case 8:
                    // View all departments
                    List<Department> departments = departmentService.getAllDepartments();
                    System.out.println("--- Registered Departments ---");
                    for (Department d : departments) {
                        System.out.println(d);
                    }
                    break;


                default:
                    System.out.println("❌ Invalid choice.");
                    break;
            }

        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Input error: " + e.getMessage());
        } finally {
            scanner.close();
        }


    }
}
