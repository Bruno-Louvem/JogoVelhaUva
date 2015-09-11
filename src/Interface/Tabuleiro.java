package Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public class Tabuleiro implements TabuleiroInterface{
    private ArrayList<Collection<Integer>> coordinates;

    public Tabuleiro(){
        coordinates = new ArrayList<Collection<Integer>>();
        coordinates.add(Arrays.asList(1,2,3));
        coordinates.add(Arrays.asList(4,5,6));
        coordinates.add(Arrays.asList(7,8,9));
    }
    @Override
    public ArrayList getTabuleiro() {
        return this.coordinates;
    }

    @Override
    public String toString() {
        return "Tabuleiro{" +
                "coordinates=" + Arrays.toString(coordinates.toArray()) +
                '}';
    }
}
