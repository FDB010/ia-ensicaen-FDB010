package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.OreExtractor;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.util.loader.planetloader.CellPosition;

import java.util.ArrayList;
import java.util.List;



public class OreExtractorCopilot extends OreExtractor {
    private CentralizerCopilot centralizer;
    public OreExtractorCopilot(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerCopilot centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
    }

    @Override
    public boolean isAvailable(Message m) {
        return false;
    }


    public void updateCentralizerMap() {
        centralizer.updateMap(captors.getSurrounding(this), (RobotInterfaceCopilot) this);
    }


    public Coordinate getPosition() {
        return centralizer.getMapper().getCoordinate(this);
    }

    @Override
    public List<Command> compute() {
        List<Command> commands = new ArrayList<>();

        return commands;
    }


}
