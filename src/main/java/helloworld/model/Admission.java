package helloworld.model;

import java.time.LocalDate;

public class Admission {

        private int admissionId;
        private int patientID;
        private int doctor_id;
        private int departmentId;
        private int room_number;
        private LocalDate admissionDate;
        private LocalDate dischargeDate;
        public Admission(){}
        public Admission(int admissionId, int patientID, int doctor_id, int departmentId,int room_number, LocalDate admissionDate, LocalDate dischargeDate) {
            this.admissionId = admissionId;
            this.patientID = patientID;
            this.doctor_id = doctor_id;
            this.departmentId = departmentId;
            this.room_number = room_number;
            this.admissionDate = admissionDate;
            this.dischargeDate = dischargeDate;
        }

        public int getAdmissionId(){
            return admissionId;
        }
        public void setAdmissionId(int admissionId){
            this.admissionId = admissionId;
        }
        public int getPatientID(){
            return patientID;
        }
        public void setPatientID(int patientID){
            this.patientID = patientID;
        }
        public int getDoctor_id(){
            return doctor_id;
        }
        public void setDoctor_id(int doctor_id){
            this.doctor_id = doctor_id;
        }
        public int getDepartmentId(){
            return departmentId;
        }
        public void setDepartmentId(int departmentId){
            this.departmentId = departmentId;
        }
        public int getRoom_number(){ return room_number; }
        public void setRoomNumber(int room_number){ this.room_number = room_number; }
        public LocalDate getAdmissionDate(){
            return admissionDate;
        }
        public void setAdmissionDate(LocalDate admissionDate){
            this.admissionDate = admissionDate;
        }
        public LocalDate getDischargeDate(){
            return dischargeDate;
        }
        public void setDischargeDate(LocalDate dischargeDate){
            this.dischargeDate = dischargeDate;
        }

}
