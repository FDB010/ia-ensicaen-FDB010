package fr.ensicaen.lv223.model.logic.localisation;

public class Coordinate {
    public final int x;
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Coordinate that = (Coordinate) obj;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

<<<<<<< HEAD
    public int getX() {
        return x;
    }

    public int getY() {
=======
    public int getX(){
        return x;
    }

    public int getY(){
>>>>>>> 0533d0dfb62be45863b0ba0ce6fafe5dcc25cc8f
        return y;
    }
}
