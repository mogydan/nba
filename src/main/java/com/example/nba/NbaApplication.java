package com.example.nba;

import com.example.nba.model.Team;
import com.example.nba.service.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class NbaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbaApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, TeamService teamService) {
        return args -> {
            ResponseEntity<List<Team>> response = restTemplate.exchange(
                    "https://erikberg.com/mlb/teams.json",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Team>>() {
                    }
            );
            List<Team> teams = response.getBody();

            if ((teams != null) && (!teams.isEmpty())) {
                teamService.addTeams(teams);
                teamService.createTeam(new Team().setTeamId("free-transfer"));
            }
        };
    }

}

