package com.Bruce.app;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class PlayerTest {
    private Game game;

@Before
public void setUp() {
    this.game = new Game();
    game.initGame("Human", "Human");
}

@Test
public void testaddWorker(){
    Cell cell1 = this.game.getCell(1, 1);
    Cell cell2 = this.game.getCell(1, 2);
    Cell cell3 = this.game.getCell(2, 1);
    Player player1 = this.game.getPlayers().get(this.game.getCurrPlayer());

    assertEquals(false, cell1.hasWorker());
    player1.addWorker(cell1);
    assertEquals(1, player1.getNumOfWorker());
    assertEquals(true, cell1.hasWorker());

    player1.addWorker(cell1);
    assertEquals(1, player1.getNumOfWorker());

    player1.addWorker(cell2);
    assertEquals(2, player1.getNumOfWorker());

    player1.addWorker(cell3);
    assertEquals(2, player1.getNumOfWorker());

    assertEquals(0, player1.getWorkers().get(0).getId());
    assertEquals(0, player1.getWorkers().get(1).getId());
}

@Test
public void testgetId(){
    Player player1 = this.game.getPlayers().get(this.game.getCurrPlayer());
    assertEquals(0, player1.getId());
    this.game.switchPlayer();
    player1 = this.game.getPlayers().get(this.game.getCurrPlayer());
    assertEquals(1, player1.getId());
}

@Test
public void testmoveWorker(){
    Cell cell1 = this.game.getCell(1, 1);
    Cell cell2 = this.game.getCell(1, 2);
    Cell cell3 = this.game.getCell(2, 1);
    Cell cell4 = this.game.getCell(4, 4);
    Cell buildCell = this.game.getCell(2, 2);
    
    Player player1 = this.game.getPlayers().get(this.game.getCurrPlayer());
    player1.addWorker(cell1);
    player1.addWorker(cell2);

    this.game.switchPlayer();
    Player player2 = this.game.getPlayers().get(this.game.getCurrPlayer());
    player2.addWorker(cell3);
    player2.addWorker(cell4);

    Worker worker1_1 = player1.getWorkers().get(0);

    player1.moveWorker(worker1_1, cell2);
    assertEquals(cell1, worker1_1.getCurrentCell());
    player2.moveWorker(worker1_1, buildCell);
    assertEquals(cell1, worker1_1.getCurrentCell());
    player1.moveWorker(worker1_1, this.game.getCell(3, 3));
    player1.buildTower(worker1_1, buildCell);
    assertEquals(1, buildCell.getHeight());
    player1.moveWorker(worker1_1, cell1);
    assertEquals(cell1, worker1_1.getCurrentCell());
    player1.buildTower(worker1_1, buildCell);
    player1.moveWorker(worker1_1, buildCell);
    assertEquals(cell1, worker1_1.getCurrentCell());
}

@Test
public void testbuildWorker(){
    Cell cell1 = this.game.getCell(1, 1);
    Cell cell2 = this.game.getCell(1, 2);
    Cell buildCell = this.game.getCell(2, 2);

    Player player1 = this.game.getPlayers().get(this.game.getCurrPlayer());
    player1.addWorker(cell1);
    player1.addWorker(cell2);

    this.game.switchPlayer();
    Player player2 = this.game.getPlayers().get(this.game.getCurrPlayer());

    Worker worker1_1 = player1.getWorkers().get(0);
    player2.buildTower(worker1_1, buildCell);
    assertEquals(0, buildCell.getHeight());
    player1.buildTower(worker1_1, cell2);
    assertEquals(0, cell2.getHeight());
    player1.buildTower(worker1_1, this.game.getCell(3, 3));
    assertEquals(0, this.game.getCell(3, 3).getHeight());
    player1.buildTower(worker1_1, buildCell);
    assertEquals(1, buildCell.getHeight());
    player1.buildTower(worker1_1, buildCell);
    assertEquals(2, buildCell.getHeight());
    player1.buildTower(worker1_1, buildCell);
    assertEquals(3, buildCell.getHeight());
    player1.buildTower(worker1_1, buildCell);
    assertEquals(4, buildCell.getHeight());
    player1.buildTower(worker1_1, buildCell);
    assertEquals(4, buildCell.getHeight());
}
}
