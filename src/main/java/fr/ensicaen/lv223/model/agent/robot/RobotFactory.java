package fr.ensicaen.lv223.model.agent.robot;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.teams.ProjectTeam;
import fr.ensicaen.lv223.teams.copilot.robots.*;
import fr.ensicaen.lv223.teams.jamesbond.robots.*;

public class RobotFactory {
    private final CommandFactory factory;
    private final Planet planet;
<<<<<<< HEAD
    private final RobotMapper robotMapper;

    public RobotFactory(CommandFactory factory, Planet planet, RobotMapper robotMapper) {
        this.factory = factory;
        this.planet = planet;
        this.robotMapper = robotMapper;
=======
    private RobotMapper mapper;

    public RobotFactory(CommandFactory factory, Planet planet, RobotMapper mapper) {
        this.factory = factory;
        this.planet = planet;
        this.mapper = mapper;
>>>>>>> 0533d0dfb62be45863b0ba0ce6fafe5dcc25cc8f
    }

    public Robot createRobot(RobotType type, ProjectTeam team){
        switch (type) {
            case CARTOGRAPHER -> {
                if (team == ProjectTeam.JAMES_BOND)
<<<<<<< HEAD
                    return new CartographerJB(RobotType.CARTOGRAPHER, factory, PlanetInterface.getInstance(planet, robotMapper));
                return new CartographerCopilot(RobotType.CARTOGRAPHER, factory, PlanetInterface.getInstance(planet, robotMapper));
            }
            case FOOD_RETRIEVER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new FoodRetrieverJB(RobotType.FOOD_RETRIEVER, factory, PlanetInterface.getInstance(planet, robotMapper));
                return new FoodRetrieverCopilot(RobotType.FOOD_RETRIEVER, factory, PlanetInterface.getInstance(planet, robotMapper));
            }
            case FARMER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new FarmerJB(RobotType.FARMER, factory, PlanetInterface.getInstance(planet, robotMapper));
                return new FarmerCopilot(RobotType.FARMER, factory, PlanetInterface.getInstance(planet, robotMapper));
            }
            case PIPELINE_BUILDER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new PipelineBuilderJB(RobotType.PIPELINE_BUILDER, factory, PlanetInterface.getInstance(planet, robotMapper));
                return new PipelineBuilderCopilot(RobotType.PIPELINE_BUILDER, factory, PlanetInterface.getInstance(planet, robotMapper));
            }
            case ORE_EXTRACTOR -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new OreExtractorJB(RobotType.ORE_EXTRACTOR, factory, PlanetInterface.getInstance(planet, robotMapper));
                return new OreExtractorCopilot(RobotType.ORE_EXTRACTOR, factory, PlanetInterface.getInstance(planet, robotMapper));
            }
            case CENTRALIZER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new CentralizerJB(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet, robotMapper));
                return new CentralizerCopilot(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet, robotMapper));
=======
                    return new CartographerJB(RobotType.CARTOGRAPHER, factory, PlanetInterface.getInstance(planet), CentralizerJB.getInstance(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet), mapper));
                return new CartographerCopilot(RobotType.CARTOGRAPHER, factory, PlanetInterface.getInstance(planet));
            }
            case FOOD_RETRIEVER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new FoodRetrieverJB(RobotType.FOOD_RETRIEVER, factory, PlanetInterface.getInstance(planet), CentralizerJB.getInstance(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet), mapper));
                return new FoodRetrieverCopilot(RobotType.FOOD_RETRIEVER, factory, PlanetInterface.getInstance(planet));
            }
            case FARMER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new FarmerJB(RobotType.FARMER, factory, PlanetInterface.getInstance(planet), CentralizerJB.getInstance(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet), mapper));
                return new FarmerCopilot(RobotType.FARMER, factory, PlanetInterface.getInstance(planet));
            }
            case PIPELINE_BUILDER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new PipelineBuilderJB(RobotType.PIPELINE_BUILDER, factory, PlanetInterface.getInstance(planet), CentralizerJB.getInstance(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet), mapper));
                return new PipelineBuilderCopilot(RobotType.PIPELINE_BUILDER, factory, PlanetInterface.getInstance(planet));
            }
            case ORE_EXTRACTOR -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return new OreExtractorJB(RobotType.ORE_EXTRACTOR, factory, PlanetInterface.getInstance(planet), CentralizerJB.getInstance(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet), mapper));
                return new OreExtractorCopilot(RobotType.ORE_EXTRACTOR, factory, PlanetInterface.getInstance(planet));
            }
            case CENTRALIZER -> {
                if (team == ProjectTeam.JAMES_BOND)
                    return CentralizerJB.getInstance(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet), mapper);
                return new CentralizerCopilot(RobotType.CENTRALIZER, factory, PlanetInterface.getInstance(planet));
>>>>>>> 0533d0dfb62be45863b0ba0ce6fafe5dcc25cc8f
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }
}
