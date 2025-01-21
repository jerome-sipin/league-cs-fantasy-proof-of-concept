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
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<FantasyPlayer> teams; // fantasy teams that this player is in

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "team_actual_id")
    private Integer teamActualId;

    @Column(name = "points")
    private Integer points;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "img_url")
    private String imageUrl;

}
