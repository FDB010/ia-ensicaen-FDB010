package fr.ensicaen.lv223.model.environment.planet;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.environment.Environment;
import fr.ensicaen.lv223.model.environment.EnvironmentCell;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.cells.specials.extractable.ExtractableCell;
import fr.ensicaen.lv223.model.environment.planet.behavior.EnvironmentAgent;
import fr.ensicaen.lv223.model.environment.planet.behavior.FuzzyLogic;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.Metamorphosis;
import fr.ensicaen.lv223.model.environment.planet.reaction.ShockWaveSequencer;
import fr.ensicaen.lv223.model.environment.planet.state.PlanetEmotion;
import fr.ensicaen.lv223.model.environment.planet.state.PlanetHealthStatus;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.util.loader.planetloader.JsonLoader;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetData;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.ensicaen.lv223.util.Map.MAP_HEIGHT;
import static fr.ensicaen.lv223.util.Map.MAP_WIDTH;

/**
 * The {@code Planet} class implements the {@link Environment} interface and
 * represents a 2-dimensional grid of cells in our simulation.
 * The planet is created using data loaded from a JSON file, which is passed
 * to a {@link PlanetLoader} object. This data is then processed to create a
 * two-dimensional grid of cells using the {@link CellFactory} class. The grid
 * is stored as a list of lists of {@link Cell} objects.
 * The age of the planet since the arrival of the colony can be accessed,
 * such as the width and height of the grid. Additionally, individual cells can
 * be retrieved or set using a {@link Coordinate} object to specify their
 * position in the grid.
 * Generate around the Centralisator cases with probalities random
 * toDo emotions with planet
 */
public class Planet implements Environment, EnvironmentAgent {
    private final List<List<Cell>> cells;
    private final FuzzyLogic fuzzyLogic;
    private PlanetEmotion currentEmotion;
    // simulation
    private PlanetHealthStatus currentHealthStatus;
    // shockWave
    private final ShockWaveSequencer shockWaveSequencer;
    private final List<Metamorphosis> metamorphosisList;
    // stock data
    private double initalStockFood;
    private double initalStockMineral;
    private double initalStockWater;
    private static final double quantityWater = 200000;
    private static final double quantityOre = 200;
    private static final double quantityFood = 200;
    private double stockFood;
    private double stockMineral;
    private double stockWater;

    private void load() {
        PlanetLoader planetLoader = new JsonLoader("/json/planet.json");
        PlanetData[] planetData = planetLoader.load();


        for (int i = 0; i < MAP_WIDTH; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < MAP_HEIGHT; j++) {
                Optional<Cell> o = CellFactory.factory(CellType.IMPENETRABLE, -1, i, j);
                if (o.isPresent()){
                    cells.get(i).add(o.get());
                }
            }
        }

        for (PlanetData planetDatum : planetData) {
            for (int j = 0; j < planetDatum.getCellPos().length; j++) {
                int x = planetDatum.getCellPos()[j].getX();
                int y = planetDatum.getCellPos()[j].getY();
                Optional<Cell> o = CellFactory.factory(
                        planetDatum.getType(),
                        -1,
                        planetDatum.getCellPos()[j].getX(),
                        planetDatum.getCellPos()[j].getY()
                );
                o.ifPresent(cell -> this.cells.get(x).set(y, cell));
            }
        }
    }
    private void stockSetup() {
        stockFood          = nbCaseType(CellType.FOOD)*quantityFood;
        stockMineral       = nbCaseType(CellType.ORE)*quantityOre;
        stockWater         = nbCaseType(CellType.LAKE)*quantityWater;

        initalStockFood    = stockFood;
        initalStockMineral = stockMineral;
        initalStockWater   = stockWater;

        for(List<Cell> list : cells){
            for(Cell c : list){
                if(c.getType() == CellType.LAKE){
                    ((ExtractableCell) c).setQuantity(quantityWater);
                }

                if(c.getType() == CellType.FOOD){
                    ((ExtractableCell) c).setQuantity(quantityFood);
                }

                if(c.getType() == CellType.ORE){
                    ((ExtractableCell) c).setQuantity(quantityOre);
                }
            }
        }
    }
    private void emotionSetup() {
        this.currentEmotion = PlanetEmotion.HAPPY;
        this.currentHealthStatus = PlanetHealthStatus.GOOD;
        this.setEmotion();
    }

    public Planet() {
        currentEmotion = PlanetEmotion.HAPPY;
        cells = new ArrayList<>();
        fuzzyLogic = new FuzzyLogic(this);
        shockWaveSequencer = new ShockWaveSequencer(this);
        metamorphosisList = new ArrayList<>();

        load();
        stockSetup();
        emotionSetup();
    }

    @Override
    public void setEmotion() {
        fuzzyLogic.executeEmotion(((this.stockMineral/this.initalStockMineral)*100) - 100,this.currentEmotion.ordinal(),((this.stockWater/this.initalStockWater)*100) - 100);
        this.currentEmotion = PlanetEmotion.values()[(int)(fuzzyLogic.getValueVariableEmotion("future_emotion"))];
    }

    @Override
    public void setCell(Coordinate c, EnvironmentCell EnvCell) {
        Cell cell = CellFactory.convert(EnvCell);
        cells.get(c.x()).set(c.y(), cell);
    }

    private void react() {
        shockWaveSequencer.updateShockWaves();
        for (Metamorphosis metamorphosis : metamorphosisList) {
            metamorphosis.transform();
        }
        metamorphosisList.clear();
    }

    @Override
    public List<Command> compute() {
        ArrayList<Command> commands = new ArrayList<>();
        react();
        // TODO possible commands for planet
        return commands;
    }

    public void extract(Coordinate coord, int value) {
        shockWaveSequencer.createShockWave(coord.getX(), coord.getY(), fuzzyLogic.getExtractionType(value));
    }

    public void sample(Coordinate coord, int value) {
        shockWaveSequencer.createShockWave(coord.getX(), coord.getY(), fuzzyLogic.getSamplingType(value));
    }

    public void addMetamorphosis(Metamorphosis metamorphosis) {
        metamorphosisList.add(metamorphosis);
    }
    @Override
    public int getWidth() {
        return cells.size();
    }
    @Override
    public int getHeight() {
        return cells.get(0).size();
    }
    @Override
    public List<List<Cell>> getCells() {
        return cells;
    }
    @Override
    public EnvironmentCell getCell(Coordinate c) {
        return cells.get(c.x()).get(c.y());
    }
    @Override
    public EnvironmentCell getCell( int x, int y ) {
        Coordinate coord = new Coordinate(x, y);
        return getCell(coord);
    }
    public double nbCaseType(CellType cellType) {
        double cpt = 0;
        for (List<Cell> list : this.cells) {
            for (Cell a : list) {
                if (a.getType() == cellType) {
                    cpt++;
                }
            }
        }
        return cpt;
    }
    public PlanetEmotion getCurrentEmotion(){
        return this.currentEmotion;
    }
    public PlanetHealthStatus getCurrentHealthStatus() {
        return currentHealthStatus;
    }
    public double getStockFood() {
        return stockFood;
    }
    public double getStockMineral() {
        return stockMineral;
    }
    public double getStockWater() {
        return stockWater;
    }
    public double getInitalStockFood() {
        return initalStockFood;
    }
    public double getInitalStockMineral() {
        return initalStockMineral;
    }
    public double getInitalStockWater() {
        return initalStockWater;
    }
    public FuzzyLogic getFuzzyLogic() {
        return fuzzyLogic;
    }
}
