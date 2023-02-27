package fr.ensicaen.lv223.model.logic.agentInterface;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code RobotInterface} class is an interface that represents a way to
 * interact with the robot. For instance, in the case of the planet the robot interface represent
 * what the planet can see and what it can do about this specific robot.
 */
public class RobotInterface implements AgentInterface {
    private final Robot robot;
    private final RobotMapper robotMapper;

    private static List<RobotInterface> robotInterfaces = new ArrayList<>();

    public static void addRobotInterface(RobotInterface robotInterface) {
        robotInterfaces.add(robotInterface);
    }

    public static List<RobotInterface> getRobotInterfaces(List<Robot> robots) {
        return robotInterfaces;
    }

    public RobotInterface(Robot robot, RobotMapper robotMapper) {
        this.robot = robot;
        this.robotMapper = robotMapper;
    }
}
