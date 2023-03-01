package fr.ensicaen.lv223.model.environment.planet.behavior;

import javafx.scene.control.Alert;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.JFuzzyLogic;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class FuzzyLogic {

    private String filenameTransformation;
    private String filenameEmotion;
    private FunctionBlock functionBlockTransformation;
    private FunctionBlock functionBlockEmotion;


    public FuzzyLogic(){
        this.filenameTransformation = getClass().getClassLoader().getResource("planet-transformation.fcl").getPath();
        this.filenameEmotion = getClass().getClassLoader().getResource("planet-emotion.fcl").getPath();
        this.functionBlockTransformation = loadFisFile(this.filenameTransformation);
        this.functionBlockEmotion = loadFisFile(this.filenameEmotion);
    }
    private FunctionBlock loadFisFile(String filename) {
        FIS fis = FIS.load(filename, true);
        if (fis == null) {
            System.err.println("Impossible to charge this file '" + filename + "'");
            System.exit(1);
        }
        return fis.getFunctionBlock(null);
    }

    public void executeTransformation(double extraction, double emotion, double sampling){
        setVariablesTransformation(extraction,emotion,sampling);
        this.functionBlockTransformation.evaluate();
    }

    public void executeEmotion(double extraction, double emotion, double sampling){
        setVariablesEmotion(extraction,emotion,sampling);
        this.functionBlockEmotion.evaluate();
    }

    public double getValueVariableTransformation(String name){
        return this.functionBlockTransformation.getVariable(name).getValue();
    }

    public double getValueVariableEmotion(String name){
        return this.functionBlockEmotion.getVariable(name).getValue();
    }

    private void setVariablesTransformation(double extraction, double emotion, double sampling) {
        try {
            this.functionBlockTransformation.setVariable("extraction", extraction);
            this.functionBlockTransformation.setVariable("emotion", emotion);
            this.functionBlockTransformation.setVariable("prelevement", sampling);

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    private void setVariablesEmotion(double extraction, double emotion, double sampling) {
        try {
            this.functionBlockEmotion.setVariable("extraction", extraction);
            this.functionBlockEmotion.setVariable("emotion", emotion);
            this.functionBlockEmotion.setVariable("prelevement", sampling);

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }


    public void viewAllChartTransformation(){
        JFuzzyChart.get().chart(functionBlockTransformation);
    }


}
