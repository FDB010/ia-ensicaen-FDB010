package fr.ensicaen.lv223.model.agent.command.implementations;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.Random;

public class RandomMovementCommand extends Command {
    public int x;
    public int y;

    public RandomMovementCommand(Robot robot, RobotMapper robotMapper, int value) {
        super(robot, robotMapper, value);
        Random random = new Random();
        // set x and y to a random value between -value and value
        x = random.nextInt(value * 2 + 1) - value;
        y = random.nextInt(value * 2 + 1) - value;
    }

    @Override
    public void apply() {
        robotMapper.moveRobot(robot, x, y);
    }

    @Override
    public void unapply() {
        robotMapper.moveRobot(robot, -x, -y);
    }
}
