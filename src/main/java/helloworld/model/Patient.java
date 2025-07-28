package helloworld.model;

import java.time.LocalDate;


public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate DOB;
    private String email;
    private String phone;
    private String address;
    private String bloodGroup;
    private LocalDate registeredDate;

    public Patient(){}

    public Patient(int patientID, String firstName, String lastName, String gender,
                   LocalDate DOB, String email, String phone, String bloodGroup, String address,LocalDate registeredDate){
        this.patientID = patientID;
        this.firstName = firstName;
        this .lastName = lastName;
        this.gender = gender;
        this.DOB  = DOB;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.registeredDate = registeredDate;

    }

    public int getPatientID(){
        return patientID;
    }
    public void setPatientID(int patientID){
        this.patientID = patientID;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public LocalDate getDOB(){
        return DOB;
    }
    public void setDOB(LocalDate DOB){
        this.DOB = DOB;
    }
    public String getPhone(){
        return phone;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getBloodGroup(){
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup){
        this.bloodGroup = bloodGroup;
    }
    public LocalDate getRegisteredDate(){
        return registeredDate;
    }
    public void setRegisteredDate(LocalDate registeredDate){
        this.registeredDate = registeredDate;
    }

    @Override
   public String toString(){
      return "Patient{" + "patientID =" + patientID +
              ",firstName = '" + firstName + '\'' +
              ",lastName = '" + lastName + '\'' +
              ",Gender = '" + gender + '\'' +
              ",DOB = '" + DOB +
              ", phone ='" + phone + '\'' +
              ",email = '" + email + '\'' +
              ",address = '" + address + '\'' +
              ",bloodGroup = '" + bloodGroup + '\'' +
              ",registeredDate = '" + registeredDate + '\'' +
         '}';
    }


}
