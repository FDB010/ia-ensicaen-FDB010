package fr.ensicaen.lv223.util.astar;

import fr.ensicaen.lv223.model.environment.cells.Cell;

import java.util.List;

public interface Agent {
    void compute();
    void computeStep();
    Cell[][] getCells();
    Cell getCell(int x, int y);
    boolean isFinished();

    List<Cell> getPath();
}
