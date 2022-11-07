//Declaração do pacote
package br.com.model;

//Importação de classes externas
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

//Declaração da classe
public class KeyReader {
    //Atributos privados
    String chave = "";

    //Declaração de método getter
    public String getChave() throws Exception {
        //Evocaçãod e método que define uma chave advinda do arquivo k_imdb.txt
        leChave();
        //Retorna o valor do atributo chave
        return chave;
    }

    //Declaração de método privado que fará a leitura da chave de acesso ao IMDb API advinda do arquivo k_imdb.txt 
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
