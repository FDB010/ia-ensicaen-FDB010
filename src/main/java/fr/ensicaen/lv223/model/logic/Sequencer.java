package fr.ensicaen.lv223.model.logic;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.planet.Planet;

import java.util.List;

public class Sequencer {
    public final Planet planet;
    public final List<Robot> robots;

    public Sequencer(Planet planet, List<Robot> robots) {
        this.planet = planet;
        this.robots = robots;
    }

    public void step() {
        //TODO : implement the step
    }
}
