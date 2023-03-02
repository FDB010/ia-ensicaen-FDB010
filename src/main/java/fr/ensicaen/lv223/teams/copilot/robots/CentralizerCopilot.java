package fr.ensicaen.lv223.teams.copilot.robots;

import fr.ensicaen.lv223.model.agent.command.Command;
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

public class CentralizerCopilot extends Centralizer {
    private static CentralizerCopilot instance;
    private List<List<UnknownCell>> cells;
    private RobotMapper mapper;
    int height = 0;
    int width = 0;
    private CentralizerCopilot(RobotType type, CommandFactory commandFactory, PlanetInterface captors) {
        super(type, commandFactory, captors);
    }

    @Override
    public boolean isAvailable(Message m) {
        return false;
    }

    public void setCells(List<List<UnknownCell>> cells){
        this.cells = cells;
    }

    @Override
    public List<Command> compute() {
        manageMessages();
        List<Command> commands = new ArrayList<>();
        return commands;
    }
    public RobotMapper getMapper(){
        return mapper;
    }

    static public CentralizerCopilot getInstance(RobotType type, CommandFactory commandFactory, PlanetInterface captors){
        if(instance == null){
            instance = new CentralizerCopilot(type, commandFactory, captors);
        }
        return instance;
    }



    private void manageMessages(){
        for (Message m : this.priorityQueueMessage) {
            switch (m.getType()) {
                case CARTOGRAPHER_OVER:
                    // Init the cells
                    cells = new ArrayList<>();
                    for (int i = 0; i < height; i++) {
                        cells.add(new ArrayList<>());
                        for (int j = 0; j < width; j++) {
                            cells.get(i).add(new UnknownCell(i, j));
                        }
                    }
            }
        }
    }

    public void addHeight(int height){
        this.height += height;
    }

    public void addWidth(int width){
        this.width += width;
    }

    public void updateMap(HashMap<Direction, CellType>map ,RobotInterfaceCopilot robot){
        Coordinate coord =robot.getPosition();
        int x = coord.getX();
        int y = coord.getY();
        for(Direction dir : map.keySet()){
            switch (dir){
                case NORTH:
                    cells.get(x-1).get(y).update(map.get(dir));
                    break;
                case SOUTH:
                    cells.get(x+1).get(y).update(map.get(dir));
                    break;
                case EAST:
                    cells.get(x).get(y+1).update(map.get(dir));
                    break;
                case WEST:
                    cells.get(x).get(y-1).update(map.get(dir));
                    break;
            }
        }
    }
}
