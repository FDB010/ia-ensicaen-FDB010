package fr.ensicaen.lv223.model.agent.command;

import fr.ensicaen.lv223.model.agent.command.implementations.ExtractFromCellCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.IdleCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.InstallPipeCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.RandomMovementCommand;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

/**
 * This is an example of a factory class. It is used to create commands.
 * Please extend this class to create your own commands.
 */
public class CommandFactory {
    /**
     * The robot mapper used to localise robots.
     */
    private RobotMapper robotMapper;
    /**
     * The planet where the robots are.
     */
    private Planet planet;

    public CommandFactory(Planet planet, RobotMapper robotMapper) {
        this.robotMapper = robotMapper;
        this.planet = planet;
    }

    /**
     * Create a command.
     * @param robot The robot that will execute the command.
     * @param type The type of the command.
     * @param value The value of the command.
     * @return The command.
     */
    public Command createCommand(Robot robot, CommandType type, int value) {
        switch (type) {
            case INSTALL_PIPE:
                return new InstallPipeCommand(robot, robotMapper, value, planet);
            case EXTRACT:
                return new ExtractFromCellCommand(planet, robot, robotMapper, value);
            case MOVE:
                return new RandomMovementCommand(robot, robotMapper, value);
            case IDLE:
            default:
                return new IdleCommand(robot, robotMapper);
        }
    }
}
