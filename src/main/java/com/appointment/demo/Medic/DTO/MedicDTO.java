package com.appointment.demo.Medic.DTO;

import com.appointment.demo.User.DTO.UserDTO;

public class MedicDTO {

    private UserDTO userDTO;

    private String medicalLicense;

    public MedicDTO(UserDTO userDTO, String medicalLicense) {
        this.userDTO = userDTO;
        this.medicalLicense = medicalLicense;
    }

    public MedicDTO(MedicDTO medicDTO) {
    }

    public UserDTO getUserDTO() {
        return this.userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getMedicalLicense() {
        return this.medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }

}
