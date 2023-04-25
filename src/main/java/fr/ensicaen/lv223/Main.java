package fr.ensicaen.lv223;

import fr.ensicaen.lv223.presenter.IPresenter;
import fr.ensicaen.lv223.presenter.Presenter;
import fr.ensicaen.lv223.view.PlanetView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * {@code Main} class extends the {@link Application} class of JavaFX to
 * create the GUI application for our simulation.
 * It sets up the scene, size, style and behavior of the application.
 */
public class Main extends Application {
    /**
     * Override the start method of {@link Application} class to set up the GUI
     * elements and behaviors.
     *
     * @param stage the stage object used to show the application
     * @throws IOException when there is an error in loading the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        // TODO : update legend of main frame
        int width;
        int height;
        Presenter presenter;
        IPresenter view;
        FXMLLoader fxmlLoader;
        Scene scene;

        width = 500;
        height = 500;
        presenter = new Presenter();
        view = new PlanetView(presenter, width, height);
        presenter.setView(view);

        fxmlLoader = new FXMLLoader(Main.class.getResource("/board_view.fxml"));
        fxmlLoader.setController(view);

        scene = new Scene(fxmlLoader.load(), width, height);
        scene.getRoot().setStyle("-fx-font-family: 'sans-serif'");
        stage.setScene(scene);
        stage.setTitle("Simulation LV-223");

        view.setOnclick();
        view.setChoicesOfNumberOfSteps();
        view.setChoicesOfPlanetTolerance();
        scene.getRoot().setStyle("-fx-font-family: 'sans-serif'");
        presenter.drawPlanet();

        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            presenter.drawPlanet();
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            view.setSceneHeight(newSceneHeight.intValue());
            view.setSceneWidth(newSceneHeight.intValue());

            presenter.drawPlanet();
        });

        presenter.updateView();
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Main method to launch the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
