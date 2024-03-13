package com.appointment.demo.User.Service;

import com.appointment.demo.User.Exception.UserException;
import com.appointment.demo.User.Model.UserModel;
import com.appointment.demo.User.Repository.UserRepository;
import com.appointment.demo.User.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private UserDTO mapUserModelToCreateUserDTO(UserModel userModel) {
        return new UserDTO(
                userModel.getUserName(),
                userModel.getUserSurname(),
                userModel.getUserPassword(),
                userModel.getUserId()
        );
    }

    private UserModel mapCreateUserDTOToUserModel(UserDTO userDTO) {
        return new UserModel(
                userDTO.getUserName(),
                userDTO.getUserSurname(),
                userDTO.getUserPassword(),
                userDTO.getUserId()
        );
    }

    public UserDTO createUser(UserDTO userDTO){

        try {
            UserModel userModel = mapCreateUserDTOToUserModel(userDTO);
            userRepository.save(userModel);
            return mapUserModelToCreateUserDTO(userModel);
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }

    public List<UserDTO> findAll() {

        List<UserModel> usersList = userRepository.findAll();

        return usersList.stream()
                .map(this::mapUserModelToCreateUserDTO)
                .collect(Collectors.toList());

    }

    public UserDTO getUserById(Long userId) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserModel foundUser = optionalUser.get();
            return mapUserModelToCreateUserDTO(foundUser);
        } else {
            throw new UserException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserModel foundUser = optionalUser.get();
            foundUser.setUserName(userDTO.getUserName());
            foundUser.setUserSurname(userDTO.getUserSurname());
            foundUser.setUserPassword(userDTO.getUserPassword());
            userRepository.save(foundUser);
            return mapUserModelToCreateUserDTO(foundUser);
        } else {
            throw new UserException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }

    public UserDTO deleteUser(Long userId) {
        Optional<UserModel> foundUser = userRepository.findById(userId);

        if (foundUser.isPresent()) {
            userRepository.deleteById(userId);
            return null;
        } else {
            throw new UserException("El usuario con el ID " + userId + " no fue encontrado.");
        }

    }

}
