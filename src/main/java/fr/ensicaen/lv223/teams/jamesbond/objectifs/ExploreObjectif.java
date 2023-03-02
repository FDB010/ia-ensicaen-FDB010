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

public class ExploreObjectif implements Objectif {
    private final RobotInterfaceJB robot;
    private final CentralizerJB centralizer;

    public ExploreObjectif(RobotInterfaceJB currentRobot, CentralizerJB centralizer) {
        this.robot = currentRobot;
        this.centralizer = centralizer;
    }
    @Override
    public Queue<Command> generateCommmandList() {
        Coordinate current = robot.getPosition();
        UnknownCell focus = centralizer.closestCellToRobot(robot, centralizer.closestUndiscoveredCells());
        List<List<UnknownCell>> map = centralizer.getCells();
        Cell start = map.get(current.getX()).get(current.getY());
        Cell end = map.get(focus.getX()).get(focus.getY());
        Astar astar = new Astar(map, start, end);
        astar.compute();

        ArrayList<Cell> cellPath = new ArrayList<>();
        cellPath.add(new UnknownCell(current.getX(), current.getY()));
        cellPath.addAll(astar.getPath());
        System.out.println("Départ: " + start.getX() + " " + start.getY());
        System.out.println("Arrivée: " + end.getX() + " " + end.getY());
        System.out.println("Nbr de cases pour y arriver (départ et arrivé compris): " + cellPath.size());
        for(Cell c : cellPath){
            System.out.println(c.getX() + " " + c.getY());
        }
        return cellListToCommandList(cellPath, ProjectTeam.JAMES_BOND, (Robot)robot);
    }

}
