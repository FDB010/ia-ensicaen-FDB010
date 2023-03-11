package fr.ensicaen.lv223.teams.jamesbond.robots;

import fr.ensicaen.lv223.model.logic.localisation.Coordinate;

public interface RobotInterfaceJB {
    CentralizerJB getCentralizer();
    void updateCentralizerMap();
    Coordinate getPosition();
}
