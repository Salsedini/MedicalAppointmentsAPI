package com.appointment.demo.User.DTO;

public class UserDTO {

    private String userName;
    private String userSurname;
    private String userPassword;
    private Long userId;

    public UserDTO(String userName, String userSurname, String userPassword, Long userId) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPassword = userPassword;
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserSurname() {
        return this.userSurname;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public Long getUserId() {
        return this.userId;
    }

}
