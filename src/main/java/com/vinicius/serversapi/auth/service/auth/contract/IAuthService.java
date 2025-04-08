package com.vinicius.serversapi.auth.service.auth.contract;

import com.vinicius.serversapi.auth.dto.auth.*;

public interface IAuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(LoginRequest request);
}
