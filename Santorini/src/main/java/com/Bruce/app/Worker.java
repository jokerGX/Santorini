package com.Bruce.app;

public class Worker {
    private Cell currentCell;
    private int id;

    /** @brief constructor of Worker 
     * @param currentCell the cell that the worker is on
     * @param id the id of the worker
    */
    public Worker(Cell currentCell, int id) {
        this.currentCell = currentCell;
        this.currentCell.setWorker();
        this.id = id;
    }

    /** @brief get id of the worker
     * @return the id of the worker
     */
    public int getId() {
        return this.id;
    }

    /** @brief get current cell of the worker 
     * @return the current cell of the worker
    */
    public Cell getCurrentCell() {
        return this.currentCell;
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    /** @brief move the worker to the target cell 
     * @param targetCell the cell that the worker is moved to
    */
    public void moveTo(Cell targetCell) {
        // check whether the target cell has a worker
        if (targetCell.hasWorker()) {
            System.out.println("There is a worker on the target cell");
        // check whether the target cell has dome
        }else if (targetCell.getHeight() == targetCell.getTower().getMax()){
            System.out.println("Cannot move to a cell with a dome");
        }else{
            this.currentCell.removeWorker();
            targetCell.setWorker();
            this.currentCell = targetCell;
        }
    }

    @Override
    public String toString() {
        return """
            {  "id": %d,
               "Cell": %s
               }
                  """.formatted(id,this.currentCell);
    }
 
}
