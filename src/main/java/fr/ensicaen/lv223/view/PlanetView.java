package fr.ensicaen.lv223.view;

import java.util.List;

import fr.ensicaen.lv223.presenter.IPresenter;
import fr.ensicaen.lv223.presenter.Presenter;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
    private ChoiceBox<String> choiceOfNumberOfSteps;

    /**
     * The {@code choiceOfThePlanetTolerance} is a {@link ChoiceBox} object
     * used to choose the tolerance level of the planet.
     */
    @FXML
    private ChoiceBox<String> choiceOfThePlanetTolerance;

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
    private final Observable items = FXCollections.observableArrayList("1",
            "10", "100");

    /**
     * An Observable list of items to select the tolerance of the planet
     */
    private final Observable tolerances = FXCollections.observableArrayList(
            "Hostile", "Adaptative", "Tolérante");

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
        buttonToStartOneOrMoreSteps.setOnAction(event -> presenter.simulate(Integer.parseInt(choiceOfNumberOfSteps.getValue())));
        choiceOfThePlanetTolerance.setOnAction(event -> {
            switch (choiceOfThePlanetTolerance.getValue()) {
                case "Hostile":
                    presenter.setTolerance(1.0);
                    break;
                case "Adaptative":
                    presenter.setTolerance(1.5);
                    break;
                case "Tolérante":
                    presenter.setTolerance(2.0);
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Sets the items of the choice boxe to select the number of steps of the
     * simulation.
     */
    public void setChoicesOfNumberOfSteps() {
        choiceOfNumberOfSteps.setItems((ObservableList<String>) items);
        choiceOfNumberOfSteps.setValue("1");
    }

    /**
     * Sets the items of the choice boxe to select the tolerance for the planet.
     */
    public void setChoicesOfPlanetTolerance() {
        choiceOfThePlanetTolerance.setItems((ObservableList<String>) tolerances);
        choiceOfThePlanetTolerance.setValue("Tolérante");
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

    /**
     * Updates the planet's status in the UI.
     * @param age the planet's age since the arrival of the colony
     * @param healthStatus the planet's health status
     * @param foodStock the colony's food stock
     * @param waterStock the colony's water stock
     * @param oreStock the colony's ore stock
     * @param nbRobots the number of robots for the colony
     */
    public void updateStatus(int age, String healthStatus, double foodStock,
                             double waterStock, double oreStock, int nbRobots) {
        ageSinceTheArrivalOfTheColony.setText("Âge de la planète : " + age + " an(s)");
        healthStatusOfThePlanet.setText("Santé de la planète : " + healthStatus);
        foodStockOfTheColony.setText("Food Stock : " + foodStock);
        waterStockOfTheColony.setText("Water-Stock : " + waterStock + " Km3");
        oreStockOfTheColony.setText("Ore Stock : " + oreStock);
        currentNumberOfRobots.setText("Nombre de robots : " + nbRobots);
    }

}
