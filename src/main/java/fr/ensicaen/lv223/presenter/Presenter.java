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

        // TODO implement the choice to see the turn or not
        if (nbTurns == 0 || sequencer.hasHumansArrived()) {
            updateView();
            return;
        }
        sequencer.step();

        simulate(nbTurns - 1);
    }

    public void updateView() {
        view.updateAge(sequencer.getDays());
        planetPresenter.updateStatus();
        colonyPresenter.updateStatus();
        planetPresenter.drawPlanet();
        colonyPresenter.drawColony();
    }

    public void drawPlanet(){
        planetPresenter.drawPlanet();
        colonyPresenter.drawColony();
    }
}
