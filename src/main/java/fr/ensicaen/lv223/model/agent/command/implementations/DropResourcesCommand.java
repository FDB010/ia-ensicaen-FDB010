package fr.ensicaen.lv223.model.agent.command.implementations;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.specials.Centralizer;
import fr.ensicaen.lv223.model.environment.construction.WaterPipe;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

public class DropResourcesCommand extends Command {
    private Robot robot;
    private int oreQtt;
    private int foodQtt;
    private Centralizer centralizer;

    public DropResourcesCommand(Robot robot, RobotMapper robotMapper, Centralizer centralizer) {
        super(robot, robotMapper, 0);
        this.robot = robot;
        this.oreQtt = robot.getOre();
        this.foodQtt = robot.getFood();
        this.centralizer = centralizer;
    }

    @Override
    public void apply() {
        this.centralizer.addOre(this.oreQtt);
        robot.clearOreQtt();
        this.centralizer.addFood(this.foodQtt);
        robot.clearFoodQtt();
    }

    @Override
    public void unapply() {
        robot.addOre(this.oreQtt);
        robot.addFood(this.foodQtt);
        this.centralizer.addOre(-this.oreQtt);
        this.centralizer.addFood(-this.foodQtt);
    }
}

