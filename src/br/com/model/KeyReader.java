//Declaração do pacote
package br.com.model;

//Importação de classes externas
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Classe concreta com método de leitura de um arquivo contendo a chave de acesso ao IMDb API e atribui ao atributo chave. Atributo acessível via método getter
 * @author Marcus Vieira
 */
public class KeyReader {
    //Atributos privados
    String chave = "";

    /**
     * Método getter de recuperação do valor da chave de acessoao IMDb API
     * @return String contendo a chave de acesso ao IMDb API
     * @throws Exception
     */
    public String getChave() throws Exception {
        //Evocação de método que define uma chave advinda do arquivo k_imdb.txt
        leChave();
        //Retorna o valor do atributo chave
        return chave;
    }

    /**
     * Método que realiza o Stream do arquivo de texto k-imdb.txt e atribui ao atributo chave o valor lido
     * @throws Exception
     */
    private void leChave() throws Exception {
        //Declaração de variável que armazeno o nome do arquivo consultado
        String arquivo = "k_imdb.txt";

        //Instanciamento de objetos para realizar o fluxo de leitura dos dados do arquivo específicado via valor da variável arquivo, tranformação em uma sequencia de chars e posterior buffer dessa cadeia 
        InputStream fileInputStream = new FileInputStream(arquivo);
        Reader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //Atribuição do valor de retorno do método .readLine() do objeto de referência atribuída à variável bufferedReader ao atributo chave
        this.chave = bufferedReader.readLine();
        //Fechamento do acesso ao objeto bufferedReader
        bufferedReader.close();
    }
}
