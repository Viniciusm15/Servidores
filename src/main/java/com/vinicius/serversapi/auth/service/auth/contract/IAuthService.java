package com.vinicius.serversapi.auth.service.auth.contract;

import com.vinicius.serversapi.auth.dto.auth.*;
import com.vinicius.serversapi.auth.model.User;

public interface IAuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(LoginRequest request);

    AuthResponse refreshToken(User user);
}
