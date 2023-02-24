package fr.ensicaen.lv223.model.agent.robot.command;

public interface Command {
    void apply();
    void unapply();
}
