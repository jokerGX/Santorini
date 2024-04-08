package com.Bruce.app;
import java.util.List;

public class Minotaur extends Player{
    private Game game;
    private int id;

    Minotaur(int id, Game game) {
        super(id);
        this.id = id;
        this.game = game;
    }

    @Override public void moveWorker(Worker worker, Cell targetCell) {
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
        // check whether the target cell has a dome
        if (targetCell.getHeight() == targetCell.getTower().getMax()) {
            System.out.println("Cannot move to a cell with a dome");
            return;
        }
        
        if(targetCell.hasWorker()){
            List<Worker> workers = super.getWorkers();
            for (Worker w : workers) {
                if (w.getCurrentCell() == targetCell) {
                    return;
                }
            }
            Player opponent = game.getOtherPlayer();
            Worker opponentWorker = opponent.getWorker(targetCell.getX(), targetCell.getY());
            int xDiff = targetCell.getX() - worker.getCurrentCell().getX();
            int yDiff = targetCell.getY() - worker.getCurrentCell().getY();
            Cell newCell = game.getCell(targetCell.getX() + xDiff, targetCell.getY() + yDiff);
            if (newCell == null) {
                System.out.println("Cannot move oppnent worker to a cell that is not on the board");
                return;
            }
            opponentWorker.moveTo(newCell);
            if (opponentWorker.getCurrentCell() == newCell) {
                worker.getCurrentCell().removeWorker();
                targetCell.setWorker();
                worker.setCurrentCell(targetCell);
                super.setMovedWorker(worker);
                return;
            }else{
                System.out.println("Cannot move opponent worker cell");
                return;
            }
            
        }
        worker.getCurrentCell().removeWorker();
        targetCell.setWorker();
        worker.setCurrentCell(targetCell);
        super.setMovedWorker(worker);
        return;
    }

}
