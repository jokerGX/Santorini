package com.Bruce.app;
import java.util.ArrayList;
import java.util.List;

enum State {
  newgame,
  init,
  move,
  build,
  end,
}

public class GameState {
    private Game game;
    private int currPlayer;
    private State currState;
    private List<Worker> workers0;
    private List<Worker> workers1;

    /**constructor of GameState
     * @param game the game that the state is based on
     * @param state the state of the game
     */
    public GameState(Game game, State state) {
        this.game = game;
        this.currState = state;
        this.currPlayer = -1;
        this.workers0 = new ArrayList<Worker>();
        this.workers1 = new ArrayList<Worker>();
        if(game != null && game.getCurrPlayer() != -1){
            this.currPlayer = game.getCurrPlayer();
            this.workers0 = game.getPlayers().get(0).getWorkers();
            this.workers1 = game.getPlayers().get(1).getWorkers();
        }
    }

    /** convert to string */
    @Override
    public String toString() {
       return """
         {  "currPlayer": %d,
            "currState": "%s",
            "workers_0": %s,
            "workers_1": %s,
            "cells": %s
            }
               """.formatted(currPlayer, this.currState, this.workers0, workers1, this.game.getCells());
    }

}