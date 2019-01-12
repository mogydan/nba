package com.example.nba.controller;

import com.example.nba.model.Team;
import com.example.nba.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PostMapping("/addAll")
    public void addTeams(@RequestBody List<Team> teams) {
        teamService.addTeams(teams);
    }

    @GetMapping("/{teamId}")
    public Team getTeam(@PathVariable String teamId) {
        return teamService.getTeam(teamId);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PutMapping("/{teamId}")
    public void updateTeam(@PathVariable String teamId, @RequestBody Team updates) {
        teamService.updateTeam(teamId, updates);
    }

    @DeleteMapping("/{teamId}")
    public void deleteTeam(@PathVariable String teamId) {
        teamService.deleteTeam(teamId);
    }

}
