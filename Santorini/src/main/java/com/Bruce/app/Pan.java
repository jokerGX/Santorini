package com.Bruce.app;

import java.util.List;

public class Pan extends Player{
    private int worker1Height = 0;
    private int worker2Height = 0;
    private int diff = -1;

    Pan(int id) {
        super(id);
    }

    /** @brief move the worker to the target cell
     * @param worker the worker that moves
     * @param targetCell the cell that the worker moves to
     */
    @Override public void moveWorker(Worker worker, Cell targetCell) {
       super.moveWorker(worker, targetCell);
       List <Worker> workers = super.getWorkers();
       if (worker == workers.get(0)) {
           int newHeight = workers.get(0).getCurrentCell().getHeight();
           diff = worker1Height - newHeight;
           worker1Height = newHeight;
       } else {
           int newHeight = workers.get(0).getCurrentCell().getHeight();
           diff = worker2Height - newHeight;
           worker1Height = newHeight;
       }
    }

    /** @brief check whether the player wins
     * @return the player that wins
     */
    @Override public Player checkWin() {
        Player result = super.checkWin();
        if (result != null) {
            return this;
        }else if (diff >= 2) {
            return this;
        }
        return null;
    }
}
