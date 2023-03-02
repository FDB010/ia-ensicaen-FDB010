package fr.ensicaen.lv223.util.astar;


import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;
import fr.ensicaen.lv223.util.astar.heuristic.Heuristic;
import fr.ensicaen.lv223.util.astar.heuristic.ManhattanHeuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Astar implements Agent {
    private final AstarCell[][] cells;
    private final AstarCell start;
    private final AstarCell end;
    private ArrayList<AstarCell> openList;
    private final ArrayList<AstarCell> closedList;
    private final Heuristic heuristic;

    public Astar(List<List<UnknownCell>> map, Cell start, Cell end) {
        this.heuristic = new ManhattanHeuristic();
        Cell[][] cells = new Cell[map.size()][];

        for (int i = 0; i < cells.length; i++) {
            List<UnknownCell> currentList = map.get(i);
            Cell[] currentArray = new Cell[currentList.size()];
            for (int j = 0; j < currentArray.length; j++) {
                currentArray[j] = currentList.get(j);
            }
            cells[i] = currentArray;
        }

        openList = new ArrayList<>();
        closedList = new ArrayList<>();

        this.cells = new AstarCell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new AstarCell(cells[i][j]);
            }
        }
        this.start = this.cells[start.getX()][start.getY()];
        this.end = this.cells[end.getX()][end.getY()];

        openList.add(this.start);
    }

    public Astar(Cell[][] cells, Cell start, Cell end) {
        this(cells, start, end, new ManhattanHeuristic());
    }
    public Astar(Cell[][] cells, Cell start, Cell end, Heuristic heuristic) {
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        this.heuristic = heuristic;

        this.cells = new AstarCell[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new AstarCell(cells[i][j]);
            }
        }
        this.start = this.cells[start.getX()][start.getY()];
        this.end = this.cells[end.getX()][end.getY()];

        openList.add(this.start);
    }

    @Override
    public AstarCell[][] getCells() {
        return cells;
    }

    @Override
    public AstarCell getCell(int x, int y) {
        return cells[x][y];
    }

    @Override
    public void compute() {
        while (!isFinished()) {
            computeStep();
        }
    }

    private List<AstarCell> getNeighbours(Cell cell) {
        List<AstarCell> neighbours = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x > 0) { // TOP
            if (cells[x - 1][y].getType() != CellType.UNKNOWN
                    && cells[x - 1][y].getType() != CellType.IMPENETRABLE
                    && cells[x - 1][y].getType() != CellType.LAKE) {
                neighbours.add(cells[x - 1][y]);
            }
            if (y > 0
                    && cells[x - 1][y - 1].getType() != CellType.UNKNOWN
                    && cells[x - 1][y - 1].getType() != CellType.IMPENETRABLE
                    && cells[x - 1][y - 1].getType() != CellType.LAKE) { // TOP LEFT
                neighbours.add(cells[x - 1][y - 1]);
            }
            if (y < cells[0].length - 1
                    && cells[x - 1][y + 1].getType() != CellType.UNKNOWN
                    && cells[x - 1][y + 1].getType() != CellType.IMPENETRABLE
                    && cells[x - 1][y + 1].getType() != CellType.LAKE) { // TOP RIGHT
                neighbours.add(cells[x - 1][y + 1]);
            }
        }
        if (x < cells.length - 1) { // BOTTOM
            if (cells[x + 1][y].getType() != CellType.UNKNOWN
                    && cells[x + 1][y].getType() != CellType.IMPENETRABLE
                    && cells[x + 1][y].getType() != CellType.LAKE) {
                neighbours.add(cells[x + 1][y]);
            }
            if (y > 0
                    && cells[x + 1][y - 1].getType() != CellType.UNKNOWN
                    && cells[x + 1][y - 1].getType() != CellType.IMPENETRABLE
                    && cells[x + 1][y - 1].getType() != CellType.LAKE) { // BOTTOM LEFT
                neighbours.add(cells[x + 1][y - 1]);
            }
            if (y < cells[0].length - 1
                    && cells[x + 1][y + 1].getType() != CellType.UNKNOWN
                    && cells[x + 1][y + 1].getType() != CellType.IMPENETRABLE
                    && cells[x + 1][y + 1].getType() != CellType.LAKE) { // BOTTOM RIGHT
                neighbours.add(cells[x + 1][y + 1]);
            }
        }
        if (y > 0
                && cells[x][y - 1].getType() != CellType.UNKNOWN
                && cells[x][y - 1].getType() != CellType.IMPENETRABLE
                && cells[x][y - 1].getType() != CellType.LAKE) { // LEFT
            neighbours.add(cells[x][y - 1]);
        }
        if (y < cells[0].length - 1
                && cells[x][y + 1].getType() != CellType.UNKNOWN
                && cells[x][y + 1].getType() != CellType.IMPENETRABLE
                && cells[x][y + 1].getType() != CellType.LAKE) { // RIGHT
            neighbours.add(cells[x][y + 1]);
        }

        return neighbours;
    }

    private List<AstarCell> sortList(List<AstarCell> list) {
        List<AstarCell> sortedList = new ArrayList<>();
        while (!list.isEmpty()) {
            AstarCell min = list.get(0);
            for (AstarCell cell : list) {
                if (cell.getF() < min.getF()) {
                    min = cell;
                } else if (cell.getF() == min.getF()) {
                    if (cell.getH() < min.getH()) {
                        min = cell;
                    }
                }
            }
            sortedList.add(min);
            list.remove(min);
        }
        return sortedList;
    }

    @Override
    public void computeStep() {
        if (openList.isEmpty()) {// no path available
            closedList.add(end);
            return;
        }
        if (isFinished())
            return;

        AstarCell current = openList.remove(0);
        List<AstarCell> neighbours = getNeighbours(current);
        for (AstarCell neighbour : neighbours) {
            if (!closedList.contains(neighbour)) {
                neighbour.setParent(current);
                neighbour.setH(heuristic.compute(neighbour.getX(), neighbour.getY(), end.getX(), end.getY()));
                openList.add(neighbour);
            } else if (!neighbour.equals(start) && neighbour.getG() > current.getParent().getG()) {
                neighbour.setParent(current);
            }
        }
        openList = (ArrayList<AstarCell>) sortList(openList);
        closedList.add(current);
    }

    @Override
    public boolean isFinished() {
        return closedList.contains(end);
    }

    public List<Cell> getPath() {
        List<Cell> path = new ArrayList<>();
        if (!isFinished())
            return path;

        AstarCell cell = end;
        while (cell != null) {
            path.add(cell);
            cell = cell.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    public String pathToString() {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : getPath()) {
            sb.append("(").append(cell.getX()).append(",").append(cell.getY()).append(") ");
        }
        return sb.toString();
    }
}
