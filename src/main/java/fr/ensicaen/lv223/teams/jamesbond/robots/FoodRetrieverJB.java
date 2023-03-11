package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.FoodRetriever;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.teams.jamesbond.objectifs.CollectObjectif;

import java.util.ArrayList;
import java.util.List;

public class FoodRetrieverJB extends FoodRetriever implements RobotInterfaceJB {
    private final CentralizerJB centralizer;
    private int foodqtt;
    private final int maxFoodQtt;

    public FoodRetrieverJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerJB centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
        this.foodqtt = 0;
        this.maxFoodQtt = 1000;
        super.primalObjectif =  new CollectObjectif(this, this.centralizer);
    }

    @Override
    public boolean isAvailable( Message m ) {
        return false;
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
        return foodqtt >= maxFoodQtt;
    }

    public void addFood(int foodqtt) {
        this.foodqtt += foodqtt;
    }

    public int getFood() {
        int tmp = this.foodqtt;
        this.foodqtt = 0;
        return tmp;
    }

    public int getMaxQttToExtract() {
        return this.maxFoodQtt - this.foodqtt;
    }
}
