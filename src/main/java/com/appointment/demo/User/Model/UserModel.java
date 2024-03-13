package com.appointment.demo.User.Model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@DiscriminatorValue("UserModel")
public class UserModel {
    private String _userName;
    private String _userSurname;
    private String _userPassword;

    @Id
    private long _userId;

    public UserModel() {
        this._userId = 0L;
    }
    public UserModel(String userName, String userSurname, String userPassword, Long userId) {
        this._userName = userName;
        this._userSurname = userSurname;
        this._userPassword = userPassword;
        this._userId = userId;
    }

    public String getUserName() {
        return _userName;
    }

    public String getUserSurname() {
        return _userSurname;
    }

    public String getUserPassword() {
        return _userPassword;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserName(String userName) {
        this._userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this._userSurname = userSurname;
    }
    public void setUserPassword(String userPassword) {
        this._userPassword = userPassword;
    }

    public void set_userId(Long userId) {
        this._userId = userId;
    }


}
