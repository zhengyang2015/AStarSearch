package xyz.devyang.cs520.astar;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by YangYu on 2/13/16.
 */
public class Grid {

    private Cell[][] grid;
    private int width;
    private int height;
    private double blockPct;
    private Random randnum = new Random(System.currentTimeMillis());

    public Grid(int width, int height, double blockPct) {
        this.width = width;
        this.height = height;
        this.blockPct = blockPct;
        this.init(width, height, blockPct);
        this.resetVisited();
    }

    /**
     * Initialize the grids
     * @param width     grids width
     * @param height    grids height
     * @param blockPct  percentage for blocks
     */
    private void init(int width, int height, double blockPct) {
        // initialize the grid
        grid = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Cell()
                        .x(i)
                        .y(j)
                        .isOpen(false)
                        .isClosed(false)
                        .isBlocked(false);
            }
        }

        // get random start cell
        int start_x = randnum.nextInt(width);
        int start_y = randnum.nextInt(height);
        Cell start = grid[start_x][start_y];

        // set block with depth first search
        Stack<Cell> stack = new Stack<Cell>();
        stack.push(start);
        while(!stack.isEmpty()) {
            Cell currCell = stack.pop();
            currCell.isClosed(true);
            for (Cell cell : getNeighbor(currCell)) {
                setBlock(cell, blockPct, stack);
            }
        }
    }

    /**
     * Return neighbors of current cell
     * @param currCell
     * @return
     */
    public Cell[] getNeighbor(Cell currCell) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        // left neighbor
        if (currCell.x > 0 && !grid[currCell.x-1][currCell.y].isBlocked) {
            neighbors.add(grid[currCell.x-1][currCell.y]);
        }
        // right neighbor
        if (currCell.x < width - 1 && !grid[currCell.x+1][currCell.y].isBlocked) {
            neighbors.add(grid[currCell.x+1][currCell.y]);
        }
        // top neighbor
        if (currCell.y > 0 && !grid[currCell.x][currCell.y-1].isBlocked) {
            neighbors.add(grid[currCell.x][currCell.y-1]);
        }
        // bottom neighbor
        if (currCell.y < height - 1 && !grid[currCell.x][currCell.y+1].isBlocked) {
            neighbors.add(grid[currCell.x][currCell.y+1]);
        }
        Cell[] cells = new Cell[neighbors.size()];
        return neighbors.toArray(cells);
    }


    /**
     * Set the cell as block with blockPct percentage if this cell is not visited
     * @param cell
     * @param blockPct
     * @param stack
     */
    private void setBlock(Cell cell, double blockPct, Stack stack) {
        if (!cell.isClosed) {
            double pct = randnum.nextDouble();
            if (pct <= blockPct) {
                cell.isBlocked(true);
            }
            stack.push(cell);
        }
    }

    /**
     * Reset all the cell as not visited
     */
    private void resetVisited() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j].isClosed(false);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getBlockPct() {
        return blockPct;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    protected Cell[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < height; i++) {
            StringBuffer row = new StringBuffer();
            for (int j = 0; j < width; j++) {
                if (grid[j][i].isBlocked) {
                    row.append("1 ");
                } else {
                    row.append("0 ");
                }
            }
            row.append("\n");
            sb.append(row.toString());
        }
        return sb.toString();
    }
}
