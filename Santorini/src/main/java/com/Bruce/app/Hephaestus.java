package com.Bruce.app;

public class Hephaestus extends Player{
    private Cell prevBuildCell = null;

    Hephaestus(int id) {
        super(id);
    }

    /** @brief build a tower on the target cell
     * @param worker the worker that builds the tower
     * @param targetCell the cell that the tower is built on
     */
    @Override public void buildTower(Worker worker, Cell targetCell) {
        int initialHeight = targetCell.getHeight();
        if (prevBuildCell == null) {
            super.buildTower(worker, targetCell);
            if(targetCell.getHeight() != initialHeight){
                prevBuildCell = targetCell;
           }
        } else if (targetCell.checkEqual(prevBuildCell)) {
            if(targetCell.getHeight() != targetCell.getWinHeight()){
                super.buildTower(worker, targetCell);
                if(targetCell.getHeight() != initialHeight){
                    prevBuildCell = null;
                }
            }
        }
    }

    /** @brief set the previous build cell to null
     */
    public void setPrevBuildCellToNull(){
        this.prevBuildCell = null;
    }
}
