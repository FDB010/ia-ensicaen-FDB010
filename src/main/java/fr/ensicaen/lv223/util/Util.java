package fr.ensicaen.lv223.util;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.command.CommandType;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.teams.ProjectTeam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class Util {
    public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static List<Coordinate> getNeighbors(Coordinate coord, int width, int height) {
        ArrayList<Coordinate> neighbors = new ArrayList<>();
        int x = coord.x;
        int y = coord.y;
        if (x > 0) {
            neighbors.add(new Coordinate(x - 1, y));
            if (y > 0) {
                neighbors.add(new Coordinate(x - 1, y - 1));
            }
            if (y < width - 1) {
                neighbors.add(new Coordinate(x - 1, y + 1));
            }
        }
        if (x < height - 1) {
            neighbors.add(new Coordinate(x + 1, y));
            if (y > 0) {
                neighbors.add(new Coordinate(x + 1, y - 1));
            }
            if (y < width - 1) {
                neighbors.add(new Coordinate(x + 1, y + 1));
            }
        }
        if (y > 0) {
            neighbors.add(new Coordinate(x, y - 1));
        }
        if (y < width - 1) {
            neighbors.add(new Coordinate(x, y + 1));
        }
        return neighbors;
    }

    public static Queue<Command> cellListToCommandList(ArrayList<Cell> AStarPath, ProjectTeam team, Robot rob) {
        LinkedList<Command> commandList = new LinkedList<>();
        for (int i = 0; i < AStarPath.size() - 1; i++) {
            Cell current = AStarPath.get(i);
            Cell next = AStarPath.get(i + 1);
            if(team==ProjectTeam.JAMES_BOND){
                Command cmd = rob.getCommandFactory().createCommand(rob, CommandType.MOVEJB, getDirectionTo(current, next));
                commandList.add(cmd);
            }
            else{
                //todo
            }
        }
        return commandList;
    }

    public static Queue<Command> directionListToCommandList(ArrayList<Direction> directionArrayList, ProjectTeam team, Robot rob) {
        LinkedList<Command> commandList = new LinkedList<>();
        for (Direction direction : directionArrayList) {
            if(team==ProjectTeam.JAMES_BOND) {
                Command cmd = rob.getCommandFactory().createCommand(rob, CommandType.MOVEJB, direction);
                commandList.add(cmd);
            } else{
                //todo
            }
        }
        return commandList;
    }



    public static Direction getDirectionTo(Cell previous, Cell next) {
        if (next.getX() == previous.getX()) {
            if (next.getY() == previous.getY() + 1) {
                return Direction.SOUTH;
            } else if (next.getY() == previous.getY() - 1) {
                return Direction.NORTH;
            }
        } else if (next.getY() == previous.getY()) {
            if (next.getX() == previous.getX() + 1) {
                return Direction.EAST;
            } else if (next.getX() == previous.getX() - 1) {
                return Direction.WEST;
            }
        }
        else if (next.getX() == previous.getX() + 1 && next.getY() == previous.getY() + 1){
            return Direction.SOUTH_EAST;
        }
        else if (next.getX() == previous.getX() - 1 && next.getY() == previous.getY() + 1){
            return Direction.SOUTH_WEST;
        }
        else if (next.getX() == previous.getX() + 1 && next.getY() == previous.getY() - 1){
            return Direction.NORTH_EAST;
        }
        else if (next.getX() == previous.getX() - 1 && next.getY() == previous.getY() - 1){
            return Direction.NORTH_WEST;
        }
        return null;
    }
}
