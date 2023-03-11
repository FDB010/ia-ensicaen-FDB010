package fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis;

public enum MetamorphosisType {
    NONE(0),
    NEGLIGIBLE(10),
    SMALL(40),
    MEDIUM(60),
    GREAT(80);

    public final int chanceOfRealization;
    public static final int MAX_CHANCE = 100;
    MetamorphosisType(int value) {
        this.chanceOfRealization = value;
    }
}
