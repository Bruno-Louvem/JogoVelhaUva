package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruno Louvem on 12/09/2015.
 */
public class KnowledgeBase {
    private ArrayList<List<String>> goals;
    private ArrayList<Integer> OpponentChoices;
    private ArrayList weigth;

    public KnowledgeBase() {
        startGoals();

    }

    private void startGoals() {
        this.goals = new ArrayList<>();
        this.goals.add(Arrays.asList("1","2","3"));
        this.goals.add(Arrays.asList("4","5","6"));
        this.goals.add(Arrays.asList("7","8","9"));
        this.goals.add(Arrays.asList("1","4","7"));
        this.goals.add(Arrays.asList("2","5","8"));
        this.goals.add(Arrays.asList("3","6","9"));
        this.goals.add(Arrays.asList("1","5","9"));
        this.goals.add(Arrays.asList("3","5","7"));
    }

    public ArrayList<List<String>> getGoals() {
        return goals;
    }

    public void setOpponentChoices(int opponentChoice) {
        OpponentChoices.add(opponentChoice);
    }

    public ArrayList getOpponentChoices(){
        return this.OpponentChoices;
    }
}
