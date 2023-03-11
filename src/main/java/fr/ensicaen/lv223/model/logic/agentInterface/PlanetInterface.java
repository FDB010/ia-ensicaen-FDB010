package fr.ensicaen.lv223.model.logic.agentInterface;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.HashMap;

/**
 * The {@code PlanetInterface} class is an interface that represents a way to
 * interact with the planet. For instance, in the case of a robot the planet interface represent
 * what the robot can see and what it can do.
 */
public class PlanetInterface implements AgentInterface {
    private static PlanetInterface instance;
    private final RobotMapper robotMapper;

    public static PlanetInterface getInstance(Planet planet, RobotMapper robotMapper) {
        if (instance == null) {
            instance = new PlanetInterface(planet, robotMapper);
        }
        return instance;
    }
    private final Planet planet;

    private PlanetInterface(Planet planet, RobotMapper robotMapper) {
        this.planet = planet;
        this.robotMapper = robotMapper;
    }

    public CellType getCellType(Robot robot) {
        return planet.getCell(robotMapper.getCoordinate(robot)).getType();
    }

    public HashMap<Direction, CellType> getSurrounding(Robot robot) throws IllegalArgumentException {
        if (!robotMapper.getRobots().contains(robot)) {
            throw new IllegalArgumentException("This robot does not exist in the simulation");
        }
        Coordinate coord = robotMapper.getCoordinate(robot);
        HashMap<Direction, CellType> surrounding = new HashMap<>();
        if (coord.getX() > 0) { // NORTH
            surrounding.put(Direction.NORTH, planet.getCell(coord.getX() - 1, coord.getY()).getType());
            if (coord.getY() > 0) { // NORTH WEST
                surrounding.put(Direction.NORTH_WEST, planet.getCell(coord.getX() - 1, coord.getY() - 1).getType());
            }
            if (coord.getY() < planet.getHeight() - 1) { // NORTH EAST
                surrounding.put(Direction.NORTH_EAST, planet.getCell(coord.getX() - 1, coord.getY() + 1).getType());
            }
        }
        if (coord.getX() < planet.getHeight() - 1) { // SOUTH
            surrounding.put(Direction.SOUTH, planet.getCell(coord.getX() + 1, coord.getY()).getType());
            if (coord.getY() > 0) { // SOUTH WEST
                surrounding.put(Direction.SOUTH_WEST, planet.getCell(coord.getX() + 1, coord.getY() - 1).getType());
            }
            if (coord.getY() < planet.getWidth() - 1) { // SOUTH EAST
                surrounding.put(Direction.SOUTH_EAST, planet.getCell(coord.getX() + 1, coord.getY() + 1).getType());
            }
        }
        if (coord.getY() > 0) { // WEST
            surrounding.put(Direction.WEST, planet.getCell(coord.getX(), coord.getY() - 1).getType());
        }
        if (coord.getY() < planet.getWidth() - 1) { // EAST
            surrounding.put(Direction.EAST, planet.getCell(coord.getX(), coord.getY() + 1).getType());
        }
        return surrounding;
    }
}
