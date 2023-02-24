package fr.ensicaen.lv223.model.agent.robot.objectif;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.command.Command;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.util.astar.Astar;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HelpRobotObjectif implements Objectif{
    private Robot currentRobot;
    private Robot robotToHelp;

    public HelpRobotObjectif(Robot currentRobot, Robot robotToHelp) {
        this.currentRobot = currentRobot;
        this.robotToHelp = robotToHelp;
    }
    @Override
    public PriorityQueue<Command> generateCommmandList() {
        // Start : position du robot. End : position du robot à aider.
        //Cell start = currentRobot.getCell();
        //Cell end = robotToHelp.getCell();
        //Astar astar = new Astar(cells, start, end);
        //astar.compute();
        PriorityQueue<Command> path = new PriorityQueue<>();
        // cast la liste de cellules en liste de commande : comment ?
        //ArrayList<Cell> cellPath = (ArrayList<Cell>) astar.getPath();
        //while(!cellPath.isEmpty()){

        //}
        return path;
    }
}
