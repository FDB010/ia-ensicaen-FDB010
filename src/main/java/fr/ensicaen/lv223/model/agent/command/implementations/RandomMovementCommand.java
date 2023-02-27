package fr.ensicaen.lv223.model.agent.command.implementations;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.Random;

public class RandomMovementCommand extends Command {
    public int x;
    public int y;

    public RandomMovementCommand(Robot robot, RobotMapper robotMapper) {
        super(robot, robotMapper);
        Random random = new Random();
        // set x and y to a random value between -1 and 1
        x = random.nextInt(3) - 1;
        y = random.nextInt(3) - 1;
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
