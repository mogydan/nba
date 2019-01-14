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

    @GetMapping("/byTeam/{teamId}")
    public List<Player> getPlayersByTeam(@PathVariable String teamId) {
        return playerService.getPlayersByTeam(teamService.getTeam(teamId));
    }

    @PatchMapping
    public void setTeam(@RequestParam long playerId, @RequestParam String teamId) {
        playerService.setTeam(playerId, teamService.getTeam(teamId).getTeamId());
    }

    @DeleteMapping("/{playerId}")
    public void deletePlayer(@PathVariable long playerId) {
        playerService.deletePlayer(playerId);
    }
}
