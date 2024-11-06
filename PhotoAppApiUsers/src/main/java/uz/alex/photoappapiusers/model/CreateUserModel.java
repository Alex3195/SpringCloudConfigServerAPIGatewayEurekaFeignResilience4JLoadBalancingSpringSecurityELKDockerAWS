package uz.alex.photoappapiusers.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CreateUserModel {
    @NotNull(message = "First name is required.")
    @Size(min = 2, message = "First name must be at least 2 characters.")
    private String firstName;
    @NotNull(message = "Last name is required.")
    @Size(min = 2, message = "Last name must be at least 2 characters.")
    private String lastName;
    @Email(message = "Invalid email format")
    @NotNull(message = "Email is required.")
    private String email;
    @NotNull(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be at least 6 characters and at most 20.")
    private String password;
}
