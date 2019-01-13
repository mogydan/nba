package com.example.nba.controller;

import com.example.nba.model.Player;
import com.example.nba.model.Team;
import com.example.nba.service.PlayerService;
import com.example.nba.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @PostMapping
    public Player createPlayer(
            @RequestParam String fullName,
            @RequestParam int height,
            @RequestParam String phone,
            @RequestParam String teamId
    ) {
        Team team = teamService.getTeam(teamId);
        return playerService.createPlayer(
                new Player()
                        .setFullName(fullName)
                        .setHeight(height)
                        .setPhone(phone)
                        .setTeam(team)
        );
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
