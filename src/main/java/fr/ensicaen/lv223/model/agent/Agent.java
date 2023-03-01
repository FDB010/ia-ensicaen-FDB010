package fr.ensicaen.lv223.model.agent;

import fr.ensicaen.lv223.model.agent.command.Command;

import java.util.List;

public interface Agent {
    List<Command> compute();
}
