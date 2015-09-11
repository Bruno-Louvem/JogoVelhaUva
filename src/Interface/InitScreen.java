package Interface;

import java.util.Scanner;

/**
 * Created by Bruno Louvem on 07/09/2015.
 */
public class InitScreen extends Screen{

    public InitScreen() {

        System.out.println("Bem vindo ao Jogo da Velha UVA");

    }

    public String getNamePlayer(){
        System.out.println("Qual o seu nome?");
        Teclado = new Scanner(System.in);

        return Teclado.nextLine();
    }

    public int getInitPlayerFlag(){
        plot("Deseja ser o primeiro a jogar?");
        plot("0 - Nao        |       1 - Sim");
        Teclado = new Scanner(System.in);

        return Teclado.nextInt();
    }

    public void invalidOption(){
        plot("Opcao Invalida");
    }
}
