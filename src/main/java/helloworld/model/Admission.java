package helloworld.model;

import java.time.LocalDate;

public class Admission {

        private int admissionId;
        private int patientID;
        private int doctor_id;
        private int room_number;
        private LocalDate admissionDate;
        public Admission(){}
        public Admission(int admissionId, int patientID, int doctor_id, int room_number, LocalDate admissionDate) {
            this.admissionId = admissionId;
            this.patientID = patientID;
            this.doctor_id = doctor_id;
            this.room_number = room_number;
            this.admissionDate = admissionDate;
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
        public int getRoom_number(){ return room_number; }
        public void setRoomNumber(int room_number){ this.room_number = room_number; }
        public LocalDate getAdmissionDate(){
            return admissionDate;
        }
        public void setAdmissionDate(LocalDate admissionDate){
            this.admissionDate = admissionDate;
        }

}
