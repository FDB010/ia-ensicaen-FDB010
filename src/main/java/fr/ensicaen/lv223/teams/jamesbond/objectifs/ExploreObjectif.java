package fr.ensicaen.lv223.teams.jamesbond.objectifs;

import fr.ensicaen.lv223.model.agent.command.Command;
import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.objectif.Objectif;
import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import fr.ensicaen.lv223.teams.ProjectTeam;
import fr.ensicaen.lv223.teams.jamesbond.UnknownCell;
import fr.ensicaen.lv223.teams.jamesbond.robots.CentralizerJB;
import fr.ensicaen.lv223.teams.jamesbond.robots.RobotInterfaceJB;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static fr.ensicaen.lv223.util.Util.directionListToCommandList;

public class ExploreObjectif implements Objectif {
    private final RobotInterfaceJB robot;
    private final CentralizerJB centralizer;
    private final int serialNumber;

    private Direction majorDirection;
    private Direction secondDirection;

    public ExploreObjectif(RobotInterfaceJB currentRobot, CentralizerJB centralizer, int serialNumber) {
        this.robot = currentRobot;
        this.centralizer = centralizer;
        this.serialNumber = serialNumber;
        switch (serialNumber) {
            case -1 -> {
                majorDirection = Direction.NORTH;
                secondDirection = Direction.EAST;
            }
            case 1 -> {
                majorDirection = Direction.SOUTH;
                secondDirection = Direction.WEST;
            }
            default -> {
                majorDirection = null;
                secondDirection = null;
            }
        }
    }
    @Override
    public Queue<Command> generateCommmandList() {
        Coordinate current = robot.getPosition();
        List<List<UnknownCell>> map = centralizer.getCells();
        Cell start = map.get(current.getX()).get(current.getY());
        int width = map.size();
        int height = map.get(0).size();

        int x = start.getX();
        int y = start.getY();

        ArrayList<Direction> directionArrayList = new ArrayList<>();

        majorDirection = majorDirection.reverseDirection();
        do {
            directionArrayList.add(majorDirection);
            x += majorDirection.getDirection_x();
            y += majorDirection.getDirection_y();
        } while (y < height-2 && y > 1);

        // 3 moves on the side
        for (int i = 0; i < 3; i++) {
            if (x == width-2 || x == 1) {
                secondDirection = secondDirection.reverseDirection();
            }
            directionArrayList.add(secondDirection);
            x += secondDirection.getDirection_x();
            y += secondDirection.getDirection_y();
        }

        return directionListToCommandList(directionArrayList, ProjectTeam.JAMES_BOND, (Robot)robot);
    }

}
