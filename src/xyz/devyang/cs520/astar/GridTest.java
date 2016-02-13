package xyz.devyang.cs520.astar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by YangYu on 2/13/16.
 */
public class GridTest {

    private Grid grid;

    @Before
    public void init() {
        grid = new Grid(101, 101, 0.3);
    }

    @Test
    public void testGridInitialization() {
        Cell[][] cells = grid.getGrid();
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                Assert.assertEquals(false, cells[i][j].isClosed);
            }
        }
    }

    @Test
    public void testBlockNumber() {
        Cell[][] cells = grid.getGrid();
        int blocked = 0;
        int unblocked = 0;
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                if (cells[i][j].isBlocked) {
                    blocked++;
                } else {
                    unblocked++;
                }
            }
        }
        System.out.println("blocked: "+blocked+"\n"
                +"unblocked: "+unblocked+"\n"
                +"pct: "+(double)blocked/(blocked+unblocked));
        Assert.assertEquals(grid.getWidth()*grid.getHeight(), blocked+unblocked);
    }

}