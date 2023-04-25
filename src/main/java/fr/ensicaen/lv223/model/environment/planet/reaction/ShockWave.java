package fr.ensicaen.lv223.model.environment.planet.reaction;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import javafx.scene.input.ScrollEvent;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class ShockWave {
    private Stack<List<Cell>> steps;
    private int amplitude;
    private int speed;

    public ShockWave(Planet planet, Coordinate coordinate, int amplitude, int speed) {
        this.amplitude = amplitude;
        this.speed = speed;
        steps = new Stack<>();
        // TODO : create the shockwave steps
    }

    public static Optional<ShockWave> createShockWave(Planet planet, Coordinate coord, ExtractionType type) {
        switch (type) {
            case SMALL:
                return Optional.of(new ShockWave(planet, coord, 2, 2));
            case MEDIUM:
                return Optional.of(new ShockWave(planet, coord, 3, 2));
            case GREAT:
                return Optional.of(new ShockWave(planet, coord, 4, 3));
        }
        return Optional.empty();
    }

    public static Optional<ShockWave> createShockWave(Planet planet, Coordinate coord, SamplingType type) {
        switch (type) {
            case NEGLIGIBLE:
            case SMALL:
            case MEDIUM:
                return Optional.of(new ShockWave(planet, coord, 1, 1));
            case GREAT:
                return Optional.of(new ShockWave(planet, coord, 2, 2));
        }
        return Optional.empty();
    }

    public boolean isFinished() {
        return steps.isEmpty();
    }
}
