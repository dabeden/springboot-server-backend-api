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
    
}
