package fr.ensicaen.lv223.model.agent.robot.objectif;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.cells.specials.MineralCell;

import java.util.PriorityQueue;

public class CollectOreObjectif implements Objectif{

    private Robot robot;

    private MineralCell focusedCell = null;

    public CollectOreObjectif(Robot currentRobot) {
        this.robot = currentRobot;
    }

    @Override
    public PriorityQueue<Command> generateCommmandList() {
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
