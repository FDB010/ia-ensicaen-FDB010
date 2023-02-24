package fr.ensicaen.lv223.presenter;

import java.util.ArrayList;
import java.util.List;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.view.CellView;
import javafx.scene.effect.GaussianBlur;

/**
 * The {@code Presenter} class is responsible for coordinating the interactions
 * between the model ({@link Planet}) and the view ({@link IPresenter}). It
 * implements the simulation logic and updates the view accordingly.
 */
public class Presenter {
    private final Planet planet;

    private IPresenter view;

    private double tolerance;

    /**
     * Constructs a new {@code Presenter} instance.
     */
    public Presenter() {
        planet = new Planet();

    }

    /**
     * Sets the {@code IPresenter} view.
     * @param view the view to be set
     */
    public void setView(IPresenter view) {
        this.view = view;
    }

    /**
     * Draws the planet on the view.
     */
    public void drawPlanet() {
        List<List<Cell>> cells = planet.getCells();
        List<List<CellView>> cellsView = new ArrayList<>();
        GaussianBlur blur = new GaussianBlur(60);

        for (int i = 0; i < cells.size(); i++) {
            cellsView.add(new ArrayList<>(cells.get(0).size()));
            for (int j = 0; j < cells.get(i).size(); j++) {
                cellsView.get(i).add(new CellView(view.getSceneWidth() / 21.0,
                        view.getSceneHeight() / 21.0,
                        cells.get(i).get(j).getType().name()));
                cellsView.get(i).get(j).getRobotView().hide();
                if (i == j) {
                    cellsView.get(i).get(j).getRobotView().setVisible();
                    cellsView.get(i).get(j).getRobotView().setColor(RobotType.values()[i%RobotType.values().length].getColor());
                }
                cellsView.get(i).get(j).getPane().setEffect(blur);
            }
        }

        GaussianBlur noBlur = new GaussianBlur(0.0);
        view.setCellsView(cellsView);
        view.draw();
    }

    /**
     * Simulates the evolution of the planet for a given number of turns.
     * @param nbTurns the number of turns to simulate
     */
    public void simulate(int nbTurns) {
        boolean terminated = false;
        int i = 0;

        while (!terminated && i < nbTurns) {
            if (planet.getAgeSinceTheArrivalOfTheColony() == 8 * 365 || noMoreLivingRobots()) {
                view.finish();
                terminated = true;
            } else {
                simulateOneTurn();
                i++;
            }
        }
    }

    /**
     * Simulates one turn of the planet's evolution.
     */
    public void simulateOneTurn() {
        // TO COMPLETE
        planet.play();
        drawPlanet();
        updateRobotPositionOnView();
    }

    /**
     * Updates the robot positions on the view.
     */
    public void updateRobotPositionOnView() {
        // TO COMPLETE
    }

    /**
     * Sets the tolerance of the planet.
     * @param d the tolerance value to set
     */
    public void setTolerance(double d) {
        this.tolerance = d;
    }

    /**
     * Determines whether there are no more living robots on the planet.
     * @return true if there are no more living robots, false otherwise
     */
    public boolean noMoreLivingRobots() {
        // TO COMPLETE
        return false;
    }
}
