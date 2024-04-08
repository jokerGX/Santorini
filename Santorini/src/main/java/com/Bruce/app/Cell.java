package com.Bruce.app;

public class Cell {
    private Tower tower;
    private int x;
    private int y;
    private boolean hasWorker;
    private final int winHeight = 3;

    /** @brief constructor of Cell 
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
    */
    public Cell(int x, int y) {
        this.tower = new Tower();
        this.x = x;
        this.y = y;
        this.hasWorker = false;
    }

    /** @brief get x coordinate of the cell
     * @return the x coordinate of the cell
     */
    public int getX() {
        return this.x;
    }

    /** @brief get y coordinate of the cell
     * @return the y coordinate of the cell
     */
    public int getY() {
        return this.y;
    }

    /** @brief get 3, which is the winning height of the tower on the cell
     * @return the winning height of the tower on the cell, which is 3
    */
    public int getWinHeight() {
        return this.winHeight;
    }

    /** @brief check whether the given coordinate is the same as the cell's coordinate
     * @return the cell with the given coordinate
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     */
    public Cell getCell(int x, int y) {
        if (this.x == x && this.y == y) {
            return this;
        }
        return null;
    }

    /** @brief get the tower on the cell
     * @return the tower on the cell
     */
    public Tower getTower() {
        return this.tower;
    }

    /** @brief add a block to the tower on the cell */
    public void addBlock() {
        this.tower.addBlock();
        return;
    }

    /** @brief get the height of the tower on the cell
     * @return the height of the tower on the cell
     */
    public int getHeight() {
        return this.tower.getHeight();
    }

    /** @brief set the cell to have a worker */
    public void setWorker() {
            this.hasWorker = true;
    }

    /** @brief check whether the cell has a worker
     * @return true if the cell has a worker, otherwise return false
     */
    public boolean hasWorker() {
        return this.hasWorker;
    }

    /** @brief remove the worker on the cell */
    public void removeWorker() {
            this.hasWorker = false;
    }

    public boolean checkEqual(Cell cell) {
        if (this.x == cell.getX() && this.y == cell.getY()) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return """
            {  "x": %d,
               "y": %d,
               "height": %d,
               "hasWorker": %b
               }
               """.formatted(this.x, this.y, this.getHeight(), this.hasWorker);
    }
}
