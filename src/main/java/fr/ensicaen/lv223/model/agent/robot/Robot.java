package fr.ensicaen.lv223.model.agent.robot;

import fr.ensicaen.lv223.model.agent.Agent;
import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class Robot implements Agent {
    protected final PlanetInterface captors;
    /**
     * The command factory of the robot. This factory is used to create the commands and interact with the model.
     */
    protected final CommandFactory commandFactory;
    /**
     * The type of the robot. Only temporary and implemented because of a lack of time.
     */
    public final RobotType type;
    protected PriorityQueue<Message> priorityQueueMessage;
    protected PriorityQueue<Command> priorityQueueCommand;
    /**
     * This objective is the basic objective of the robot.
     */
    private Objectif primalObjectif;
    /**
     * This objective is only temporary and prevail on the primal objective.
     */
    private Objectif temporaryObjectif;

    private int pos_x;
    private int pos_y;

    protected Robot(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        this.type = type;
        this.commandFactory = commandFactory;
        this.captors = captors;
        this.priorityQueueMessage = new PriorityQueue<Message>();
        this.priorityQueueCommand = new PriorityQueue<Command>();
    }

    /**
     * Compute the commands to execute this turn.
     *
     * @return the list of commands to execute this turn. This return can't be null.
     */
    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();
        return commands;
    }

    public abstract boolean isAvailable(Message m);

    public void addMessage(Message message) {
        this.priorityQueueMessage.add(message);
    }

    private Message getTopMessage() {
        if (priorityQueueMessage.size() != 0) {
            return this.priorityQueueMessage.poll();
        }
        return null;
    }

    private Command getTopCommand() {
        if (priorityQueueCommand.size() != 0) {
            return this.priorityQueueCommand.peek();
        }
        return null;
    }

    public void resetCommandQueue() {
        this.priorityQueueCommand.clear();
    }

    public CommandFactory getCommandFactory() {
        return this.commandFactory;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void update_position(int offset_x, int offset_y) {
        this.pos_x += offset_x;
        this.pos_y += offset_y;
    }
}
