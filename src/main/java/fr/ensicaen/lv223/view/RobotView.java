package fr.ensicaen.lv223.view;

import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.util.loader.viewloader.ImageLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A visual representation of a robot in the simulation. The robot is
 * displayed as a circle shape. The circle is filled with a color that depends
 * on the type of the robot.
 */
public class RobotView {
    /** The color of the robot */
    private Color color;

    /** The circle shape representing the robot */
    private final ImageView displayElement;

    /**
     * Creates a new instance of the {@code RobotView} class.
     *
     * @param cellWidth the width of the cell in which the robot is displayed
     */
    public RobotView(double cellWidth) {
        this.displayElement = new ImageView();
    }

    /**
     * Makes the robot visible in the simulation.
     */
    public void setVisible() {
        this.displayElement.setVisible(true);
    }

    /**
     * Hides the robot in the simulation.
     */
    public void hide() {
        this.displayElement.setVisible(false);
    }

    public void setRobotType(RobotType type) {
        try {
            displayElement.setImage(ImageLoader.getInstance().getRobotImage(type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the circle shape representing the robot.
     *
     * @return the circle shape representing the robot
     */
    public Node getNode() {
        return displayElement;
    }
}
