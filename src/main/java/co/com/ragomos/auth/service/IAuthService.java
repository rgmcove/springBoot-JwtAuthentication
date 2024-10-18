package co.com.ragomos.auth.service;

import co.com.ragomos.auth.models.AuthResponse;
import co.com.ragomos.auth.models.LoginRequest;
import co.com.ragomos.auth.models.RegisterRequest;

public interface IAuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);
}
