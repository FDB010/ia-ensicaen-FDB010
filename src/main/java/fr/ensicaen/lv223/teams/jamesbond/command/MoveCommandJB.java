package fr.ensicaen.lv223.teams.jamesbond.command;

import fr.ensicaen.lv223.model.agent.command.implementations.MoveCommand;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.teams.jamesbond.robots.RobotInterfaceJB;

public class MoveCommandJB extends MoveCommand {
    public MoveCommandJB(Robot robot, RobotMapper robotMapper, Direction dir) {
        super(robot, robotMapper, dir);
    }

    @Override
    public void apply() {
        super.apply();
        ((RobotInterfaceJB)robot).updateCentralizerMap();
    }
}
