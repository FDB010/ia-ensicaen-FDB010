package fr.ensicaen.lv223.util.astar;

import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AStarTest {
    @Test
    public void testConstructor() {
        Planet planet = new Planet();
        Cell[][] cells = new Cell[planet.getCells().size()-1][];

        for (int i = 0; i < planet.getCells().size(); i++) {
            List<Cell> currentList = planet.getCells().get(i);
            Cell[] currentArray = new Cell[currentList.size()-1];
            for (int j = 0; j < currentArray.length; j++) {
                currentArray[j] = currentList.get(j);
            }
            cells[i] = currentArray;
        }

        Cell start = planet.getCells().get(0).get(0);
        Cell end = planet.getCells()
                        .get(planet.getCells().size()-1)
                        .get(planet.getCells().get(0).size()-1);
        Astar astar = new Astar(cells, start, end);

        assert astar.getCells().length == planet.getCells().size();
        assert astar.getCells()[0].length == planet.getCells().get(0).size();
        assert astar.getCell(0, 0).getX() == start.getX();
        assert astar.getCell(0, 0).getY() == start.getY();
        assert astar.getCell(planet.getCells().size()-1,
                             planet.getCells().get(0).size()-1).getX() == end.getX();
        assert astar.getCell(planet.getCells().size()-1,
                                planet.getCells().get(0).size()-1).getY() == end.getY();
    }

    @Test
    public void testCompute() {
        Planet planet = new Planet();
        Cell[][] cells = new Cell[planet.getCells().size()-1][];

        for (int i = 0; i < cells.length; i++) {
            List<Cell> currentList = planet.getCells().get(i);
            Cell[] currentArray = new Cell[currentList.size()-1];
            for (int j = 0; j < currentArray.length; j++) {
                currentArray[j] = currentList.get(j);
            }
            cells[i] = currentArray;
        }

        Cell start = planet.getCells().get(0).get(0);
        Cell end = cells[cells.length-1]
                        [cells[cells.length-1].length-1];

        Astar astar = new Astar(cells, start, end);

        astar.compute();

        assert astar.isFinished();
    }
}
