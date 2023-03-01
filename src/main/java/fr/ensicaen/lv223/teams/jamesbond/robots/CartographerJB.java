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

public class CartographerJB extends Cartographer implements RobotInterfaceJB{
    private CentralizerJB centralizer;
    public CartographerJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerJB centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
        this.primalObjectif = new ExploreObjectif(this, centralizer);
        this.temporaryObjectif = null;
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
        if(super.priorityQueueCommand.isEmpty()){
            if(this.temporaryObjectif != null){
                System.out.println("Temporary objectif");
                super.priorityQueueCommand.addAll(this.temporaryObjectif.generateCommmandList());
            }
            else{
                System.out.println("Primal objectif");
                super.priorityQueueCommand.addAll(this.primalObjectif.generateCommmandList());}
        }
        commands.add(super.priorityQueueCommand.poll());

        return commands;
    }


}
