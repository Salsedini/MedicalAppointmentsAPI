package com.appointment.demo.Patient.DTO;

import com.appointment.demo.Medic.DTO.MedicDTO;
import com.appointment.demo.User.DTO.UserDTO;

public class PatientDTO {

    private UserDTO userDTO;

    private Number socialSecurityNumber;

    private Number cardNumber;

    private Number patientPhone;

    private String patientAdress;

    public PatientDTO(
            UserDTO userDTO,
            Number socialSecurityNumber,
            Number cardNumber,
            Number patientPhone,
            String patientAdress) {
        this.userDTO = userDTO;
        this.socialSecurityNumber = socialSecurityNumber;
        this.cardNumber = cardNumber;
        this.patientPhone = patientPhone;
        this.patientAdress = patientAdress;
    }

    public PatientDTO(PatientDTO patientDTO) {
    }

    public UserDTO getUserDTO() {
        return this.userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Number getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    public void setSocialSecurityNumber(Number socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Number getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(Number cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Number getPatientPhone() {
        return this.patientPhone;
    }

    public void setPatientPhone(Number patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientAdress() {
        return this.patientAdress;
    }

    public void setPatientAdress(String patientAdress) {
        this.patientAdress = patientAdress;
    }

}
