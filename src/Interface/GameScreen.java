package Interface;

import java.util.ArrayList;
import java.util.Collection;
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
        ArrayList<Collection<Integer>> coodinates = tabuleiro.getTabuleiro();
        for(Collection<Integer> linha : coodinates){

            for(Integer col : linha){

                if(col == linha.toArray()[0]){
                    plot("   "+col+"  ");
                }else if(col == linha.toArray()[1]){
                    plot("|  "+col+"  |");
                }else{
                    plotln("  " + col + "   ");
                }
                
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
}
