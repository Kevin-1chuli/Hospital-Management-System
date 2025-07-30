package helloworld.model;

import java.time.LocalDate;

public class Bill {

    private int billId;
    private int patientId;
    private LocalDate billingDate;
    private double amount;
    private String status; // e.g., "Paid", "Unpaid"

    public Bill() {}

    public Bill(int billId, int patientId, LocalDate billingDate, double amount, String status) {
        this.billId = billId;
        this.patientId = patientId;
        this.billingDate = billingDate;
        this.amount = amount;
        this.status = status;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", patientId=" + patientId +
                ", billingDate=" + billingDate +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}

