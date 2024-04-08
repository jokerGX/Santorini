package com.Bruce.app;

import java.util.List;
import java.util.ArrayList;
public class Game {
    private int currPlayer;
    private List<Player> players;
    private List<Cell> cells;
    private final int numOfCell = 25;
    private final int sideLength = 5;
    // constructor
    public Game() {
        this.players = new ArrayList<Player>();
        this.currPlayer = -1;
        this.cells = new ArrayList<Cell>();
    }

    /** @brief get current player 
     * @return the id of current player
    */
    public int getCurrPlayer() {
        return this.currPlayer;
    }

    /** @brief get all players
     * @return a list of players
    */
    public List<Player> getPlayers() {
        return this.players;
    }

    public Player getOtherPlayer() {
        if (this.currPlayer == 0) {
            return this.players.get(1);
        } else {
            return this.players.get(0);
        }
    }

    /** @brief get all cells 
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return a list of cells
    */
    public Cell getCell(int x, int y) {
        for (Cell cell : this.cells) {
            if (cell.getCell(x, y) != null) {
                return cell;
            }
        }
        return null;
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    public Player playerInit(String godPower, int id) {
        if (godPower.equals("Pan")) {
            Player player = new Pan(id);
            return player;
        } else if (godPower.equals("Minotaur")){
            Player player = new Minotaur(id, this);
            return player;
        }else if(godPower.equals("Demeter")){
            Player player = new Demeter(id);
            return player;
        }else if (godPower.equals("Hephaestus")){
            Player player = new Hephaestus(id);
            return player;
        }else{
            Player player = new Player(id);
            return player;
        }
    }

    /** @brief initialize the game 
     * @param godPower1 the god power of player 1
     * @param godPower2 the god power of player 2
    */
    public void initGame(String godPower1, String godPower2) {
        
        for (int i = 0; i < this.numOfCell; i++) {
            Cell cell = new Cell((i / sideLength) + 1, (i % sideLength) + 1);
            this.cells.add(cell);
        }
        // init all the players
        Player player1 = playerInit(godPower1, 0);
        Player player2 = playerInit(godPower2, 1);
        this.players.add(player1);
        this.players.add(player2);

        this.currPlayer = 0;
    }

    /** @brief check whether there is a winning player 
     * @return the winning player if there is one, otherwise return null
    */
    public Player checkWin() {
        Player currPlayer = this.players.get(this.currPlayer);
        Player result = currPlayer.checkWin(); 
        if (result != null) {
            return result;
        }
        return null;
    }

    /** @brief switch the current player */
    public void switchPlayer() {
        // check whether the game has init
        if (this.currPlayer == -1) {
            System.out.println("Game has not init yet");
        } else
        if (this.currPlayer == 0) {
            this.currPlayer = 1;
        } else {
            this.currPlayer = 0;
        }
    }
}
