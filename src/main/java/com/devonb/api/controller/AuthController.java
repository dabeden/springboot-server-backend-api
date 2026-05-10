package com.devonb.api.controller;

import com.devonb.api.dto.AuthRequest;
import com.devonb.api.model.Player;
import com.devonb.api.repository.PlayerRepository;
import com.devonb.api.service.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final PlayerRepository playerRepository;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder =
        new BCryptPasswordEncoder();

    public AuthController(PlayerRepository playerRepository, JwtService jwtService) {
        this.playerRepository = playerRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        Player player = new Player();
        player.setUsername(request.getUsername());
        player.setPassword(hashedPassword);
        player.setScore(0);

        playerRepository.save(player);

        return "Player registered";


    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest request) {
        Player player = playerRepository
            .findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
    

    boolean validPassword = passwordEncoder.matches(request.getPassword(), player.getPassword());

    if (!validPassword) {
        throw new RuntimeException("Invalid password");   
    }

    String token = jwtService.generateToken(player.getUsername());

    return Map.of("token", token);
    
    }

}
