package fr.ensicaen.lv223.model.agent.command;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

public abstract class Command {
    protected Robot robot;
    protected RobotMapper robotMapper;
    protected int value;

    public Command(Robot robot, RobotMapper robotMapper, int value) {
        this.robot = robot;
        this.robotMapper = robotMapper;
        this.value = value;
    }

    public abstract void apply();
    public abstract void unapply();
}
