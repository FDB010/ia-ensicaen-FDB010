package fr.ensicaen.lv223.model.agent.robot.specials;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;

public abstract class Centralizer extends Robot {
    private int totalOreCollected;
    private int totalWaterCollected;
    private int totalFoodCollected;

    public Centralizer(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        super(type, commandFactory, captors);
        this.totalOreCollected = 0;
        this.totalWaterCollected = 0;
        this.totalFoodCollected = 0;
    }

    public void addOre(int ore){
        this.totalOreCollected += ore;
        System.out.println("Total ore collected : " + this.totalOreCollected);
    }

    public void addWater(int water){
        this.totalWaterCollected += water;
    }

    public void addFood(int food){
        this.totalFoodCollected += food;
        System.out.println("Total food collected : " + this.totalFoodCollected);
    }
}
