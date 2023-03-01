package fr.ensicaen.lv223.model.environment.planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import fr.ensicaen.lv223.model.agent.Agent;
import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import fr.ensicaen.lv223.model.environment.Environment;
import fr.ensicaen.lv223.model.environment.EnvironmentCell;
import fr.ensicaen.lv223.model.environment.construction.WaterPipe;
import fr.ensicaen.lv223.model.environment.planet.behavior.EnvironmentAgent;
import fr.ensicaen.lv223.model.environment.planet.behavior.FuzzyLogic;
import fr.ensicaen.lv223.model.environment.planet.state.PlanetEmotion;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.util.loader.planetloader.JsonLoader;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetData;
import fr.ensicaen.lv223.util.loader.planetloader.PlanetLoader;
import fr.ensicaen.lv223.model.environment.construction.WaterPipe;
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
    private final List<WaterPipe> waterPipes;
    private FuzzyLogic fuzzyLogic;
    private PlanetEmotion currentEmotion;
    private Dispatcher dispatcher;

    private PlanetHealthStatus currentHealthStatus;

    private final double initalstockFood;
    private final double initalstockMineral;
    private final double initalstockWater;


    private double stockFood;
    private double stockMineral;
    private double stockWater;
    public Planet() {
        Random r = new Random(System.currentTimeMillis());

        this.currentEmotion = PlanetEmotion.HAPPY;
        this.currentHealthStatus = PlanetHealthStatus.GOOD;

        this.cells = new ArrayList<>();
        this.waterPipes = new ArrayList<>();
        this.fuzzyLogic = new FuzzyLogic();
        this.dispatcher = new Dispatcher(this.cells);

        PlanetLoader planetLoader = new JsonLoader("/json/planet.json");
        PlanetData[] planetData = planetLoader.load();


        for (int i = 0; i < 21; i++) {
            this.cells.add(new ArrayList<>());
            for (int j = 0; j < 21; j++) {
                Optional<Cell> o = CellFactory.factory("IMPENETRABLE", -1, i, j,0);
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
                        planetDatum.getCellPos()[j].getY(),0
                );
                this.cells.get(x).set(y, o.get());
            }
        }
        this.stockFood      = Math.floor((100.0 - nbCaseType(CellType.FOOD))*r.nextDouble() +nbCaseType(CellType.FOOD));
        this.stockMineral   = Math.floor((100.0 - nbCaseType(CellType.ORE))*r.nextDouble()  +nbCaseType(CellType.ORE));
        this.stockWater     = Math.floor((100.0 - nbCaseType(CellType.LAKE))*r.nextDouble() +nbCaseType(CellType.LAKE));

        this.initalstockFood = stockFood;
        this.initalstockMineral = stockMineral;
        this.initalstockWater = stockWater;

        dispatcher.dispatched(CellType.FOOD,this.stockFood);
        dispatcher.dispatched(CellType.ORE,this.stockFood);
        dispatcher.dispatched(CellType.LAKE,this.stockFood);

        this.setEmotion();

    }

    @Override
    public List<List<Cell>> getCells() {
        return cells;
    }

    @Override
    public void setEmotion() {
        fuzzyLogic.executeEmotion(((this.stockMineral/this.initalstockMineral)*100) - 100,this.currentEmotion.ordinal(),((this.stockWater/this.initalstockWater)*100) - 100);
        this.currentEmotion = PlanetEmotion.values()[(int)(fuzzyLogic.getValueVariableEmotion("future_emotion"))];
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
        this.getCells().get(c.getX()).set(c.getY(), (Cell) cell);
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

    public void addPipe(WaterPipe pipe) {
        waterPipes.add(pipe);
    }

    public void removePipe(WaterPipe pipe) {
        waterPipes.remove(pipe);
    }

}
