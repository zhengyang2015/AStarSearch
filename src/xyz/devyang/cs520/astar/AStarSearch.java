package xyz.devyang.cs520.astar;

/**
 * Created by YangYu on 2/13/16.
 */
public class AStarSearch {

    private Cell source;
    private Cell destination;
    private Grid grid;
    private int weight;         // cost of single action
    protected boolean isSuccess = false; // whether find a path

    private Heuristic heuristic;
    private BinaryHeap openList;

    public AStarSearch(Cell source, Cell destination, Grid grid,
                       Heuristic heuristic, int weight) {
        this.source = source;
        this.destination = destination;
        this.grid = grid;
        this.heuristic = heuristic;
        this.weight = weight;
        this.openList = new BinaryHeap();
        openList.insert(source);
    }

    public void findPath() {
        int count = 0;
        while (!openList.isEmpty()) {
            count += weight;
            Cell currCell = openList.pop();
            if (currCell.equals(destination)) {
                this.isSuccess = true;
                return;
            }
            currCell.isClosed(true);
            Cell[] neighbors = grid.getNeighbor(currCell);
            for (Cell neighbor : neighbors) {
                neighbor.isOpen(true);
                if (neighbor.isClosed) {
                    continue;
                }
                neighbor.g = Math.min(currCell.g + weight, count);
                neighbor.h = heuristic.distance(currCell, neighbor);
                neighbor.f = neighbor.g + neighbor.h;
                neighbor.parent = currCell;
                openList.insert(neighbor);
            }
        }
    }
}