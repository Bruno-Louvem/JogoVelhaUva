package Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public class Tabuleiro implements TabuleiroInterface{
    private ArrayList<List<String>> coordinates;

    public Tabuleiro(){
        coordinates = new ArrayList<>();
        coordinates.add(Arrays.asList("1","2","3"));
        coordinates.add(Arrays.asList("4","5","6"));
        coordinates.add(Arrays.asList("7","8","9"));
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

                    coordinates.get(coordinates.indexOf(lin)).set(lin.indexOf(pos),symbol);
                }

            }
        }
    }

    @Override
    public boolean symbolCompleteGoal(String symbol, List<Integer> goal) {
        for(int pos: goal){
            if(!this.getCoordinatesByIntPos(pos).equals(symbol)){
                return false;
            }
        }
        return true;
    }

    public String getCoordinatesByIntPos(int pos) {
        switch (pos){
            case 1:
                return this.coordinates.get(0).get(0);
            case 2:
                return this.coordinates.get(0).get(1);
            case 3:
                return this.coordinates.get(0).get(2);
            case 4:
                return this.coordinates.get(1).get(0);
            case 5:
                return this.coordinates.get(1).get(1);
            case 6:
                return this.coordinates.get(1).get(2);
            case 7:
                return this.coordinates.get(2).get(0);
            case 8:
                return this.coordinates.get(2).get(1);
            case 9:
                return this.coordinates.get(2).get(2);
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
