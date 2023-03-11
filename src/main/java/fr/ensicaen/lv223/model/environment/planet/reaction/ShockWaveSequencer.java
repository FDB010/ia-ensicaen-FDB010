package fr.ensicaen.lv223.model.environment.planet.reaction;

import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ShockWaveSequencer {
    private Planet planet;
    private Queue<ShockWave> shockwaves;

    public ShockWaveSequencer(Planet planet) {
        this.planet = planet;
        shockwaves = new LinkedList<>();
    }

    public void createShockWave(int x, int y, ExtractionType type) {
        shockwaves.add(ShockWave.createShockWave(planet, new Coordinate(x, y), type).get());
    }

    public void createShockWave(int x, int y, SamplingType type) {
        shockwaves.add(ShockWave.createShockWave(planet, new Coordinate(x, y), type).get());
    }

    public void updateShockWaves() {
        Queue<ShockWave> newShockwaves = new LinkedList<>();
        while (!shockwaves.isEmpty()) {
            ShockWave shockwave = shockwaves.remove();
            shockwave.update();
            if (!shockwave.isFinished()) {
                newShockwaves.add(shockwave);
            }
        }
        shockwaves = newShockwaves;
    }
}
