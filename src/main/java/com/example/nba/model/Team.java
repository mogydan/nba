package com.example.nba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    @Id
    @Column(name = "TEAM_ID", updatable = false, nullable = false)
    @JsonProperty("team_id")
    private String teamId;

    @Column(name = "ABBREVIATION")
    @JsonProperty("abbreviation")
    private String abbreviation;

    @Column(name = "ACTIVE")
    @Type(type="yes_no")
    @JsonProperty("active")
    private Boolean active;

    @Column(name = "FIRST_NAME")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "LAST_NAME")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "CONFERENCE")
    @JsonProperty("conference")
    private String conference;

    @Column(name = "DIVISION")
    @JsonProperty("division")
    private String division;

    @Column(name = "SITE_NAME")
    @JsonProperty("site_name")
    private String siteName;

    @Column(name = "CITY")
    @JsonProperty("city")
    private String city;

    @Column(name = "STATE")
    @JsonProperty("state")
    private String state;

    @Column(name = "FULL_NAME")
    @JsonProperty("full_name")
    private String fullName;

}
