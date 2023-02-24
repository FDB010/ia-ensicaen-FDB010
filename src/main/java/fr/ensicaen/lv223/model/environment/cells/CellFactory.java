package fr.ensicaen.lv223.model.environment.cells;

import java.util.Optional;

/**
 * The {@code CellFactory} class is a utility class for creating cells.
 */
public class CellFactory {
    private CellFactory() {}

    /**
     * Returns an {@link Optional} containing the {@link Cell} object with the
     * specified type and intensity, or an empty Optional if the type is
     * invalid.
     *
     * @param type the type of the cell
     * @param intensity the intensity of the cell
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @return an Optional containing the Cell object, or an empty Optional if
     * the type is invalid
     */
    public static Optional<Cell> factory(String type, double intensity, int x, int y) {
        Cell c;
        switch (type) {
            case "BASE":
                c = new Cell(x, y, CellType.BASE, intensity);
                break;
            case "DESERT":
                c = new Cell(x, y, CellType.DESERT, intensity);
                break;
            case "DRY_GRASS":
                c = new Cell(x, y, CellType.DRY_GRASS, intensity);
                break;
            case "FOOD":
                c = new Cell(x, y, CellType.FOOD, intensity);
                break;
            case "FOREST":
                c = new Cell(x, y, CellType.FOREST, intensity);
                break;
            case "GRASS":
                c = new Cell(x, y, CellType.GRASS, intensity);
                break;
            case "IMPENETRABLE":
                c = new Cell(x, y, CellType.IMPENETRABLE, intensity);
                break;
            case "LAKE":
                c = new Cell(x, y, CellType.LAKE, intensity);
                break;
            case "ORE":
                c = new Cell(x, y, CellType.ORE, intensity);
                break;
            case "STONE":
                c = new Cell(x, y, CellType.STONE, intensity);
                break;
            case "WET_GRASS":
                c = new Cell(x, y, CellType.WET_GRASS, intensity);
                break;
            default:
                return null;
        }
        return Optional.of(c);
    }
}
