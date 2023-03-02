package fr.ensicaen.lv223.teams.jamesbond;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;

public class UnknownCell extends Cell{
    private Boolean isFocused = false;
    private Robot focusedBy = null;
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

    public void setFocusedRobot(Robot robot){
        this.focusedBy = robot;
        this.isFocused = true;
    }

    public void removeFocusedRobot(){
        this.focusedBy = null;
        this.isFocused = false;
    }

    public boolean isFocused(){
        return this.isFocused;
    }
}
