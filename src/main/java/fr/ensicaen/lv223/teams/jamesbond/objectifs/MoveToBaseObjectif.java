package fr.ensicaen.lv223.teams.jamesbond.objectifs;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.teams.ProjectTeam;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;
import fr.ensicaen.lv223.teams.jamesbond.robots.RobotInterfaceJB;
import fr.ensicaen.lv223.util.astar.Astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static fr.ensicaen.lv223.util.Util.cellListToCommandList;

public class MoveToBaseObjectif implements Objectif {
    private final RobotInterfaceJB robot;

    private final CentralizerJB centralizer;

    public MoveToBaseObjectif(RobotInterfaceJB currentRobot, CentralizerJB centralizer) {
        this.robot = currentRobot;
        this.centralizer = centralizer;
    }

    @Override
    public Queue<Command> generateCommmandList() {
        Coordinate base = centralizer.getPosition();
        Coordinate current = robot.getPosition();
        List<List<UnknownCell>> map = centralizer.getCells();
        Cell start = map.get(current.getX()).get(current.getY());
        Cell end = map.get(base.getX()).get(base.getY());
        Astar astar = new Astar(map, start, end);

        astar.compute();
        ArrayList<Cell> cellPath = (ArrayList<Cell>) astar.getPath();

        // cast la liste de cellules en liste de commande : comment ?
        return cellListToCommandList(cellPath, ProjectTeam.JAMES_BOND, (Robot)robot);
    }
}
