package fr.ensicaen.lv223.model.cells;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import fr.ensicaen.lv223.model.environment.cells.CellType;

import java.util.Optional;

class CellTest {
    @Test
    public void testConstructor() {
        int x = 2;
        int y = 3;

        CellType type = CellType.GRASS;
        double intensity = 0.5;
        Optional<Cell> cellOptionnal = CellFactory.factory(type, intensity, x, y);
        Cell cell = cellOptionnal.get();
        if (cell == null) {
            fail("Cell is null");
        }

        assertEquals(x, cell.getX());
        assertEquals(y, cell.getY());
        assertEquals(type, cell.getType());
        assertEquals(intensity, cell.getIntensity());
    }

    @Test
    public void testGetX() {
        Optional<Cell> cellOptionnal = CellFactory.factory(CellType.DESERT, 0, 1, 1);
        Cell cell = cellOptionnal.get();
        if (cell == null) {
            fail("Cell is null");
        }
        assertEquals(1, cell.getX());
    }

    @Test
    public void testGetY() {
        Optional<Cell> cellOptionnal = CellFactory.factory(CellType.DESERT, 0, 1, 2);
        Cell cell = cellOptionnal.get();
        if (cell == null) {
            fail("Cell is null");
        }
        assertEquals(2, cell.getY());
    }

    @Test
    public void testGetType() {
        Optional<Cell> cellOptionnal = CellFactory.factory(CellType.DESERT, 0, 1, 1);
        Cell cell = cellOptionnal.get();
        if (cell == null) {
            fail("Cell is null");
        }
        assertEquals(CellType.DESERT, cell.getType());
    }

    @Test
    public void testSetIntensity() {
        Optional<Cell> cellOptionnal = CellFactory.factory(CellType.DESERT, 0.5, 1, 1);
        Cell cell = cellOptionnal.get();
        if (cell == null) {
            fail("Cell is null");
        }
        double newIntensity = 0.8;
        cell.setIntensity(newIntensity);
        assertEquals(newIntensity, cell.getIntensity());
    }

    @Test
    public void testToString() {
        Optional<Cell> cellOptionnal = CellFactory.factory(CellType.DESERT, 0, 1, 1);
        Cell cell = cellOptionnal.get();
        if (cell == null) {
            fail("Cell is null");
        }
        String expected = "Cell{x=0, y=0, type=GRASS, intensity=0.5}";
        assertNotEquals(expected, cell.toString());
    }
}

