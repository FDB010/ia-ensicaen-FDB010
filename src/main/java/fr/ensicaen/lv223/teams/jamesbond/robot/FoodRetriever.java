package fr.ensicaen.lv223.teams.jamesbond.robot;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.AFoodRetriever;

import java.util.List;

public class FoodRetriever extends AFoodRetriever {
    public FoodRetriever(RobotType type) {
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
