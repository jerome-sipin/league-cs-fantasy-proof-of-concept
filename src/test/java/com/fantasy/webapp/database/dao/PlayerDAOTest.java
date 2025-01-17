package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerDAOTest {

    @Autowired
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
