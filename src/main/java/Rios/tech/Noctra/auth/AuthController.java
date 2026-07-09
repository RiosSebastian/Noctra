package Rios.tech.Noctra.auth;

import Rios.tech.Noctra.auth.dto.AuthenticationRequest;
import Rios.tech.Noctra.auth.dto.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Registro e inicio de sesión")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @Operation(summary = "Registrar usuario")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(

            @RequestBody RegisterRequest request){

        return ResponseEntity.ok(

                authService.register(request)

        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(

            @RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(

                authService.authenticate(request)

        );
    }

}