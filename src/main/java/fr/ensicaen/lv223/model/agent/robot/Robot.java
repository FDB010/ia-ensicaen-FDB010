package fr.ensicaen.lv223.model.agent.robot;

import fr.ensicaen.lv223.model.agent.Agent;
import fr.ensicaen.lv223.model.agent.robot.command.Command;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.environment.cells.Cell;

import java.util.List;
import java.util.PriorityQueue;

public abstract class Robot implements Agent {
    public final RobotType type;
    private PriorityQueue<Message> priorityQueueMessage;
    private PriorityQueue<Command> priorityQueueCommand;
    private Objectif primalObjectif;
    //Objectif temporaire prioritaire sur l'objectif principal
    private Objectif temporaryObjectif;

    protected Robot(RobotType type) {
        this.type = type;
    }

    @Override
    public void compute() {

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
