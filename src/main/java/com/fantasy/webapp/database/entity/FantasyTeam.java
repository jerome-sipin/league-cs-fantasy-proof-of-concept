package com.fantasy.webapp.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="teams_fantasy")
public class FantasyTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "fantasyTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<FantasyPlayer> players; // players that are in this fantasy team

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "points")
    private Integer points;

    @Column(name = "budget")
    private Integer budget;
}
