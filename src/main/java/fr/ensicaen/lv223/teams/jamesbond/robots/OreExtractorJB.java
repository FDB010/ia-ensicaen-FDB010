package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.OreExtractor;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

public class OreExtractorJB extends OreExtractor implements RobotInterfaceJB{
    private final CentralizerJB centralizer;
    public OreExtractorJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerJB centralizer) {
        super(type, commandFactory, captors);
        this.centralizer = centralizer;
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
        return new Coordinate(12,12);
    }
    @Override
    public CentralizerJB getCentralizer() {
        return this.centralizer;
    }
}
