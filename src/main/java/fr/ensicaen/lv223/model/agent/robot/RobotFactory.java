package fr.ensicaen.lv223.model.agent.robot;

import fr.ensicaen.lv223.model.agent.robot.specials.*;

public class RobotFactory {
    public static Robot createRobot(RobotType type){
        switch (type) {
            case CARTOGRAPHER -> {
                return new Cartographer();
            }
            case FOOD_RETRIEVER -> {
                return new FoodRetriever();
            }
            case FARMER -> {
                return new Farmer();
            }
            case PIPELINE_BUILDER -> {
                return new PipelineBuilder();
            }
            case ORE_EXTRACTOR -> {
                return new OreExtractor();
            }
            case CENTRALIZER -> {
                return new Centralizer();
            }
            default -> {
                return null;
            }
        }
    }
}
