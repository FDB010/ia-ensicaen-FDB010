package fr.ensicaen.lv223.presenter;

import fr.ensicaen.lv223.view.CellView;

import java.util.List;

/**
 * {@code IPresenter} interface specifies the methods that must be
 * implemented by the presenter of the view in a Model-View-Presenter (MVP)
 * architecture.
 */
public interface IPresenter {
    /**
     * Sets the cells view of the environment.
     * @param cellsView a two-dimensional list of {@link CellView} objects.
     */
    void setCellsView(List<List<CellView>> cellsView);

    /**
     * Returns the list of cells view.
     * @return the list of cells view.
     */
    List<List<CellView>> getCellView();

    /**
     * Returns the width of the scene.
     * @return the width of the scene.
     */
    int getSceneWidth();

    /**
     * Returns the height of the scene.
     * @return the height of the scene.
     */
    int getSceneHeight();

    /**
     * Sets the height of the scene.
     * @param height the new height of the scene.
     */
    void setSceneHeight(int height);

    /**
     * Sets the width of the scene.
     * @param width the new width of the scene.
     */
    void setSceneWidth(int width);

    /**
     * Draws the view.
     */
    void draw();
    void updateAge(int age);
    void updateStock(double foodStock, double waterStock, double oreStock);
    void updateNbRobots(int nbRobots);
    /**
     * Set the onclick behavior of the view.
     */
    void setOnclick();
    /**
     * Set the number of steps using the choice box of the view.
     */
    void setChoicesOfNumberOfSteps();
    void setChoicesOfVisionMode();
    void setChoicesOfAgents();
    /**
     * Finish the view.
     */
    void finish();
}