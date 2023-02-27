package fr.ensicaen.lv223.model.environment.planet;

import fr.ensicaen.lv223.model.environment.Environment;
import fr.ensicaen.lv223.model.environment.EnvironmentCell;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import fr.ensicaen.lv223.util.loader.planetloader.JsonLoader;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetData;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetLoader;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code Planet} class implements the {@link Environment} interface and
 * represents a 2-dimensional grid of cells in our simulation.
 * The planet is created using data loaded from a JSON file, which is passed
 * to a {@link PlanetLoader} object. This data is then processed to create a
 * two-dimensional grid of cells using the {@link CellFactory} class. The grid
 * is stored as a list of lists of {@link Cell} objects.
 * The age of the planet since the arrival of the colony can be accessed,
 * such as the width and height of the grid. Additionally, individual cells can
 * be retrieved or set using a {@link fr.ensicaen.lv223.model.logic.localisation.Coordinate} object to specify their
 * position in the grid.
 */
public class Planet implements Environment {
    private final List<List<Cell>> cells;
    private int ageSinceTheArrivalOfTheColony;

    public Planet() {
        PlanetLoader planetLoader = new JsonLoader("/json/planet.json");
        PlanetData[] planetData = planetLoader.load();

        ageSinceTheArrivalOfTheColony = 0;

        cells = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < 21; j++) {
                Optional<Cell> o = CellFactory.factory("IMPENETRABLE", -1, i,
                        j);
                cells.get(i).add(o.get());
            }
        }

        for (PlanetData planetDatum : planetData) {
            for (int j = 0; j < planetDatum.getCellPos().length; j++) {
                int x = planetDatum.getCellPos()[j].getX();
                int y = planetDatum.getCellPos()[j].getY();
                Optional<Cell> o = CellFactory.factory(planetDatum.getType(),
                        -1,
                        planetDatum.getCellPos()[j].getX(),
                        planetDatum.getCellPos()[j].getY());

                cells.get(x).set(y, o.get());
            }
        }
    }

    @Override
    public List<List<Cell>> getCells() {
        return cells;
    }

    public int getAgeSinceTheArrivalOfTheColony() {
        return ageSinceTheArrivalOfTheColony;
    }

    @Override
    public int getWidth() {
        return cells.size();
    }

    @Override
    public int getHeight() {
        return cells.get(0).size();
    }

    @Override
    public EnvironmentCell getCell(Coordinate coord) {
        return cells
                .get(coord.x)
                .get(coord.y);
    }

    @Override
    public EnvironmentCell getCell(int x, int y) {
        return cells
                .get(x)
                .get(y);
    }

    @Override
    public void setCell(Coordinate c, EnvironmentCell cell) {
        this.getCells()
                .get(c.x)
                .set(c.y, (Cell) cell);
    }
}
