package fr.ensicaen.lv223.model.cells;

import fr.ensicaen.lv223.model.CellType;

/**
 * This class extends the {@link ExtractableCell} class and represents a
 * cell containing water in the environment (e.g. a lake).
 */
public class LakeCell extends ExtractableCell {
    /**
     * Constructs a new {@code LakeCell} instance with the specified properties.
     *
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param type the type of the cell
     * @param intensity the intensity of metamorphosis for the cell
     * @param quantity the quantity of water on the cell
     */
    public LakeCell(int x, int y, CellType type, double intensity, double quantity) {
        super(x, y, type, intensity, quantity);
    }
}
