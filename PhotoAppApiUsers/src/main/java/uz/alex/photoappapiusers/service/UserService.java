package uz.alex.photoappapiusers.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.alex.photoappapiusers.dto.UserDto;
import uz.alex.photoappapiusers.model.CreateUserModel;

public interface UserService extends UserDetailsService {
    UserDto createUser(CreateUserModel createUserModel);

    UserDto getById(Long id);
}
