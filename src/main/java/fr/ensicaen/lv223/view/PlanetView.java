package fr.ensicaen.lv223.view;

import fr.ensicaen.lv223.presenter.IPresenter;
import fr.ensicaen.lv223.presenter.Presenter;
import fr.ensicaen.lv223.view.content.NumberOfSteps;
import fr.ensicaen.lv223.view.content.VisionMode;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

/**
 * {@code PlanetView} is a JavaFX class that represents the visual aspect of
 * the planet and its simulation.
 * The class implements the {@code IPresenter} interface and contains methods
 * to interact with the user and display the simulation status.
 * @version 1.0
 * @since 01/12/2023
 */
public class PlanetView implements IPresenter {
    /**
     * The {@code gridOfPlanet} is a {@link GridPane} object used to display
     * the cells of the planet simulation.
     */
    @FXML
    private GridPane gridOfPlanet;

    /**
     * The {@code buttonToStartOneOrMoreSteps} is a {@link Button} object used
     * to initiate a certain number of steps for the simulation.
     */
    @FXML
    private Button buttonToStartOneOrMoreSteps;

    /**
     * The {@code choiceOfNumberOfSteps} is a {@link ChoiceBox} object used to
     * choose the number of steps when pushing the simulation button (1, 10
     * or 100 steps).
     */
    @FXML
    private ChoiceBox<NumberOfSteps> choiceOfNumberOfSteps;
    @FXML
    private ChoiceBox<VisionMode> choiceOfVisionMode;
    @FXML
    private ChoiceBox<String> agentsChoiceBox;

    /**
     * The label to display the age of the planet since the arrival of the
     * colony
     */
    @FXML
    private Label ageSinceTheArrivalOfTheColony;

    /** The label to display the health status of the planet */
    @FXML
    private Label healthStatusOfThePlanet;

    /** The label to display the food stock of the colony */
    @FXML
    private Label foodStockOfTheColony;

    /** The label to display the water stock of the colony */
    @FXML
    private Label waterStockOfTheColony;

    /** The label to display the ore stock of the colony */
    @FXML
    private Label oreStockOfTheColony;

    /** The label to display the current number of robots of the colony */
    @FXML
    private Label currentNumberOfRobots;

    /** The width of the scene */
    private int sceneWidth;

    /** The height of the scene */
    private int sceneHeight;

    /** A reference to the presenter of the simulation */
    private final Presenter presenter;

    /**
     * The 2D list of {@code CellView} objects to represent the cells within the
     * simulation grid
     */
    private List<List<CellView>> cellsView = null;

    /**
     * An Observable list of items to select the number of steps of the simulation
     */
    private final Observable nbTurnsItems = FXCollections.observableArrayList(NumberOfSteps.values());
    private final Observable visionModeItems = FXCollections.observableArrayList(VisionMode.values());
    private final Observable agentsItems = FXCollections.observableArrayList("ALL");

    /**
     * Constructs a new {@code PlanetView} object with a reference to the
     * presenter and the width and height of the scene.
     * @param presenter a reference to the presenter of the simulation
     * @param width the width of the scene
     * @param height the height of the scene
     */
    public PlanetView(Presenter presenter, int width, int height) {
        this.presenter = presenter;
        this.sceneWidth = width;
        this.sceneHeight = height;
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public int getSceneHeight() {
        return sceneHeight;
    }

    @Override
    public void setSceneWidth(int sceneWidth) {
        this.sceneWidth = sceneWidth;
    }

    @Override
    public void setSceneHeight(int sceneHeight) {
        this.sceneHeight = sceneHeight;
    }

    /**
     * Sets the action when the user clicks on the button to simulate.
     */
    public void setOnclick() {
        buttonToStartOneOrMoreSteps.setOnAction(
            (event) -> {
                presenter.simulate(choiceOfNumberOfSteps.getValue().getValue());
            }
        );
    }

    /**
     * Sets the items of the choice boxe to select the number of steps of the
     * simulation.
     */
    public void setChoicesOfNumberOfSteps() {
        choiceOfNumberOfSteps.getItems().setAll(NumberOfSteps.values());
        choiceOfNumberOfSteps.setValue(NumberOfSteps.ONE);
    }

    public void setChoicesOfVisionMode() {
        choiceOfVisionMode.getItems().setAll(VisionMode.values());
        choiceOfVisionMode.setValue(VisionMode.DEFAULT);
    }

    public void setChoicesOfAgents() {
        agentsChoiceBox.setDisable(true);
        agentsChoiceBox.setValue("ALL");
    }

    /**
     * Displays an information alert when the simulation ends.
     */
    @Override
    public void finish() {
        buttonToStartOneOrMoreSteps.setDisable(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Fin de la simulation !");
        alert.showAndWait();
    }

    @Override
    public void draw() {
        gridOfPlanet.getChildren().clear();
        for (int i = 0; i < cellsView.size(); i++) {
            for (int j = 0; j < cellsView.get(i).size(); j++) {
                gridOfPlanet.add(cellsView.get(i).get(j).getPane(), i, j);
            }
        }
    }

    @Override
    public void setCellsView(List<List<CellView>> cellsView) {
        this.cellsView = cellsView;
    }

    @Override
    public List<List<CellView>> getCellView() {
        return cellsView;
    }

    @Override
    public void updateAge(int age) {
        ageSinceTheArrivalOfTheColony.setText("Âge de la planète : " + age + " jour(s)");
    }

    @Override
    public void updateStock(double foodStock, double waterStock, double oreStock) {
        foodStockOfTheColony.setText("Food Stock : " + foodStock);
        waterStockOfTheColony.setText("Water-Stock : " + waterStock + " Km3");
        oreStockOfTheColony.setText("Ore Stock : " + oreStock);
    }

    @Override
    public void updateNbRobots(int nbRobots) {
        currentNumberOfRobots.setText("Nombre de robots : " + nbRobots);
    }

}
