package fr.ensicaen.lv223.model.logic.agentInterface;

import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.util.Util;

import java.util.HashMap;
import java.util.List;

/**
 * The {@code PlanetInterface} class is an interface that represents a way to
 * interact with the planet. For instance, in the case of a robot the planet interface represent
 * what the robot can see and what it can do.
 */
public class PlanetInterface implements AgentInterface {
    private static PlanetInterface instance;

    public static PlanetInterface getInstance(Planet planet) {
        if (instance == null) {
            instance = new PlanetInterface(planet);
        }
        return instance;
    }
    private final Planet planet;

    private PlanetInterface(Planet planet) {
        this.planet = planet;
    }

    public HashMap<Direction, CellType> getSurrounding(Coordinate coord) {
        HashMap<Direction, CellType> surrounding = new HashMap<>();
        if (coord.x > 0) { // NORTH
            surrounding.put(Direction.NORTH, planet.getCell(coord.x - 1, coord.y).getType());
            if (coord.y > 0) { // NORTH WEST
                surrounding.put(Direction.NORTH_WEST, planet.getCell(coord.x - 1, coord.y - 1).getType());
            }
            if (coord.y < planet.getHeight() - 1) { // NORTH EAST
                surrounding.put(Direction.NORTH_EAST, planet.getCell(coord.x - 1, coord.y + 1).getType());
            }
        }
        if (coord.x < planet.getHeight() - 1) { // SOUTH
            surrounding.put(Direction.SOUTH, planet.getCell(coord.x + 1, coord.y).getType());
            if (coord.y > 0) { // SOUTH WEST
                surrounding.put(Direction.SOUTH_WEST, planet.getCell(coord.x + 1, coord.y - 1).getType());
            }
            if (coord.y < planet.getWidth() - 1) { // SOUTH EAST
                surrounding.put(Direction.SOUTH_EAST, planet.getCell(coord.x + 1, coord.y + 1).getType());
            }
        }
        if (coord.y > 0) { // WEST
            surrounding.put(Direction.WEST, planet.getCell(coord.x, coord.y - 1).getType());
        }
        if (coord.y < planet.getWidth() - 1) { // EAST
            surrounding.put(Direction.EAST, planet.getCell(coord.x, coord.y + 1).getType());
        }
        return surrounding;
    }
}
