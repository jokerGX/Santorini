package com.Bruce.app;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private List<Worker> workers;
    private final int maxWorker = 2;
    private Worker movedWorker = null;

    /** @brief constructor of Player */
    Player(int id) {
        this.id = id;
        this.workers = new ArrayList<Worker>();
    }

    /** @brief get the worker that is moved
     * @return the worker that is moved
     */
    public Worker getMovedWorker() {
        return this.movedWorker;
    }

    /** @brief set the worker that is moved
     * @param worker the worker that is moved
     */
    public void setMovedWorker(Worker worker) {
        this.movedWorker = worker;
    }

    /** @brief add a worker to the player
     * @param targetCell the cell that the worker is added to
     */
    public void addWorker(Cell targetCell) {
        // check whether the target cell has a worker and whether the player has reached 2 workers
        if (workers.size() < this.maxWorker && targetCell.hasWorker() == false) {
            Worker worker = new Worker(targetCell, this.id);
            this.workers.add(worker);
            targetCell.setWorker();
        } else {
            System.out.println("Cannot execute addWorker");
        }
    }

    /** @brief get id of the player
     * @return the id of the player
     */
    public int getId() {
        return this.id;
    }

    /** @brief get the number of workers of the player
     * @return the number of workers of the player
     */
    public int getNumOfWorker() {
        return this.workers.size();
    }

    /** @brief get all workers of the player
     * @return a list of workers of the player
     */
    public List<Worker> getWorkers() {
        return this.workers;
    }

    /** @brief get the worker with the given coordinate
     * @param x the x coordinate of the worker
     * @param y the y coordinate of the worker
     * @return the worker with the given coordinate
     */
    public Worker getWorker(int x, int y) {
        for (Worker worker : this.workers) {
            if (worker.getCurrentCell().getX() == x && worker.getCurrentCell().getY() == y) {
                return worker;
            }
        }
        return null;
    }


    /** @brief move the worker to the target cell
     * @param worker the worker that is moved
     * @param targetCell the cell that the worker is moved to
     */
    public void moveWorker(Worker worker, Cell targetCell) {
        // check whether the worker belongs to the player
        if (worker.getId() != this.id) {
            System.out.println("Cannot move other player's worker");
            return;
        }
        // check whether the target cell's hight is at most 1 higher than the worker's current cell
        if (targetCell.getHeight() - worker.getCurrentCell().getHeight() > 1){
            System.out.println("Cannot move to a higher cell with height difference larger than 1");
            return;
        }
        // check whether the target cell and the worker's current cell are adjacent
        if (Math.abs(targetCell.getX() - worker.getCurrentCell().getX()) > 1 || Math.abs(targetCell.getY() - worker.getCurrentCell().getY()) > 1) {
            System.out.println("Cannot move to a cell that is not adjacent");
            return;
        }
        // whether the target cell has a worker and whether it has dome is checked in moveTo() method
        worker.moveTo(targetCell);
        this.movedWorker = worker;
    }

    public Player checkWin() {
        if (this.movedWorker.getCurrentCell().getHeight() == this.movedWorker.getCurrentCell().getWinHeight()) {
            return this;
        }
        this.movedWorker = null;
        return null;
    }

    /** @brief build a block on the target cell
     * @param worker the worker that builds the block
     * @param targetCell the cell that the block is built on
     */
    public void buildTower(Worker worker, Cell targetCell) {
        // check whether the worker belongs to the player
        if (worker.getId() != this.id) {
            System.out.println("Cannot operate other player's worker");
            return;
        }
        // check whether the target cell has a worker
        if (targetCell.hasWorker()) {
            System.out.println("Cannot build tower on a cell that has a worker");
            return;
        }
        // check whether the target cell's height is at most 1 higher than the worker's current cell
        if (Math.abs(targetCell.getX() - worker.getCurrentCell().getX()) > 1 || Math.abs(targetCell.getY() - worker.getCurrentCell().getY()) > 1) {
            System.out.println("Cannot build on a cell that is not adjacent");
            return;
        }

        // whether the target cell has reached max height(dome) is checked in addBlock() method
        targetCell.addBlock();
    }

    /** @brief set the previous build cell to null
     */
    public void setPrevBuildCellToNull(){
        return;
    }

}
