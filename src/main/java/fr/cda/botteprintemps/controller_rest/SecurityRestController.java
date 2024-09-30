package fr.cda.botteprintemps.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.botteprintemps.custom_response.JwtResponse;
import fr.cda.botteprintemps.dto.redditish.UserLoginDTO;
import fr.cda.botteprintemps.dto.redditish.UserRegisterDTO;
import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.json_views.JsonViews;
import fr.cda.botteprintemps.security.JwtAuthenticatorService;
import fr.cda.botteprintemps.service.redditish.UserRedditishService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SecurityRestController {

    private final UserRedditishService userService;
    private final JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/api/register")
    @JsonView(JsonViews.UserRedditishShow.class)
    public UserRedditish register(@Valid @RequestBody UserRegisterDTO user) {
        return userService.create(user);
    }

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginDTO user) {
        return jwtAuthenticatorService.authenticate(user);
    }

}