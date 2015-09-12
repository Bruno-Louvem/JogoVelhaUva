package Controller;

import Interface.GameScreen;
import Interface.Tabuleiro;
import Interface.TabuleiroInterface;
import Model.Entity.Jogador;
import Model.Entity.JogadorHumano;
import Model.Entity.Machine;

import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public class JogoVelha {

    private JogoVelha jogo;
    private InferenceEngine IE;
    private GameScreen Screen;
    private JogadorHumano JogadorH;
    private Machine Machine;
    private Jogador Starter;
    private Jogador CurrentPlayer;
    private Jogador Winner;
    private TabuleiroInterface Tabuleiro;
    private final String[] dificultList = {"FACIL","MEDIO","DIFICIL"};
    private String dificultChoice;
    private Integer countPick = 0;


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
                Screen.error("Não foi possivel ler a entrada de dados");
            }
        }while (selection != 2);

    }

    private void startGame() {
        boolean config;
        do {
            config = this.settingGame();
        }while(!config);

        Screen.showCurrentPlayer(Starter.getName());
        this.Machine.getIE().setStarter(Starter);
        this.Machine.getIE().setDificultChoice(this.dificultChoice);
        Tabuleiro = new Tabuleiro();
        this.CurrentPlayer = Starter;
        Jogador nextPlayer;
        boolean runningGame = true;

        do{
            if(this.CurrentPlayer instanceof Machine){
                this.getMachinePick();
                nextPlayer = this.JogadorH;
            }else{
                this.getHumanPlayerPick();
                nextPlayer = this.Machine;
            }

            runningGame = this.analyzeStateTabuleiro();

            this.CurrentPlayer = nextPlayer;
        }while (runningGame);

        Screen.showTabuleiro(Tabuleiro);

        if(this.Winner instanceof JogadorHumano || this.Winner instanceof Machine){
            Screen.plotln("Jogador vencedor:"+this.Winner.getName());
        }else{
            Screen.plotln("Deu Velha!!!");
        }

        Screen.plotln("Fim do Jogo");

        this.restartGame();
    }

    private void restartGame() {
        this.countPick = 0;
        this.Tabuleiro.reset();
        this.dificultChoice = "";
        this.Starter = null;
        this.CurrentPlayer = null;
        this.Winner = null;
        this.JogadorH = null;
        this.Machine = null;

    }

    private void getMachinePick() {
        boolean flag = false;
        do {
            Random rand = new Random();
            String pick = ""+(rand.nextInt(9) + 1);
            if(this.Tabuleiro.validPick(pick)){
                flag = true;
                this.Tabuleiro.setPick(pick, this.Machine.getSymbol());
            }
        }while (!flag);
    }

    private void getHumanPlayerPick() {
        boolean flag = false;
        do {
            String pick = ""+Screen.getPlayerPick(this.Tabuleiro);
            if(this.Tabuleiro.validPick(pick)){
                flag = true;
                this.Tabuleiro.setPick(pick,this.JogadorH.getSymbol());
            }else{
                this.Screen.invalidOption();
            }
        }while (!flag);
    }

    private boolean analyzeStateTabuleiro(){
        this.countPick++;
        ArrayList<List<Integer>> goals = this.Machine.getIE().getKB().getGoals();

        for(List<Integer> goal: goals){
            if(this.Tabuleiro.symbolCompleteGoal(this.CurrentPlayer.getSymbol(),goal)){
                this.Winner = CurrentPlayer;
                return false;
            }
        }

        if(this.countPick > 8) {
            return false;
        }

        return true;
    }

    private boolean settingGame() {
        //pegando o nome do usuario
        String PlayerName = this.Screen.getNamePlayer();
        boolean StarterPlayer;
        boolean DificultLevel;
        if(PlayerName.equals("")){
            return false;
        }
        //criando os jogadores
        this.createPlayers(PlayerName);

        //Setando o nivel de dificuldade
        DificultLevel = this.getDificultLevel();

        //setando quem começa
        StarterPlayer = this.getInitPlayer();
        return StarterPlayer;
    }


    //Instancia os objetos dos Jogadores
    public void createPlayers(String PlayerName){
        this.JogadorH = createPlayer(PlayerName);
        this.Machine = createMachine("Viccenzinho Bolado");
    }
    // Cria um objeto JogadorH
    public JogadorHumano createPlayer(String playerName){
        JogadorHumano player = new JogadorHumano();
        player.setName(playerName);
        player.setSymbol("O");
        return player;
    }
    // Cria um objeto Maquina
    public Machine createMachine(String playerName){
        Machine machine = new Machine();
        try{
            InferenceEngine IE = new InferenceEngine();
            machine.setName(playerName);
            machine.setSymbol("X");
            machine.setIE(IE);

        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return machine;

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
                    Starter = JogadorH;
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

    private boolean getDificultLevel() {
        boolean flag = false;

        do{
            int opt = Screen.getDificultFlag();

            if( opt <= (this.dificultList.length -1)){
                this.dificultChoice = this.dificultList[opt];
                flag = true;
            }else{
                Screen.clean();
                Screen.invalidOption();
            }

        }while (!flag);

        return flag;
    }
}
