package com.pj.bedazzled.data;

import com.pj.bedazzled.data.model.Player;
import com.pj.bedazzled.springboot.controller.BeDazzledConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BeDazzledConfiguration.class})
public class BeDazzledDataManagerTest {

    @Autowired
    private BeDazzledDataManager dataManager;

    @Test
    public void testGetAppearances() throws IOException {
        Collection<Player> players = dataManager.getPlayers();
        assertNotNull(players);
        assertFalse(players.isEmpty());
    }

    @Test
    public void testGetCosts() throws IOException {
        dataManager.getCosts(31);
    }

}