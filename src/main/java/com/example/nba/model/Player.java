package com.example.nba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "team")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    @JsonProperty("id")
    private long id;

    @Column(name = "FULL_NAME")
    @JsonProperty("full_name")
    private String fullName;

    @Column(name = "PHONE")
    @JsonProperty("phone")
    private String phone;

    @Column(name = "HEIGHT")
    @JsonProperty("height")
    private int height;

    @JsonProperty("team_id")
    @Transient
    private String teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false, foreignKey = @ForeignKey(name = "TEAM_FK"))
    @JsonIgnore
    private Team team;

    public String getTeamId() {
        return team.getTeamId();
    }
}
