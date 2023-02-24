package fr.ensicaen.lv223.model.environment.cells;

import fr.ensicaen.lv223.model.environment.EnvironmentCell;

/**
 * The {@code Cell} class represents a cell in a two-dimensional grid, with
 * properties such as x and y coordinates, cell type, intensity of metamorphosis,
 * intensity of wave, and whether it is part of the exoskeleton or not.
 * <p>
 * It implements {@code EnvironmentCell} and {@code Comparable} interfaces.
 * @see EnvironmentCell
 * @see Comparable
 */
public class Cell implements EnvironmentCell, Comparable {
    private final int x;
    private final int y;
    private CellType type;
    private double intensityOfMetamorphosis;
    private int intensityOfWave;
    private final boolean isExoskeleton;

    /**
     * Constructs a new {@code Cell} object with given x and y coordinates,
     * cell type, and intensity of metamorphosis. The intensity of wave is
     * initialized to 0 and isExoskeleton is set based on the cell type.
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param type the type of cell
     * @param intensity the intensity of metamorphosis
     */
    public Cell(int x, int y, CellType type, double intensity) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.intensityOfMetamorphosis = intensity;
        this.intensityOfWave = 0;
        this.isExoskeleton = type == CellType.BASE
                || type == CellType.LAKE || type == CellType.STONE
                || type == CellType.ORE || type == CellType.IMPENETRABLE;
    }

    /**
     * Returns the x coordinate of the cell.
     * @return the x coordinate of the cell
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the cell.
     * @return the y coordinate of the cell
     */
    public int getY() {
        return y;
    }

    /**
     * Returns whether the cell has been impacted by a wave or not.
     * @return true if the cell has been impacted by a wave, false otherwise
     */
    public boolean hasBeenImpactedByAWave() {
        return intensityOfWave != 0;
    }

    /**
     * Returns the intensity of wave on the cell.
     * @return the intensity of wave on the cell
     */
    public int getIntensityOfWave() {
        return intensityOfWave;
    }

    /**
     * Sets the intensity of wave on the cell to the given value.
     * @param value the new value of the intensity of wave
     */
    public void setIntensityOfWave(int value) {
        this.intensityOfWave = value;
    }

    /**
     * Returns whether the cell is a part of the exoskeleton or not.
     * @return true if the cell is a part of the exoskeleton, false otherwise
     */
    public boolean isExoskeleton() {
        return isExoskeleton;
    }

    /**
     * Returns the type of the cell.
     * @return the type of the cell
     */
    @Override
    public CellType getType() {
        return type;
    }

    /**
     * Returns the intensity of metamorphosis for the cell.
     * @return the intensity of metamorphosis for the cell
     */
    @Override
    public double getIntensity() {
        return intensityOfMetamorphosis;
    }

    /**
     * Sets the intensity of metamorphosis of the cell.
     * @param intensity the intensity of metamorphosis of the cell
     */
    @Override
    public void setIntensity(double intensity) {
        intensityOfMetamorphosis = intensity;
    }

    /**
     * Compares the intensity of wave of this cell to another cell.
     * @param o the other cell to compare to
     * @return a negative integer, zero, or a positive integer as this cell's
     * intensity of wave is less than, equal to, or greater than the other
     * cell's
     */
    public int compareTo(Object o) {
        Cell other = (Cell) o;
        return Integer.compare(intensityOfWave, other.getIntensityOfWave());
    }

    /**
     * Returns a string representation of the cell.
     * @return a string representation of the cell
     */
    public String toString() {
        return "" + intensityOfWave;
    }
}
