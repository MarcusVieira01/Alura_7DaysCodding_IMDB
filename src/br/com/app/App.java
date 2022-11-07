//Declaração do pacote
package br.com.app;

//Importação de classe interna
import br.com.model.KeyReader;

public class App {
    public static void main(String[] args) throws Exception {
        //Instanciação de objeto via construtor padrão KeyReader() e atribuição do valor de referência à variável key
        KeyReader key = new KeyReader();
        //Declaração de variável e atribuição do valor de retorno do método .getChave(), contido na classe KeyReader.
        String chave = key.getChave();
        //Exibição do valor da variável chave, para conferência
        System.out.println(chave);
    }
}
