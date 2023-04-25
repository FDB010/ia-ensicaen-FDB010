package fr.ensicaen.lv223.util.loader.planetloader;

/**
 * {@code PlanetLoader} is an interface that represents a way to load planet
 * data. Implementations of this interface are responsible for loading a
 * representation of planet data, which can then be used to create a Planet
 * instance.
 */
public interface PlanetLoader {
    /**
     * Loads the planet data from a source.
     * @return An array of {@link PlanetData} instances representing the
     * loaded planet data.
     */
    PlanetData[] load();
}
