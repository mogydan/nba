package com.example.nba.service;

import com.example.nba.exception.ResourceNotFoundException;
import com.example.nba.model.Team;
import com.example.nba.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team createTeam(Team newTeam) {
        return teamRepository.save(newTeam);
    }

    @Override
    public void addTeams(List<Team> teams) {
        teamRepository.saveAll(teams);
    }

    @Override
    public Team getTeam(String teamId) {
        return teamRepository.findByTeamId(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id = {0} was not found", teamId));
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public void updateTeam(String teamId, Team updates) {
        Team team = getTeam(teamId)
                .setTeamId(updates.getTeamId())
                .setAbbreviation(updates.getAbbreviation())
                .setActive(updates.getActive())
                .setFirstName(updates.getFirstName())
                .setLastName(updates.getLastName())
                .setConference(updates.getConference())
                .setDivision(updates.getDivision())
                .setSiteName(updates.getSiteName())
                .setCity(updates.getCity())
                .setState(updates.getState())
                .setFullName(updates.getFullName());

        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(String teamId) {
        teamRepository.delete(getTeam(teamId));
    }
}
