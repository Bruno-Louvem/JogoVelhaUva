package Model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public interface TabuleiroInterface {
    public ArrayList getTabuleiro();
    public ArrayList getValidTabuleiro();
    public boolean validPick(String pick);
    public void setPick(String pick,String symbol);
    public boolean symbolCompleteGoal(String symbol, List<String> goal);
    public void getPicksBySymbol(ArrayList<String> picks,String symbol);
    public void reset();
    public String getPosByCoordinates(int lin, int col);
}
