package com.fantasy.webapp.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "team_actual")
    private Integer teamActual;

    @Column(name = "points")
    private Integer points;

    @Column(name = "cost")
    private Integer cost;

}
