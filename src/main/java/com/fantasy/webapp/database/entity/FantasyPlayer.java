package com.fantasy.webapp.database.entity;

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
    @ToString.Exclude
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fantasy_team_id")
    @ToString.Exclude
    private FantasyTeam fantasyTeam;

    @Column(name = "fantasy_team_id")
    private Integer fantasyTeamId;

    @Column(name = "player_id")
    private Integer playerId;

}
