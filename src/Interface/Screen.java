package Interface;

import java.util.Scanner;

/**
 * Created by Bruno Louvem on 07/09/2015.
 * Classe que abstrai funcções básicas da tela como exibir e coletar entrada de dados;
 */
public abstract class Screen {

    protected Scanner Teclado;


    protected void plot(String text){
        System.out.print(text);
    }

    public void plotln(String text){
        System.out.println(text);
    }

    public void clean(){
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
