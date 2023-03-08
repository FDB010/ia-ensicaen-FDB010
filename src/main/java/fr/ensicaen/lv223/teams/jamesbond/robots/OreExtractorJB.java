package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.OreExtractor;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.teams.jamesbond.objectifs.CollectObjectif;

import java.util.ArrayList;
import java.util.List;

public class OreExtractorJB extends OreExtractor implements RobotInterfaceJB {
    private final CentralizerJB centralizer;
    private int oreqtt;
    private int maxOreQtt;

    public OreExtractorJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerJB centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
        this.oreqtt = 0;
        this.maxOreQtt = 1000;
        super.primalObjectif =  new CollectObjectif(this, this.centralizer);

    }

    @Override
    public boolean isAvailable(Message m) {
        return false;
    }

    @Override
    public void updateCentralizerMap() {
        centralizer.updateMap(captors.getSurrounding(this), this);
    }

    @Override
    public Coordinate getPosition() {
        return new Coordinate(super.getPos_x(), super.getPos_y());
    }

    @Override
    public CentralizerJB getCentralizer() {
        return this.centralizer;
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();
        this.refreshCommandList();
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

    public boolean isFull() {
        return oreqtt >= maxOreQtt;
    }

    public boolean addOre(int oreqtt) {
        if(this.oreqtt + oreqtt > maxOreQtt) {
            return false;
        }
        this.oreqtt += oreqtt;
        return true;
    }

    public int getOre() {
        int tmp = this.oreqtt;
        this.oreqtt = 0;
        return tmp;
    }

    public int getMaxQttToExtract() {
        return this.maxOreQtt - this.oreqtt;
    }
}
