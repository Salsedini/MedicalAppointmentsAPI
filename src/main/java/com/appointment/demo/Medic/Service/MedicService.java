package com.appointment.demo.Medic.Service;

import com.appointment.demo.Medic.DTO.MedicDTO;
import com.appointment.demo.Medic.Model.MedicModel;
import com.appointment.demo.Medic.Repository.MedicRepository;
import com.appointment.demo.Medic.Exception.MedicException;
import com.appointment.demo.Patient.DTO.PatientDTO;
import com.appointment.demo.Patient.Model.PatientModel;
import com.appointment.demo.User.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicService {

    @Autowired
    MedicRepository medicRepository;

    private MedicDTO mapMedicModelToCreateMedicDTO(MedicModel medicModel) {
        UserDTO userDTO = new UserDTO(
                medicModel.getUserName(),
                medicModel.getUserSurname(),
                medicModel.getUserPassword(),
                medicModel.getUserId()
        );

        return new MedicDTO(
                userDTO,
                medicModel.getMedicalLicense()
        );
    }

    private MedicModel mapCreateMedicDTOToMedicModel(MedicDTO medicDTO) {

        UserDTO userDTO = medicDTO.getUserDTO();

        return new MedicModel(
                userDTO.getUserName(),
                userDTO.getUserSurname(),
                userDTO.getUserPassword(),
                userDTO.getUserId(),
                medicDTO.getMedicalLicense()
        );
    }

    public MedicDTO createMedic(MedicDTO medicDTO){

        try {
            MedicModel medicModel = mapCreateMedicDTOToMedicModel(medicDTO);
            medicRepository.save(medicModel);
            return mapMedicModelToCreateMedicDTO(medicModel);
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }


    public List<MedicDTO> findAll() {

        List<MedicModel> medicsList = medicRepository.findAll();

        return medicsList.stream()
                .map(this::mapMedicModelToCreateMedicDTO)
                .collect(Collectors.toList());

    }

    public MedicDTO getMedicById(Long userId) {
        Optional<MedicModel> optionalMedic = medicRepository.findById(userId);

        if (optionalMedic.isPresent()) {
            MedicModel foundMedic = optionalMedic.get();
            return mapMedicModelToCreateMedicDTO(foundMedic);
        } else {
            throw new MedicException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }

    public MedicDTO updateMedic(Long userId, MedicDTO medicDTO) {
        Optional<MedicModel> optionalMedic = medicRepository.findById(userId);

        if (optionalMedic.isPresent()) {
            MedicModel foundMedic = optionalMedic.get();
            foundMedic.setUserName(medicDTO.getUserDTO().getUserName());
            foundMedic.setUserSurname(medicDTO.getUserDTO().getUserSurname());
            foundMedic.setUserPassword(medicDTO.getUserDTO().getUserPassword());
            foundMedic.setMedicalLicense(medicDTO.getMedicalLicense());
            medicRepository.save(foundMedic);
            return mapMedicModelToCreateMedicDTO(foundMedic);
        } else {
            throw new MedicException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }

    public MedicDTO deleteMedic(Long userId) {
        Optional<MedicModel> foundMedic = medicRepository.findById(userId);

        if (foundMedic.isPresent()) {
            medicRepository.deleteById(userId);
            return null;
        } else {
            throw new MedicException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }
    
}
