package uz.alex.photoappapiusers.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {
    private final Environment env;

    public UserController(Environment env) {
        this.env = env;
    }

    @GetMapping("/status")
    public String checkStatus() {
        return "Working on" + env.getProperty("local.server.port");
    }
}
