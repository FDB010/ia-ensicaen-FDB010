package fr.ensicaen.lv223.teams.jamesbond.objectifs;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.PriorityQueue;

public class MoveToBaseObjectif implements Objectif {

    private Robot robot;

    private RobotMapper robotMapper;

    public MoveToBaseObjectif(Robot currentRobot, RobotMapper robotMapper) {
        this.robot = currentRobot;
        this.robotMapper = robotMapper;
    }

    @Override
    public PriorityQueue<Command> generateCommmandList() {
        PriorityQueue<Command> commandes = new PriorityQueue<>();
        //Coordinate base = robotMapper.getBaseCoordinate();

        //Astar astar = new Astar(map, start, end);
        //astar.compute();
        PriorityQueue<Command> path = new PriorityQueue<>();
        // cast la liste de cellules en liste de commande : comment ?
        //ArrayList<Cell> cellPath = (ArrayList<Cell>) astar.getPath();
        //while(!cellPath.isEmpty()){
        return commandes;
    }
}
