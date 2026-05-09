package com.devonb.api.service;

import com.devonb.api.model.Player;
import com.devonb.api.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repo;

    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    public List<Player> getAll() {
        return repo.findAll();
    }

    public Player getById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Player create(Player player){
        return repo.save(player);
    }

    public Player update(Long id, Player updated){
        Player p = repo.findById(id).orElse(null);
        if (p == null) return null;

        p.setUsername(updated.getUsername());
        p.setScore(updated.getScore());
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Player incrementScore(Long id, int amount){
        Player player = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
        
        player.setScore(player.getScore() + amount);
        return repo.save(player);
    }

    public Player updatePlayer(Long id, Player updated){
        Player player = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
        player.setUsername(updated.getUsername());
        //player.setScore(updated.getScore());
        return repo.save(player);
    }
}