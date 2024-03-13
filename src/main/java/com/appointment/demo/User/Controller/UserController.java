package com.appointment.demo.User.Controller;

import com.appointment.demo.User.Service.UserService;
import com.appointment.demo.User.DTO.UserDTO;
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
@RequestMapping("/user")
@Tag(name = "User API")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Creates a new User", description = "Returns the User as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "The user couldnt be created")
    })
    @PostMapping("/Post")
    public ResponseEntity<UserDTO> postController(@RequestBody UserDTO userDto) {

        try {
            UserDTO createdUser = userService.createUser(userDto);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Return all Users", description = "Returns all the Users registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Users couldnt be found")
    })
    @GetMapping("/Users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        try {
            List<UserDTO> UserList = userService.findAll();
            return new ResponseEntity<>(UserList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Return the user with the an specific id", description = "Returns a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Users couldnt be found")
    })
    @GetMapping("/Users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId){

        try{

            UserDTO foundUser = userService.getUserById(userId);
            return new ResponseEntity<>(foundUser, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Updates an User", description = "Updates the User atributes with the specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Users couldnt be found")
    })
    @PutMapping("/Update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId,
                                              @RequestBody UserDTO userDTO){

        try{

            UserDTO foundUser = userService.updateUser(userId, userDTO);
            return new ResponseEntity<>(foundUser, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deletes an User", description = "Deletes the user with the specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent"),
            @ApiResponse(responseCode = "404", description = "Users couldnt be found")
    })
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "id") Long userId){

        try{

            UserDTO foundUser = userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
