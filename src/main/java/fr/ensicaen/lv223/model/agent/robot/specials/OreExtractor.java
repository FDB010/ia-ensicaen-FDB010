package fr.ensicaen.lv223.model.agent.robot.specials;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.command.CommandType;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;

import java.util.List;

public abstract class OreExtractor extends Robot {
    public OreExtractor(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        super(type, commandFactory, captors);
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = super.compute();
        commands.add(commandFactory.createCommand(this, CommandType.MOVE));
        return commands;
    }
}
