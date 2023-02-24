package fr.ensicaen.lv223.model.environment;

import java.util.Objects;
import java.util.Random;

/**
 * The {@code Coordinate} class represents a position on a two-dimensional
 * plane. It provides functionality to get/set the x and y coordinates,
 * create a random new coordinate, translate the coordinate by an offset, add
 * two coordinates and get the string representation of the coordinate. It also
 * implements hashcode and equals methods to compare if two coordinates are
 * equal based on their x and y values.
 */
public class Coordinate {
    private static final int MAX_BOUND = 2;
    private static final int MIN_BOUND = -1;
    private final int hashCode;
    int x;
    int y;

    public Coordinate(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate newRandomCoordinates() {
        Random rand = new Random();
        int offsetX = rand.nextInt(MAX_BOUND + MIN_BOUND) - MIN_BOUND;
        int offsetY = rand.nextInt(MAX_BOUND + MIN_BOUND) - MIN_BOUND;
        return new Coordinate(x + offsetX, y + offsetY);
    }

    public Coordinate translate(int offsetX, int offSetY) {
        return new Coordinate(this.getX() + offsetX, this.getY() + offSetY);
    }

    public Coordinate add(Coordinate c) {
        return new Coordinate(this.getX() + c.getX(), this.getY() + c.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate c = (Coordinate) o;
        return (x == c.getX() && y == c.getY());
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
