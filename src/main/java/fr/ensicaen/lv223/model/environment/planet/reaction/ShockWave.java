package fr.ensicaen.lv223.model.environment.planet.reaction;

import fr.ensicaen.lv223.model.environment.EnvironmentCell;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.Metamorphosis;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.MetamorphosisFactory;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.util.Util;

import java.util.*;

public class ShockWave {
    private final Queue<List<EnvironmentCell>> steps;
    private final Coordinate coordinate;
    private final int speed;
    private final Planet planet;

    public ShockWave(Planet planet, Coordinate coordinate, int amplitude, int speed) {
        this.coordinate = coordinate;
        this.speed = speed;
        this.planet = planet;
        steps = new LinkedList<>();
        // TODO : create the shockwave steps
        propagateShockWave(planet, amplitude*speed);
    }

    public void update() {
        for (int i = 0; i < speed; i++) {
            if (!steps.isEmpty()) {
                List<EnvironmentCell> cells = steps.remove();
                for (EnvironmentCell cell : cells) {
                    Optional<Metamorphosis> metamorphosis = MetamorphosisFactory.createMetamorphosis(
                            planet.getFuzzyLogic().getMetamorphosisType(),
                            planet,
                            (Cell) cell
                    );
                    metamorphosis.ifPresent(planet::addMetamorphosis);
                }
            }
        }
    }

    public static Optional<ShockWave> createShockWave(Planet planet, Coordinate coord, ExtractionType type) {
        return switch (type) {
            case SMALL -> Optional.of(new ShockWave(planet, coord, 2, 2));
            case MEDIUM -> Optional.of(new ShockWave(planet, coord, 3, 2));
            case GREAT -> Optional.of(new ShockWave(planet, coord, 4, 3));
        };
    }

    public static Optional<ShockWave> createShockWave(Planet planet, Coordinate coord, SamplingType type) {
        return switch (type) {
            case NEGLIGIBLE, SMALL, MEDIUM -> Optional.of(new ShockWave(planet, coord, 1, 1));
            case GREAT -> Optional.of(new ShockWave(planet, coord, 2, 2));
        };
    }

    public boolean isFinished() {
        return steps.isEmpty();
    }

    private void propagateShockWave(Planet planet, int n) {
        List<Coordinate> origin = new ArrayList<>();
        origin.add(coordinate);
        propagateShockWaveRec(planet, n , origin, new ArrayList<>());
    }

    private void propagateShockWaveRec(Planet planet, int n, List<Coordinate> origins, List<Coordinate> visited){
        if (n <= 0)
            return;

        List<EnvironmentCell> step = new ArrayList<>();
        for (Coordinate coord : origins) {
            step.add(planet.getCell(coord));
            if (!visited.contains(coord))
                visited.add(coord);
        }
        steps.add(step);

        List<Coordinate> toVisit = new ArrayList<>();
        for (Coordinate coord : origins) {
            for (Coordinate coordN : Util.getNeighbors(coord, planet.getWidth(), planet.getHeight())) {
                if (!visited.contains(coordN) && !toVisit.contains(coordN))
                    toVisit.add(coordN);
            }
        }

        propagateShockWaveRec(planet, n-1, toVisit, visited);
    }
}
