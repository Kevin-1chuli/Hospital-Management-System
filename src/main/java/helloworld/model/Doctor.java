package helloworld.model;

public class Doctor {
private int doctor_id;
private String first_name;
private String last_name;
private String  gender;
private String phone;
private String email;
private String specialization;
private String department;

    public Doctor(int doctor_id,String first_name,String last_name,
                  String gender,String phone,String email,String specialization,String department ){
           this.doctor_id = doctor_id;
           this.first_name = first_name;
           this.last_name = last_name;
           this.gender = gender;
           this.phone = phone;
           this.email = email;
           this.specialization = specialization;
           this.department = department;
    }
    public int getDoctors_id(){
        return doctor_id;
    }
    public void setDoctors_id(int doctor_id){
        this.doctor_id = doctor_id;
    }
    public String getFirst_name(){
        return first_name;
    }
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }
    public String getLast_name(){
        return last_name = last_name;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhone(){
        return phone;
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
    public String getSpecialization(){
        return specialization;
    }
    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department =department;
    }
    @Override
    public String toString() {
        return "Doctor{" + "doctor_id =" + doctor_id +
                ",first_name = '" + first_name + '\'' +
                ",last_name = '" + last_name + '\'' +
                ",gender = '" + gender + '\'' +
                ",phone = '" + phone + '\'' +
                ",email = '" + email + '\'' +
                ",specialization = '" + specialization + '\'' +
                ",department = '" + department + '\'' +
                '}';
    }
}

