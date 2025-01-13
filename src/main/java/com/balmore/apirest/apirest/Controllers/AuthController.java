package com.balmore.apirest.apirest.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balmore.apirest.apirest.services.UserDetailsServiceImpl;
import com.balmore.apirest.apirest.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
      @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");
    
            // Autenticación usando AuthenticationManager
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    
            // Generar token JWT
            String token = jwtUtil.generateToken(username);
    
            // Respuesta con el token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
    
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales inválidas"); // Error si la contraseña no coincide
        } catch (AuthenticationException e) {
            throw new RuntimeException("Error de autenticación"); // Error general de autenticación
        }
    }

    @GetMapping("/validate/token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            // Verifica que el encabezado Authorization contenga "Bearer <token>"
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("El token no está presente o está malformado");
            }

            // Extraer el token del encabezado
            String token = authHeader.substring(7);

            // Extraer el username del token
            String username = jwtUtil.extractUsername(token);
            if (username == null) {
                return ResponseEntity.badRequest().body("El token es inválido o no contiene un usuario válido");
            }

            // Verificar que el usuario exista
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (!jwtUtil.validateToken(token, userDetails.getUsername())) {
                return ResponseEntity.status(401).body("El token no es válido o ha expirado");
            }

            // Si todo es válido
            return ResponseEntity.ok("El token es válido y pertenece al usuario: " + username);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Error al validar el token: " + e.getMessage());
        }
    }
}
