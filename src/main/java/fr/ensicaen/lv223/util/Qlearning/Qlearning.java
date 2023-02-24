package fr.ensicaen.lv223.util.Qlearning;

import org.antlr.analysis.State;

public class Qlearning {

    // Définir les paramètres
    private double alpha; // taux d'apprentissage
    private double gamma; // facteur d'actualisation
    private double epsilon; // taux d'exploration
    static class Properties {
        private double alpha = 0.1;
        private double gamma = 0.9;
        private double epsilon = 0.1;
    }
    //Initialization de l'environnement
    private State init() {
        return null;
    }

    // Sélectionner une action
    private Action selectAction(State state) {
        return null;
    }

    void run(int iterations) {
        // Initialiser les paramètres
        Properties properties = new Properties();
        alpha = properties.alpha;
        gamma = properties.gamma;
        epsilon = properties.epsilon;

        // Exécuter le Q-learning
        for (int i = 0; i < iterations; i++) {
            // Initialiser l'état
            State state = init();
            // Tant que l'état n'est pas terminal
            while (!state.isTerminal()) {
                // Sélectionner une action
                Action action = selectAction(state);
                // Exécuter l'action
                State nextState = execute(action);
                // Mettre à jour la valeur de l'état
                update(state, action, nextState);
                // Passer à l'état suivant
                state = nextState;
            }
        }
    }
