package fr.ensicaen.lv223.util.loader.planetloader;

import fr.ensicaen.lv223.model.environment.cells.CellType;

/**
 * The {@code PlanetData} class contains the data of a planet with its type and
 * cell positions.
 */
public class PlanetData {
    private CellType type;
    private CellPosition[] cellPos;

    public CellType getType() {
        return type;
    }

    public CellPosition[] getCellPos() {
        return cellPos;
    }
}
