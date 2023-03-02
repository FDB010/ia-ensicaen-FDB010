package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.command.CommandType;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.message.MessageType;
import fr.ensicaen.lv223.model.agent.robot.specials.Cartographer;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
    public boolean isAvailable(Message m) {
        return false;
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();
        if (!hasDiscovered) {
            commands.addAll(explore());
            if (commands.isEmpty()) {
                centralizer.addMessage(new Message(MessageType.CARTOGRAPHER_OVER, 100));
                centralizer.addWidth(Math.abs(this.getPos_x()));
                centralizer.addHeight(Math.abs(this.getPos_y()));
                hasDiscovered = true;
            }
        }
        return commands;
    }

    private List<Command> explore() {
        List<Command> commands = new ArrayList<>();
        List<Direction> directions = captors.getSurrounding(this).keySet().stream().toList();
        if (id == 0) {
            if (directions.contains(Direction.NORTH)) {
                commands.add(commandFactory.createCommand(this, CommandType.MOVECP, Direction.NORTH));
            } else {
                if (directions.contains(Direction.WEST))
                    commands.add(commandFactory.createCommand(this, CommandType.MOVECP, Direction.WEST));
            }
        } else if (id == 1) {
            // Check if NORTH is available
            if (directions.contains(Direction.SOUTH)) {
                commands.add(commandFactory.createCommand(this, CommandType.MOVECP, Direction.SOUTH));
            } else {
                if (directions.contains(Direction.EAST))
                    commands.add(commandFactory.createCommand(this, CommandType.MOVECP, Direction.EAST));
            }
        }
        return commands;
    }
}
