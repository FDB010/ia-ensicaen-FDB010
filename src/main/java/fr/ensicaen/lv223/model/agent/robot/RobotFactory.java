package fr.ensicaen.lv223.model.agent.robot;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.teams.ProjectTeam;
import fr.ensicaen.lv223.teams.copilot.robots.*;
import fr.ensicaen.lv223.teams.jamesbond.robots.*;

public class RobotFactory {
    private final CommandFactory factory;
    private final Planet planet;

    public RobotFactory(CommandFactory factory, Planet planet) {
        this.factory = factory;
        this.planet = planet;
    }

    public Robot createRobot(RobotType type, ProjectTeam team){
        switch (type) {
            case CARTOGRAPHER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new CartographerJB(RobotType.CARTOGRAPHER, factory, PlanetInterface.getInstance(planet));
                return new CartographerCopilot(RobotType.CARTOGRAPHER, factory, PlanetInterface.getInstance(planet));
            }
            case FOOD_RETRIEVER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new FoodRetrieverJB(RobotType.FOOD_RETRIEVER, factory, PlanetInterface.getInstance(planet));
                return new FoodRetrieverCopilot(RobotType.FOOD_RETRIEVER, factory, PlanetInterface.getInstance(planet));
            }
            case FARMER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new FarmerJB(RobotType.FARMER, factory, PlanetInterface.getInstance(planet));
                return new FarmerCopilot(RobotType.FARMER, factory, PlanetInterface.getInstance(planet));
            }
            case PIPELINE_BUILDER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new PipelineBuilderJB(RobotType.PIPELINE_BUILDER, factory, PlanetInterface.getInstance(planet));
                return new PipelineBuilderCopilot(RobotType.PIPELINE_BUILDER, factory, PlanetInterface.getInstance(planet));
            }
            case ORE_EXTRACTOR -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new OreExtractorJB(RobotType.ORE_EXTRACTOR, factory, PlanetInterface.getInstance(planet));
                return new OreExtractorCopilot(RobotType.ORE_EXTRACTOR, factory, PlanetInterface.getInstance(planet));
            }
            case CENTRALIZER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new CentralizerJB(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet));
                return new CentralizerCopilot(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet));
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }
}
