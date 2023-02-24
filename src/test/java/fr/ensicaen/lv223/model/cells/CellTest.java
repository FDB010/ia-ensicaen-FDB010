package fr.ensicaen.lv223.model.cells;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import fr.ensicaen.lv223.model.environment.cells.CellType;

class CellTest {
    @Test
    public void testConstructor() {
        int x = 2;
        int y = 3;

        CellType type = CellType.GRASS;
        double intensity = 0.5;
        Cell cell = new Cell(x, y, type, intensity);

        assertEquals(x, cell.getX());
        assertEquals(y, cell.getY());
        assertEquals(type, cell.getType());
        assertEquals(intensity, cell.getIntensity());
    }

    @Test
    public void testGetX() {
        Cell cell = new Cell(1, 2, CellType.DESERT, 0);
        assertEquals(1, cell.getX());
    }

    @Test
    public void testGetY() {
        Cell cell = new Cell(1, 2, CellType.DESERT, 0);
        assertEquals(2, cell.getY());
    }

    @Test
    public void testGetType() {
        Cell cell = new Cell(1, 2, CellType.DESERT, 0);
        assertEquals(CellType.DESERT, cell.getType());
    }

    @Test
    public void testSetIntensity() {
        Cell cell = new Cell(0, 0, CellType.GRASS, 0.5);
        double newIntensity = 0.8;
        cell.setIntensity(newIntensity);
        assertEquals(newIntensity, cell.getIntensity());
    }

    @Test
    public void testToString() {
        Cell cell = new Cell(0, 0, CellType.GRASS, 0.5);
        String expected = "Cell{x=0, y=0, type=GRASS, intensity=0.5}";
        assertNotEquals(expected, cell.toString());
    }
}

