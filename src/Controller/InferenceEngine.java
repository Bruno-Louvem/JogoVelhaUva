package Controller;

import Model.Entity.Jogador;
import Model.Entity.Tabuleiro;
import Model.Entity.TabuleiroInterface;
import Model.KnowledgeBase;
import Model.Entity.Machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Bruno Louvem on 12/09/2015.
 */
public class InferenceEngine {
    private KnowledgeBase KB;
    private String starter;
    private String dificultChoice;
    private ArrayList<String> picksOpponent = new ArrayList<String>();
    private ArrayList<List> possibleGoalsOpponent = new ArrayList<List>();
    private ArrayList<String> myPicks = new ArrayList<String>();
    private ArrayList<List> myPossibleGoals = new ArrayList<List>();
    private ArrayList<List> npsByOppoendGoals = new ArrayList<>();
    private ArrayList<List> npsByMyGoals = new ArrayList<>();

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

    public void restart(){

    }

    public void setDificultChoice(String dificultChoice) {
        this.dificultChoice = dificultChoice;
    }

    public String getPick(TabuleiroInterface tabuleiro,Jogador opponent,Jogador machine){
        analyze(tabuleiro, opponent, machine);
        return decide(tabuleiro);
    }
    private void analyze(TabuleiroInterface tabuleiro,Jogador opponent,Jogador machine){
        tabuleiro.getPicksBySymbol(picksOpponent, opponent.getSymbol());
        tabuleiro.getPicksBySymbol(myPicks, machine.getSymbol());
        this.getPossiblesGoalsOpponent();
        this.getMyPossiblesGoals();
        this.loadNPByGoal(possibleGoalsOpponent, picksOpponent,npsByOppoendGoals);
        this.loadNPByGoal(myPossibleGoals, myPicks, npsByMyGoals);
        System.out.println("Possiveis Trajetorias do Opoente");
        for(List<Integer> pGoal: possibleGoalsOpponent) {
            System.out.print(Arrays.toString(pGoal.toArray())+" --- ");
            System.out.println("NP = " + npsByOppoendGoals.get(possibleGoalsOpponent.indexOf(pGoal)).get(1));
        }
        System.out.println("Minhas Possiveis Trajetorias");
        for(List<Integer> pGoal: myPossibleGoals) {
            System.out.print(Arrays.toString(pGoal.toArray())+" --- ");
            System.out.println("NP = " + npsByMyGoals.get(myPossibleGoals.indexOf(pGoal)).get(1));
        }
    }

    private String decide(TabuleiroInterface tabuleiro){
        if(getGreaterNP(npsByMyGoals) == 2){
            ArrayList<List<String>> goals = getGoalByGreaterNP(npsByMyGoals,myPossibleGoals);
            for(List<String> goal:goals){
                for(String pos:goal){
                    if(!myPicks.contains(pos)){
                        return pos;
                    }
                }
            }
        }
        if(getGreaterNP(npsByOppoendGoals) == 2){
            ArrayList<List<String>> goals = getGoalByGreaterNP(npsByOppoendGoals,possibleGoalsOpponent);
            for(List<String> goal:goals){
                for(String pos:goal){
                    if(!picksOpponent.contains(pos)){
                        return pos;
                    }
                }
            }
        }

        if(picksOpponent.size() != 0){//o JogadorH ja jogou
            if(picksOpponent.size() == 1 && myPicks.size()==0){//o JogadorH nesse caso começou é a minha primeira jogada.
                //A primeira jogada do humano foi no meio?
                if((Integer.parseInt(picksOpponent.get(picksOpponent.size()-1)) % 5) == 0){
                    return ""+getRandomImpar(); //jogo em uma quina qualquer

                }else if((Integer.parseInt(picksOpponent.get(picksOpponent.size()-1)) % 2) == 1){// A primeira jogada do humano foi em uma quina?
                    return "5";//jogo no meio
                }else{ // nesse caso o humano jogou em uma pos. par {2,4,6 ou 8}
                    return "5";//jogo no meio
                }

            }else{// o humano ja jogou a primeira vez e eu tambem
                //a ultima jogada dele foi no meio? (quase impossivel)
                if((Integer.parseInt(picksOpponent.get(picksOpponent.size()-1)) % 5) == 0){
                    return ""+getRandomImpar();
                }
                //a ultima jogada dele foi em uma quina?
                if((Integer.parseInt(picksOpponent.get(picksOpponent.size()-1)) % 2) == 1){

                    ArrayList<String> clearPos = tabuleiro.getClearPosition();
                    for (String pos:clearPos){
                        if((Integer.parseInt(pos) % 2) == 1){
                            return pos;
                        }else{
                            return ""+getRandomPar();
                        }
                    }

                }
                if((Integer.parseInt(picksOpponent.get(picksOpponent.size()-1)) % 2) == 0){

                    ArrayList<String> clearPos = tabuleiro.getClearPosition();
                    for (String pos:clearPos){
                        if((Integer.parseInt(pos) % 2) == 1){
                            return pos;
                        }else{
                            return ""+getRandomPar();
                        }
                    }
                }
            }

        }else{//se jogador humano n tem jogadas eu sou o primeiro a jogar e escolho o meio
            return "5";
        }


        return "0";
    }

    private ArrayList<List<String>> getGoalByGreaterNP(ArrayList<List> npsList,ArrayList<List> possibleGoals){
        int maiorNP = 0;
        ArrayList<List<String>> currentCoord = new ArrayList<>();
        currentCoord.add(Arrays.asList("0","0","0"));
        for(List<Integer> np:npsList){
            if(np.get(1)> maiorNP) {
                maiorNP = np.get(1);
                currentCoord.set(0, possibleGoals.get(np.get(0)));
            }
        }
        return currentCoord;
    }

    private int getGreaterNP(ArrayList<List> npsList){
        int maiorNP = 0;
        for(List<Integer> np:npsList){
            if(np.get(1)> maiorNP)
                maiorNP = np.get(1);
        }
        return maiorNP;
    }

    private int getRandomImpar(){
        int i = 1;
        int l = 9;
        int r = (l-i)/2;
        Random rand = new Random();
        int randomNumber = rand.nextInt(r+1);

        return 1+(2*randomNumber);
    }

    private int getRandomPar(){
        int i = 1;
        int l = 9;
        int r = (l-i)/2;
        Random rand = new Random();
        int randomNumber = rand.nextInt(r)+1;

        return 2*randomNumber;
    }

    private void getMyPossiblesGoals(){
        ArrayList<List<String>> goals = this.KB.getGoals();
        for(List<String> goal: goals){
            if(GoalContainPick(goal,myPicks) && !GoalContainPick(goal,picksOpponent)){
                if(!myPossibleGoals.contains(goal))
                    myPossibleGoals.add(goal);
            }
        }

        adjustList(myPossibleGoals,picksOpponent);
    }

    private void loadNPByGoal(ArrayList<List> goals, List<String> picks, ArrayList<List> listNPS){
        ArrayList<List> localListNPS = new ArrayList<>();

        for(List<String> goal: goals){
            localListNPS.add(Arrays.asList(goals.indexOf(goal),getNPByGoal(goal,picks)));
        }
        listNPS.clear();
        listNPS.addAll(localListNPS);

    }

    private int getNPByGoal(List<String> goals,List<String> picks){
        int np = 0;
        for(String pos:goals){
            if(picks.contains(pos)){
                np++;
            }
        }

        return np;
    }

    private void getPossiblesGoalsOpponent(){
        ArrayList<List<String>> goals = this.KB.getGoals();

        for(List<String> goal: goals){
            if(GoalContainPick(goal,picksOpponent) && !GoalContainPick(goal,myPicks)){
                if(!possibleGoalsOpponent.contains(goal))
                    possibleGoalsOpponent.add(goal);
            }
        }

        adjustList(possibleGoalsOpponent,myPicks);


    }
    private void adjustList(ArrayList<List> goals,ArrayList<String> picks){
        ArrayList<List> localGoal = new ArrayList<>();
        localGoal.addAll(goals);
        for(List<String> goal: goals){
            if(GoalContainPick(goal,picks)){
                localGoal.remove(goal);
            }
        }
        goals.clear();
        for(List<String> goal: localGoal){
            goals.add(goal);
        }
    }

    private int getFillLevel(){
        return  0;
    }

    private boolean GoalContainPick(List<String> goal,List<String> picks){
        for(String pos:goal){
            if(picks.contains(pos))
                return true;
        }
        return false;
    }


}
