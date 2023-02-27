package fr.ensicaen.lv223.presenter;

import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.Sequencer;
import fr.ensicaen.lv223.presenter.colony.ColonyPresenter;
import fr.ensicaen.lv223.presenter.planet.PlanetPresenter;

/**
 * The {@code Presenter} class is responsible for coordinating the interactions
 * between the model ({@link Planet}) and the view ({@link IPresenter}). It
 * implements the simulation logic and updates the view accordingly.
 */
public class Presenter {
    private IPresenter view;
    private final PlanetPresenter planetPresenter;
    private final ColonyPresenter colonyPresenter;
    private final Sequencer sequencer;

    private double tolerance;

    /**
     * Constructs a new {@code Presenter} instance.
     */
    public Presenter() {
        sequencer = new Sequencer(new Planet());
        planetPresenter = new PlanetPresenter(sequencer.planet);
        colonyPresenter = new ColonyPresenter(sequencer.mapper);
    }

    /**
     * Sets the {@code IPresenter} view.
     * @param view the view to be set
     */
    public void setView(IPresenter view) {
        this.view = view;
        planetPresenter.setView(view);
        colonyPresenter.setView(view);
    }

    /**
     * Simulates the evolution of the planet for a given number of turns.
     * @param nbTurns the number of turns to simulate
     */
    public void simulate(int nbTurns) {
        if (nbTurns < 0) {
            throw new IllegalArgumentException("The number of turns must be positive.");
        }

        sequencer.step();
        // TODO implement the choice to see the turn or not
        if (nbTurns == 0) {
            updateView();
            return;
        }

        simulate(nbTurns - 1);
    }

    public void updateView() {
        planetPresenter.drawPlanet();
        colonyPresenter.drawColony();
    }

    /**
     * Sets the tolerance of the planet.
     * @param d the tolerance value to set
     */
    public void setTolerance(double d) {
        this.tolerance = d;
    }
}
