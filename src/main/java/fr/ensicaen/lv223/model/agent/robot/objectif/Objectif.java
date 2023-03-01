package fr.ensicaen.lv223.model.agent.robot.objectif;

import fr.ensicaen.lv223.model.agent.command.Command;

import java.util.Queue;

public interface Objectif {
    Queue<Command> generateCommmandList();
}
