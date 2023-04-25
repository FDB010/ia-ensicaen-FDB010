module fr.ensicaen.lv223 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jackson.databind;
    requires jFuzzyLogic;
    requires java.logging;

    opens fr.ensicaen.lv223 to javafx.fxml;
    opens fr.ensicaen.lv223.util.loader.planetloader to jackson.databind;
    opens fr.ensicaen.lv223.view to javafx.fxml;

    exports fr.ensicaen.lv223;
    exports fr.ensicaen.lv223.model.environment.cells;
}