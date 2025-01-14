package com.fantasy.webapp.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="players_fantasy")
public class FantasyPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fantasy_team_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private FantasyTeam fantasyTeam;

    @Column(name = "fantasy_team_id", insertable = false, updatable = false)
    private Integer fantasyTeamId;

    @Column(name = "player_id", insertable = false, updatable = false)
    private Integer playerId;

}
