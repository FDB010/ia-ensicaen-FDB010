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

public class RobotMapper {
    private final HashMap<Robot, Coordinate> robotMap;
    private final Planet planet;

    public RobotMapper(Planet planet) {
        this.planet = planet;
        robotMap = new HashMap<>();

        CommandFactory commandFactory = new CommandFactory(this);

        RobotFactory factory = new RobotFactory(commandFactory, planet);

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

    public Robot getRobot(int x, int y) {
        for (Robot robot : robotMap.keySet()) {
            Coordinate coordinate = robotMap.get(robot);
            if (coordinate.x == x && coordinate.y == y) {
                return robot;
            }
        }
        return null;
    }

    public Coordinate getCoordinate(Robot robot) {
        return robotMap.get(robot);
    }

    public int getHeigth() {
        return planet.getHeight();
    }

    public int getWidth() {
        return planet.getWidth();
    }

    public List<Robot> getRobots() {
        return robotMap.keySet().stream().toList();
    }

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
