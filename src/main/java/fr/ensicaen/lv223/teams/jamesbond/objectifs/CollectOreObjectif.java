package fr.ensicaen.lv223.teams.jamesbond.objectifs;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.environment.cells.specials.MineralCell;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;

import java.util.PriorityQueue;
import java.util.Queue;

public class CollectOreObjectif implements Objectif {

    private Robot robot;

    private MineralCell focusedCell = null;
    private RobotMapper robotMapper;

    public CollectOreObjectif(Robot currentRobot, RobotMapper robotMapper) {
        this.robot = currentRobot;
        this.robotMapper = robotMapper;
    }

    @Override
    public Queue<Command> generateCommmandList() {
        PriorityQueue<Command> commandes = new PriorityQueue<>();
        if(this.focusedCell != null){
            // Si capacité max atteinte, se rendre au centralisateur
            // Sinon si sur une cellule de minerais, la miner
            // Sinon, se rendre sur la cellule de minerais
        }
        else{
            // Si centralisateur connait une case de minerais non utilisée, la récuppérer puis return this.generateCommmandList()
            // Sinon, se balader vers les cases non découvertes
        }
        return commandes;
    }
}
