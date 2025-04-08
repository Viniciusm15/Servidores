package com.vinicius.serversapi.auth.service.auth.contract;

import com.vinicius.serversapi.auth.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String generateToken(User user);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
}
