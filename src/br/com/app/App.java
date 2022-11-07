//Declaração do pacote
package br.com.app;

//Importação de classe interna
import br.com.model.KeyReader;

public class App {
    public static void main(String[] args) throws Exception {
        KeyReader key = new KeyReader();
        String chave = key.getChave();
        System.out.println(chave);
    }
}
