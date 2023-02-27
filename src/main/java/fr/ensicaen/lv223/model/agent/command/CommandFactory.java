package fr.ensicaen.lv223.model.agent.command;

import fr.ensicaen.lv223.model.agent.command.implementations.IdleCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.RandomMovementCommand;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

/**
 * This is an example of a factory class. It is used to create commands.
 * Please extend this class to create your own commands.
 */
public class CommandFactory {
    private RobotMapper robotMapper;

    public CommandFactory(RobotMapper robotMapper) {
        this.robotMapper = robotMapper;
    }

    public Command createCommand(Robot robot, CommandType type) {
        switch (type) {
            case MOVE:
                return new RandomMovementCommand(robot, robotMapper);
            default:
                return new IdleCommand(robot, robotMapper);
        }
    }
}
