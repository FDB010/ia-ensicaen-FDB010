package fr.ensicaen.lv223.teams.jamesbond.objectifs;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.agent.robot.specials.FoodRetriever;
import fr.ensicaen.lv223.model.agent.robot.specials.OreExtractor;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;

import java.util.PriorityQueue;
import java.util.Queue;

public class CollectObjectif implements Objectif {
    private Robot robot;
    private CentralizerJB centralizer;

    private UnknownCell focusedCell;

    public CollectObjectif(Robot currentRobot, CentralizerJB centralizer) {
        this.robot = currentRobot;
        this.centralizer = centralizer;
        focusedCell = null;
    }

    @Override
    public Queue<Command> generateCommmandList() {
        PriorityQueue<Command> commandes = new PriorityQueue<>();
        if (this.focusedCell != null) {
            // Si capacité max atteinte, se rendre au centralisateur
            // Sinon si sur une cellule de minerais, la miner
            // Sinon, se rendre sur la cellule de minerais
        } else {
            // Si centralisateur connait une case de minerais non utilisée, la récuppérer puis return this.generateCommmandList()
            if (robot instanceof OreExtractor) {
                focusedCell = centralizer.findCellToExtract(CellType.ORE);
            } else if (robot instanceof FoodRetriever) {
                focusedCell = centralizer.findCellToExtract(CellType.FOOD);
            }
            // Sinon, se balader vers les cases non découvertes
        }
        return commandes;
    }

    private void oreExtractorAction() {
    }

    private void foodRetrieverAction() {
    }
}
