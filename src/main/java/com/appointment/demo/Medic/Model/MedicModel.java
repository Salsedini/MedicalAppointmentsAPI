package com.appointment.demo.Medic.Model;

import com.appointment.demo.User.Model.UserModel;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MedicModel")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MedicModel extends UserModel {
    //@NotNull(message = "First Name cannot be null")
    private String _medicalLicense;

    public MedicModel() {}
    public MedicModel(String userName, String userSurname, String userPassword, Long userId, String medicalLicense) {
        super(userName, userSurname, userPassword, userId);
        this._medicalLicense = medicalLicense;
    }

    public String getMedicalLicense() {
        return _medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this._medicalLicense = medicalLicense;
    }



}
