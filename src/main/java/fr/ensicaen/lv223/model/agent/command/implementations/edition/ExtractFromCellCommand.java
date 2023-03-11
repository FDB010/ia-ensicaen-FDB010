package fr.ensicaen.lv223.model.agent.command.implementations;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.EnvironmentCell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.cells.specials.ExtractableCell;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

/**
 * This command allows a robot to extract a given quantity of a resource from an
 * extractable cell (only FOOD and ORE are impacted).
 */
public class ExtractFromCellCommand extends Command {
    private Planet planet;
    public ExtractFromCellCommand(Planet planet, Robot robot, RobotMapper robotMapper, int value) {
        super(robot, robotMapper, value);
        this.planet = planet;
    }

    @Override
    public void apply() {
        Coordinate coordinate = robotMapper.getCoordinate(robot);
        planet.extract(coordinate, value);

        if (this.robot instanceof OreExtractorJB){
            ((OreExtractorJB) this.robot).addOre(value);
        } else if (this.robot instanceof FoodRetrieverJB) {
            ((FoodRetrieverJB) this.robot).addFood(value);
        }
    }

    @Override
    public void unapply() {
        // TODO
    }
}
