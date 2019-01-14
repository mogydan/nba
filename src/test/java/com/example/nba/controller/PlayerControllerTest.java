package com.example.nba.controller;

import com.example.nba.NbaApplication;
import com.example.nba.utils.Utils;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = NbaApplication.class
)
@TestExecutionListeners(
        value = DbUnitTestExecutionListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@AutoConfigureMockMvc
@DatabaseTearDown("/cleanAllTables.xml")
public class PlayerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/createPlayerExpected.xml", table = "PLAYER")
    public void createPlayer() {
        mvc.perform(
                post("/players")
                        .param("fullName", "Player4")
                        .param("height", "204")
                        .param("phone", "444")
                        .param("teamId", "team2"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void getPlayer() {
        mvc.perform(get("/players/111"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content()
                        .json(Utils.resourceAsString("playerControllerIntegrationTest/getPlayerExpected.json")));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void getPlayerNotFound() {
        mvc.perform(get("/players/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Player with id = 999 was not found"));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void getAllPlayers() {
        mvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content()
                        .json(Utils.resourceAsString("playerControllerIntegrationTest/getAllPlayersExpected.json")));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void getPlayersByTeam() {
        mvc.perform(get("/players/byTeam/team1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content()
                        .json(Utils.resourceAsString("playerControllerIntegrationTest/getPlayersByTeamExpected.json")));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void getPlayersByTeamNotFoundTeam() {
        mvc.perform(get("/players/byTeam/team255"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Team with id = team255 was not found"));

    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/setTeamExpected.xml", table = "PLAYER")
    public void setTeam() {
        mvc.perform(
                patch("/players")
                        .param("playerId", "111")
                        .param("teamId", "team2"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void setTeamPlayerNotFound() {
        mvc.perform(
                patch("/players")
                        .param("playerId", "999")
                        .param("teamId", "team2"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Player with id = 999 was not found"));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void setTeamButTeamNotFound() {
        mvc.perform(
                patch("/players")
                        .param("playerId", "111")
                        .param("teamId", "team255"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Team with id = team255 was not found"));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/deletePlayerExpected.xml", table = "PLAYER")
    public void deletePlayer() {
        mvc.perform(delete("/players/333"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/playerControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/playerControllerIntegrationTest/initialDatabase.xml", table = "PLAYER")
    public void deletePlayerNotFound() {
        mvc.perform(delete("/players/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Player with id = 999 was not found"));
    }
}