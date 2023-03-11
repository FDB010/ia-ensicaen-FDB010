package fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.specials;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.Metamorphosis;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

import java.util.Random;

public class DesertMetamorphosis extends Metamorphosis {
    public DesertMetamorphosis(Cell affectedCell, Planet planet) {
        super(affectedCell, planet);
    }

    @Override
    public void transform() {
        if (getRandomInt() <= 65) {
            setNewCell(CellType.DRY_GRASS);
        }
    }
}
