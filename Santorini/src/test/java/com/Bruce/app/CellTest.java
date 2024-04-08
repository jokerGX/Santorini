package com.Bruce.app;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class CellTest {
    private Cell cell;

    @Before
    public void setUp() {
        this.cell = new Cell(1, 1);
    }

    @Test
    public void testGetX() {
        assertEquals(1, this.cell.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(1, this.cell.getY());
    }

    @Test
    public void testGetWinHeight() {
        assertEquals(3, this.cell.getWinHeight());
    }

    @Test
    public void testGetCell() {
        assertEquals(this.cell, this.cell.getCell(1, 1));
    }

    @Test
    public void testSetWorker() {
        this.cell.setWorker();
        assertEquals(true, this.cell.hasWorker());
    }

    @Test
    public void testRemoveWorker() {
        this.cell.setWorker();
        assertEquals(true, this.cell.hasWorker());
        this.cell.removeWorker();
        assertEquals(false, this.cell.hasWorker());
    }

    @Test
    public void testAddBlock() {
        this.cell.addBlock();
        assertEquals(1, this.cell.getHeight());
    }
    
}
