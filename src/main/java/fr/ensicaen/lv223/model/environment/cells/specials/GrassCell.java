package fr.ensicaen.lv223.model.environment.cells.specials;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;

public class GrassCell extends Cell {
    private boolean isDry;
    private boolean isWet;
    /**
     * Constructs a new {@code Cell} object with given x and y coordinates,
     * cell type, and intensity of metamorphosis. The intensity of wave is
     * initialized to 0 and isExoskeleton is set based on the cell type.
     *
     * @param x         the x coordinate of the cell
     * @param y         the y coordinate of the cell
     * @param intensity the intensity of metamorphosis
     */
    public GrassCell(int x, int y, double intensity) {
        super(x, y, CellType.GRASS, intensity);
        this.isDry = false;
        this.isWet = false;
    }

    public boolean isDry() {
        return isDry;
    }

    public void setDry() {
        isDry = true;
        isWet = false;
    }

    public boolean isWet() {
        return isWet;
    }

    public void setWet() {
        isWet = true;
        isDry = false;
    }

    @Override
    public CellType getType() {
        if (isWet)
            return CellType.WET_GRASS;
        if (isDry)
            return CellType.DRY_GRASS;
        return super.getType();
    }
}
