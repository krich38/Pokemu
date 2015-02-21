package org.pokemu.object;

/**
 * @author Kyle Richards
 * @version 1.0
 *          TODO: Implement this
 */
public final class Coordinate {
    final int x;
    final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final int hashCode() {
        return x >> 16 & y;
    }

    public final boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate c = (Coordinate) o;
            return c.x == x && c.y == y;
        }
        return false;
    }
}
