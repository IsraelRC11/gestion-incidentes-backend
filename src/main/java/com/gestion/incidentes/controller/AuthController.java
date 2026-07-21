package com.gestion.incidentes.controller;

import com.gestion.incidentes.dto.LoginRequest;
import com.gestion.incidentes.model.Usuario;
import com.gestion.incidentes.repository.UsuarioRepository;
import com.gestion.incidentes.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        // Verifica usuario y contraseña (soporta texto plano o hash BCrypt)
        if (usuario != null && (request.getPassword().equals(usuario.getPassword()) || 
            passwordEncoder.matches(request.getPassword(), usuario.getPassword()))) {
            
            String token = jwtUtil.generarToken(usuario.getEmail());
            return ResponseEntity.ok(Map.of("token", token, "email", usuario.getEmail()));
        }

        return ResponseEntity.status(401).body(Map.of("message", "Credenciales inválidas"));
    }
}