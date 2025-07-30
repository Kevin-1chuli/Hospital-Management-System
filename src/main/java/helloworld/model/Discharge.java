package helloworld.model;

import java.time.LocalDate;

public class Discharge {
    private int dischargeId;
    private int admissionId;
    private LocalDate dischargeDate;
    private String diagnosis;

    public Discharge() {}

    public Discharge(int dischargeId, int admissionId, LocalDate dischargeDate, String diagnosis) {
        this.dischargeId = dischargeId;
        this.admissionId = admissionId;
        this.dischargeDate = dischargeDate;
        this.diagnosis = diagnosis;
    }

    public int getDischargeId() {
        return dischargeId;
    }

    public void setDischargeId(int dischargeId) {
        this.dischargeId = dischargeId;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Discharge ID: " + dischargeId +
                ", Admission ID: " + admissionId +
                ", Discharge Date: " + dischargeDate +
                ", Diagnosis: " + diagnosis;
    }
}

