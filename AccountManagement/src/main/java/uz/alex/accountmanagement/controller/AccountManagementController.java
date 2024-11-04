package uz.alex.accountmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("account-management")
@RestController
public class AccountManagementController {
    @GetMapping("check-status")
    public String checkStatus() {
        return "OK";
    }
}
