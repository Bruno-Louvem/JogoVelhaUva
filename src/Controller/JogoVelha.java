package Controller;

import Interface.GameScreen;
import Interface.InitScreen;
import Interface.Tabuleiro;
import Interface.TabuleiroInterface;
import Model.Entity.Jogador;
import Model.Entity.JogadorInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public class JogoVelha {

    private JogoVelha jogo;
    private GameScreen Screen;
    private JogadorInterface Jogador;
    private JogadorInterface Machine;
    private JogadorInterface Starter;
    private TabuleiroInterface Tabuleiro;



    public JogoVelha()
    {
        this.Screen = new GameScreen();
    }


    //inicio do Jogo
    public void init()
    {
        int selection = 0;

        do {
            this.Screen.showMenu();
            try {
                selection = this.Screen.getPlayerAction();

                switch (selection){
                    case 1://Começa o Jogo
                        this.startGame();
                        break;
                    case 2://Sai do Jogo
                        this.Screen.goodBye();
                        break;
                    default://Exibe mensagem de escolha inválida
                        this.Screen.invalidOption();
                        break;
                }
            }catch (Exception e){
                System.out.print("Não foi possivel ler a entrada de dados");
            }
        }while (selection != 2);

    }

    private void startGame() {
        boolean config;
        do {
            config = this.settingGame();
        }while(!config);

        Screen.showCurrentPlayer(Starter.getName());

        Tabuleiro = new Tabuleiro();

        Screen.showTabuleiro(Tabuleiro);
    }

    private boolean settingGame() {
        //pegando o nome do usuario
        String PlayerName = this.Screen.getNamePlayer();
        boolean StarterPlayer;
        if(PlayerName.equals("")){
            return false;
        }
        //criando os jogadores
        this.createPlayers(PlayerName);

        //setando quem começa
        StarterPlayer = getInitPlayer();
        return StarterPlayer;
    }

    //Instancia os objetos dos Jogadores
    public void createPlayers(String PlayerName){
        Jogador = createPlayer(PlayerName);
        Machine = createPlayer("Viccenzinho Bolado");
    }
    // Cria um objeto Jogador
    public Jogador createPlayer(String playerName){
        Jogador player = new Jogador();
        player.setName(playerName);
        return player;
    }
    //Seta quem começa
    public boolean getInitPlayer() {
        boolean flag = false;

        do{
            int opt = Screen.getInitPlayerFlag();
            switch (opt) {
                case 0:
                    Starter = Machine;
                    flag = true;
                    break;
                case 1:
                    Starter = Jogador;
                    flag = true;
                    break;
                default:
                    Screen.clean();
                    Screen.invalidOption();
                    break;
            }
        }while (!flag);

        return flag;
    }
}
