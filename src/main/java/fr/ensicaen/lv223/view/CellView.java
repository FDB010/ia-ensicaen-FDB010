package fr.ensicaen.lv223.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The {@code CellView} class is a visual representation of a {@link Cell} in
 * the simulation.
 * It contains information about the width, height and color of the cell and
 * it's corresponding {@code RobotView} object.
 */
public class CellView {
    private final double width;
    private final double height;
    private final Color color;
    private final Rectangle shape;
    private RobotView robotView;
    private StackPane pane;

    /**
     * Constructs a new {@code CellView} instance with specified {@code width
     * }, {@code height}, and {@code type} parameters.
     *
     * @param width The width of the cell.
     * @param height The height of the cell.
     * @param type The type of cell, used to determine its color.
     */
    public CellView(double width, double height, String type) {
        this.pane = new StackPane();
        this.width = width;
        this.height = height;
        this.color = getColor(type);

        shape = new Rectangle(width, height);
        shape.setFill(color);
        this.pane.getChildren().add(shape);

        robotView = new RobotView(this.width);
        this.pane.getChildren().add(robotView.getShape());
    }

    /**
     * Returns the `Rectangle` shape of this cell.
     * @return The `Rectangle` shape of this cell.
     */
    public Rectangle getShape() {
        return shape;
    }

    /**
     * Returns the color of this cell based on its {@code type}.
     * @param type The type of this cell, used to determine its color.
     * @return The color of this cell.
     */
    private Color getColor(String type) {
        switch (type) {
            case "BASE":
                return Color.DARKBLUE;
            case "DESERT":
                return Color.LEMONCHIFFON;
            case "DRY_GRASS":
                return Color.YELLOWGREEN;
            case "FOOD":
                return Color.TOMATO;
            case "FOREST":
                return Color.FORESTGREEN;
            case "GRASS":
                return Color.MEDIUMSEAGREEN;
            case "IMPENETRABLE":
                return Color.BLACK;
            case "LAKE":
                return Color.MEDIUMAQUAMARINE;
            case "ORE":
                return Color.GOLD;
            case "STONE":
                return Color.GREY;
            case "WET_GRASS":
                return Color.DARKGREEN;
            default:
                return null;
        }
    }

    /**
     * Returns the {@code RobotView} instance associated with this cell.
     *
     * @return The {@code RobotView} instance associated with this cell.
     */
    public RobotView getRobotView() {
        return robotView;
    }

    /**
     * Sets the {@code RobotView} instance associated with this cell.
     *
     * @param robotView The {@code RobotView} instance to associate with this
     *        cell.
     */
    public void setRobotView(RobotView robotView) {
        this.robotView = robotView;
    }

    /**
     * Returns the {@code StackPane} associated with this cell.
     *
     * @return The {@code StackPane} associated with this cell.
     */
    public StackPane getPane() {
        return pane;
    }

    /**
     * Sets the {@code StackPane} associated with this cell.
     *
     * @param pane The {@code StackPane} to associate with this cell.
     */
    public void setPane(StackPane pane) {
        this.pane = pane;
    }
}
