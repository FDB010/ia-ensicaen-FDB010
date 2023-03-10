package fr.ensicaen.lv223.teams.jamesbond.robot;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.AFarmer;

import java.util.List;

public class Farmer extends AFarmer {
    public Farmer(RobotType type) {
        super(type);
    }

    @Override
    public boolean isAvailable( Message m ) {
        return false;
    }

    @Override
    public List<Command> compute() {
        return null;
    }
}
