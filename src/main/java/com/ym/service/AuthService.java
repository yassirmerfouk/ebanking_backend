package com.ym.service;

import com.ym.dto.AuthDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public Map<String, String> login(AuthDTO authDTO){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
                );
        return Map.of("accessToken", jwtService.generateAccessToken((UserDetails) authentication.getPrincipal()));
    }
}
