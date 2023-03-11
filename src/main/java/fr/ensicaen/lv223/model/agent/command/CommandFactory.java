package fr.ensicaen.lv223.model.agent.command;

import fr.ensicaen.lv223.model.agent.command.implementations.DropResourcesCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.IdleCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.edition.ExtractFromCellCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.edition.InstallPipeCommand;
import fr.ensicaen.lv223.model.agent.command.implementations.movement.RandomMovementCommand;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.specials.Centralizer;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.teams.jamesbond.command.MoveCommandJB;

import java.util.Objects;

/**
 * This is an example of a factory class. It is used to create commands.
 * Please extend this class to create your own commands.
 */
public class CommandFactory {
    /**
     * The robot mapper used to localise robots.
     */
    private final RobotMapper robotMapper;
    /**
     * The planet where the robots are.
     */
    private final Planet planet;

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
        return switch (type) {
            case INSTALL_PIPE -> new InstallPipeCommand(robot, robotMapper, value, planet);
            case EXTRACT -> new ExtractFromCellCommand(planet, robot, robotMapper, value);
            case MOVE -> new RandomMovementCommand(robot, robotMapper, value);
            case IDLE, default -> new IdleCommand(robot, robotMapper);
        };
    }

    /**
     *  Create a command.
     * @param robot The robot that will execute the command.
     * @param type The type of the command.
     * @param value The value of the command.
     * @return The command.
     */
    public Command createCommand(Robot robot, CommandType type, Direction value) {
        if (Objects.requireNonNull(type) == CommandType.MOVEJB) {
            return new MoveCommandJB(robot, robotMapper, value);
        }
        return new IdleCommand(robot, robotMapper);
    }

    public Command createCommand(Robot robot, CommandType type, Centralizer centralizer) {
        switch (type) {
            case DROP_RESOURCES:
                return new DropResourcesCommand(robot, robotMapper, centralizer);
            default:
                return new IdleCommand(robot, robotMapper);
        }
    }
}
