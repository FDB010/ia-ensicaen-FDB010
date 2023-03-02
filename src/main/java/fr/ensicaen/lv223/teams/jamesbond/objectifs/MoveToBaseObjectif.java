package fr.ensicaen.lv223.teams.jamesbond.objectifs;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.specials.MineralCell;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.teams.ProjectTeam;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;
import fr.ensicaen.lv223.teams.jamesbond.robots.RobotInterfaceJB;
import fr.ensicaen.lv223.util.astar.Astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static fr.ensicaen.lv223.util.Util.cellListToCommandList;

public class MoveToBaseObjectif implements Objectif {

    private RobotInterfaceJB robot;

    private CentralizerJB centralizer;

    public MoveToBaseObjectif(RobotInterfaceJB currentRobot, CentralizerJB centralizer) {
        this.robot = currentRobot;
        this.centralizer = centralizer;
    }

    @Override
    public PriorityQueue<Command> generateCommmandList() {
        Coordinate base = centralizer.getPosition();
        Coordinate current = robot.getPosition();
        List<List<UnknownCell>> map = centralizer.getCells();
        UnknownCell start = map.get(current.getX()).get(current.getY());
        UnknownCell end = map.get(base.getX()).get(base.getY());

        Cell[][] cells = new Cell[map.size()][];

        for (int i = 0; i < cells.length; i++) {
            List<UnknownCell> currentList = map.get(i);
            Cell[] currentArray = new Cell[currentList.size()];
            for (int j = 0; j < currentArray.length; j++) {
                currentArray[j] = currentList.get(j);
            }
            cells[i] = currentArray;
        }
        Astar astar = new Astar(cells, start, end);

        astar.compute();
        ArrayList<Cell> cellPath = (ArrayList<Cell>) astar.getPath();

        // cast la liste de cellules en liste de commande : comment ?
        return cellListToCommandList(cellPath, ProjectTeam.JAMES_BOND, (Robot)robot);
    }


}
