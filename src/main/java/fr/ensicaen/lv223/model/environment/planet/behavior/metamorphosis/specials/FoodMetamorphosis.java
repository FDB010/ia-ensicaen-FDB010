package fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.specials;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.Metamorphosis;

public class FoodMetamorphosis extends Metamorphosis {
    public FoodMetamorphosis(Cell affectedCell, Planet planet) {
        super(affectedCell, planet);
    }

    @Override
    public void transform() {
        int randomInt = getRandomInt();
        if (randomInt < 10) {
            if (getRandBool()) {
                setNewCell(CellType.FOREST);
            } else {
                setNewCell(CellType.DRY_GRASS);
            }
        } else if (randomInt < 30) {
            setNewCell(CellType.GRASS);
        } else if (randomInt < 50) {
            setNewCell(CellType.WET_GRASS);
        }
    }
}
