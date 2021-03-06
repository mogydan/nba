package com.example.nba.repository;

import com.example.nba.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByTeamId(String teamId);
}
