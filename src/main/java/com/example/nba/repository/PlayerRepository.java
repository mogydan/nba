package com.example.nba.repository;

import com.example.nba.model.Player;
import com.example.nba.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findById(long playerId);

    List<Player> findAllByOrderByTeamAsc();

    List<Player> findAllByTeam(Team team);

    @Modifying
    @Transactional
    @Query(value = "update Player p set p.team_id = ?2 where p.id = ?1", nativeQuery = true)
    void setTeam(long playerId, String teamId);
}
