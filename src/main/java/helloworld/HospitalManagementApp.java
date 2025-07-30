package helloworld;

import helloworld.model.*;

import helloworld.service.*;

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
            AdmissionService admissionService = new AdmissionService(conn);
            DischargeService dischargeService = new DischargeService(conn);
            BillService billService = new BillService(conn);
            InventoryService inventoryService = new InventoryService(conn);
            LabTestService labTestService = new LabTestService(conn);
            MedicineService medicineService = new MedicineService(conn);
            PaymentService paymentService = new PaymentService(conn);
            PrescriptionService prescriptionService = new PrescriptionService(conn);
            PrescriptionMedicineService prescriptionMedicineService = new PrescriptionMedicineService(conn);
            UserService userService = new UserService(conn);

            System.out.println("Welcome to Hospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Register Doctor");
            System.out.println("3. Schedule Appointments");
            System.out.println("4. View All patients");
            System.out.println("5. View All Doctors");
            System.out.println("6. View All Appointments");
            System.out.println("7. Register department");
            System.out.println("8. View All Departments");
            System.out.println("9. Admit a patient");
            System.out.println("10. View All Admissions");
            System.out.println("11. Discharge patient");
            System.out.println("12. View All Discharges");
            System.out.println("13. Create Bill");
            System.out.println("14. View All Bills");
            System.out.println("15. View All Bills by Patient ID");
            System.out.println("16. Manage Inventory");
            System.out.println("17. Manage Lab Test");
            System.out.println("18. Manage Medical Records");
            System.out.println("19. Manage medicines");
            System.out.println("20. Payment");
            System.out.println("21. View All Payments");
            System.out.println("22. Add Prescription");
            System.out.println("23. View All prescriptions");
            System.out.println("24. Manage Prescription Medicines");
            System.out.println("25. Manage Users");
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

                case 9:
                    System.out.print("Enter Patient ID: ");
                    int admitPatientId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Doctor ID: ");
                    int admitDoctorId = Integer.parseInt(scanner.nextLine());


                    System.out.print("Enter Room Number: ");
                    int roomNumber = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Admission Date (YYYY-MM-DD): ");
                    LocalDate admissionDate = LocalDate.parse(scanner.nextLine());


                    Admission newAdmission = new Admission(0, admitPatientId, admitDoctorId, roomNumber, admissionDate);

                    admissionService.addAdmission(newAdmission);
                    System.out.println("✅ Patient admitted successfully!");
                    break;

                case 10:
                    admissionService.displayAllAdmissionsWithDetails();
                    break;

                case 11:
                    // Discharge Patient
                    System.out.print("Enter admission ID: ");
                    int admissionId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter discharge date (YYYY-MM-DD): ");
                    LocalDate discharge_date = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter diagnosis: ");
                    String diagnosis = scanner.nextLine();

                    Discharge discharge = new Discharge(0, admissionId, discharge_date, diagnosis);
                    dischargeService.addDischarge(discharge);
                    System.out.println("✅ Patient discharged successfully!");
                    break;

                case 12:
                    // View All Discharges
                    List<Discharge> discharges = dischargeService.getAllDischarges();
                    System.out.println("--- Discharge Records ---");
                    for (Discharge d : discharges) {
                        System.out.println(d);
                    }
                    break;

                case 13:
                    // Create Bill
                    System.out.print("Enter patient ID: ");
                    int billPatientId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter billing amount: ");
                    double billAmount = Double.parseDouble(scanner.nextLine());

                    System.out.print("Enter bill status (Paid/Unpaid): ");
                    String billStatus = scanner.nextLine();

                    LocalDate billingDate = LocalDate.now();

                    Bill newBill = new Bill(0, billPatientId, billingDate, billAmount, billStatus);
                    billService.addBill(newBill);
                    System.out.println("✅ Bill created successfully!");
                    break;

                case 14:
                    // View All Bills
                    List<Bill> allBills = billService.getAllBills();
                    System.out.println("--- All Bills ---");
                    for (Bill b : allBills) {
                        System.out.println(b);
                    }
                    break;
                case 15:
                    // View Bills by Patient ID
                    System.out.print("Enter patient ID: ");
                    int searchPatientId = Integer.parseInt(scanner.nextLine());

                    List<Bill> patientBills = billService.getBillsByPatientId(searchPatientId);
                    System.out.println("--- Bills for Patient ID " + searchPatientId + " ---");
                    for (Bill b : patientBills) {
                        System.out.println(b);
                    }
                    break;

                case 16:
                    while (true) {
                        System.out.println("\n--- Manage Inventory ---");
                        System.out.println("1. Add Inventory Item");
                        System.out.println("2. View Inventory Items");
                        System.out.println("3. Update Inventory Quantity");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Choose an inventory option: ");
                        int invChoice = Integer.parseInt(scanner.nextLine());

                        switch (invChoice) {
                            case 1:
                                System.out.print("Enter item name: ");
                                String itemName = scanner.nextLine();

                                System.out.print("Enter unit (e.g., pieces, packs): ");
                                String unit = scanner.nextLine();

                                System.out.print("Enter quantity: ");
                                int quantity = Integer.parseInt(scanner.nextLine());

                                Inventory newItem = new Inventory(0, itemName, quantity, unit, LocalDateTime.now());
                                inventoryService.addInventoryItem(newItem);
                                System.out.println("✅ Inventory item added successfully!");
                                break;

                            case 2:
                                List<Inventory> items = inventoryService.getAllInventoryItems();
                                System.out.println("--- Inventory Items ---");
                                for (Inventory item : items) {
                                    System.out.println(item);
                                }
                                break;

                            case 3:
                                System.out.print("Enter Item ID: ");
                                int itemId = Integer.parseInt(scanner.nextLine());

                                System.out.print("Enter new quantity: ");
                                int newQuantity = Integer.parseInt(scanner.nextLine());

                                LocalDateTime updatedDate = LocalDateTime.now();

                                inventoryService.updateInventoryQuantity(newQuantity, itemId, updatedDate);
                                System.out.println("✅ Inventory quantity updated.");
                                break;

                            case 0:
                                // Exit inventory management
                                System.out.println("🔙 Returning to Main Menu...");
                                break;

                            default:
                                System.out.println("❌ Invalid inventory option.");
                        }

                        if (invChoice == 0) {
                            break; // Exit inventory loop
                        }
                    }
                    break;


                case 17:
                    while(true){
                        System.out.println("----- Manage Lab Tests -----");
                        System.out.println("1. Add Lab Test");
                        System.out.println("2. View All Lab Tests");
                        System.out.println("3. Update Lab Test Result and Status");
                        System.out.println("4. Delete Lab Test");
                        System.out.println("0. Back to Menu");
                        System.out.print("Select an option: ");
                        int labChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (labChoice) {
                            case 1:
                                try {
                                    System.out.print("Enter Lab Test ID: ");
                                    int testId = scanner.nextInt();
                                    System.out.print("Enter Patient ID: ");
                                    int patient_Id = scanner.nextInt();
                                    System.out.print("Enter Doctor ID: ");
                                    int doctor_Id = scanner.nextInt();
                                    scanner.nextLine(); // consume newline
                                    System.out.print("Enter Test Name: ");
                                    String testName = scanner.nextLine();
                                    System.out.print("Enter Test Date (YYYY-MM-DD): ");
                                    LocalDate testDate = LocalDate.parse(scanner.nextLine());
                                    System.out.print("Enter Result: ");
                                    String result = scanner.nextLine();
                                    System.out.print("Enter Status: ");
                                    String status = scanner.nextLine();

                                    Lab_test newTest = new Lab_test(testId, patient_Id, doctor_Id, testName, testDate, result, status);
                                    labTestService.addLabTest(newTest);
                                    System.out.println("✅ Lab test added successfully!");
                                } catch (Exception e) {
                                    System.out.println("❌ Error adding lab test: " + e.getMessage());
                                }
                                break;

                            case 2:
                                try {
                                    List<Lab_test> allTests = labTestService.getAllLabTests();
                                    System.out.println("📋 All Lab Tests:");
                                    for (Lab_test t : allTests) {
                                        System.out.println(t);
                                    }
                                } catch (Exception e) {
                                    System.out.println("❌ Error fetching lab tests: " + e.getMessage());
                                }
                                break;

                            case 3:
                                try {
                                    System.out.print("Enter Lab Test ID to update: ");
                                    int updateId = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter new result: ");
                                    String newResult = scanner.nextLine();
                                    System.out.print("Enter new status: ");
                                    String newStatus = scanner.nextLine();
                                    labTestService.updateLabTestResult(updateId, newResult, newStatus);
                                    System.out.println("✅ Lab test updated.");
                                } catch (Exception e) {
                                    System.out.println("❌ Error updating lab test: " + e.getMessage());
                                }
                                break;

                            case 4:
                                try {
                                    System.out.print("Enter Lab Test ID to delete: ");
                                    int deleteId = scanner.nextInt();
                                    labTestService.deleteLabTest(deleteId);
                                    System.out.println("🗑️ Lab test deleted.");
                                } catch (Exception e) {
                                    System.out.println("❌ Error deleting lab test: " + e.getMessage());
                                }
                                break;
                            case 0:
                                System.out.println("Returning back to main Menu!....");
                                break;

                            default:
                                System.out.println("❗ Invalid lab test option.");
                        }
                        if (labChoice == 0) {
                            break; //break from the loop
                        }
                    }

                    break;
                case 18:
                    manageMedicalRecords(scanner, new MedicalRecordService(conn));
                    break;

                case 19:
                    while (true) {
                        System.out.println("\n📦 Manage Medicines:");
                        System.out.println("1. ➕ Add New Medicine");
                        System.out.println("2. 📋 View All Medicines");
                        System.out.println("3. 🔄 Update Medicine Stock");
                        System.out.println("4. 🔙 Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int medChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (medChoice == 1) {
                            System.out.print("Enter Medicine ID: ");
                            int medId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Medicine Name: ");
                            String medName = scanner.nextLine();
                            System.out.print("Enter Description: ");
                            String desc = scanner.nextLine();
                            System.out.print("Enter Price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Enter Stock Quantity: ");
                            int stock = scanner.nextInt();
                            scanner.nextLine();

                            Medicine newMed = new Medicine(medId, medName, desc, price, stock);
                            try {
                                medicineService.addMedicine(newMed);
                                System.out.println("✅ Medicine added successfully!");
                            } catch (SQLException e) {
                                System.out.println("❌ Error: " + e.getMessage());
                            }

                        } else if (medChoice == 2) {
                            try {
                                List<Medicine> meds = medicineService.getAllMedicines();
                                if (meds.isEmpty()) {
                                    System.out.println("📭 No medicines found.");
                                } else {
                                    System.out.println("📋 Medicines List:");
                                    for (Medicine m : meds) {
                                        System.out.println(m);
                                    }
                                }
                            } catch (SQLException e) {
                                System.out.println("❌ Error: " + e.getMessage());
                            }

                        } else if (medChoice == 3) {
                            System.out.print("Enter Medicine ID to update: ");
                            int medId = scanner.nextInt();
                            System.out.print("Enter New Stock Quantity: ");
                            int newStock = scanner.nextInt();
                            scanner.nextLine();

                            try {
                                medicineService.updateStock(medId, newStock);
                                System.out.println("✅ Stock updated successfully.");
                            } catch (SQLException e) {
                                System.out.println("❌ Error: " + e.getMessage());
                            }

                        } else if (medChoice == 4) {
                            break; // back to main menu
                        } else {
                            System.out.println("❌ Invalid choice. Try again.");
                        }
                    }
                    break;

                case 20: // Add Payment
                    try {
                        System.out.print("Enter Bill ID: ");
                        int billId = scanner.nextInt();

                        System.out.print("Enter Amount Paid: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline

                        System.out.print("Enter Payment Date (YYYY-MM-DD): ");
                        LocalDate paymentDate = LocalDate.parse(scanner.nextLine());

                        System.out.print("Enter Payment Method (e.g., Cash, Card, Mobile): ");
                        String method = scanner.nextLine();

                        Payment payment = new Payment(0, billId, amount, paymentDate, method);
                        paymentService.addPayment(payment);
                        System.out.println("✅ Payment added successfully!");
                    } catch (Exception e) {
                        System.out.println("❌ Error adding payment: " + e.getMessage());
                    }
                    break;


                case 21:  // View All Payments
                    try {
                        List<Payment> payments = paymentService.getAllPayments();
                        if (payments.isEmpty()) {
                            System.out.println("No payments found.");
                        } else {
                            for (Payment payment : payments) {
                                System.out.println(payment);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Error retrieving payments: " + e.getMessage());
                    }
                    break;

                case 22:
                    System.out.print("Enter Prescription ID: ");
                    int prescriptionId = scanner.nextInt();
                    System.out.print("Enter Patient ID: ");
                    int patient_Id = scanner.nextInt();
                    System.out.print("Enter Doctor ID: ");
                    int doctor_Id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Prescription Date (YYYY-MM-DD): ");
                    String dateStr = scanner.nextLine();
                    LocalDate prescriptionDate = LocalDate.parse(dateStr);
                    System.out.print("Enter Notes: ");
                    String notes = scanner.nextLine();

                    Prescription prescription = new Prescription(prescriptionId, patient_Id, doctor_Id, prescriptionDate, notes);
                    try {
                        prescriptionService.addPrescription(prescription);
                        System.out.println("✅ Prescription added successfully!");
                    } catch (SQLException e) {
                        System.out.println("❌ Error adding prescription: " + e.getMessage());
                    }
                    break;


                case 23:
                    try {
                        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
                        for (Prescription p : prescriptions) {
                            System.out.println(p);
                        }
                    } catch (SQLException e) {
                        System.out.println("❌ Error fetching prescriptions: " + e.getMessage());
                    }
                    break;

                case 24:
                    managePrescriptionMedicines(prescriptionMedicineService, scanner);
                    break;

                case 25:
                    manageUsers(scanner, userService);
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

    private static void manageUsers(Scanner scanner, UserService userService) {
        while (true) {
            System.out.println("\n--- Manage Users ---");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Update Password");
            System.out.println("4. Delete User");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter User ID: ");
                        int userId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter Password Hash: ");
                        String passwordHash = scanner.nextLine(); // For simplicity, using raw hash input
                        System.out.print("Enter Role: ");
                        String role = scanner.nextLine();
                        System.out.print("Enter Related Doctor ID (or 0 if N/A): ");
                        int relatedDoctorId = scanner.nextInt();
                        Integer relatedId = (relatedDoctorId == 0) ? null : relatedDoctorId;

                        User newUser = new User(userId, username, passwordHash, role, relatedId);
                        userService.addUser(newUser);
                        System.out.println("✅ User added successfully!");
                        break;

                    case 2:
                        List<User> users = userService.getAllUsers();
                        System.out.println("📋 All Users:");
                        for (User user : users) {
                            System.out.println(user);
                        }
                        break;

                    case 3:
                        System.out.print("Enter User ID to update password: ");
                        int uid = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new password hash: ");
                        String newPass = scanner.nextLine();
                        userService.updatePassword(uid, newPass);
                        System.out.println("🔐 Password updated.");
                        break;

                    case 4:
                        System.out.print("Enter User ID to delete: ");
                        int delId = scanner.nextInt();
                        userService.deleteUser(delId);
                        System.out.println("🗑️ User deleted.");
                        break;

                    case 0:
                        return;

                    default:
                        System.out.println("❌ Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }


    public static void managePrescriptionMedicines(PrescriptionMedicineService service, Scanner scanner) {
        while (true) {
            System.out.println("\n🔹 Prescription Medicines Menu");
            System.out.println("1. Add Prescription Medicine");
            System.out.println("2. View All Prescription Medicines");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Prescription Medicine ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter Prescription ID: ");
                        int prescriptionId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter Medicine ID: ");
                        int medicineId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter Dosage (e.g. 1 tablet twice a day): ");
                        String dosage = scanner.nextLine();

                        PrescriptionMedicine pm = new PrescriptionMedicine(id, prescriptionId, medicineId, dosage);
                        service.addPrescriptionMedicine(pm);
                        System.out.println("✅ Prescription medicine added successfully.");
                        break;

                    case 2:
                        List<PrescriptionMedicine> allPM = service.getAllPrescriptionMedicines();
                        for (PrescriptionMedicine p : allPM) {
                            System.out.println(p);
                        }
                        break;

                    case 3:
                        return;

                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }



    private static void manageMedicalRecords(Scanner scanner, MedicalRecordService medicalRecordService) {
        while (true) {
            System.out.println("\n--- Medical Records Menu ---");
            System.out.println("1. Add Medical Record");
            System.out.println("2. View All Medical Records");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Record ID: ");
                        int recordId = scanner.nextInt();

                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();

                        System.out.print("Enter Doctor ID: ");
                        int doctorId = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        System.out.print("Enter Record Date (YYYY-MM-DD): ");
                        LocalDate recordDate = LocalDate.parse(scanner.nextLine());

                        System.out.print("Enter Notes: ");
                        String notes = scanner.nextLine();

                        MedicalRecord record = new MedicalRecord(recordId, patientId, doctorId, recordDate, notes);
                        medicalRecordService.addMedicalRecord(record);
                        System.out.println("✅ Medical record added successfully.");
                        break;

                    case 2:
                        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords();
                        if (records.isEmpty()) {
                            System.out.println("No records found.");
                        } else {
                            for (MedicalRecord r : records) {
                                System.out.println(r);
                            }
                        }
                        break;

                    case 3:
                        return; // exit to main menu

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

}

