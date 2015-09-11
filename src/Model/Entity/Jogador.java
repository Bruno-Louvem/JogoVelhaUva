package Model.Entity;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public class Jogador implements JogadorInterface{
    private String symbol;
    private String name;


    @Override
    public JogadorInterface setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    @Override
    public JogadorInterface setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
