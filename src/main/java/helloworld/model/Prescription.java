package helloworld.model;

import java.time.LocalDate;

public class Prescription {
    private int prescriptionId;
    private int patientId;
    private int doctorId;
    private LocalDate prescriptionDate;
    private String notes;

    public Prescription(int prescriptionId, int patientId, int doctorId, LocalDate prescriptionDate, String notes) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionDate = prescriptionDate;
        this.notes = notes;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Prescription ID: " + prescriptionId +
                ", Patient ID: " + patientId +
                ", Doctor ID: " + doctorId +
                ", Date: " + prescriptionDate +
                ", Notes: " + notes;
    }
}

