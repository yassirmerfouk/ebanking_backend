package com.ym.web;

import com.ym.dto.AuthDTO;
import com.ym.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody AuthDTO authDTO){
        return authService.login(authDTO);
    }
}
