package Interface;

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
    public boolean symbolCompleteGoal(String symbol, List<Integer> goal);
    public void reset();
}
