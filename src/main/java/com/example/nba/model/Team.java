package com.example.nba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String teamId;

    @Column(name = "ABBREVIATION")
    private String abbreviation;

    @Column(name = "ACTIVE")
    @Type(type="yes_no")
    private Boolean active;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "CONFERENCE")
    private String conference;

    @Column(name = "DIVISION")
    private String division;

    @Column(name = "SITE_NAME")
    private String siteName;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "FULL_NAME")
    private String fullName;

}
