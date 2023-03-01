package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.agent.command.CommandFactory;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;
import fr.ensicaen.lv223.model.agent.robot.specials.Centralizer;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.logic.agentInterface.PlanetInterface;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CentralizerJB extends Centralizer {
    private List<List<UnknownCell>> cells;
    private RobotMapper mapper;
    private static CentralizerJB instance;

    private CentralizerJB(RobotType type, CommandFactory commandFactory, PlanetInterface captors, RobotMapper mapper) {
        super(type, commandFactory, captors);
        this.mapper = mapper;
        cells = new ArrayList<>();
        for(int i = 0; i< mapper.getHeigth(); i++){
            cells.add(new ArrayList<>());
            for(int j = 0; j< mapper.getWidth(); j++){
                cells.get(i).add(new UnknownCell(i,j));
            }
        }
    }

    public RobotMapper getMapper(){
        return mapper;
    }

    public static CentralizerJB getInstance(RobotType type, CommandFactory commandFactory, PlanetInterface captors, RobotMapper mapper){
        if(instance == null){
            instance = new CentralizerJB(type, commandFactory, captors, mapper);
        }
        return instance;
    }

    @Override
    public boolean isAvailable(Message m) {
        return false;
    }

    public void updateMap(HashMap<Direction, CellType> map, RobotInterfaceJB robot){
        Coordinate c = robot.getPosition();
        int x = c.getX();
        int y = c.getY();
        for(Direction d : map.keySet()){
            switch (d){
                case NORTH:
                    cells.get(x-1).get(y).update(map.get(d));
                    break;
                case SOUTH:
                    cells.get(x+1).get(y).update(map.get(d));
                    break;
                case EAST:
                    cells.get(x).get(y+1).update(map.get(d));
                    break;
                case WEST:
                    cells.get(x).get(y-1).update(map.get(d));
                    break;
                case NORTH_EAST:
                    cells.get(x-1).get(y+1).update(map.get(d));
                    break;
                case NORTH_WEST:
                    cells.get(x-1).get(y-1).update(map.get(d));
                    break;
                case SOUTH_EAST:
                    cells.get(x+1).get(y+1).update(map.get(d));
                    break;
                case SOUTH_WEST:
                    cells.get(x+1).get(y-1).update(map.get(d));
                    break;
            }
        }
    }
}
