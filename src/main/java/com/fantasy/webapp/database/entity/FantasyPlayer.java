package com.fantasy.webapp.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="players_fantasy")
public class FantasyPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fantasy_team_id")
    private Integer fantasyTeamId;

    @Column(name = "player_id")
    private Integer playerId;

}
