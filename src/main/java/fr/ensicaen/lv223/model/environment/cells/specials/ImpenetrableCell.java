package fr.ensicaen.lv223.model.environment.cells.specials;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;

public class ImpenetrableCell extends Cell {
    /**
     * Constructs a new {@code Cell} object with given x and y coordinates,
     * cell type, and intensity of metamorphosis. The intensity of wave is
     * initialized to 0 and isExoskeleton is set based on the cell type.
     *
     * @param x         the x coordinate of the cell
     * @param y         the y coordinate of the cell
     * @param intensity the intensity of metamorphosis
     */
    public ImpenetrableCell(int x, int y, double intensity) {
        super(x, y, CellType.IMPENETRABLE, intensity);
    }
}
