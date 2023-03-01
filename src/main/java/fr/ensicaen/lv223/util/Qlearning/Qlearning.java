package fr.ensicaen.lv223.util.Qlearning;

import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.logic.localisation.Direction;
import org.antlr.analysis.State;

import java.util.Arrays;

public class Qlearning {
    private final Planet planet;
    private final double[][][] qValues;
    private int numberOfEpisodes;
    private double maxLearningRate;
    private double learningRate;
    private double epsilon;
    private static final int DEFAULTPATHCOST = 1;
    private boolean decayingLR;
    public boolean isBestAction = true;
    public boolean receivedPenalty = false;
    QlearningState start;
    QlearningState currentState;
    double[][] stateValues;

    static class Properties {
        public static int LearningRate = 2;
        public static int Epsilon = 3;
        public static int DecayingLR = 4;
    }

    public Qlearning(Planet planet, double aLearningRate, double anEpsilon, boolean aDecayingLR) {
        this.planet = planet;
        maxLearningRate = aLearningRate;
        learningRate = maxLearningRate;
        epsilon = anEpsilon;
        decayingLR = aDecayingLR;
        start = new QlearningState(0, 0);
        currentState = new QlearningState(0, 0);
        stateValues = new double[planet.getWidth()][planet.getHeight()];
        for (double[] doubles : stateValues) {
            Arrays.fill(doubles, 0);
        }
        qValues = new double[planet.getWidth()][planet.getHeight()][Direction.values().length];
        for (double[][] doubles : qValues) {
            for (double[] aDouble : doubles) {
                Arrays.fill(aDouble, 0);
            }
        }
        numberOfEpisodes = 0;
    }

    public void setProperty(int name, String value) {
        if (name == Properties.Epsilon) {
            epsilon = Double.parseDouble(value);
        } else if (name == Properties.LearningRate) {
            maxLearningRate = Double.parseDouble(value);
        } else if (name == Properties.DecayingLR) {
            decayingLR = Boolean.parseBoolean(value);
        }
    }

    public boolean step() {
        double transitionCost;
        int currentAction;
        QlearningState nextState;
        if (hasReachedGoal()) {
            return true;
        }

        if (currentState.getX() == -1 && currentState.getY() == -1) {
            currentState = start;
        }
        currentAction = chooseAction(currentState, Math.random());

        nextState = getNextState(currentState, currentAction, 1.0);

        if (nextState.getX() == currentState.getX() && nextState.getY() == currentState.getY()) {
            receivedPenalty = true;
        } else {
            receivedPenalty = false;
        }

        if (nextState.getX() == planet.getWidth() - 1 && nextState.getY() == planet.getHeight() - 1) {
            isBestAction = true;
        } else {
            isBestAction = false;
        }

        epsilon *= 0.99;
        return hasReachedGoal();


    }

    private double getTransitionCost(QlearningState currentState, int currentAction, QlearningState nextState) {
        if (nextState.getX() == currentState.getX() && nextState.getY() == currentState.getY()) {
            return 0;
        }
        return DEFAULTPATHCOST;
    }

    private QlearningState getNextState(QlearningState currentState, int currentAction, double v) {
        int x = currentState.getX();
        int y = currentState.getY();
        int newX = x + Direction.values()[currentAction].getDirection_x();
        int newY = y + Direction.values()[currentAction].getDirection_y();
        if (newX < 0 || newX >= planet.getWidth() || newY < 0 || newY >= planet.getHeight()) {
            return currentState;
        }
        return new QlearningState(newX, newY);
    }

    private boolean hasReachedGoal() {
        return currentState.getX() == planet.getWidth() - 1 && currentState.getY() == planet.getHeight() - 1;
    }

    private int chooseAction(QlearningState currentState, double random) {
        int action = 0;
        double maxQValue = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < Direction.values().length; i++) {
            if (qValues[currentState.getX()][currentState.getY()][i] > maxQValue) {
                maxQValue = qValues[currentState.getX()][currentState.getY()][i];
                action = i;
            }
        }
        if (random < epsilon) {
            action = (int) (Math.random() * Direction.values().length);
        }
        return action;
    }
}
