package uz.alex.photoappapiusers.model;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
