package fr.ensicaen.lv223.model.environment.cells.specials.extractable;

import fr.ensicaen.lv223.model.environment.cells.CellType;

/**
 * The {@code OreCell} class extends the {@link ExtractableCell} class and
 * represents a cell with some minerals that can be extracted.
 */
 public class OreCell extends ExtractableCell {
    /**
     * Constructs a new {@code OreCell} instance with the specified properties.
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param intensity the intensity of metamorphosis for the cell
     * @param quantity the quantity of minerals that can be extracted on the
     *                 cell
     */
    public OreCell(int x, int y, double intensity, double quantity) {
        super(x, y, CellType.ORE, intensity, quantity);
    }
}
