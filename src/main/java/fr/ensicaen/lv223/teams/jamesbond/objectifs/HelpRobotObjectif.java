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

public class HelpRobotObjectif implements Objectif {
    private final RobotInterfaceJB currentRobot;
    private final RobotInterfaceJB robotToHelp;
    private final CentralizerJB centralizer;

    public HelpRobotObjectif(RobotInterfaceJB currentRobot, CentralizerJB centralizer, RobotInterfaceJB robotToHelp) {
        this.currentRobot = currentRobot;
        this.robotToHelp = robotToHelp;
        this.centralizer = centralizer;
    }
    @Override
    public Queue<Command> generateCommmandList() {
        Coordinate helpedRobot = robotToHelp.getPosition();
        Coordinate current = currentRobot.getPosition();
        List<List<UnknownCell>> map = centralizer.getCells();
        UnknownCell start = map.get(current.getX()).get(current.getY());
        UnknownCell end = map.get(helpedRobot.getX()).get(helpedRobot.getY());
        Astar astar = new Astar(map, start, end);

        astar.compute();
        ArrayList<Cell> cellPath = (ArrayList<Cell>) astar.getPath();

        // cast la liste de cellules en liste de commande : comment ?
        return cellListToCommandList(cellPath, ProjectTeam.JAMES_BOND, (Robot)currentRobot);
    }
}
