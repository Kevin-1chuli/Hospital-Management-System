package helloworld.model;

public class PrescriptionMedicine {
    private int id;
    private int prescriptionId;
    private int medicineId;
    private String dosage;

    public PrescriptionMedicine() {}

    public PrescriptionMedicine(int id, int prescriptionId, int medicineId, String dosage) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.medicineId = medicineId;
        this.dosage = dosage;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(int prescriptionId) { this.prescriptionId = prescriptionId; }

    public int getMedicineId() { return medicineId; }
    public void setMedicineId(int medicineId) { this.medicineId = medicineId; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    @Override
    public String toString() {
        return "PrescriptionMedicine{" +
                "ID=" + id +
                ", Prescription ID=" + prescriptionId +
                ", Medicine ID=" + medicineId +
                ", Dosage='" + dosage + '\'' +
                '}';
    }
}
