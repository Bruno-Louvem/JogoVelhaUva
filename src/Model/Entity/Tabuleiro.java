package Model.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public class Tabuleiro implements TabuleiroInterface {
    private ArrayList<List<String>> coordinates;
    private ArrayList<List<String>> fixCoordinates;

    public Tabuleiro(){
        this.startCoordinates();
    }

    public void startCoordinates(){
        this.coordinates = new ArrayList<>();
        this.coordinates.clear();
        this.coordinates.add(Arrays.asList("1","2","3"));
        this.coordinates.add(Arrays.asList("4","5","6"));
        this.coordinates.add(Arrays.asList("7", "8", "9"));

        this.fixCoordinates = new ArrayList<>();
        this.fixCoordinates.clear();
        this.fixCoordinates.add(Arrays.asList("1","2","3"));
        this.fixCoordinates.add(Arrays.asList("4","5","6"));
        this.fixCoordinates.add(Arrays.asList("7", "8", "9"));
    }

    @Override
    public ArrayList getTabuleiro() {
        return this.coordinates;
    }

    @Override
    public ArrayList getValidTabuleiro() {
        return this.coordinates;
    }

    @Override
    public boolean validPick(String pick) {
        for(List<String> lin : this.coordinates){
            for(String pos: lin){
                if(pos.equals(pick))
                    return true;
            }
        }
        return false;
    }

    public void setPick(String pick, String symbol){
        for(List<String> lin : this.coordinates){
            for(String pos: lin){
                if(pos.equals(pick)){
                    this.coordinates.get(this.coordinates.indexOf(lin)).set(lin.indexOf(pos),symbol);
                }

            }
        }
    }

    @Override
    public boolean symbolCompleteGoal(String symbol, List<String> goal) {
        for(String pos: goal){
            if(!this.getCoordinatesByPos(pos).equals(symbol)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void getPicksBySymbol(ArrayList<String> picks,String symbol) {

        for(int i = 0; i < 3 ; i++){
            List<String> linha = this.coordinates.get(i);
            for(int j = 0; j < 3;j++){
                String pos = linha.get(j);
                if(pos.equals(symbol)){
                    String pick = this.getPosByCoordinates(i, j);
                    if(!picks.contains(pick)){
                        picks.add(pick);
                    }
                }
            }
        }
        //Collections.sort(picks);
    }

    @Override
    public void reset() {
        this.startCoordinates();
    }

    @Override
    public String getPosByCoordinates(int lin, int col) {
        return this.fixCoordinates.get(lin).get(col);
    }

    public String getCoordinatesByPos(String pos) {
        switch (pos){
            case "1":
                return this.coordinates.get(0).get(0);//1 0+0+1
            case "2":
                return this.coordinates.get(0).get(1);//2 0+1+1
            case "3":
                return this.coordinates.get(0).get(2);//3 0+2+1
            case "4":
                return this.coordinates.get(1).get(0);//4 1+0+3
            case "5":
                return this.coordinates.get(1).get(1);//5 1+1+3
            case "6":
                return this.coordinates.get(1).get(2);//6 1+2+3
            case "7":
                return this.coordinates.get(2).get(0);//7 2+0+5
            case "8":
                return this.coordinates.get(2).get(1);//8 2+1+5
            case "9":
                return this.coordinates.get(2).get(2);//9 2+2+5
        }
        return "";
    }

    @Override
    public String toString() {
        return "Tabuleiro{" +
                "coordinates=" + Arrays.toString(coordinates.toArray()) +
                '}';
    }
}
