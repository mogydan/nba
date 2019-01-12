package com.example.nba.controller;

import com.example.nba.model.Player;
import com.example.nba.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @PostMapping("/addAll")
    public void addPlayers(@RequestBody List<Player> players) {
        playerService.addPlayers(players);
    }

    @GetMapping("/{playerId}")
    public Player getPlayer(@PathVariable long playerId) {
        return playerService.getPlayer(playerId);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PutMapping("/{playerId}")
    public void updatePlayer(@PathVariable long playerId, @RequestBody Player updates) {
        playerService.updatePlayer(playerId, updates);
    }

    @DeleteMapping("/{playerId}")
    public void deletePlayer(@PathVariable long playerId) {
        playerService.deletePlayer(playerId);
    }
}
