package fr.ensicaen.lv223.model.agent.robot.specials;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.message.Message;

public class OreExtractor extends Robot {
    @Override
    public boolean isAvailable( Message m ) {
        return false;
    }
}
