package com.example.nba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    @JsonProperty("id")
    private long id;

    @Column(name = "FULL_NAME")
    @NotEmpty
    @JsonProperty("full_name")
    private String fullName;

    @Column(name = "PHONE")
    @NotEmpty
    @JsonProperty("phone")
    private String phone;

    @Column(name = "HEIGHT")
    @NotEmpty
    @JsonProperty("height")
    private int height;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", nullable = false, foreignKey = @ForeignKey(name = "TEAM_FK"))
    private Team team;
}
