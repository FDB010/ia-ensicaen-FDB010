package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.Centralizer;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.ArrayList;
import java.util.List;

public class CentralizerCopilot extends Centralizer {
    private static CentralizerCopilot instance;
    private CentralizerCopilot(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        super(type, commandFactory, captors);
    }

    @Override
    public boolean isAvailable(Message m) {
        return false;
    }


    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();
        return commands;
    }

    static public CentralizerCopilot getInstance(RobotType type, CommandFactory commandFactory, PlanetInterface captors, RobotMapper mapper){
        if(instance == null){
            instance = new CentralizerCopilot(type, commandFactory, captors);
        }
        return instance;
    }
}
