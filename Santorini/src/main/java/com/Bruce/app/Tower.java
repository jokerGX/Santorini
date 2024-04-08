package com.Bruce.app;

public class Tower{
    private int height;
    private final int max = 4;

    /** @brief constructor of Tower */
    public Tower() {
        this.height = 0;
    }

    /** @brief add a block to the tower */
    public void addBlock() {
        // check whether the tower has reached max height
        if (this.height < this.max) {
            this.height++;
        }else{
            System.out.println("Cannot add block to a tower that has reached max height");
        }
    }

    /** @brief get the height of the tower
     * @return the height of the tower
     */
    public int getHeight() {
        return this.height;
    }

    /** @brief get the max height of the tower
     * @return the max height of the tower
     */
    public int getMax() {
        return this.max;
    }
}
