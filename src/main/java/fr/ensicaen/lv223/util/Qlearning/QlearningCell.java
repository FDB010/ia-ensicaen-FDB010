package fr.ensicaen.lv223.util.Qlearning;

import fr.ensicaen.lv223.model.CellType;
import fr.ensicaen.lv223.model.cells.Cell;

import java.util.UUID;

public class QlearningCell extends Cell {




    private final String uniqueID = UUID.randomUUID().toString();

    public QlearningCell(Cell cell) {

        super(cell.getX(), cell.getY(), cell.getType(), cell.getIntensity());

    }



}

