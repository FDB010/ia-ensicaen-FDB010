package fr.ensicaen.lv223.util.loader.planetloader;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * The {@code JsonLoader} class implements the {@link PlanetLoader} interface
 * and is used for loading planet data from a JSON data source.
 * @see PlanetLoader
 * @see PlanetData
 */
public class JsonLoader implements PlanetLoader {
    private final String dataSource;

    /**
     * Constructs a new {@code JsonLoader} with the specified data source.
     * @param dataSource the path to the JSON data source
     */
    public JsonLoader(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Loads planet data from the specified JSON data source.
     * @return an array of {@link PlanetData} objects
     */
    @Override
    public PlanetData[] load() {
        PlanetData[] loadedData = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            loadedData = mapper.readValue(getClass().getResource(this.dataSource), PlanetData[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedData;
    }
}
