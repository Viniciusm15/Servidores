package com.vinicius.serversapi.auth.service.auth.contract;

import com.vinicius.serversapi.auth.model.User;
import java.util.Map;

public interface IJwtService {
    String generateToken(User user);
    String generateToken(User user, Map<String, Object> extraClaims);
    boolean isTokenValid(String token, User user);
    String extractUsername(String token);
}

