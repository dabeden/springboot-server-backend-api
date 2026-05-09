//PlayerController.java
package com.devonb.api.controller;

import com.devonb.api.model.Player;
import com.devonb.api.service.PlayerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Player getById(@PathVariable Long id){
        return service.getById(id);
    }

   // @PostMapping
   // public Player update(@PathVariable Long id, @RequestBody Player player) {
    //    return service.update(id,player);
    //}

    @PostMapping
    public ResponseEntity<Player> create(@RequestBody Player player) {
        Player saved = service.create(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
