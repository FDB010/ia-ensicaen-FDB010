package fr.ensicaen.lv223.model.agent.robot.command;

import javafx.scene.paint.Color;

public enum Direction {
    NORTH(0,-1),
    SOUTH(0,1),
    EAST(1,0),
    WEST(-1,0),
    NORTHWEST(-1,-1),
    NORTHEAST(1,-1),

    SOUTHWEST(-1, 1),

    SOUTHEAST(1,1);

    private final int direction_x, direction_y;
    Direction(int x, int y)
    {
        this.direction_x = x;
        this.direction_y = y;
    }
    public int getDirection_x()
    {
        return this.direction_x;
    }
    public int getDirection_y()
    {
        return this.direction_y;
    }

}
