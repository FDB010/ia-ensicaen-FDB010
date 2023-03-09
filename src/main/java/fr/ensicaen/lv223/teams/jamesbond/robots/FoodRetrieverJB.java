package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.FoodRetriever;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

public class FoodRetrieverJB extends FoodRetriever implements RobotInterfaceJB {
    private final CentralizerJB centralizer;
    private int foodqtt;
    private int maxFoodQtt;
    public FoodRetrieverJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerJB centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
        this.foodqtt = 0;
        this.maxFoodQtt = 1000;
    }
    @Override
    public CentralizerJB getCentralizer() {
        return this.centralizer;
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
