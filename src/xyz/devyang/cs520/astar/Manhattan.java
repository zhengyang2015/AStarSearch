package xyz.devyang.cs520.astar;

/**
 * Manhattan heuristic
 * Created by YangYu on 2/13/16.
 */
public class Manhattan implements Heuristic {

    @Override
    public int distance(Cell a, Cell b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
