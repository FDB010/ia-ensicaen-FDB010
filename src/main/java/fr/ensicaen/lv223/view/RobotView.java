package fr.ensicaen.lv223.view;

import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.util.Util;
import fr.ensicaen.lv223.util.loader.viewloader.ImageLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * A visual representation of a robot in the simulation. The robot is
 * displayed as a circle shape. The circle is filled with a color that depends
 * on the type of the robot.
 */
public class RobotView {

    /** The circle shape representing the robot */
    private final ImageView displayElement;
    private final double cellWidth;

    /**
     * Creates a new instance of the {@code RobotView} class.
     *
     * @param cellWidth the width of the cell in which the robot is displayed
     */
    public RobotView(double cellWidth) {
        this.displayElement = new ImageView();
        this.cellWidth = cellWidth;
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
            this.displayElement.setImage(ImageLoader.getInstance(cellWidth, cellWidth).getRobotImage(type));
        } catch (Exception e) {
            Util.LOGGER.severe("Error while loading robot image: " + e.getMessage());
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
