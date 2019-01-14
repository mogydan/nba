package com.example.nba.service;

import com.example.nba.model.Player;
import com.example.nba.model.Team;

import java.util.List;

public interface PlayerService {

    Player createPlayer(Player newPlayer);

    Player getPlayer(long playerId);

    List<Player> getPlayersByTeam(Team team);

    List<Player> getAllPlayers();

    void setTeam(long playerId, String teamId);

    void deletePlayer(long playerId);
}
