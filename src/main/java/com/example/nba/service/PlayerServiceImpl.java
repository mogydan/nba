package com.example.nba.service;

import com.example.nba.exception.ResourceNotFoundException;
import com.example.nba.model.Player;
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
    public void addPlayers(List<Player> players) {
        playerRepository.saveAll(players);
    }

    @Override
    public Player getPlayer(long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Pl with id = {0} was not found", playerId));
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void updatePlayer(long playerId, Player updates) {
        Player player = getPlayer(playerId)
                .setFullName(updates.getFullName())
                .setPhone(updates.getPhone())
                .setHeight(updates.getHeight());
        playerRepository.save(player);
    }

    @Override
    public void deletePlayer(long playerId) {
        playerRepository.delete(getPlayer(playerId));
    }
}
