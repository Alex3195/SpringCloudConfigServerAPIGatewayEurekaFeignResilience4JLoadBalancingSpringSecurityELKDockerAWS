package uz.alex.photoappapiusers.controller;

import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.photoappapiusers.dto.AccessTokenResDto;
import uz.alex.photoappapiusers.dto.UserDto;
import uz.alex.photoappapiusers.model.CreateUserModel;
import uz.alex.photoappapiusers.model.LoginRequestModel;
import uz.alex.photoappapiusers.service.AuthenticationService;
import uz.alex.photoappapiusers.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
    private final Environment env;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(Environment env, UserService userService, AuthenticationService authenticationService) {
        this.env = env;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/status")
    public String checkStatus() {
        return "Working on" + env.getProperty("local.server.port");
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserModel createUserModel) {

        return ResponseEntity.status(HttpStatus.SC_CREATED).body(userService.createUser(createUserModel));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResDto> login(
            @RequestBody @Valid LoginRequestModel signInDto) {

        String token = authenticationService.signIn(signInDto);

        AccessTokenResDto authResponseDto = new AccessTokenResDto();
        authResponseDto.setAccessToken(token);

        return ResponseEntity.ok(authResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.SC_OK).body(userService.getById(id));
    }
}
