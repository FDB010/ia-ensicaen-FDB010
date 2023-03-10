package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.PipelineBuilder;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class PipelineBuilderCopilot extends PipelineBuilder {
    private CentralizerCopilot centralizer;
    public PipelineBuilderCopilot(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerCopilot centralizer) {
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
