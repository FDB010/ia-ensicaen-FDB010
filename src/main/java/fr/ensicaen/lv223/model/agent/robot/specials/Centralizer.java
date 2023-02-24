package fr.ensicaen.lv223.model.agent.robot.specials;

import fr.ensicaen.lv223.model.agent.robot.Robot;
import fr.ensicaen.lv223.model.agent.robot.RobotType;
import fr.ensicaen.lv223.model.agent.robot.message.Message;

public class Centralizer extends Robot {
<<<<<<< HEAD

    public Centralizer() {
        super();
    }
=======
    public Centralizer(RobotType type) {
        super(type);
    }

>>>>>>> f1148a1eea95f7e32594ccc4cb242f14e19e669f
    @Override
    public boolean isAvailable(Message m) {
        return false;
    }

    @Override
    public void compute() {

    }
}
