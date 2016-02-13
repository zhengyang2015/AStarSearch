package xyz.devyang.cs520.astar;

import java.util.LinkedList;

/**
 * Created by YangYu on 2/13/16.
 */
public class PathFinder {

    public static void main(String[] args) {
        Grid grid = new Grid(6, 6, 0.3);
        Cell source = grid.getCell(1, 1).isBlocked(false);
        Cell destination = grid.getCell(5, 5).isBlocked(false);
        System.out.println(grid);
        AStarSearch astar = new AStarSearch(source,
                destination, grid, new Manhattan(), 1);
        astar.findPath();
        System.out.println("Find Path: " + astar.isSuccess);
        if (astar.isSuccess) {
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            LinkedList<String> linkedList = new LinkedList<String>();
            linkedList.addFirst(destination.toString());
            while (destination.parent != null) {
                linkedList.addFirst(destination.parent.toString());
                destination = destination.parent;
            }
            System.out.println("Path: ");
            while (!linkedList.isEmpty()) {
                System.out.print(linkedList.poll());
            }
        }
    }
}
