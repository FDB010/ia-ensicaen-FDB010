package fr.ensicaen.lv223.model.environment.planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import fr.ensicaen.lv223.model.environment.Environment;
import fr.ensicaen.lv223.model.environment.EnvironmentCell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.cells.specials.extractable.ExtractableCell;
import fr.ensicaen.lv223.model.environment.planet.behavior.EnvironmentAgent;
import fr.ensicaen.lv223.model.environment.planet.behavior.FuzzyLogic;
import fr.ensicaen.lv223.model.environment.planet.reaction.ExtractionType;
import fr.ensicaen.lv223.model.environment.planet.reaction.SamplingType;
import fr.ensicaen.lv223.model.environment.planet.reaction.ShockWaveSequencer;
import fr.ensicaen.lv223.model.environment.planet.state.PlanetEmotion;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.util.loader.planetloader.JsonLoader;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetData;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetLoader;
import fr.ensicaen.lv223.model.environment.planet.state.PlanetHealthStatus;

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
 */


/**
 * toDo emotions with planet
 * Generate around the Centralisator cases with probalities random
 */
public class Planet implements Environment, EnvironmentAgent {
    private final List<List<Cell>> cells;
    private FuzzyLogic fuzzyLogic;
    private PlanetEmotion currentEmotion;
    private Dispatcher dispatcher;

    private PlanetHealthStatus currentHealthStatus;

    private final double initalStockFood;
    private final double initalStockMineral;
    private final double initalStockWater;

    private final double quantityWater = 200000;
    private final double quantityOre = 200;
    private final double quantityFood = 200;



    private double stockFood;
    private double stockMineral;
    private double stockWater;
    private ShockWaveSequencer shockWaveSequencer;
    public Planet() {
        currentEmotion = PlanetEmotion.HAPPY;
        cells = new ArrayList<>();
        fuzzyLogic = new FuzzyLogic(this);
        shockWaveSequencer = new ShockWaveSequencer(this);
        dispatcher = new Dispatcher(cells);

        Random r = new Random(System.currentTimeMillis());

        this.currentEmotion = PlanetEmotion.HAPPY;
        this.currentHealthStatus = PlanetHealthStatus.GOOD;



        PlanetLoader planetLoader = new JsonLoader("/json/planet.json");
        PlanetData[] planetData = planetLoader.load();


        for (int i = 0; i < 21; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < 21; j++) {
                Optional<Cell> o = CellFactory.factory(CellType.IMPENETRABLE, -1, i, j);
                cells.get(i).add(o.get());
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
                this.cells.get(x).set(y, o.get());
            }
        }
        this.stockFood      = nbCaseType(CellType.FOOD)*quantityFood;
        this.stockMineral   = nbCaseType(CellType.ORE)*quantityOre;
        this.stockWater     = nbCaseType(CellType.LAKE)*quantityWater;

        this.initalStockFood = stockFood;
        this.initalStockMineral = stockMineral;
        this.initalStockWater = stockWater;

        setStocks();

        this.setEmotion();
    }

    @Override
    public PlanetEmotion setEmotion() {
        fuzzyLogic.executeEmotion(((this.stockMineral/this.initalStockMineral)*100) - 100,this.currentEmotion.ordinal(),((this.stockWater/this.initalStockWater)*100) - 100);
        this.currentEmotion = PlanetEmotion.values()[(int)(fuzzyLogic.getValueVariableEmotion("future_emotion"))];
        return this.currentEmotion;
    }


    public void setStocks(){
        for(List<Cell> list : this.cells){
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
        return cells.get(c.getX()).get(c.getY());
    }

    @Override
    public EnvironmentCell getCell( int x, int y ) {
        Coordinate coord = new Coordinate(x, y);
        return getCell(coord);
    }

    @Override
    public void setCell(Coordinate c, EnvironmentCell cell) {
        this.getCells().get(c.getX()).set(c.getY(), CellFactory.convert(cell));
    }

    private void react() {
        // TODO
    }

    @Override
    public List<Command> compute() {
        ArrayList commands = new ArrayList();
        react();
        // TODO possible commands for planet
        return commands;
    }

    public void extract(Coordinate coord, int value) {
        // TODO evaluate the extraction type
        shockWaveSequencer.createShockWave(coord.x, coord.y, ExtractionType.SMALL);

    }

    public void sample(Coordinate coord, int value) {
        // TODO evaluate the sampling type
        shockWaveSequencer.createShockWave(coord.x, coord.y, SamplingType.NEGLIGIBLE);
    }

    public PlanetHealthStatus getCurrentHealthStatus() {
        return currentHealthStatus;
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

    public double getStockFood() {
        return stockFood;
    }

    public PlanetEmotion getCurrentEmotion(){
        return this.currentEmotion;
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
}
