package fr.ensicaen.lv223.model.logic;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.ArrayList;
import java.util.List;

public class Sequencer {
    public final Planet planet;
    public List<Robot> robots;
    public RobotMapper mapper;

    public Sequencer(Planet planet) {
        this.planet = planet;
        mapper = new RobotMapper(planet);
        this.robots = mapper.getRobots();
    }

    public void step() {
        ArrayList<Command> commands = new ArrayList<>();
        for (Robot robot : robots) {
            robot.compute();
        }
        for (Robot robot : robots) {
            commands.addAll(robot.compute());
        }
        for (Command command: commands) {
            command.apply();
        }
    }
}
