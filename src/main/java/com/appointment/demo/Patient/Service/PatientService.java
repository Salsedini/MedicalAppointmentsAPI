package com.appointment.demo.Patient.Service;

import com.appointment.demo.Patient.DTO.PatientDTO;
import com.appointment.demo.Patient.Model.PatientModel;
import com.appointment.demo.Patient.Repository.PatientRepository;
import com.appointment.demo.Patient.Exception.PatientException;
import com.appointment.demo.User.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    private PatientDTO mapPatientModelToCreatePatientDTO(PatientModel patientModel) {
        UserDTO userDTO = new UserDTO(
                patientModel.getUserName(),
                patientModel.getUserSurname(),
                patientModel.getUserPassword(),
                patientModel.getUserId()
        );

        return new PatientDTO(
                userDTO,
                patientModel.getSocialSecurityNumber(),
                patientModel.getCardNumber(),
                patientModel.getPatientPhone(),
                patientModel.getPatientAdress()
        );
    }


    private PatientModel mapCreatePatientDTOToPatientModel(PatientDTO patientDTO) {

        UserDTO userDTO = patientDTO.getUserDTO();

        return new PatientModel(
                userDTO.getUserName(),
                userDTO.getUserSurname(),
                userDTO.getUserPassword(),
                userDTO.getUserId(),
                patientDTO.getSocialSecurityNumber(),
                patientDTO.getCardNumber(),
                patientDTO.getPatientPhone(),
                patientDTO.getPatientAdress()
        );

    }

    public PatientDTO createPatient(PatientDTO patientDTO){

        try {
            PatientModel patientModel = mapCreatePatientDTOToPatientModel(patientDTO);
            patientRepository.save(patientModel);
            return mapPatientModelToCreatePatientDTO(patientModel);
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }


    public List<PatientDTO> findAll() {

        List<PatientModel> patientsList = patientRepository.findAll();

        return patientsList.stream()
                .map(this::mapPatientModelToCreatePatientDTO)
                .collect(Collectors.toList());

    }

    public PatientDTO getPatientById(Long patientId) {
        Optional<PatientModel> optionalPatient = patientRepository.findById(patientId);

        if (optionalPatient.isPresent()) {
            PatientModel foundPatient = optionalPatient.get();
            return mapPatientModelToCreatePatientDTO(foundPatient);
        } else {
            throw new PatientException("El usuario con el ID " + patientId + " no fue encontrado.");
        }

    }

    public PatientDTO updatePatient(Long userId, PatientDTO patientDTO) {
        Optional<PatientModel> optionalPatient = patientRepository.findById(userId);

        if (optionalPatient.isPresent()) {
            PatientModel foundPatient = optionalPatient.get();
            foundPatient.setUserName(patientDTO.getUserDTO().getUserName());
            foundPatient.setUserSurname(patientDTO.getUserDTO().getUserSurname());
            foundPatient.setUserPassword(patientDTO.getUserDTO().getUserPassword());
            foundPatient.setSocialSecurityNumber(patientDTO.getSocialSecurityNumber());
            foundPatient.setCardNumber(patientDTO.getCardNumber());
            foundPatient.setPatientPhone(patientDTO.getPatientPhone());
            foundPatient.setPatientAdress(patientDTO.getPatientAdress());
            patientRepository.save(foundPatient);
            return mapPatientModelToCreatePatientDTO(foundPatient);
        } else {
            throw new PatientException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }

    public PatientDTO deletePatient(Long userId) {
        Optional<PatientModel> foundPatient = patientRepository.findById(userId);

        if (foundPatient.isPresent()) {
            patientRepository.deleteById(userId);
            return null;
        } else {
            throw new PatientException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }
    
}
