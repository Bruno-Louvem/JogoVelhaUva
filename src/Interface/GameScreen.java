package Interface;

import Model.Entity.TabuleiroInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Bruno Louvem on 10/09/2015.
 */
public class GameScreen extends Screen {

    public GameScreen() {

        System.out.println("Bem vindo ao Jogo da Velha UVA");

    }

    public String getNamePlayer(){
        System.out.println("Qual o seu nome?");
        Teclado = new Scanner(System.in);

        return Teclado.nextLine();
    }

    public int getInitPlayerFlag(){
        plotln("Deseja ser o primeiro a jogar?");
        plotln("0 - Nao        |       1 - Sim");
        Teclado = new Scanner(System.in);

        return Teclado.nextInt();
    }

    public void invalidOption(){
        plotln("Opcao Invalida");

    }

    public void showMenu() {
        plotln("[1] - Iniciar Jogo |      [2] - Sair");
    }

    public int getPlayerAction() {
        plot("Selecione uma opcao:");
        Teclado = new Scanner(System.in);
        return Teclado.nextInt();
    }

    public void goodBye(){
        plot("Ate mais, estamos aguardando sua volta...");
    }

    public void showTabuleiro(TabuleiroInterface tabuleiro) {
        ArrayList<List<String>> coodinates = tabuleiro.getTabuleiro();
        for(List<String> linha : coodinates){
            int i = 0;
            for(String col : linha){
                if(i == 2){
                    plotln("  " + col + "   ");
                }else{
                    plot("  "+col+"  |");
                }
                i++;
            }
            if(linha != coodinates.get(coodinates.size() - 1)) {
                plotln(" - - - - - - - - - ");
            }

        }
        plotln("");
        plotln("");
    }

    public void showCurrentPlayer(String name) {
        plotln("");
        plotln("Jogador da vez: "+name);
        plotln("");
    }

    public int getDificultFlag() {
        plotln("Escolha o nivel de dificuldade?");
        plotln("  0 - Facil");
        plotln("  1 - Medio");
        plotln("  2 - Dificil");
        plotln("");
        plot("Selecione uma opcao:");
        Teclado = new Scanner(System.in);

        return Teclado.nextInt();
    }

    public void error(String s) {
        plotln("");
        plotln(s);
        plotln("");
        plotln("");
        plotln("");
    }

    public int getPlayerPick(TabuleiroInterface tabuleiro) {
        this.showTabuleiro(tabuleiro);

        plot("Selecione uma posicao:");
        Teclado = new Scanner(System.in);

        return Teclado.nextInt();
    }

    public int getInitSymbolFlag() {
        plotln("");
        plotln("");
        plotln("Deseja jogar com qual simbolo?");
        plotln("");
        plotln("1 - (X)      |       2 - (O)");
        plotln("");
        plot("Selecione uma posicao:");
        Teclado = new Scanner(System.in);

        return Teclado.nextInt();
    }
}
