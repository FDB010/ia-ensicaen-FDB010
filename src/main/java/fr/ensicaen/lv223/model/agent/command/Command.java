package fr.ensicaen.lv223.model.agent.command;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

public abstract class Command {
    protected Robot robot;
    protected RobotMapper robotMapper;

    public Command(Robot robot, RobotMapper robotMapper) {
        this.robot = robot;
        this.robotMapper = robotMapper;
    }

    public abstract void apply();
    public abstract void unapply();
}
