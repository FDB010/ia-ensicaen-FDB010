package fr.ensicaen.lv223.model.agent.robot.specials;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;

public class Farmer extends Robot {
    public Farmer(RobotType type) {
        super(type);
    }

    @Override
    public boolean isAvailable( Message m ) {
        return false;
    }

    @Override
    public void compute() {

    }
}
