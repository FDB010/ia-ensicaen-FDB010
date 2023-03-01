package fr.ensicaen.lv223.model.logic.localisation;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.agentInterface.RobotInterface;
import fr.ensicaen.lv223.teams.ProjectTeam;

import java.util.HashMap;
import java.util.List;

/**
 * This class is used to map robots to coordinates on the planet.
 * It also provides methods to move robots.
 */
public class RobotMapper {
    /**
     * The map of robots to coordinates.
     */
    private final HashMap<Robot, Coordinate> robotMap;
    /**
     * The planet on which the robots are.
     */
    private final Planet planet;

    public RobotMapper(Planet planet) {
        this.planet = planet;
        robotMap = new HashMap<>();

        CommandFactory commandFactory = new CommandFactory(planet, this);

        RobotFactory factory = new RobotFactory(commandFactory, planet, this);

        Coordinate baseCoord = new Coordinate(planet.getHeight()/2, planet.getWidth()/2);
        ProjectTeam currentTeam = ProjectTeam.JAMES_BOND;
        robotMap.put(factory.createRobot(RobotType.CENTRALIZER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.ORE_EXTRACTOR, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.ORE_EXTRACTOR, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.ORE_EXTRACTOR, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.CARTOGRAPHER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.CARTOGRAPHER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FOOD_RETRIEVER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FOOD_RETRIEVER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FOOD_RETRIEVER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.PIPELINE_BUILDER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.PIPELINE_BUILDER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.PIPELINE_BUILDER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FARMER, currentTeam), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FARMER, currentTeam), baseCoord);

        for (Robot robot : robotMap.keySet()) {
            RobotInterface robotInterface = new RobotInterface(robot, this);
            RobotInterface.addRobotInterface(robotInterface);
        }
    }

    /**
     * Get the robot at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return The robot at the given coordinates, or null if there is no robot at the given coordinates.
     */
    public Robot getRobot(int x, int y) {
        for (Robot robot : robotMap.keySet()) {
            Coordinate coordinate = robotMap.get(robot);
            if (coordinate.x == x && coordinate.y == y) {
                return robot;
            }
        }
        return null;
    }

    /**
     * Get the coordinate of the given robot.
     * @param robot The robot.
     * @return The coordinate of the given robot.
     */
    public Coordinate getCoordinate(Robot robot) {
        return robotMap.get(robot);
    }

    /**
     * Get the height of the planet on which the robots are.
     * @return The height of the planet.
     */
    public int getHeigth() {
        return planet.getHeight();
    }

    /**
     * Get the width of the planet on which the robots are.
     * @return The width of the planet.
     */
    public int getWidth() {
        return planet.getWidth();
    }

    /**
     * Get the list of all the robots.
     * @return The list of robots.
     */
    public List<Robot> getRobots() {
        return robotMap.keySet().stream().toList();
    }

    /**
     * Move the given robot by the given offset.
     * @param robot The robot to move.
     * @param offsetX The offset on the x axis.
     * @param offsetY The offset on the y axis.
     * @return True if the robot has been moved, false otherwise.
     */
    public boolean moveRobot(Robot robot, int offsetX, int offsetY) {
        Coordinate coordinate = robotMap.get(robot);
        Coordinate newCoordinate = new Coordinate(coordinate.x + offsetX, coordinate.y + offsetY);
        if (newCoordinate.x < 0 || newCoordinate.x >= planet.getHeight() || newCoordinate.y < 0 || newCoordinate.y >= planet.getWidth()) {
            return false;
        }
        robotMap.replace(robot, newCoordinate);
        return true;
    }
}
