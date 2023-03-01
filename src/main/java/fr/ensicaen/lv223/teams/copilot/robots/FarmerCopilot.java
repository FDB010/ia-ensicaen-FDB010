package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.Farmer;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;

import java.util.ArrayList;
import java.util.List;

public class FarmerCopilot extends Farmer {
    private CentralizerCopilot centralizer;
    public FarmerCopilot(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerCopilot centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
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
}
