package fr.ensicaen.lv223.model.environment.planet.behavior;

import fr.ensicaen.lv223.model.environment.planet.Planet;
import fr.ensicaen.lv223.model.environment.planet.behavior.metamorphosis.MetamorphosisType;
import fr.ensicaen.lv223.model.environment.planet.reaction.ExtractionType;
import fr.ensicaen.lv223.model.environment.planet.reaction.SamplingType;
import fr.ensicaen.lv223.model.environment.planet.state.PlanetEmotion;
import javafx.scene.control.Alert;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

import java.util.Objects;

public class FuzzyLogic {

    private final Planet planet;
    private String filenameTransformation;
    private String filenameEmotion;
    private final FunctionBlock functionBlockTransformation;
    private final FunctionBlock functionBlockEmotion;


    public FuzzyLogic( Planet planet){
        this.planet = planet;
        this.filenameTransformation = Objects
                .requireNonNull(getClass().getClassLoader().getResource("planet-transformation.fcl")).getPath();
        this.filenameEmotion = Objects
                .requireNonNull(getClass().getClassLoader().getResource("planet-emotion.fcl")).getPath();
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

    public ExtractionType getExtractionType(int extractionPct) {
        try {
            this.functionBlockEmotion.setVariable("extraction", extractionPct);
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        functionBlockEmotion.evaluate();

        return defineExtraction(extractionPct);
    }

    public SamplingType getSamplingType(int samplingPct) {
        try {
            this.functionBlockEmotion.setVariable("extraction", samplingPct);
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        functionBlockEmotion.evaluate();
        return defineSampling(samplingPct);
    }

    public MetamorphosisType getMetamorphsisType(int samplingPct) {
        try {
            this.functionBlockTransformation.setVariable("extraction", ((planet.getStockMineral()/planet.getInitalStockMineral() * 100)-100));
            this.functionBlockTransformation.setVariable("sampling", ((planet.getStockWater()/planet.getInitalStockWater() * 100)-100));
            this.functionBlockTransformation.setVariable("emotion", planet.getCurrentEmotion().ordinal());
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        functionBlockTransformation.evaluate();
        return defineMetamorphosis(functionBlockTransformation.getVariable("metamorphose").getValue());
    }

    private ExtractionType defineExtraction(int value){
        if(this.functionBlockEmotion.getVariable("extraction").getMembershipFunction("petite").membership(value) == 1.0){
            return ExtractionType.SMALL;
        }
        if(this.functionBlockEmotion.getVariable("extraction").getMembershipFunction("moyenne").membership(value) == 1.0){
            return ExtractionType.MEDIUM;
        }
        if(this.functionBlockEmotion.getVariable("extraction").getMembershipFunction("grande").membership(value) == 1.0){
            return ExtractionType.GREAT;
        }
        return ExtractionType.SMALL;
    }

    private SamplingType defineSampling(int value){
        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("petit").membership(value) == 1.0){
            return SamplingType.NEGLIGIBLE;
        }
        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("petit").membership(value) == 1.0){
            return SamplingType.SMALL;
        }
        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("moyen").membership(value) == 1.0){
            return SamplingType.MEDIUM;
        }
        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("grand").membership(value) == 1.0){
            return SamplingType.GREAT;
        }
        return SamplingType.NEGLIGIBLE;
    }

    private MetamorphosisType defineMetamorphosis(double value){

        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("limitee").membership(value) == 1.0){
            return MetamorphosisType.NEGLIGIBLE;
        }
        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("petite").membership(value) == 1.0){
            return MetamorphosisType.SMALL;
        }
        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("moyenne").membership(value) == 1.0){
            return MetamorphosisType.MEDIUM;
        }
        if(this.functionBlockEmotion.getVariable("sampling").getMembershipFunction("grande").membership(value) == 1.0){
            return MetamorphosisType.GREAT;
        }
        return MetamorphosisType.NEGLIGIBLE;
    }

    public PlanetEmotion getCurrentEmotion(int emotionPct) {
        // TODO - see getCurrentEmotion() method on planet
        return PlanetEmotion.HAPPY;
    }

    public MetamorphosisType getMetamorphosisType() {
        // TODO get the current chances to metamorphose
        return MetamorphosisType.SMALL;
    }
}
