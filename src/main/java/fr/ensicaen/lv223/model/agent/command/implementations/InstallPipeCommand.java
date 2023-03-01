package fr.ensicaen.lv223.model.agent.command.implementations;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.construction.WaterPipe;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

public class InstallPipeCommand extends Command {
    private Planet planet;
    private WaterPipe pipe;

    public InstallPipeCommand(Robot robot, RobotMapper robotMapper, int value, Planet planet) {
        super(robot, robotMapper, value);
        this.planet = planet;
        Coordinate coord = robotMapper.getCoordinate(robot);
        pipe = WaterPipe.createWaterPipe(coord.x, coord.y);
    }

    @Override
    public void apply() {
        planet.addPipe(pipe);
    }

    @Override
    public void unapply() {
        planet.removePipe(pipe);
    }
}
