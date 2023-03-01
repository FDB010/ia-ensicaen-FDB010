package fr.ensicaen.lv223.util.loader.planetloader;

/**
 * The class {@code CellPosition} represents the position of a cell on a
 * two-dimensional grid. It has two fields, {@code x} and {@code y},
 * representing the horizontal and vertical position of the cell, respectively.
 */
public class CellPosition {
    private int x;
    private int y;

    /**
     * Returns the horizontal position of the cell.
     * @return the horizontal position of the cell.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the vertical position of the cell.
     * @return the vertical position of the cell.
     */
    public int getY() {
        return y;
    }
}
