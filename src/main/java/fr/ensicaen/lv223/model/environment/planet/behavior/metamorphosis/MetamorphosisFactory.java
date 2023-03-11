package fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.specials.*;

import java.util.Optional;
import java.util.Random;

public class MetamorphosisFactory {
    public static Optional<Metamorphosis> createMetamorphosis(MetamorphosisType type, Planet planet, Cell cell) {
        // if probability is not reached, return empty
        Random random = new Random();
        if (!cell.isExoskeleton() && random.nextInt(MetamorphosisType.MAX_CHANCE + 1) < type.chanceOfRealization) {
            switch (cell.getType()) {
                case FOREST:
                    return Optional.of(new ForestMetamorphosis(cell, planet));
                case DRY_GRASS:
                    return Optional.of(new DryGrassMetamorphosis(cell, planet));
                case GRASS:
                    return Optional.of(new GrassMetamorphosis(cell, planet));
                case WET_GRASS:
                    return Optional.of(new WetGrassMetamorphosis(cell, planet));
                case DESERT:
                    return Optional.of(new DesertMetamorphosis(cell, planet));
                case FOOD:
                    return Optional.of(new FoodMetamorphosis(cell, planet));
            }
        }
        return Optional.empty();
    }
}
