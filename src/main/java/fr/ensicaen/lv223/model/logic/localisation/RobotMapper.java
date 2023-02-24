package fr.ensicaen.lv223.model.logic.localisation;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.environment.planet.Planet;

import java.util.HashMap;
import java.util.List;

public class RobotMapper {
    private final HashMap<Robot, Coordinate> robotMap;
    private final Planet planet;

    public RobotMapper(Planet planet) {
        this.planet = planet;
        robotMap = new HashMap<>();
        RobotFactory factory = new RobotFactory();
        Coordinate baseCoord = new Coordinate(planet.getHeight()/2, planet.getWidth()/2);
        robotMap.put(factory.createRobot(RobotType.CENTRALIZER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.ORE_EXTRACTOR), baseCoord);
        robotMap.put(factory.createRobot(RobotType.ORE_EXTRACTOR), baseCoord);
        robotMap.put(factory.createRobot(RobotType.ORE_EXTRACTOR), baseCoord);
        robotMap.put(factory.createRobot(RobotType.CARTOGRAPHER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.CARTOGRAPHER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FOOD_RETRIEVER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FOOD_RETRIEVER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FOOD_RETRIEVER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.PIPELINE_BUILDER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.PIPELINE_BUILDER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.PIPELINE_BUILDER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FARMER), baseCoord);
        robotMap.put(factory.createRobot(RobotType.FARMER), baseCoord);
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

    public Coordinate getCoordiante(Robot robot) {
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
}
