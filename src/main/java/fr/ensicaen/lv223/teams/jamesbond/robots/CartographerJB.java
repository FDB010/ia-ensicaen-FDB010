package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.Cartographer;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.teams.jamesbond.objectifs.ExploreObjectif;

import java.util.ArrayList;
import java.util.List;

public class CartographerJB extends Cartographer implements RobotInterfaceJB {
    /**
     * id possible is -1 or +1
     */
    private static int id = -1;
    private final CentralizerJB centralizer;
    public CartographerJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerJB centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
        this.primalObjectif = new ExploreObjectif(this, centralizer, CartographerJB.id);
        this.temporaryObjectif = null;
        CartographerJB.id += 2;
    }

    @Override
    public boolean isAvailable( Message m ) {
        return false;
    }


    @Override
    public CentralizerJB getCentralizer() {
        return this.centralizer;
    }

    @Override
    public void updateCentralizerMap() {
        centralizer.updateMap(captors.getSurrounding(this), this);
    }

    @Override
    public Coordinate getPosition() {
        return new Coordinate(super.getPos_x(),super.getPos_y());
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();
        this.refreshCommandList();
        commands.add(super.priorityQueueCommand.poll());
        if(super.priorityQueueCommand.isEmpty()){
            this.refreshCommandList();
        }
        commands.add(super.priorityQueueCommand.poll());
        return commands;
    }

    private void refreshCommandList(){
        if(super.priorityQueueCommand.isEmpty()){
            if(this.temporaryObjectif != null){
                super.priorityQueueCommand.addAll(this.temporaryObjectif.generateCommmandList());
            }
            else{
                super.priorityQueueCommand.addAll(this.primalObjectif.generateCommmandList());
            }
        }
    }

}
