package fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.specials;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.Metamorphosis;

public class WetGrassMetamorphosis extends Metamorphosis {
    public WetGrassMetamorphosis(Cell affectedCell, Planet planet) {
        super(affectedCell, planet);
    }

    @Override
    public void transform() {
        int randomInt = getRandomInt();
        if (randomInt < 5) {
            setNewCell(CellType.DESERT);
        } else if (randomInt < 25) {
            setNewCell(CellType.FOOD);
        } else if (randomInt < 30) {
            setNewCell(CellType.DRY_GRASS);
        } else if (randomInt < 40) {
            setNewCell(CellType.GRASS);
        }
    }
}
