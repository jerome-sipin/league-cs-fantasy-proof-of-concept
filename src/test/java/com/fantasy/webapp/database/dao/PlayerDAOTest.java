package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlayerDAOTest {

    // TODO - @Autowired annotation is invalid???
    // TODO - why is the DAO blanked out??????
    private PlayerDAO playerDAO;

    @Test
    public void findPlayerContainingIgnoreCaseTest(){
        // given
        String givenPlayer = "jks";

        // when
        List<Player> actual = playerDAO.findByPlayerNameContainingIgnoreCase(givenPlayer);

        // then
        System.out.println(actual);
    }


}
