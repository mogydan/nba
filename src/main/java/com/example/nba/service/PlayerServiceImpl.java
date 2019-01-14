package com.example.nba.service;

import com.example.nba.exception.ResourceNotFoundException;
import com.example.nba.model.Player;
import com.example.nba.model.Team;
import com.example.nba.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player createPlayer(Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    @Override
    public Player getPlayer(long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player with id = {0} was not found", playerId));
    }

    @Override
    public List<Player> getPlayersByTeam(Team team) {
        return playerRepository.findAllByTeam(team);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAllByOrderByTeamAsc();
    }

    @Override
    public void setTeam(long playerId, String teamId) {
        playerRepository.setTeam(getPlayer(playerId).getId(), teamId);
    }

    @Override
    public void deletePlayer(long playerId) {
        playerRepository.delete(getPlayer(playerId));
    }
}
