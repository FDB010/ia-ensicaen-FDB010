package fr.ensicaen.lv223.model.agent.robot.objectif;

import fr.ensicaen.lv223.model.agent.robot.command.Command;

import java.util.PriorityQueue;

public interface Objectif {
    PriorityQueue<Command> generateCommmandList();
}
