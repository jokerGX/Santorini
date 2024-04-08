package com.Bruce.app;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        this.game = new Game();
        game.initGame("Pan", "Human");
    }

    @Test
    public void IntegrationTest() {
        assertEquals(0, this.game.getCurrPlayer());
        assertEquals(0, this.game.getPlayers().get(0).getId());
        assertEquals(null, this.game.getCell(7, 7));

        Cell cell1 = this.game.getCell(1, 1);
        Cell cell2 = this.game.getCell(2, 2);
        Player player1 = this.game.getPlayers().get(this.game.getCurrPlayer());

        player1.addWorker(cell1);
        assertEquals(1, player1.getNumOfWorker());
        player1.addWorker(cell1);
        assertEquals(1, player1.getNumOfWorker());
        player1.addWorker(cell2);
        assertEquals(2, player1.getNumOfWorker());
        assertEquals(0, player1.getWorkers().get(0).getId());
        assertEquals(0, player1.getWorkers().get(1).getId());

        Cell cell3 = this.game.getCell(3, 3);
        Cell cell4 = this.game.getCell(4, 4);

        this.game.switchPlayer();
        player1 = this.game.getPlayers().get(this.game.getCurrPlayer());
        assertEquals(1, player1.getId());
        player1.addWorker(cell3);
        assertEquals(1, player1.getNumOfWorker());
        player1.addWorker(cell1);
        assertEquals(1, player1.getNumOfWorker());
        player1.addWorker(cell4);
        assertEquals(2, player1.getNumOfWorker());
        assertEquals(1, player1.getWorkers().get(0).getId());
        assertEquals(1, player1.getWorkers().get(1).getId());

        this.game.switchPlayer();
        player1 = this.game.getPlayers().get(this.game.getCurrPlayer());
        Worker worker1 = player1.getWorkers().get(0);


        assertEquals(cell1, worker1.getCurrentCell());
        assertEquals(0, cell1.getHeight());
        player1.buildTower(worker1, cell1);
        player1.buildTower(worker1, this.game.getCell(4, 4));
        assertEquals(0, this.game.getCell(4, 4).getHeight());
        assertEquals(0, cell1.getHeight());
        player1.moveWorker(worker1, this.game.getCell(4, 4));
        player1.moveWorker(worker1, this.game.getCell(1, 2));
        assertEquals(this.game.getCell(1, 2), worker1.getCurrentCell());

        player1.buildTower(worker1, cell1);
        assertEquals(1, cell1.getHeight());
        player1.buildTower(worker1, cell1);
        assertEquals(2, cell1.getHeight());
        player1.moveWorker(worker1, cell1);
        assertEquals(this.game.getCell(1, 2), worker1.getCurrentCell());

        assertEquals(null, this.game.checkWin());
        player1.buildTower(worker1, this.game.getCell(2, 1));
        player1.moveWorker(worker1, this.game.getCell(2, 1));
        player1.moveWorker(worker1, cell1);
        player1.buildTower(worker1, this.game.getCell(2, 1));
        player1.buildTower(worker1, this.game.getCell(2, 1));
        player1.moveWorker(worker1, this.game.getCell(2, 1));
        assertEquals(player1, this.game.checkWin());

    }

}
