package fr.cda.botteprintemps.controller_rest;

import fr.cda.botteprintemps.dto.redditish.UserUpdateDTO;
import fr.cda.botteprintemps.entity.User;
import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.service.redditish.UserRedditishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
// Toutes les requêtes HTTP commençant par "/api/user" sont intercéptées ICI
@RequestMapping(value = "/api/user", name = "app_user_")
public class UserRedditishRestController {

    private UserRedditishService userRedditishService;

    // Requête HTTP de type GET
    @GetMapping(name = "list")
    public List<UserRedditish> list() {
        return userRedditishService.list();
    }

    @GetMapping(value = "/{slug}", name = "show")
    public UserRedditish show(@PathVariable String slug) {
        return userRedditishService.findOneBySlug(slug);
    }

    @PutMapping(value = "/{id}", name = "update")
    public UserRedditish update(@PathVariable String id, @RequestBody UserUpdateDTO dto) {
        System.out.println(id);
        System.out.println(dto);
        // http://localhost:8080/api/user/bd7c0af3-5cf5-4c19-9930-3629b5fd0b7d
        return new UserRedditish();
    }

}