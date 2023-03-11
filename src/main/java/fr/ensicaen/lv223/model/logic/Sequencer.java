package fr.ensicaen.lv223.model.logic;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to compute the next state of the game.
 * It is the main class of the game logic.
 */
public class Sequencer {
    /**
     * The planet on which the simulation is played.
     */
    public final Planet planet;
    /**
     * The list of robots on the planet.
     */
    public List<Robot> robots;
    /**
     * The robot mapper used for robot localisation.
     */
    public RobotMapper mapper;
    /**
     * The number of days since the beginning of the simulation.
     */
    private int days;

    public Sequencer(Planet planet) {
        this.planet = planet;
        mapper = new RobotMapper(planet);
        this.robots = mapper.getRobots();
        days = 0;
    }

    /**
     * Compute the next state of the game.
     */
    public void step() {
        // Planet turn
        ArrayList<Command> commands = new ArrayList<>(planet.compute());
        // Robots turn
        // Inutile de les faire compute 2 fois
        // for (Robot robot : robots) {
        //    robot.compute();
        //}
        for (Robot robot : robots) {
            commands.addAll(robot.compute());
        }
        for (Command command: commands) {
            command.apply();
        }
        days++;
    }

    /**
     * Check if the humans have arrived at the planet.
     * @return true if the humans have arrived, false otherwise.
     */
    public boolean hasHumansArrived() {
        return days >= 8 * 365;
    }

    public int getDays() {
        return days;
    }
}
