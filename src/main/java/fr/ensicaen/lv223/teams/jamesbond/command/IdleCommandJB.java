package fr.ensicaen.lv223.teams.jamesbond.command;

import fr.ensicaen.lv223.model.agent.command.implementations.IdleCommand;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

/**
 * Example of a command for the James Bond team.
 */
public class IdleCommandJB extends IdleCommand {
    public IdleCommandJB(Robot robot, RobotMapper robotMapper) {
        super(robot, robotMapper);
    }
}
