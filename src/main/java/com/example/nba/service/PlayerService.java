package com.example.nba.service;

import com.example.nba.model.Player;

import java.util.List;

public interface PlayerService {

    Player createPlayer(Player newPlayer);

    Player getPlayer(long playerId);

    List<Player> getAllPlayers();

    void updatePlayer(long playerId, Player updates);

    void deletePlayer(long playerId);
}
