package xyz.devyang.cs520.astar;

/**
 * Created by YangYu on 2/13/16.
 */
public class Cell implements Comparable {

    protected int x;              // x-coordinate
    protected int y;              // y-coordinate
    protected boolean isOpen;
    protected boolean isClosed;   // is visited
    protected boolean isBlocked;  // is blocked
    protected int g;              // g value, distance from source
    protected int h;              // h value, estimated distance to destination
    protected int f;              // f value, sum of g and h
    protected Cell parent;
    private static TieStrategy tieStrategy = TieStrategy.SMALL_G;

    public Cell() {
    }

    public Cell x(int x) {
        this.x = x;
        return this;
    }

    public Cell y(int y) {
        this.y = y;
        return this;
    }

    /**
     * Set true if the cell is firstly visited.
     * @param isOpen
     * @return
     */
    public Cell isOpen(boolean isOpen) {
        this.isOpen = isOpen;
        return this;
    }

    /**
     * Set true if the cell is closed.
     * @param isClosed
     * @return
     */
    public Cell isClosed(boolean isClosed) {
        this.isClosed = isClosed;
        return this;
    }

    /**
     * Set true if the cell is blocked.
     * @param isBlocked
     * @return
     */
    public Cell isBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
        return this;
    }

    public Cell f(int f) {
        this.f = f;
        return this;
    }

    public Cell g(int g) {
        this.g = g;
        return this;
    }

    public Cell h(int h) {
        this.h = h;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        return y == cell.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        // not safe cast
        Cell c = (Cell)o;
        if(this.f - c.f != 0) {
            return this.f - c.f;
        } else {
            switch (Cell.tieStrategy) {
                case SMALL_G:
                    return this.g - c.g;
                case Large_G:
                    return c.g - this.g;
                default:
                    return 0;
            }
        }
    }

    @Override
    public String toString() {
        return "[" + x + ", "+ y + ']';
    }
}
