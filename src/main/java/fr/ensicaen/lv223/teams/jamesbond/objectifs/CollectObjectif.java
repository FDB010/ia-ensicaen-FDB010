package fr.ensicaen.lv223.teams.jamesbond.objectifs;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.agent.robot.specials.FoodRetriever;
import fr.ensicaen.lv223.model.agent.robot.specials.OreExtractor;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.teams.ProjectTeam;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;
import fr.ensicaen.lv223.teams.jamesbond.robots.RobotInterfaceJB;
import fr.ensicaen.lv223.util.astar.Astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static fr.ensicaen.lv223.util.Util.cellListToCommandList;

public class CollectObjectif implements Objectif {
    private Robot robot;
    private CentralizerJB centralizer;

    private UnknownCell focusedCell;
    private CellType focusedCellType;

    public CollectObjectif(Robot currentRobot, CentralizerJB centralizer) {
        this.robot = currentRobot;
        this.centralizer = centralizer;
        focusedCell = null;
        if(robot instanceof OreExtractor) {
            focusedCellType = CellType.ORE;
        } else if(robot instanceof FoodRetriever) {
            focusedCellType = CellType.FOOD;
        }
    }

    @Override
    public Queue<Command> generateCommmandList() {
        PriorityQueue<Command> commandes = new PriorityQueue<>();
        if (this.focusedCell != null) {
            this.focusedCell = this.centralizer.findCellToExtract(this.focusedCellType);
            if(this.focusedCell != null){
                List<List<UnknownCell>> map = centralizer.getCells();
                UnknownCell start = map.get(robot.getPos_x()).get(robot.getPos_y());
                UnknownCell end = map.get(this.focusedCell.getX()).get(this.focusedCell.getY());
                Astar astar = new Astar(map, start, end);

                astar.compute();
                ArrayList<Cell> cellPath = (ArrayList<Cell>) astar.getPath();
                return cellListToCommandList(cellPath, ProjectTeam.JAMES_BOND, robot);
            }
            else {
                return commandes;
            }
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
