package Controller;

import Model.Entity.Jogador;
import Model.Entity.Machine;

import java.util.ArrayList;

/**
 * Created by Bruno Louvem on 12/09/2015.
 */
public class InferenceEngine {
    private KnowledgeBase KB;
    private String starter;
    private String dificultChoice;

    public  InferenceEngine(){
        this.KB = new KnowledgeBase();
    }

    void setStarter(Jogador $starterO){
        if($starterO instanceof Machine){
            this.starter = "I";
            return;
        }
        this.starter = "Opponent";
    }

    public KnowledgeBase getKB() {
        return KB;
    }

    public void setDificultChoice(String dificultChoice) {
        this.dificultChoice = dificultChoice;
    }
}
