package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.command.CommandType;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.Cartographer;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;

import java.util.ArrayList;
import java.util.List;

public class CartographerCopilot extends Cartographer {
    private boolean hasDiscovered = false;
    private CentralizerCopilot centralizer;
    int id;
    public CartographerCopilot(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerCopilot centralizer, int id) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
        this.id = id;
    }

    @Override
    public boolean isAvailable( Message m ) {
        return false;
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();
        if (!hasDiscovered) {
            if (id == 0) {
                commands.add(commandFactory.createMoveCommand(this, Direction.EAST));
            }
            else if (id == 1) {
                commands.add(commandFactory.createMoveCommand(this, Direction.WEST));
            }
        }
        return commands;
    }
}
