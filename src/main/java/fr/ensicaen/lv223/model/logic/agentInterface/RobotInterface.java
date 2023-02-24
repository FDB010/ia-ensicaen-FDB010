package fr.ensicaen.lv223.model.logic.agentInterface;

import fr.ensicaen.lv223.model.agent.robot.Robot;

public class RobotInterface implements AgentInterface {
    private final Robot robot;

    public RobotInterface(Robot robot, int x, int y) {
        this.robot = robot;
    }
}
