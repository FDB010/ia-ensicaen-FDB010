package fr.ensicaen.lv223.teams.copilot;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;

public class UnknownCell extends Cell{
    /**
     * Constructs a new {@code Cell} object with given x and y coordinates,
     * cell type, and intensity of metamorphosis. The intensity of wave is
     * initialized to 0 and isExoskeleton is set based on the cell type.
     *
     * @param x         the x coordinate of the cell
     * @param y         the y coordinate of the cell
     */
    public UnknownCell(int x, int y) {
        super(x, y, CellType.UNKNOWN, 0);
    }


    public void update(CellType type){
        super.type = type;
    }
    public CellType getType(){
        return super.type;
    }
}
