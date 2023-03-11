package fr.ensicaen.lv223.model.environment.construction;

import java.util.ArrayList;
import java.util.List;

// TODO replace the system of singletons for the presenter
public class WaterPipe {

    public static final List<WaterPipe> waterPipes = new ArrayList<>();
    private int x;
    private int y;

    public static WaterPipe hasPipe(int x, int y) {
        for (WaterPipe pipe: WaterPipe.waterPipes) {
            if (pipe.getX() == x && pipe.getY() == y) {
                return pipe;
            }
        }
        return null;
    }

    public static WaterPipe createWaterPipe(int x, int y) {
        WaterPipe pipe = hasPipe(x, y);
        if (pipe != null)
            return pipe;
        pipe = new WaterPipe(x, y);
        waterPipes.add(pipe);
        return pipe;
    }

    public static void removePipe(WaterPipe pipe) {
        waterPipes.remove(pipe);
    }

    private WaterPipe(int x, int y) {
        this.x = x;
        this.y = y;

        waterPipes.add(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
