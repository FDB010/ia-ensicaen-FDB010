package fr.ensicaen.lv223.model.agent.command.implementations;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

public class IdleCommand extends Command {
    public IdleCommand(Robot robot, RobotMapper robotMapper) {
        super(robot, robotMapper);
    }

    @Override
    public void apply() {
        // idle motion nothing to do
    }

    @Override
    public void unapply() {
        // idle motion nothing to do
    }
}
