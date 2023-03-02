package fr.ensicaen.lv223.model.logic.localisation;

public enum Direction {
    NORTH(0,-1),
    SOUTH(0,1),
    EAST(1,0),
    WEST(-1,0),
    NORTH_WEST(-1,-1),
    NORTH_EAST(1,-1),

    SOUTH_WEST(-1, 1),

    SOUTH_EAST(1,1);

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

    public Direction reverseDirection() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
            case NORTH_WEST -> SOUTH_EAST;
            case NORTH_EAST -> SOUTH_WEST;
            case SOUTH_WEST -> NORTH_EAST;
            case SOUTH_EAST -> NORTH_WEST;
        };
    }

}
