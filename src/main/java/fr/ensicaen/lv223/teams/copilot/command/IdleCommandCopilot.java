package fr.ensicaen.lv223.teams.copilot.command;

import fr.ensicaen.lv223.model.agent.command.implementations.IdleCommand;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

/**
 * Example of a command for the copilot team.
 */
public class IdleCommandCopilot extends IdleCommand {
    public IdleCommandCopilot(Robot robot, RobotMapper robotMapper) {
        super(robot, robotMapper);
    }
}
