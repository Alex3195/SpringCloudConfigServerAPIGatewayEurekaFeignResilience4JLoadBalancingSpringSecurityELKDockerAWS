package uz.alex.photoappapiusers.service.impl;

import ch.qos.logback.core.testUtil.RandomUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.alex.photoappapiusers.dto.AuthenticationDetailsDto;
import uz.alex.photoappapiusers.dto.UserDto;
import uz.alex.photoappapiusers.entitiy.UserEntity;
import uz.alex.photoappapiusers.exception.UserNotFoundException;
import uz.alex.photoappapiusers.model.CreateUserModel;
import uz.alex.photoappapiusers.repository.UsersRepository;
import uz.alex.photoappapiusers.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(CreateUserModel createUserModel) {
        UserDto dto = new UserDto(createUserModel.getFirstName(), createUserModel.getLastName(), createUserModel.getEmail());
        UserEntity e = modelMapper.map(dto, UserEntity.class);
        e.setPassword(bCryptPasswordEncoder.encode(createUserModel.getPassword()));
        usersRepository.save(e);
        return modelMapper.map(e, UserDto.class);
    }

    @Override
    public UserDto getById(Long id) {
        Optional<UserEntity> user = usersRepository.findById(id);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity e = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new AuthenticationDetailsDto(e.getId(), e.getEmail(), e.getPassword());
    }
}
