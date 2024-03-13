package com.appointment.demo.Patient.Model;

import com.appointment.demo.User.Model.UserModel;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@DiscriminatorValue("PatientModel")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PatientModel extends UserModel {
    //@NotNull(message = "First Name cannot be null")
    private Number _socialSecurityNumber;

    private Number _cardNumber;

    private Number _patientPhone;

    private String _patientAdress;

    public PatientModel() {}
    public PatientModel(String userName,
                        String userSurname,
                        String userPassword,
                        Long userId,
                        Number socialSecurityNumber,
                        Number cardNumber,
                        Number patientPhone,
                        String patientAdress) {
        super(userName, userSurname, userPassword, userId);
        this._socialSecurityNumber = socialSecurityNumber;
        this._cardNumber = cardNumber;
        this._patientPhone = patientPhone;
        this._patientAdress = patientAdress;
    }

    public String getPatientAdress() {
        return _patientAdress;
    }

    public void setPatientAdress(String patientAdress) {
        this._patientAdress = patientAdress;
    }

    public  Number getSocialSecurityNumber(){ return _socialSecurityNumber; }

    public void setSocialSecurityNumber(Number socialSecurityNumber){ this._socialSecurityNumber = socialSecurityNumber; }

    public  Number getCardNumber(){ return _cardNumber; }

    public void setCardNumber(Number cardNumber){ this._cardNumber = cardNumber; }

    public  Number getPatientPhone(){ return _patientPhone; }

    public void setPatientPhone(Number patientPhone){ this._patientPhone = patientPhone; }


}
