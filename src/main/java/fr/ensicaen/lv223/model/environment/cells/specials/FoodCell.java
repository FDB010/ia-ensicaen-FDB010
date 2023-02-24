package fr.ensicaen.lv223.model.environment.cells.specials;

import fr.ensicaen.lv223.model.environment.cells.CellType;

/**
 * The class {@code FoodCell} extends the class {@link ExtractableCell} and
 * represents a cell that contains some food in the simulation environment.
 */
public class FoodCell extends ExtractableCell {
    /**
     * Constructs a new {@code FoodCell} instance with the specified properties.
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @param type the type of the cell
     * @param intensity the intensity of metamorphosis for the cell
     * @param quantity the quantity of food that the cell can produce
     */
    public FoodCell(int x, int y, CellType type, double intensity, double quantity) {
        super(x, y, type, intensity, quantity);
    }
}
