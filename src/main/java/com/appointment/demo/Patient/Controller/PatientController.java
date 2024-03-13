package com.appointment.demo.Patient.Controller;

import com.appointment.demo.Patient.Service.PatientService;
import com.appointment.demo.Patient.DTO.PatientDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Tag(name = "Patient API")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Creates a new Patient", description = "Returns the Patient as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "The patient couldnt be created")
    })
    @PostMapping("/Post")
    public ResponseEntity<PatientDTO> postController(@RequestBody PatientDTO patientDto) {

        try {
            PatientDTO createdPatient = patientService.createPatient(patientDto);
            return new ResponseEntity<>(createdPatient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Return all Patients", description = "Returns all the Patients registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Patients couldnt be found")
    })
    @GetMapping("/Patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {

        try {
            List<PatientDTO> PatientList = patientService.findAll();
            return new ResponseEntity<>(PatientList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Return the patient with the an specific id", description = "Returns a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Patients couldnt be found")
    })
    @GetMapping("/Patients/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable(value = "id") Long userId){

        try{

            PatientDTO foundPatient = patientService.getPatientById(userId);
            return new ResponseEntity<>(foundPatient, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Updates an Patient", description = "Updates the Patient atributes with the specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Patients couldnt be found")
    })
    @PutMapping("/Update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable(value = "id") Long userId,
                                              @RequestBody PatientDTO patientDTO){

        try{

            PatientDTO foundPatient = patientService.updatePatient(userId, patientDTO);
            return new ResponseEntity<>(foundPatient, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deletes an Patient", description = "Deletes the patient with the specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Patients couldnt be found")
    })
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable(value = "id") Long userId){

        try{

            PatientDTO foundPatient = patientService.deletePatient(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
