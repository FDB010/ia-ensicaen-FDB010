package fr.ensicaen.lv223.util.Qlearning;

public class QlearningState {
    int x;
    int y;

    public QlearningState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void copy(QlearningState newState) {
        x = newState.x;
        y = newState.y;
    }

    @Override
    public boolean equals(Object Obj) {
        QlearningState st = (QlearningState) Obj;
        return (x == st.x && y == st.y);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
