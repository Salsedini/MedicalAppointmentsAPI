package com.appointment.demo.Medic.Controller;

import com.appointment.demo.Medic.Service.MedicService;
import com.appointment.demo.Medic.Service.MedicService;
import com.appointment.demo.Medic.DTO.MedicDTO;
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
@RequestMapping("/medic")
@Tag(name = "Medic API")
public class MedicController {

    private final MedicService medicService;

    @Autowired
    public MedicController(MedicService medicService) {
        this.medicService = medicService;
    }

    @Operation(summary = "Creates a new Medic", description = "Returns the Medic as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "The medic couldnt be created")
    })
    @PostMapping("/Post")
    public ResponseEntity<MedicDTO> postController(@RequestBody MedicDTO medicDTO) {

        try {
            MedicDTO createdMedic = medicService.createMedic(medicDTO);
            return new ResponseEntity<>(createdMedic, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Return all Medics", description = "Returns all the Medics registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Medics couldnt be found")
    })
    @GetMapping("/Medics")
    public ResponseEntity<List<MedicDTO>> getAllMedics() {

        try {
            List<MedicDTO> MedicList = medicService.findAll();
            return new ResponseEntity<>(MedicList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Return the medic with the an specific id", description = "Returns a medic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Medics couldnt be found")
    })
    @GetMapping("/Medics/{id}")
    public ResponseEntity<MedicDTO> getMedicById(@PathVariable(value = "id") Long userId){

        try{

            MedicDTO foundMedic = medicService.getMedicById(userId);
            return new ResponseEntity<>(foundMedic, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Updates an Medic", description = "Updates the Medic atributes with the specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Medics couldnt be found")
    })
    @PutMapping("/Update/{id}")
    public ResponseEntity<MedicDTO> updateMedic(@PathVariable(value = "id") Long userId,
                                              @RequestBody MedicDTO medicDTO){

        try{

            MedicDTO foundMedic = medicService.updateMedic(userId, medicDTO);
            return new ResponseEntity<>(foundMedic, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deletes an Medic", description = "Deletes the medic with the specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Medics couldnt be found")
    })
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<HttpStatus> deleteMedic(@PathVariable(value = "id") Long userId){

        try{

            MedicDTO foundMedic = medicService.deleteMedic(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
