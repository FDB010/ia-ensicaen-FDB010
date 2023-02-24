package fr.ensicaen.lv223.model.cells;

import fr.ensicaen.lv223.model.CellType;

/**
 * The {@code ExtractableCell} is a subclass of {@link Cell} that represents a
 * cell containing some extractable resources as food, water or minerals.
 */
public abstract class ExtractableCell extends Cell {
    /** The current amount of a given resource that is available on the cell */
    protected double  quantity;

    /**
     * The initial amount of a given resource that was available at the start
     * of the simulation
     */
    protected final double initialQuantity;

    /**
     * Constructs a new {@code FoodCell} instance with the specified cell type,
     * intensity, and quantity.
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @param type the type of the cell
     * @param intensity the intensity of metamorphosis for the cell
     * @param quantity the quantity of food that the cell can produce
     */
    protected ExtractableCell(int x, int y, CellType type, double intensity,
                       double quantity) {
        super(x, y, type, intensity);
        this.quantity = quantity;
        this.initialQuantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getInitialQuantity() {
        return initialQuantity;
    }

    public double getPercentageExtraction() {
        return 100 - (quantity / initialQuantity) * 100;
    }
}
