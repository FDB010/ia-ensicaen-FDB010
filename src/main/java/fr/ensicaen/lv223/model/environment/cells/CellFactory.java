package fr.ensicaen.lv223.model.environment.cells;

import fr.ensicaen.lv223.model.environment.EnvironmentCell;
import fr.ensicaen.lv223.model.environment.cells.specials.*;
import fr.ensicaen.lv223.model.environment.cells.specials.extractable.ExtractableCell;
import fr.ensicaen.lv223.model.environment.cells.specials.extractable.FoodCell;
import fr.ensicaen.lv223.model.environment.cells.specials.extractable.LakeCell;
import fr.ensicaen.lv223.model.environment.cells.specials.extractable.OreCell;

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
    public static Optional<Cell> factory(CellType type, double intensity, int x, int y) {
        switch (type) {
            case BASE -> {
                return Optional.of(new BaseCell(x, y, intensity));
            }
            case DESERT -> {
                return Optional.of(new DesertCell(x, y, intensity));
            }
            case FOOD -> {
                return Optional.of(new FoodCell(x, y, intensity, 1));
            }
            case FOREST -> {
                return Optional.of(new ForestCell(x, y, intensity));
            }
            case IMPENETRABLE -> {
                return Optional.of(new ImpenetrableCell(x, y, intensity));
            }
            case LAKE -> {
                return Optional.of(new LakeCell(x, y, intensity, 1));
            }
            case ORE -> {
                return Optional.of(new OreCell(x, y, intensity, 1));
            }
            case STONE -> {
                return Optional.of(new StoneCell(x, y, intensity));
            }
            case GRASS -> {
                return Optional.of(new GrassCell(x, y, intensity));
            }
            case WET_GRASS -> {
                GrassCell cell = new GrassCell(x, y, intensity);
                cell.setWet();
                return Optional.of(cell);
            }
            case DRY_GRASS -> {
                GrassCell cell = new GrassCell(x, y, intensity);
                cell.setDry();
                return Optional.of(cell);
            }
        }
        return Optional.of(null);
    }

    public static Optional<ExtractableCell> convert(Cell cell) {
        switch (cell.getType()) {
            case FOOD -> {
                return Optional.of(new FoodCell(cell.getX(), cell.getY(), cell.getIntensity(), 1));
            }
            case LAKE -> {
                return Optional.of(new LakeCell(cell.getX(), cell.getY(), cell.getIntensity(), 1));
            }
            case ORE -> {
                return Optional.of(new OreCell(cell.getX(), cell.getY(), cell.getIntensity(), 1));
            }
        }
        return Optional.of(null);

    }

    public static Cell convert(EnvironmentCell cell) {
        return (Cell) cell;
    }
}
