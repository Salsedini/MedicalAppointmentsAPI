package com.appointment.demo.Patient.Repository;

import com.appointment.demo.Medic.Model.MedicModel;
import com.appointment.demo.Patient.Model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long>{

}
