package fr.ensicaen.lv223.model.agent.robot;

import fr.ensicaen.lv223.model.agent.robot.specials.*;

public class RobotFactory {
    public RobotFactory() {}
    public Robot createRobot(RobotType type){
        switch (type) {
            case CARTOGRAPHER -> {
                return new Cartographer(RobotType.CARTOGRAPHER);
            }
            case FOOD_RETRIEVER -> {
                return new FoodRetriever(RobotType.FOOD_RETRIEVER);
            }
            case FARMER -> {
                return new Farmer(RobotType.FARMER);
            }
            case PIPELINE_BUILDER -> {
                return new PipelineBuilder(RobotType.PIPELINE_BUILDER);
            }
            case ORE_EXTRACTOR -> {
                return new OreExtractor(RobotType.ORE_EXTRACTOR);
            }
            case CENTRALIZER -> {
                return new Centralizer(RobotType.CENTRALIZER);
            }
            default -> {
                return null;
            }
        }
    }
}
