package fr.ensicaen.lv223.presenter.colony;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Coordinate;
import fr.ensicaen.lv223.model.logic.localisation.RobotMapper;
import fr.ensicaen.lv223.presenter.IPresenter;
import fr.ensicaen.lv223.util.Util;
import fr.ensicaen.lv223.view.CellView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

/**
 * The colony presenter is responsible for coordinating the interactions between
 * the model ({@link Robot}) and the view ({@link IPresenter}). It implements
 * the simulation logic and updates the view accordingly. It is responsible for
 * the drawing the robots and to blur non-visible cases.
 */
public class ColonyPresenter {
    private IPresenter view;
    private final RobotMapper robotMapper;
    public ColonyPresenter(RobotMapper robotMapper) {
        this.robotMapper = robotMapper;
    }

    public void drawColony() {
        List<List<CellView>> cellViews = view.getCellView();
        GaussianBlur blur = new GaussianBlur(10);
        GaussianBlur noBlur = new GaussianBlur(0.0);
        for (int i = 0; i < cellViews.size(); i++) {
            List<CellView> row = cellViews.get(i);
            for (int j = 0; j < row.size(); j++) {
                CellView cell = row.get(j);
                cell.getPane().setEffect(blur);
                cell.getRobotView().hide();
            }
        }

        for (Robot robot : robotMapper.getRobots()) {
            Coordinate robotCoordinate = robotMapper.getCoordinate(robot);
            cellViews.get(robotCoordinate.x).get(robotCoordinate.y).getRobotView().setRobotType(robot.type);
            cellViews.get(robotCoordinate.x).get(robotCoordinate.y).getRobotView().setVisible();
            cellViews.get(robotCoordinate.x).get(robotCoordinate.y).getPane().setEffect(noBlur);
            for (Coordinate coord : Util.getNeighbors(robotCoordinate, robotMapper.getHeigth(), robotMapper.getWidth())) {
                cellViews.get(coord.x).get(coord.y).getPane().setEffect(noBlur);
            }
        }
    }

    public void setView(IPresenter view) {
        this.view = view;
    }

    public void updateStatus() {
        view.updateNbRobots(robotMapper.getRobots().size());
    }
}
