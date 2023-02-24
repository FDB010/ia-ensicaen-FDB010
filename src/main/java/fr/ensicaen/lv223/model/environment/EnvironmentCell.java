package fr.ensicaen.lv223.model.environment;

import fr.ensicaen.lv223.model.environment.cells.CellType;

/**
 * The {@code EnvironmentCell} interface represents a cell in an environment.
 */
public interface EnvironmentCell {
    /**
     * Returns the type of this cell.
     * @return the type of this cell
     */
    CellType getType();

    /**
     * Returns the intensity of metamorphosis for this cell.
     * @return the intensity of metamorphosis for this cell
     */
    double getIntensity();

    /**
     * Sets the intensity of metamorphosis for this cell.
     * @param intensity the intensity of metamorphosis for this cell
     */
    void setIntensity(double intensity);
}
