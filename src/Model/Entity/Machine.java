package Model.Entity;

import Controller.InferenceEngine;

/**
 * Created by Bruno Louvem on 12/09/2015.
 */
public class Machine extends Jogador {
    private InferenceEngine IE;

    public Machine() {

    }

    public void setIE(InferenceEngine IE) {
        this.IE = IE;
    }

    public InferenceEngine getIE() {
        return IE;
    }
}
