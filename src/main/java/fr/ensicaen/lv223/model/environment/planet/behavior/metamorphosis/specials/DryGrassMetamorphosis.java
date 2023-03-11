package fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.specials;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.Metamorphosis;

public class DryGrassMetamorphosis extends Metamorphosis {
    public DryGrassMetamorphosis(Cell affectedCell, Planet planet) {
        super(affectedCell, planet);
    }

    @Override
    public void transform() {
        int randomInt = getRandomInt();
        if (randomInt < 19) {
            setNewCell(CellType.FOOD);
        } else if (randomInt < 80) {
            setNewCell(CellType.DESERT);
        }
    }
}
