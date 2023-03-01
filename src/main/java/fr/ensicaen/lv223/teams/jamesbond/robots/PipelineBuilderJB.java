package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.PipelineBuilder;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

public class PipelineBuilderJB extends PipelineBuilder implements RobotInterfaceJB {
    private final CentralizerJB centralizer;
    public PipelineBuilderJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, CentralizerJB centralizer) {
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
            return new Coordinate(super.getPos_x(),super.getPos_y());
        }

    @Override
    public CentralizerJB getCentralizer() {
        return this.centralizer;
    }
}
