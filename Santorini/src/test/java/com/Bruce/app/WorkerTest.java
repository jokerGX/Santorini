package com.Bruce.app;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class WorkerTest {
    private Cell cell1;
    private Cell cell2;
    private Cell cell3;
    private Cell cell4;
    private Worker worker1;
    private Worker worker2;

    @Before
    public void setUp() {
        this.cell1 = new Cell(1, 1);
        this.cell2 = new Cell(2, 2);
        this.cell3 = new Cell(2, 1);
        this.cell4 = new Cell(1, 2);
        this.worker1 = new Worker(this.cell1, 1);
        this.worker2 = new Worker(this.cell2, 2);
    }

    @Test
    public void testGetId() {
        assertEquals(1, this.worker1.getId());
    }

    @Test
    public void testGetCurrentCell() {
        assertEquals(this.cell1, this.worker1.getCurrentCell());
    }

    @Test
    public void testMoveTo() {
        assertEquals(this.cell2, this.worker2.getCurrentCell());
        assertEquals(this.cell2.hasWorker(), true);
        this.worker1.moveTo(this.cell2);
        assertEquals(this.cell1, this.worker1.getCurrentCell());
        assertEquals(this.cell1.hasWorker(), true);
        assertEquals(this.cell3.hasWorker(), false);
        this.worker1.moveTo(this.cell4);
        assertEquals(this.cell1.hasWorker(), false);
        assertEquals(this.cell4, this.worker1.getCurrentCell());
    }



}
