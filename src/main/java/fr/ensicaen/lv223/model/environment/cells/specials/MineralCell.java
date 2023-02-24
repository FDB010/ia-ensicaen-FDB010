package fr.ensicaen.lv223.model.environment.cells.specials;

import fr.ensicaen.lv223.model.environment.cells.CellType;

/**
 * The {@code MineralCell} class extends the {@link ExtractableCell} class and
 * represents a cell with some minerals that can be extracted.
 */
 public class MineralCell extends ExtractableCell {
    /**
     * Constructs a new {@code MineralCell} instance with the specified properties.
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param type the type of the cell
     * @param intensity the intensity of metamorphosis for the cell
     * @param quantity the quantity of minerals that can be extracted on the
     *                 cell
     */
    public MineralCell(int x, int y, CellType type, double intensity, double quantity) {
        super(x, y, type, intensity, quantity);
    }
}
