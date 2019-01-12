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
import org.springframework.web.util.NestedServletException;

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
public class TeamControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/createTeamExpected.xml", table = "TEAM")
    public void createTeam() {
        mvc.perform(
                post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(Utils.resourceAsString("teamControllerIntegrationTest/createTeamRequestBody.json")))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/addTeamsExpected.xml", table = "TEAM")
    public void addTeams() {
        mvc.perform(
                post("/teams/addAll")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(Utils.resourceAsString("teamControllerIntegrationTest/addTeamsRequestBody.json")))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/initialDatabase.xml", table = "TEAM")
    public void getTeam() {
        mvc.perform(get("/teams/team1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content()
                        .json(Utils.resourceAsString("teamControllerIntegrationTest/getTeamExpected.json")));
    }

    @Test(expected = NestedServletException.class)
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/initialDatabase.xml", table = "TEAM")
    public void getTeamNotFound() {
        mvc.perform(get("/teams/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Team with id = 1 was not found"));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/initialDatabase.xml", table = "TEAM")
    public void getAllTeams() {
        mvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content()
                        .json(Utils.resourceAsString("teamControllerIntegrationTest/getAllExpected.json")));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/updateTeamExpected.xml", table = "TEAM")
    public void updateTeam() {
        mvc.perform(
                put("/teams/team1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(Utils.resourceAsString("teamControllerIntegrationTest/updateTeamRequestBody.json"))
        )
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/deleteTeamExpected.xml", table = "TEAM")
    public void deleteTeam() {
        mvc.perform(delete("/teams/team1"))
                .andExpect(status().isOk());
    }

    @Test(expected = NestedServletException.class)
    @SneakyThrows
    @DatabaseSetup("/teamControllerIntegrationTest/initialDatabase.xml")
    @ExpectedDatabase(value = "/teamControllerIntegrationTest/initialDatabase.xml", table = "TEAM")
    public void deleteTeamNotFound() {
        mvc.perform(delete("/teams/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Team with id = 1 was not found"));
    }
}