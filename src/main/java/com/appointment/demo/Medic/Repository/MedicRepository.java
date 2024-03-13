package com.appointment.demo.Medic.Repository;

import com.appointment.demo.Medic.Model.MedicModel;
import com.appointment.demo.User.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicRepository extends JpaRepository<MedicModel, Long>{

}
