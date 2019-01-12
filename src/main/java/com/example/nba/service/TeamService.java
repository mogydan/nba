package com.example.nba.service;

import com.example.nba.model.Team;

import java.util.List;

public interface TeamService {

    Team createTeam(Team newTeam);

    void addTeams(List<Team> teams);

    Team getTeam(String teamId);

    List<Team> getAllTeams();

    void updateTeam(String teamId, Team updates);

    void deleteTeam(String teamId);
}
