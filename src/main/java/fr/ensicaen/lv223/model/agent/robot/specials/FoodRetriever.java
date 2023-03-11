package fr.ensicaen.lv223.model.agent.robot.specials;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.command.CommandType;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;

import java.util.List;

public abstract class FoodRetriever extends Robot {
    public FoodRetriever(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        super(type, commandFactory, captors);
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = super.compute();
        if (captors.getCellType(this) == CellType.FOOD) {
            commands.add(commandFactory.createCommand(this, CommandType.EXTRACT, 1));
        }
        commands.add(commandFactory.createCommand(this, CommandType.MOVE, 1));
        return commands;
    }

}
