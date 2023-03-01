package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.Centralizer;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;

import java.util.ArrayList;
import java.util.List;

public class CentralizerCopilot extends Centralizer {
    private static CentralizerCopilot instance;
    private List<List<UnknownCell>> cells;
    private RobotMapper mapper;
    private CentralizerCopilot(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        super(type, commandFactory, captors);
    }

    @Override
    public boolean isAvailable(Message m) {
        return false;
    }

    public void setCells(List<List<UnknownCell>> cells){
        this.cells = cells;
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();
        return commands;
    }

    static public CentralizerCopilot getInstance(RobotType type, CommandFactory commandFactory, PlanetInterface captors){
        if(instance == null){
            instance = new CentralizerCopilot(type, commandFactory, captors);
        }
        return instance;
    }
}
