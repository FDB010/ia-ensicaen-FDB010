package fr.ensicaen.lv223.view;

import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.construction.ConstructionType;
import fr.ensicaen.lv223.util.Util;
import fr.ensicaen.lv223.util.loader.viewloader.ImageLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The {@code CellView} class is a visual representation of a {@code Cell} in
 * the simulation.
 * It contains information about the width, height and color of the cell, and
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
    public CellView(double width, double height, CellType type, boolean hasPipe) {
        this.pane = new StackPane();
        this.width = width;
        this.height = height;
        this.color = getColor(type);

        shape = new Rectangle(width, height);
        shape.setFill(color);
        this.pane.getChildren().add(shape);

        try {
            ImageLoader imageLoader = ImageLoader.getInstance(width, height);
            this.pane.getChildren().add(new ImageView(imageLoader.getCellImage(type)));
            if (hasPipe) {
                this.pane.getChildren().add(new ImageView(imageLoader.getConstructionImage(ConstructionType.PIPE)));
            }
        } catch (Exception e) {
            Util.LOGGER.severe("Error while loading cell image: " + e.getMessage());
        }


        robotView = new RobotView(this.width);
        this.pane.getChildren().add(robotView.getNode());
    }

    public CellView(double width, double height, CellType type) {
        this(width, height, type, false);
    }

    /**
     * Returns the {@link Rectangle} shape of this cell.
     * @return The {@code Rectangle} shape of this cell.
     */
    public Rectangle getShape() {
        return shape;
    }

    /**
     * Returns the color of this cell based on its {@code type}.
     * @param type The type of this cell, used to determine its color.
     * @return The color of this cell.
     */
    private Color getColor(CellType type) {
        return switch (type) {
            case BASE -> Color.DARKBLUE;
            case DESERT -> Color.LEMONCHIFFON;
            case DRY_GRASS -> Color.YELLOWGREEN;
            case FOOD -> Color.TOMATO;
            case FOREST -> Color.FORESTGREEN;
            case GRASS -> Color.MEDIUMSEAGREEN;
            case IMPENETRABLE -> Color.BLACK;
            case LAKE -> Color.MEDIUMAQUAMARINE;
            case ORE -> Color.GOLD;
            case STONE -> Color.GREY;
            case WET_GRASS -> Color.DARKGREEN;
            default -> Color.CHOCOLATE;
        };
    }

    /**
     * Returns the {@link RobotView} instance associated with this cell.
     *
     * @return The {@code RobotView} instance associated with this cell.
     */
    public RobotView getRobotView() {
        return robotView;
    }

    /**
     * Sets the {@link RobotView} instance associated with this cell.
     *
     * @param robotView The {@code RobotView} instance to associate with this
     *        cell.
     */
    public void setRobotView(RobotView robotView) {
        this.robotView = robotView;
    }

    /**
     * Returns the {@link StackPane} associated with this cell.
     *
     * @return The {@code StackPane} associated with this cell.
     */
    public StackPane getPane() {
        return pane;
    }

    /**
     * Sets the {@link StackPane} associated with this cell.
     *
     * @param pane The {@code StackPane} to associate with this cell.
     */
    public void setPane(StackPane pane) {
        this.pane = pane;
    }
}
