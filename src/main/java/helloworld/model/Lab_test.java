package helloworld.model;

import java.time.LocalDate;

public class Lab_test {
    private int labTestId;
    private int patientId;
    private int doctorId;
    private String testName;
    private LocalDate testDate;
    private String result;
    private String status;

    public Lab_test(int labTestId, int patientId, int doctorId, String testName,
                   LocalDate testDate, String result, String status) {
        this.labTestId = labTestId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.testName = testName;
        this.testDate = testDate;
        this.result = result;
        this.status = status;
    }

    // Getters
    public int getLabTestId() {
        return labTestId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getTestName() {
        return testName;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setLabTestId(int labTestId) {
        this.labTestId = labTestId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LabTest{" +
                "ID=" + labTestId +
                ", PatientID=" + patientId +
                ", DoctorID=" + doctorId +
                ", TestName='" + testName + '\'' +
                ", TestDate=" + testDate +
                ", Result='" + result + '\'' +
                ", Status='" + status + '\'' +
                '}';
    }
}

