package com.Bruce.app;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class GodPowerTest {
    private Game game;

    @Before
    public void setUp() {
        this.game = new Game();
    }

    @Test
    public void testPan() {
        game.initGame("Pan", "Pan");
        Player player = game.getPlayers().get(game.getCurrPlayer());
        player.addWorker(game.getCell(1, 1));
        player.addWorker(game.getCell(2, 2));

        Worker worker1 = player.getWorkers().get(0);

        player.buildTower(worker1, game.getCell(1, 2));
        player.buildTower(worker1, game.getCell(2, 1));
        player.buildTower(worker1, game.getCell(2, 1));

        player.moveWorker(worker1, game.getCell(1, 2));
        assertEquals(null, game.checkWin());
        player.moveWorker(worker1, game.getCell(2, 1));
        assertEquals(null, game.checkWin());
        player.moveWorker(worker1, game.getCell(1, 1));
        assertEquals(player, game.checkWin());
    }

    @Test
    public void testDemeter(){
        game.initGame("Demeter", "Demeter");
        Player player = game.getPlayers().get(game.getCurrPlayer());
        player.addWorker(game.getCell(1, 1));
        player.addWorker(game.getCell(2, 2));

        Worker worker1 = player.getWorkers().get(0);
        player.buildTower(worker1, game.getCell(1, 2));
        assertEquals(1, game.getCell(1, 2).getHeight());
        player.buildTower(worker1, game.getCell(1, 2));
        assertEquals(1, game.getCell(1, 2).getHeight());
        player.buildTower(worker1, game.getCell(2, 1));
        assertEquals(1, game.getCell(2, 1).getHeight());
        player.buildTower(worker1, game.getCell(2, 1));
        assertEquals(2, game.getCell(2, 1).getHeight());
    }

    @Test
    public void testMinotaur(){
        game.initGame("Minotaur", "Minotaur");
        Player player1 = game.getPlayers().get(game.getCurrPlayer());
        Player player2 = game.getOtherPlayer();
        player1.addWorker(game.getCell(1, 1));
        player1.addWorker(game.getCell(2, 2));
        player2.addWorker(game.getCell(1, 2));
        player2.addWorker(game.getCell(2, 1));

        Worker worker1 = player1.getWorkers().get(0);
        Worker worker2 = player1.getWorkers().get(1);
        Worker worker3 = player2.getWorkers().get(0);

        player1.moveWorker(worker1, game.getCell(1, 2));
        assertEquals(game.getCell(1, 2), worker1.getCurrentCell());
        assertEquals(game.getCell(1, 3), worker3.getCurrentCell());

        player1.moveWorker(worker2, game.getCell(1, 2));
        assertEquals(game.getCell(2, 2), worker2.getCurrentCell());
        player1.moveWorker(worker2, game.getCell(2, 1));
        assertEquals(game.getCell(2, 2), worker2.getCurrentCell());
    }

    @Test
    public void testHephaestus(){
        game.initGame("Hephaestus", "Hephaestus");
        Player player = game.getPlayers().get(game.getCurrPlayer());
        player.addWorker(game.getCell(1, 1));
        player.addWorker(game.getCell(2, 2));

        Worker worker1 = player.getWorkers().get(0);
        player.buildTower(worker1, game.getCell(1, 2));
        assertEquals(1, game.getCell(1, 2).getHeight());
        player.buildTower(worker1, game.getCell(2, 1));
        assertEquals(0, game.getCell(2, 1).getHeight());

        player.buildTower(worker1, game.getCell(1, 2));
        assertEquals(2, game.getCell(1, 2).getHeight());

        player.buildTower(worker1, game.getCell(1, 2));
        assertEquals(3, game.getCell(1, 2).getHeight());
        player.buildTower(worker1, game.getCell(1, 2));
        assertEquals(3, game.getCell(1, 2).getHeight());
    }
}
