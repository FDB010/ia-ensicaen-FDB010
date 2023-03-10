package fr.ensicaen.lv223.model.logic.agentInterface;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
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
    private RobotMapper robotMapper;

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
        if (coord.y > 0) { // NORTH
            surrounding.put(Direction.NORTH, planet.getCell(coord.x, coord.y - 1).getType());
            if (coord.x > 0) { // NORTH WEST
                surrounding.put(Direction.NORTH_WEST, planet.getCell(coord.x - 1, coord.y - 1).getType());
            }
            if (coord.x < planet.getHeight() - 1) { // NORTH EAST
                surrounding.put(Direction.NORTH_EAST, planet.getCell(coord.x + 1, coord.y - 1).getType());
            }
        }
        if (coord.y < planet.getHeight() - 1) { // SOUTH
            surrounding.put(Direction.SOUTH, planet.getCell(coord.x, coord.y + 1).getType());
            if (coord.x > 0) { // SOUTH WEST
                surrounding.put(Direction.SOUTH_WEST, planet.getCell(coord.x - 1, coord.y + 1).getType());
            }
            if (coord.x < planet.getWidth() - 1) { // SOUTH EAST
                surrounding.put(Direction.SOUTH_EAST, planet.getCell(coord.x + 1, coord.y + 1).getType());
            }
        }
        if (coord.x > 0) { // WEST
            surrounding.put(Direction.WEST, planet.getCell(coord.x - 1, coord.y).getType());
        }
        if (coord.x < planet.getWidth() - 1) { // EAST
            surrounding.put(Direction.EAST, planet.getCell(coord.x + 1, coord.y).getType());
        }
        return surrounding;
    }
}
