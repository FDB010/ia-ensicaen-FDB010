package fr.ensicaen.lv223.model.logic.agentInterface;

import fr.ensicaen.lv223.model.environment.planet.Planet;

public class PlanetInterface implements AgentInterface {
    private static PlanetInterface instance;

    public static PlanetInterface getInstance(Planet planet) {
        if (instance == null) {
            instance = new PlanetInterface(planet);
        }
        return instance;
    }
    private final Planet planet;

    private PlanetInterface(Planet planet) {
        this.planet = planet;
    }
}
