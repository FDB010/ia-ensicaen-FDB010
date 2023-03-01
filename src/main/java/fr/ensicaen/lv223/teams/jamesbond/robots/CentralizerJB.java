package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.Centralizer;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CentralizerJB extends Centralizer implements RobotInterfaceJB{
    private List<List<UnknownCell>> cells;

    private static CentralizerJB instance;

    private CentralizerJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        super(type, commandFactory, captors);
        cells = new ArrayList<>();
        for(int i = 0; i< 25; i++){
            cells.add(new ArrayList<>());
            for(int j = 0; j< 25; j++){
                cells.get(i).add(new UnknownCell(i,j));
            }
        }
    }



    public static CentralizerJB getInstance(RobotType type, CommandFactory commandFactory, PlanetInterface captors, RobotMapper mapper){
        if(instance == null){
            instance = new CentralizerJB(type, commandFactory, captors);
        }
        return instance;
    }

    @Override
    public boolean isAvailable(Message m) {
        return false;
    }

    public List<List<UnknownCell>> getCells(){
        return cells;
    }

    public void updateMap(HashMap<Direction, CellType> map, RobotInterfaceJB robot){
        Coordinate c = robot.getPosition();
        int x = c.getX();
        int y = c.getY();
        for(Direction d : map.keySet()){
            switch (d) {
                case NORTH -> cells.get(x - 1).get(y).update(map.get(d));
                case SOUTH -> cells.get(x + 1).get(y).update(map.get(d));
                case EAST -> cells.get(x).get(y + 1).update(map.get(d));
                case WEST -> cells.get(x).get(y - 1).update(map.get(d));
                case NORTH_EAST -> cells.get(x - 1).get(y + 1).update(map.get(d));
                case NORTH_WEST -> cells.get(x - 1).get(y - 1).update(map.get(d));
                case SOUTH_EAST -> cells.get(x + 1).get(y + 1).update(map.get(d));
                case SOUTH_WEST -> cells.get(x + 1).get(y - 1).update(map.get(d));
            }
        }
    }

    @Override
    public CentralizerJB getCentralizer() {
        return this;
    }

    @Override
    public void updateCentralizerMap() {
        this.updateMap(captors.getSurrounding(this), this);
    }

    @Override
    public Coordinate getPosition() {
        return new Coordinate(12,12);
    }

    public ArrayList<UnknownCell> closestUndiscoveredCells(){
        int x = 12;
        int y = 12;
        ArrayList<UnknownCell> closestCells = new ArrayList<>();
        int distance = 1;
        while(closestCells.size() < 3){
            for(int i = x-distance; i <= x+distance; i++){
                for(int j = y-distance; j <= y+distance; j++){
                    if(i >= 0 && i < 15 && j >= 0 && j < 25){
                        if(cells.get(i).get(j).getType() == CellType.UNKNOWN && !(i == 12 && j == 12)){
                            closestCells.add(cells.get(i).get(j));
                        }
                    }
                }
            }
            if(closestCells.size() > 0){
                return closestCells;
            }
            distance++;
        }
        return closestCells;
    }

    public UnknownCell closestCellToRobot(RobotInterfaceJB robot, ArrayList<UnknownCell> cellsList){
        System.out.println("Robot Position " + robot.getPosition().getX() + " " + robot.getPosition().getY());
        System.out.println("closestCellToRobot " + cellsList.size());
        int x = robot.getPosition().getX();
        int y = robot.getPosition().getY();
        ArrayList<UnknownCell> closestCell = new ArrayList<>();
        int distance = 100;
        for(UnknownCell c : cellsList){
            int d = Math.max(Math.abs(x-c.getX()),Math.abs(y-c.getY()));
            if(d < distance && d != 0){
                distance = d;
                closestCell.clear();
                closestCell.add(c);
            }
            if(d == distance){
                closestCell.add(c);
            }
        }
        Random rand = new Random();
        UnknownCell ce = closestCell.get(rand.nextInt(closestCell.size()));
        System.out.println("closestCellToRobot " + ce.getX() + " " + ce.getY());
        return ce;
    }
}
