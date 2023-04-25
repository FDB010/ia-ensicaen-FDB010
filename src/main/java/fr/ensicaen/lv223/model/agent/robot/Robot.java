package fr.ensicaen.lv223.model.agent.robot;

import fr.ensicaen.lv223.model.agent.Agent;
import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;

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
    private PriorityQueue<Message> priorityQueueMessage;
    private PriorityQueue<Command> priorityQueueCommand;
    /**
     * This objective is the basic objective of the robot.
     */
    private Objectif primalObjectif;
    /**
     * This objective is only temporary and prevail on the primal objective.
     */
    private Objectif temporaryObjectif;

    protected Robot(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        this.type = type;
        this.commandFactory = commandFactory;
        this.captors = captors;
    }

    /**
     * Compute the commands to execute this turn.
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

    private Message getTopMessage(){
        if(priorityQueueMessage.size() != 0){
            return this.priorityQueueMessage.poll();
        }
        return null;
    }

    private Command getTopCommand(){
        if(priorityQueueCommand.size() != 0){
            return this.priorityQueueCommand.peek();
        }
        return null;
    }

    public void resetCommandQueue(){
        this.priorityQueueCommand.clear();
    }
}
